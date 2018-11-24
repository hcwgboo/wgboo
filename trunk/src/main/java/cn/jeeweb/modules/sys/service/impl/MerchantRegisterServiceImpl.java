package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.exception.ExceptionResultInfo;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.sys.constants.DictConstants;
import cn.jeeweb.modules.sys.entity.*;
import cn.jeeweb.modules.sys.mapper.MerchantRegisterMapper;
import cn.jeeweb.modules.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**   
 * @Title: 商家注册
 * @Description: 商家注册
 * @author cql
 * @date 2018-11-24 10:55:30
 * @version V1.0   
 *
 */
@Transactional
@Service("merchantRegisterService")
public class MerchantRegisterServiceImpl  extends CommonServiceImpl<MerchantRegisterMapper,MerchantRegister>
        implements  IMerchantRegisterService {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IOrganizationService organizationService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IUserOrganizationService userOrganizationService;
    @Autowired
    private IMerCapitalService merCapitalService;
    @Autowired
    PasswordService passwordService;

    @Override
    public void checkMerSuccess(String id) throws ExceptionResultInfo {
        if(StringUtils.isEmpty(id)) throw new ExceptionResultInfo("参数错误");
        MerchantRegister merchantRegister = baseMapper.selectById(id);
        if(merchantRegister == null) throw new ExceptionResultInfo("商户不存在");
        merchantRegister.setStatus(DictConstants.SJSH_DICT_VALUE_2);
        baseMapper.updateById(merchantRegister);

        User user = new User();
        user.setUsername(merchantRegister.getUsername());
        user.setRealname(merchantRegister.getRealname());
        user.setStatus(User.STATUS_NORMAL);
        user.setType(DictConstants.USER_TYPE_2);
        user.setSalt(merchantRegister.getSalt());
        user.setPassword(merchantRegister.getPassword());
        user.setCompanyname(merchantRegister.getCompanyname());
        user.setPhone(merchantRegister.getPhone());
        userService.insert(user);

        // 用户角色
        Role role = roleService.selectOne(new EntityWrapper<Role>().eq("code", "merchant"));
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(role.getId());
        userRoleService.insert(userRole);

        // 用户部门
        Organization root = organizationService.getRoot();
        UserOrganization userOrganization = new UserOrganization();
        userOrganization.setUserId(user.getId());
        userOrganization.setOrganizationId(root.getId());
        userOrganizationService.insert(userOrganization);

        // 资金初始化
        MerCapital merCapital = new MerCapital();
        merCapital.setBalanceMoney(new BigDecimal(0));
        merCapital.setFreezeMoney(new BigDecimal(0));
        merCapital.setMerchantId(user.getId());
        merCapitalService.insert(merCapital);

    }

    @Override
    public void checkMerFail(String id) throws ExceptionResultInfo {
        if(StringUtils.isEmpty(id)) throw new ExceptionResultInfo("参数错误");
        MerchantRegister merchantRegister = baseMapper.selectById(id);
        if(merchantRegister == null) throw new ExceptionResultInfo("商户不存在");
        merchantRegister.setStatus(DictConstants.SJSH_DICT_VALUE_3);

        baseMapper.updateById(merchantRegister);
    }
}

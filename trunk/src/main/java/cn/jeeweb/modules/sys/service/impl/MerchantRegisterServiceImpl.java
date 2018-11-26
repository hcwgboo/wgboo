package cn.jeeweb.modules.sys.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.exception.ExceptionResultInfo;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.utils.JedisUtils;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.excel.validate.ValidateHelper;
import cn.jeeweb.modules.sys.Constants;
import cn.jeeweb.modules.sys.constants.DictConstants;
import cn.jeeweb.modules.sys.entity.MerCapital;
import cn.jeeweb.modules.sys.entity.MerchantRegister;
import cn.jeeweb.modules.sys.entity.Organization;
import cn.jeeweb.modules.sys.entity.Role;
import cn.jeeweb.modules.sys.entity.User;
import cn.jeeweb.modules.sys.entity.UserOrganization;
import cn.jeeweb.modules.sys.entity.UserRole;
import cn.jeeweb.modules.sys.mapper.MerchantRegisterMapper;
import cn.jeeweb.modules.sys.service.IMerCapitalService;
import cn.jeeweb.modules.sys.service.IMerchantRegisterService;
import cn.jeeweb.modules.sys.service.IOrganizationService;
import cn.jeeweb.modules.sys.service.IRoleService;
import cn.jeeweb.modules.sys.service.IUserOrganizationService;
import cn.jeeweb.modules.sys.service.IUserRoleService;
import cn.jeeweb.modules.sys.service.IUserService;

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
public class MerchantRegisterServiceImpl extends CommonServiceImpl<MerchantRegisterMapper, MerchantRegister>
		implements IMerchantRegisterService {

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
		if (StringUtils.isEmpty(id))
			throw new ExceptionResultInfo("参数错误");
		MerchantRegister merchantRegister = baseMapper.selectById(id);
		if (merchantRegister == null)
			throw new ExceptionResultInfo("商户不存在");
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
		if (StringUtils.isEmpty(id))
			throw new ExceptionResultInfo("参数错误");
		MerchantRegister merchantRegister = baseMapper.selectById(id);
		if (merchantRegister == null)
			throw new ExceptionResultInfo("商户不存在");
		merchantRegister.setStatus(DictConstants.SJSH_DICT_VALUE_3);

		baseMapper.updateById(merchantRegister);
	}

	@Override
	public void officialRegister(MerchantRegister dto) throws Exception {

		// 1.非空校验
		registerParamCheck(dto);
		// 2.账号校验
		User temp = userService.selectOne(
				new EntityWrapper<User>().eq("username", dto.getUsername()).in("del_flag", Arrays.asList("0", "1")));

		if (temp != null)
			throw new ExceptionResultInfo("账号已经存在");//

		// 3.验证码校验
		String cashCode = JedisUtils.get(Constants.SMS_CODE + Constants.REGISTER + "_" + dto.getUsername());
		if (StringUtils.isEmpty(cashCode))
			throw new ExceptionResultInfo("验证码不正确");
		if (!cashCode.equals(dto.getCode()))
			throw new ExceptionResultInfo("验证码不正确");

		// 4.密码校验
		if (!dto.getPassword().equals(dto.getVpassword()))
			throw new ExceptionResultInfo("两次密码不一样");

		insert(dto);

	}

	private void registerParamCheck(MerchantRegister dto) throws ExceptionResultInfo {
		if (StringUtils.isEmpty(dto.getCompanyname()))
			throw new ExceptionResultInfo("公司名称为空");
		if (StringUtils.isEmpty(dto.getUsername()))
			throw new ExceptionResultInfo("联系方式为空");
		if (StringUtils.isEmpty(dto.getRealname()))
			throw new ExceptionResultInfo("用户名称为空");
		if (StringUtils.isEmpty(dto.getPassword()))
			throw new ExceptionResultInfo("密码为空");
		if (StringUtils.isEmpty(dto.getVpassword()))
			throw new ExceptionResultInfo("校验密码为空");
		if (StringUtils.isEmpty(dto.getCode()))
			throw new ExceptionResultInfo("验证码为空");
		if (!ValidateHelper.regCheckTelphone(dto.getUsername()))
			throw new ExceptionResultInfo("手机号码不合法");
	}
}

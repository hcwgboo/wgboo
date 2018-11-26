package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.exception.ExceptionResultInfo;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.sys.constants.DictConstants;
import cn.jeeweb.modules.sys.dto.UserRegisterDto;
import cn.jeeweb.modules.sys.entity.User;
import cn.jeeweb.modules.sys.entity.UserOrganization;
import cn.jeeweb.modules.sys.entity.UserRole;
import cn.jeeweb.modules.sys.mapper.UserMapper;
import cn.jeeweb.modules.sys.service.*;
import cn.jeeweb.modules.sys.utils.DeepFieldCopy;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("userService")
public class UserServiceImpl extends CommonServiceImpl<UserMapper, User> implements IUserService {
	@Autowired
	PasswordService passwordService;
	@Autowired
	private IUserOrganizationService userOrganizationService;
	@Autowired
	private IUserRoleService userRoleService;

	@Override
	public void changePassword(String userid, String newPassword) {
		User user = selectById(userid);
		if (user != null) {
			user.setPassword(newPassword);
			passwordService.encryptPassword(user);
		}
		insertOrUpdate(user);
	}

	@Override
	public User findByUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			return null;
		}
		return selectOne(new EntityWrapper<User>(User.class).eq("username", username));
	}

	@Override
	public User findByEmail(String email) {
		if (StringUtils.isEmpty(email)) {
			return null;
		}
		return selectOne(new EntityWrapper<User>(User.class).eq("email", email));
	}

	@Override
	public User findByPhone(String phone) {
		if (StringUtils.isEmpty(phone)) {
			return null;
		}
		return selectOne(new EntityWrapper<User>(User.class).eq("phone", phone));
	}

	@Override
	public boolean insert(User user) {
		passwordService.encryptPassword(user);
		return super.insert(user);
	}

	@Override
	public boolean deleteById(Serializable id) {
		// 删除角色关联
		userRoleService.delete(new EntityWrapper<UserRole>(UserRole.class).eq("userId", id));
		// 删除部门关联
		userOrganizationService.delete(new EntityWrapper<UserOrganization>(UserOrganization.class).eq("userId", id));
		return super.deleteById(id);
	}

	@Override
	public boolean deleteBatchIds(List<? extends Serializable> idList) {
		for (Object id : idList) {
			this.deleteById((Serializable) id);
		}
		return true;
	}

	@Override
	public Page<User> selectPage(Page<User> page, Wrapper<User> wrapper) {
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectUserList(page, wrapper));
		return page;
	}


	@Override
	public void userRegister(UserRegisterDto dto) throws ExceptionResultInfo {

		checkRegisterParam(dto);

		User user = DeepFieldCopy.transform(dto, User.class);
		user.setPassword(dto.getPassword1());
		passwordService.encryptPassword(user);
		user.setPhone(user.getUsername());
		user.setType(DictConstants.USER_TYPE_2);
		baseMapper.insert(user);

	}

	private void checkRegisterParam(UserRegisterDto dto) throws ExceptionResultInfo{
		if(StringUtils.isEmpty(dto.getCompanyname())) throw new ExceptionResultInfo("公司名称不能为空");
		if(StringUtils.isEmpty(dto.getRealname())) throw new ExceptionResultInfo("用户姓名不能为空");
		if(StringUtils.isEmpty(dto.getUsername())) throw new ExceptionResultInfo("联系方式不能为空");
		if(StringUtils.isEmpty(dto.getPassword1())) throw new ExceptionResultInfo("密码不能为空");
		if(StringUtils.isEmpty(dto.getPassword2())) throw new ExceptionResultInfo("验证密码不能为空");

		if(!dto.getPassword1().equals(dto.getPassword2()))throw new ExceptionResultInfo("两次密码不相同");

		User uu = new User();
		List<User> list = baseMapper.selectList(new EntityWrapper<User>()
				.eq("companyname", dto.getCompanyname()).eq("del_flag", 0));
		if(list != null && list.size() > 0) throw new ExceptionResultInfo("公司名称已经存在");

		list = baseMapper.selectList(new EntityWrapper<User>().eq("username", dto.getUsername())
				.eq("del_flag", 0));
		if(list != null && list.size() > 0)throw new ExceptionResultInfo("用户名已经存在");
	}

	@Override
	public void userLockOrNot(String id, String status) throws ExceptionResultInfo {
		if(StringUtils.isEmpty(id)) throw new ExceptionResultInfo("参数错误");
		User user = baseMapper.selectById(id);
		if(user == null) throw new ExceptionResultInfo("参数错误");
		user.setStatus(status);

		baseMapper.updateById(user);
	}

}

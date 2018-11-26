package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.TreeCommonServiceImpl;
import cn.jeeweb.modules.sys.entity.Menu;
import cn.jeeweb.modules.sys.mapper.MenuMapper;
import cn.jeeweb.modules.sys.service.IMenuService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("menuService")
public class MenuServiceImpl extends TreeCommonServiceImpl<MenuMapper, Menu, String> implements IMenuService {

	@Override
	public List<Menu> findMenuByUserId(String userId) {
		return baseMapper.findMenuByUserId(userId);
	}

	@Override
	public List<Menu> findMenuByRoleId(String roleId) {
		return baseMapper.findMenuByRoleId(roleId);
	}

	@Override
	public List<String> getParentMenuId(List<String> list) {
		return baseMapper.getParentMenuId(list);
	}

	@Override
	public List<Menu> getMenuIdListByPId(String id) {
		return baseMapper.getMenuIdListByPId(id);
	}

}

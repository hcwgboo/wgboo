package cn.jeeweb.modules.sys.mapper;

import java.util.List;
import cn.jeeweb.core.common.mapper.BaseTreeMapper;
import cn.jeeweb.modules.sys.entity.Menu;

public interface MenuMapper extends BaseTreeMapper<Menu> {

	/**
	 * 
	 * @title: findMenuByUserId
	 * @description: 通过用户查找菜单
	 * @param userId
	 * @return
	 * @return: List<Menu>
	 */
	List<Menu> findMenuByUserId(String userId);
	
	/**
	 * 
	 * @title: findMenuByRoleId
	 * @description: 通过角色查找菜单
	 * @param userId
	 * @return
	 * @return: List<Menu>
	 */
	List<Menu> findMenuByRoleId(String roleId);

	/**
	 * 获取父菜单的id
	 * @param list
	 * @return
	 */
	List<String> getParentMenuId(List<String> list);
	/**
	 * 通过父id获取父子id
	 * @return
	 */
	List<Menu> getMenuIdListByPId(String id);
}
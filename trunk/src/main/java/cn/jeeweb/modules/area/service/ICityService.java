package cn.jeeweb.modules.area.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.modules.area.entity.City;

import java.util.List;

/**   
 * @Title: 城市信息
 * @Description: 城市信息
 * @author zhangyouwei
 * @date 2018-02-02 09:33:55
 * @version V1.0   
 *
 */
public interface ICityService extends ICommonService<City> {

	/**
	 * 
	 * @Description:获取所有的城市信息
	 * @date:2018年2月5日 上午9:49:46
	 * @param:
	 * @return
	 * @throws
	 *
	 */
	List<City> selectCityList();
	
	Boolean add(City city);
	
	Boolean addOrUpdate(City city);


}


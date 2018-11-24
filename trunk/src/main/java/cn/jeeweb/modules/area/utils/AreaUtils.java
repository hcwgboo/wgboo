package cn.jeeweb.modules.area.utils;

import cn.jeeweb.core.utils.SpringContextHolder;
import cn.jeeweb.modules.area.entity.Area;
import cn.jeeweb.modules.area.entity.City;
import cn.jeeweb.modules.area.service.IAreaService;
import cn.jeeweb.modules.area.service.ICityService;

import java.util.List;

/**
 * @author zhangyouwei
 * @email zhangyouwei1988@sina.cn
 * @date 2018年2月5日 上午9:44:45
 * @Description：区域相关工具类
 *
 */
public class AreaUtils {
	private static ICityService cityService = SpringContextHolder.getBean(ICityService.class);
	private static IAreaService areaService =  SpringContextHolder.getBean(IAreaService.class);
	
	/**
	 * 
	 * @Description:获取所有的城市
	 * @date:2018年2月5日 上午11:23:01
	 * @param:
	 * @return
	 * @throws
	 *
	 */
	public static List<City> getCityList() {
		
		return cityService.selectCityList();
	}

	/**
	 * 
	 * @Description:根据城市Id获取区域列表
	 * @date:2018年2月5日 上午11:23:18
	 * @param:
	 * @return
	 * @throws
	 *
	 */
	public static List<Area> getAreaList(String cityId) {
		return areaService.selectAreaListByCityId(cityId);
	}

}

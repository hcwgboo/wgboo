package cn.jeeweb.modules.area.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.modules.area.entity.Area;

import java.util.List;

/**   
 * @Title: 区域信息
 * @Description: 区域信息
 * @author zhangyouwei
 * @date 2018-02-02 09:33:55
 * @version V1.0   
 *
 */
public interface IAreaService extends ICommonService<Area> {

	/**
	 * 
	 * @Description:根据城市id获取区域列表
	 * @date:2018年2月5日 上午11:24:59
	 * @param:
	 * @return
	 * @throws
	 *
	 */
	List<Area> selectAreaListByCityId(String cityId);

}


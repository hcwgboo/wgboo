package cn.jeeweb.modules.sys.service;


import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.modules.sys.entity.Advertise;

/**
 * @Title: 广告
 * @Description: 广告
 * @author java
 * @date 2018-11-12 19:58:04
 * @version V1.0   
 *
 */
public interface IAdvertiseService extends ICommonService<Advertise> {

	/**
	 * 新增广告
	 * @param advertise
	 */
	void insertAdvertise(Advertise advertise);
	
	/**
	 * 修改广告
	 * @param advertise
	 */
	void updateAdvertise(Advertise advertise);
	
	/**
	 * 提交审核
	 * @param id
	 */
	void submitCheckAdv(String id);
	
	/**
	 * 审核通过
	 * @param id
	 */
	void checkSuccessAdv(String id);
	
	/**
	 * 审核不通过
	 * @param id
	 */
	void checkFailAdv(String id);
}


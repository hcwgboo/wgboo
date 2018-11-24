package cn.jeeweb.core.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.jeeweb.core.utils.CalendarUtil;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.sys.constants.DictConstants;

/**
 * 
 * @author zhangyouwei
 * @email zhangyouwei1988@sina.cn
 * @date 2018年8月9日 下午1:46:59
 * @Description：报表帮助类
 *
 */
public class ReportUtils {

	/**
	 * 
	 * @Description:跟据类型获取开始和结束时间 @date:2018年5月23日
	 * 下午6:00:49 @param: @return @throws
	 *
	 */
	public static Map<String, Object> getTimeMapByType(String type) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(type) || DictConstants.bbcxkjfs_dict_value_1.equals(type)) {
			//昨天
			Date  yesterday =CalendarUtil.getYesterday(new Date());
			map.put("reportDateStart", CalendarUtil.getDayStart(yesterday));
			map.put("reportDateEnd", CalendarUtil.getDayEnd(yesterday));
		}else if (DictConstants.bbcxkjfs_dict_value_2.equals(type)) {//上周
			//上周
			map.put("reportDateStart",CalendarUtil.getBeforWeekMonday(new Date()));
			map.put("reportDateEnd",CalendarUtil.getBeforWeekSunday(new Date()));
		}else if (DictConstants.bbcxkjfs_dict_value_3.equals(type)) {//上月
			//上月
			map.put("reportDateStart",CalendarUtil.getBeforMonthFistDayByDate(new Date()));
			map.put("reportDateEnd",CalendarUtil.getBeforMonthLastDayByDate(new Date()));
		}
		return map;
	}
	/**
	 * 
	 * @Description 获取开始和结束时间 @date:2018年5月23日 下午5:47:54 @param: @return @throws
	 *
	 */
	public static Map<String, Object> getTimeMapByReportDate(String reportDate) {
		String[] reportDates = reportDate.split(",");
		if (reportDates == null || reportDates.length != 2) {
			throw new RuntimeException("报表日期选择不正确！");
		}
		Date reportDateStart = CalendarUtil.parseDate(reportDates[0], CalendarUtil.DEFAULT_FORMAT);
		Date reportDateEnd = CalendarUtil.parseDate(reportDates[1], CalendarUtil.DEFAULT_FORMAT);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportDateStart", CalendarUtil.getDayStart(reportDateStart));
		map.put("reportDateEnd", CalendarUtil.getDayEnd(reportDateEnd));
		return map;
	}
}

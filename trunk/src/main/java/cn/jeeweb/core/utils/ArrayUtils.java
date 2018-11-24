package cn.jeeweb.core.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jeeweb.modules.sys.constants.DictConstants;

public class ArrayUtils extends org.apache.commons.lang3.ArrayUtils {
	public static List<String> split(final String str, final String separatorChar) {
		List<String> strList = new ArrayList<String>();
		String[] strs = StringUtils.split(str, separatorChar);
		for (String string : strs) {
			strList.add(string);
		}
		return strList;
	}
	
	
	public static Map<String,Object> MapFormat(Map<String,Object> map){
		Map<String,Object> timeMap=null;
		if (!StringUtils.isEmpty(map.get("reportDate").toString())) {
			// 开始时间和结束时间有值的时候
			timeMap = ReportUtils.getTimeMapByReportDate(map.get("reportDate").toString());
		} else {
			// 开始和结束时间没有值当type为空的时候默认查询今天
			if (!StringUtils.isStringAvaliable(map.get("type").toString()) || DictConstants.bbcxkjfs_dict_value_0.equals(map.get("type").toString())) {
				timeMap = new HashMap<String, Object>();
				Date reportDate = CalendarUtil.getDayEnd(new Date());
				// 统计开始日期
				timeMap.put("reportDateStart",CalendarUtil.getDayStart(reportDate));
				// 统计结束时间
				timeMap.put("reportDateEnd", CalendarUtil.getDayEnd(reportDate));
			} else {
				timeMap = ReportUtils.getTimeMapByType(map.get("type").toString());
			}
		}
		return timeMap;
	}
}

package cn.jeeweb.modules.excel.util;

import java.util.Date;
import java.util.Map;

import cn.jeeweb.core.utils.DateUtils;
import cn.jeeweb.core.utils.StringUtils;

public class ExportTips {

	private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	
	public static void setReportDate(Map<String, Object> map){
		String reportDate = "";
		String type = (String)map.get("type");
		if (!StringUtils.isEmpty((String)map.get("reportDate"))) {
			String str = (String)map.get("reportDate");
			String[] split = str.split(",");
			reportDate = split[0].replace("-", ".")+"-"+split[1].replace("-", ".");
		}else if(!StringUtils.isEmpty(type)){
			switch (type) {// type 0今天 1昨天 2上周 3上月
			case "0":
				reportDate = DateUtils.formatDate(new Date(), "yyyy.MM.dd");
				break;
			case "1":
				reportDate = DateUtils.getYesterdayDate(new Date(), "yyyy.MM.dd");
				break;
			case "2":
				reportDate = DateUtils.getLastWeekMon(new Date(), "yyyy.MM.dd")+"-"+DateUtils.getLastWeekSun(new Date(), "yyyy.MM.dd");
				break;
			case "3":
				reportDate = DateUtils.getLastMonthFirst(new Date(), "yyyy.MM.dd")+"-"+DateUtils.getLastMonthLast(new Date(), "yyyy.MM.dd");
				break;
			}
		}else{
			reportDate = DateUtils.formatDate(new Date(), "yyyy.MM.dd");
		}
		threadLocal.set("("+reportDate+")");
	}
	
	public static String getReportDate(){
		if (StringUtils.isEmpty(threadLocal.get())) {
			threadLocal.set("");
		}
		return threadLocal.get();
	}
}

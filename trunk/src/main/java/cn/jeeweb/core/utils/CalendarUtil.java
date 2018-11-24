package cn.jeeweb.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description:时间处理工具类
 * @author zhangyouwei1988@sina.cn
 * @date: 2016-5-4 下午5:54:56
 */
public class CalendarUtil {

	public static final String DEFAULT_FORMAT = "yyyy-MM-dd";

	/***
	 * 默认格式的现在日期
	 * 
	 * @return
	 */
	public static Date now() {
		return new Date();
	}

	/***
	 * 返回指定格式的现在日期
	 * 
	 * @return
	 */
	public static Date now(String format) {
		return formatDate(format, now());
	}

	/***
	 * yyyy-MM-dd 格式的现在日期
	 * 
	 * @return
	 */
	public static Date today() {
		return formatDate(DEFAULT_FORMAT, now());
	}

	/***
	 * yyyy-MM-dd 格式的日期
	 * 
	 * @return
	 */
	public static Date getTomorrow() {
		Calendar c = convertToCalendar(today());
		c.add(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	/***
	 * 按照指定的格式解析日期字符串
	 * 
	 * @return
	 */
	public static Date parseDate(String dateString, String format) {
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(format);
		formater.setLenient(false);
		try {
			return formater.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	/***
	 * 按照指定的格式格式化日期
	 * 
	 * @return
	 */
	private static Date formatDate(String format, Date date) {
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(format);
		formater.setLenient(false);
		try {
			return formater.parse(formater.format(date));
		} catch (ParseException e) {
			return null;
		}
	}

	/***
	 * 返回yyyy-MM-dd 格式的日期字符串
	 * 
	 * @return
	 */
	public static String format(Date date) {
		return format(DEFAULT_FORMAT, date);
	}

	/***
	 * 返回指定格式的日期字符串
	 * 
	 * @return
	 */
	public static String format(String format, Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(format);
		return formater.format(date);
	}

	/***
	 * 返回今天是星期几 例如：星期一
	 * 
	 * @return
	 */
	public static String getWeekDay() {
		return format("EEEE", today());
	}

	/***
	 * 返回指定的日期是星期几 例如：星期一
	 * 
	 * @return
	 */
	public static String getWeekDay(Date date) {
		return format("EEEE", date);
	}

	/***
	 * 返回现在日期的年份
	 * 
	 * @return
	 */
	public static int getNowYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/***
	 * 返回现在日期的月份
	 * 
	 * @return
	 */
	public static int getNowMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/***
	 * 返回现在日期的天
	 * 
	 * @return
	 */
	public static int getNowDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	public static int getLastYear() {
		return Calendar.getInstance().get(Calendar.YEAR) - 1;
	}

	public static int getLastMonth() {
		Calendar temp = Calendar.getInstance();
		temp.add(Calendar.MONTH, -1);
		return temp.get(Calendar.MONTH) + 1;
	}

	public static int getLastMonthYearValue() {
		Calendar temp = Calendar.getInstance();
		temp.add(Calendar.MONTH, -1);
		return temp.get(Calendar.YEAR);
	}

	public static Date getMonthStart(int year, int month) {
		if (0 == year) {
			return null;
		}
		return parseDate(String.valueOf(year) + month, "yyyyM");
	}

	public static Date getNextMonthStart(int year, int month) {
		if (0 == year) {
			return null;
		}
		Calendar temp = getMonthEndCalendar(year, month);
		temp.add(Calendar.DAY_OF_MONTH, 1);
		return temp.getTime();
	}

	public static Date getLastMonthEnd(int year, int month) {
		Calendar temp = getMonthEndCalendar(year, month);
		temp.add(Calendar.MONTH, -1);
		return temp.getTime();
	}

	public static Date getLastMonthStart(int year, int month) {
		Calendar result = Calendar.getInstance();
		result.setTime(getMonthStart(year, month));
		result.add(Calendar.MONTH, -1);
		return result.getTime();
	}

	public static Date getMonthEnd(int year, int month) {
		return getMonthEndCalendar(year, month).getTime();
	}

	private static Calendar getMonthEndCalendar(int year, int month) {
		Calendar result = Calendar.getInstance();
		result.setTime(getMonthStart(year, month));
		result.set(Calendar.DAY_OF_MONTH,
				result.getActualMaximum(Calendar.DAY_OF_MONTH));
		return result;
	}

	/***
	 * 返回指定日期的年份
	 * 
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar c = convertToCalendar(date);
		return c.get(Calendar.YEAR);
	}

	/***
	 * 返回指定日期的月份
	 * 
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar c = convertToCalendar(date);
		return c == null ? 1 : c.get(Calendar.MONTH) + 1;
	}

	/***
	 * 返回指定日期的天
	 * 
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar c = convertToCalendar(date);
		return c == null ? 1 : c.get(Calendar.DAY_OF_MONTH);
	}

	public static Calendar convertToCalendar(Date date) {
		if (date == null) {
			return null;
		}
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		return result;
	}

	/** 时间转换将指定日期转换为long型数据 */
	public static Long transferStringDateToLong(String formatDate,
			int addTimeValue) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
			Calendar nowTime = Calendar.getInstance();
			nowTime.add(Calendar.HOUR, addTimeValue);// 当前时间增加指定小时数
			Date dt = sdf.parse(sdf.format(nowTime.getTime()));
			return dt.getTime();
		} catch (ParseException e) {
			return null;
		}
	}

	/***
	 * 根据系统时间取得上个月的月末
	 * 
	 * @return
	 */
	public static Date getBeforeMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getMaximum(Calendar.DATE));
		calendar.add(Calendar.MONTH, -1);
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return formatDate(dateFormat, calendar.getTime());
	}

	/***
	 * 根据系统时间取得下个月的月初
	 * 
	 * @return
	 */
	public static Date getNextMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getMinimum(Calendar.DATE));
		calendar.add(Calendar.MONTH, 1);
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return formatDate(dateFormat, calendar.getTime());
	}

	/***
	 * 根据当前时间获得本月月初
	 * 
	 * @param args
	 */
	public static Date getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getMinimum(Calendar.DATE));
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return formatDate(dateFormat, calendar.getTime());
	}

	/***
	 * 根据当前时间获得本月月末
	 * 
	 * @param args
	 */
	public static Date getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getMaximum(Calendar.DATE));
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return formatDate(dateFormat, calendar.getTime());
	}

	/***
	 * 根据系统时间获取当前周的周一
	 */
	public static Date getWeekMonday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String dateFormat = "yyyy-MM-dd";
		return formatDate(dateFormat, calendar.getTime());
	}

	/***
	 * 根据系统时间获取下周的周一
	 */
	public static Date getNextWeekMonday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String dateFormat = "yyyy-MM-dd";
		return formatDate(dateFormat, calendar.getTime());
	}

	/**
	 * 根据当前时间获取当前月的最后一天精确到23:59:59
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeforeMonthLastDayByTime(int year, int month) {
		Date date = getDateByYearAndMonth(year, month);
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 58);
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return formatDate(dateFormat, calendar.getTime());
	}

	/***
	 * 根据传入时间获取上月最后一天，精确到时分秒
	 */
	public static Date getBeforMonthLastDayByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		int MaxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), MaxDay, 23,
				59, 58);
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return formatDate(dateFormat, cal.getTime());
	}
	
	/***
	 * 根据传入时间获取上月第一天，精确到时分秒
	 */
	public static Date getBeforMonthFistDayByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		int minDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), minDay, 0,
				0, 0);
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return formatDate(dateFormat, cal.getTime());
	}

	/**
	 * 
	 * @Description:获取传入时间的上周的周一精确到时分秒
	 * @date:2018年5月23日 下午7:40:44
	 * @param:
	 * @return
	 * @throws
	 *
	 */
	public static Date getBeforWeekMonday(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.add(Calendar.DAY_OF_WEEK, -7);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
        if (1 == dayWeek) {  
           cal.add(Calendar.DAY_OF_MONTH, -1);  
        }  
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
        cal.setFirstDayOfWeek(Calendar.MONDAY);  
        // 获得当前日期是一个星期的第几天  
        int day = cal.get(Calendar.DAY_OF_WEEK);  
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);  
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();  
   }
	/**
	 * 
	 * @Description:获取传入时间的上周的周日精确到时分秒
	 * @date:2018年5月23日 下午7:42:28
	 * @param:
	 * @return
	 * @throws
	 *
	 */
	public static Date getBeforWeekSunday(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.add(Calendar.DAY_OF_WEEK, -7);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
        if (1 == dayWeek) {  
           cal.add(Calendar.DAY_OF_MONTH, -1);  
        }  
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
        cal.setFirstDayOfWeek(Calendar.MONDAY);  
        // 获得当前日期是一个星期的第几天  
        int day = cal.get(Calendar.DAY_OF_WEEK);  
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        cal.add(Calendar.DATE, 6);  
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 58);
        return cal.getTime();  
   }

	/**
	 * 根据当前时间获取当前月的第一天精确到00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeforeMonthFirstDayByTime(int year, int month) {
		Date date = getDateByYearAndMonth(year, month);
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return formatDate(dateFormat, calendar.getTime());
	}

	/**
	 * 根据当前时间获取当前月的最后一天向前推7天精确到00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeforeMonthWeekDayByTime(int year, int month) {
		Date date = getDateByYearAndMonth(year, month);
		if (date == null) {
			return null;
		}
		Date lastDay = getBeforeMonthLastDayByTime(year, month);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(lastDay);
		calendar.add(Calendar.DATE, -6);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return formatDate(dateFormat, calendar.getTime());
	}

	/**
	 * 根据当前时间获取当前月往前推2个自然月，到00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeforeQuarterDayByTime(int year, int month) {
		Date date = getDateByYearAndMonth(year, month);
		if (date == null) {
			return null;
		}
		Date lastDay = getBeforeMonthLastDayByTime(year, month);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(lastDay);
		calendar.add(Calendar.MONTH, -2);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return formatDate(dateFormat, calendar.getTime());
	}

	public static Date getDateByYearAndMonth(int year, int month) {
		if (year <= 0 || month <= 0) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.add(Calendar.MONTH, -1);
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return formatDate(dateFormat, calendar.getTime());
	}

	/***
	 * 根据系统时间获取上个月所在年份
	 * 
	 * @param args
	 */
	public static int getLastYearByMonth() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -15);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String yearStr = sdf.format(c.getTime());
		return Integer.parseInt(yearStr);

	}

	/***
	 * 根据系统时间获取上个月
	 * 
	 * @param args
	 */
	public static int getLastMonthByMonth() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -15);
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		String monthStr = sdf.format(c.getTime());
		return Integer.parseInt(monthStr);

	}

	/***
	 * 根据传入时间获取本月最后一天 精确到时分秒
	 */
	public static Date getNextDate(int year, int month) {
		Date date = getDateByYearAndMonth(year, month);
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getMinimum(Calendar.DATE));
		calendar.add(Calendar.MONTH, 1);
		String dateFormat = "yyyy-MM";
		return formatDate(dateFormat, calendar.getTime());
	}

	/**
	 * 
	 * @Description:获取传入年份的第一天
	 * @param
	 * @return
	 */
	public static Date getYearFirstDay(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String dateFormat = "yyyy-MM-dd";
		return formatDate(dateFormat, calendar.getTime());

	}

	/**
	 * 
	 * @Description:获取传入年份的最后一天
	 * @param
	 * @return
	 */
	public static Date getYearLastDay(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		String dateFormat = "yyyy-MM-dd";
		return formatDate(dateFormat, calendar.getTime());
	}

	/**
	 * 
	 * @Description:获取下一天
	 * @param
	 * @return
	 */
	public static Date getNextDay(Date date) {
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		result.add(Calendar.DAY_OF_MONTH, 1);
		String dateFormat = "yyyy-MM-dd";
		return formatDate(dateFormat, result.getTime());
	}

	/**
	 * 
	 * @Description:获取前一天
	 * @param
	 * @return
	 */
	public static Date getYesterday(Date date) {
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		result.add(Calendar.DAY_OF_MONTH, -1);
		String dateFormat = "yyyy-MM-dd";
		return formatDate(dateFormat, result.getTime());
	}
	
	/**
	 * 
	 * @Description:获取传入时间天的结束精确到时分秒23:59:59
	 * @param
	 * @return
	 */
	public static Date getDayEnd(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 58);
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return formatDate(dateFormat, calendar.getTime());
	}
	
	/**
	 * 根据当前时间获取当前天的开始时间精确到时分秒00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayStart(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		return formatDate(dateFormat, calendar.getTime());
	}

	/**
	 * 
	 * @Description:根据传入的开始时间和结束时间获取该时间段内所有的日期，不包含头尾
	 * @param
	 * @return
	 */
	public static List<Date> getAllDay(Date startDate, Date endDate) {

		if (startDate == null || endDate == null || endDate.after(new Date())
				|| startDate.after(endDate)) {
			return null;
		}

		List<Date> result = new ArrayList<Date>();
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(startDate);
		tempStart.add(Calendar.DAY_OF_YEAR, 1);
		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(endDate);
		while (tempStart.before(tempEnd)) {
			result.add(tempStart.getTime());
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}
		return result;
	}

	public static void main(String[] args) {
		//System.out.println(format("yyyy-MM-dd HH:mm:ss",getBeforWeekMonday(new Date())));
		//System.out.println(getBeforWeekMonday(parseDate("2018-05-20", "yyyy-MM-dd")));
		System.out.println(format("yyyy-MM-dd HH:mm:ss",getBeforWeekSunday(parseDate("2018-05-20", "yyyy-MM-dd"))));
		System.out.println(format("yyyy-MM-dd HH:mm:ss",getBeforWeekMonday(parseDate("2018-05-20", "yyyy-MM-dd"))));
		
	}
}

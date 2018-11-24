package cn.jeeweb.modules.excel;

import java.math.BigDecimal;
import java.util.Date;

import cn.jeeweb.core.utils.CalendarUtil;
import cn.jeeweb.core.utils.StringUtils;


/**
 * 
 * @Description:类型转换帮助类
 * @author zhangyouwei1988@sina.cn
 * @date: 2016-5-4 下午5:48:35
 */
public class DataConvertionHelper {

	public static Date convertToDate(String dateText) {
		if (!StringUtils.isStringAvaliable(dateText)) {
			return null;
		}
		Date result = null;
		result = CalendarUtil.parseDate(dateText, "yyyy/MM/dd");
		if (result == null) {
			result = CalendarUtil.parseDate(dateText, "yyyy-MM-dd");
		}
		if (result == null) {
			result = CalendarUtil.parseDate(dateText, "MM/dd/yy");
		}
		if (result == null) {
			result = CalendarUtil.parseDate(dateText, "yyyyMMdd");
		}

		if (result == null) {
			try {
				long date = Date.parse(dateText);

				result = new Date(date);
			} catch (Exception e) {
				result = null;
			}
		}

		return result;
	}

	public static boolean convertToBoolean(String booleanText) {
		if (!StringUtils.isStringAvaliable(booleanText)) {
			return Boolean.FALSE;
		}
		return "是".equals(booleanText.trim()) ? true : false;
	}

	public static Double convertToDouble(String doubleText) {
		if (StringUtils.isStringAvaliable(doubleText)) {
			return Double.valueOf(doubleText);
		} else {
			return null;
		}
	}

	public static double convertToDoubleValue(String doubleText) {
		Double result = convertToDouble(doubleText);
		return result == null ? 0 : result.doubleValue();
	}

	public static BigDecimal convertToBigDecima(String bigText) {
		if (StringUtils.isStringAvaliable(bigText)) {
			return new BigDecimal(bigText);
		} else {
			return null;
		}
	}

	public static Long convertToLong(String longText) {
		if (StringUtils.isStringAvaliable(longText)) {
			return Long.valueOf(longText);
		} else {
			return null;
		}
	}

	public static Integer convertToInteger(String text) {
		if (StringUtils.isStringAvaliable(text)) {
			return Integer.valueOf(text);
		} else {
			return null;
		}
	}

	public static BigDecimal convertToBigdecimal(String text) throws Exception {
		if (StringUtils.isStringAvaliable(text)) {
			if (text.indexOf(",") != -1) {
				text = text.replace(",", "");
			}
			/*if(!StringUtils.isNumeric(text)){
			    return null;
			}*/
			BigDecimal bigDecimal = new BigDecimal(text);
			return bigDecimal;
		} else {
			return null;
		}
	}
}

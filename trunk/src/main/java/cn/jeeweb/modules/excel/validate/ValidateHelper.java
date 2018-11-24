package cn.jeeweb.modules.excel.validate;

import java.util.Date;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.jeeweb.core.utils.CalendarUtil;
import cn.jeeweb.core.utils.NumbericUtil;
import cn.jeeweb.core.utils.StringUtils;

public class ValidateHelper {

	/**
	 * 功能：校验字符串 并返回(如果为空或者null)
	 */
	public static boolean emptyString(String text) {
		return !StringUtils.isStringAvaliable(text);
	}

	public static boolean nullObj(Object obj) {
		return null == obj;
	}

	public static boolean nullBoolean(Boolean flag) {
		return null == flag;
	}

	public static boolean containValue(String value, String[] values) {
		boolean flag = false;
		for (String vl : values) {
			if (vl.equals(value)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static String getShortClassName(Class clazz) {
		String[] names = clazz.getName().split("\\.");
		String attr = names[names.length - 1];
		attr = attr.substring(0, 1).toLowerCase() + attr.substring(1);
		return attr;
	}

	public static String[] cellValueTrim(String[] cellValues) {
		for (int i = 0; i < cellValues.length; i++) {
			cellValues[i] = cellValues[i].trim();
		}
		return cellValues;
	}

	public static boolean illegalIMSI(String str) {
		if (!StringUtils.isStringAvaliable(str)) {
			return true;
		}
		if (str.length() != 15) {
			return true;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		return !pattern.matcher(str).matches();
	}

	/**
	 * 验证手机账号的imsi号码必须是纯数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean illegalMobileUserIMSI(String str) {
		if (!StringUtils.isStringAvaliable(str)) {
			return true;
		}
		if (str.length() > 30) {
			return true;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		return !pattern.matcher(str).matches();
	}

	/**
	 * 验证用户名是否是@ sg结尾的
	 * 
	 * @param userName
	 * @return
	 */
	public static boolean illegalMobileUserName(String userName) {
		String regex = ("^([a-zA-Z0-9_\\-\\.]+)@([a-z]{2,4})sg$");
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(userName);
		return !m.find();
	}

	/**
	 * 功能：判断一个字符串是否包含特殊字符 汉字a-z A-Z 0-9
	 */
	public static boolean isConSpeCharacters(String string) {
		if (string.replaceAll(
				"[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*）*（*\\(*\\)*", "")
				.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 功能：判断一个字符串是否是一个或多个以','分割的ip地址
	 */
	public static boolean isIP(String ip) {
		String regex = "^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3},?)+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(ip);
		return m.find();
	}

	public static boolean illegalStringLength(int min, int max, String text) {
		if (emptyString(text)) {
			if (min > 0) {
				return true;
			} else {
				return false;
			}
		}
		// 先去掉输入的字符串中的换行和回车添加的符号
		String[] remove = { "\n" };
		for (int i = 0; i < remove.length; i++) {
			text = text.replaceAll(remove[i], "");
		}
		return text.length() < min || text.length() > max;
	}

	public static boolean illegalMobilePhone(String text) {
		if (text.length() < 11 || text.length() > 11) {
			return true;
		}
		return !text.matches("^1[0-9]\\d{9}$");
	}

	public static boolean illegalEmail(String mail) {
		String regex = ("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mail);
		return !m.find();
	}

	public static boolean illegalWebSite(String mail) {
		String regex = "^(http|https)://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mail);
		return !m.find();
	}

	public static boolean illegalTelephone(String text) {
		if (text.length() <= 20 && text.charAt(0) != '-'
				&& text.charAt(text.length() - 1) != '-') {
			for (int i = 0; i < text.length(); i++) {
				int charToInt = text.charAt(i);
				if ((charToInt < 48 && charToInt != 45) || charToInt > 57) {
					return true;
				}
			}
			return false;
		}
		return true;
	}

	public static boolean illegalIdcard(String idcard) {
		idcard = idcard.trim();
		// 验证长度
		if (idcard == null || (idcard.length() != 15 && idcard.length() != 18))
			return true;
		// 验证地区
		String cantonCode = idcard.substring(0, 2);
		if (getAreaCode().containsKey(cantonCode) == false)
			return true;
		// 验证出生日期
		String dateStr = "";
		if (idcard.length() == 15)
			dateStr = "19" + idcard.substring(6, 8) + "-"
					+ idcard.substring(8, 10) + "-" + idcard.substring(10, 12);
		if (idcard.length() == 18)
			dateStr = idcard.substring(6, 10) + "-" + idcard.substring(10, 12)
					+ "-" + idcard.substring(12, 14);
		// 验证数字
		if (checkNumeric(idcard) == false)
			return true;
		if (checkDate(dateStr) == false)
			return true;
		// 18位身份证的年份验证
		if (idcard.length() == 18
				&& Integer.parseInt(idcard.substring(6, 8)) < 19)
			return true;
		// 18位身份证需要 验证最后一位数字码
		// if(idcard.length()==18 &&
		// getLastNum(idcard).equalsIgnoreCase(idcard.substring(idcard.length()-1,idcard.length()))==false)
		// return true;
		else
			return false;
	}

	public static String getGenderByIdcard(String idCard) {
		if (idCard == null || "".equals(idCard.trim())) {
			return "";
		}
		if (idCard.length() != 15 && idCard.length() != 18) {
			return "";
		}
		String genderByIdCardNo = "";
		if (idCard.length() == 15) {
			if (Long.parseLong(idCard.substring(14, 15)) % 2 == 1) {
				genderByIdCardNo = "男";
			} else {
				genderByIdCardNo = "女";
			}
		}
		if (idCard.length() == 18) {
			if (Long.parseLong(idCard.substring(16, 17)) % 2 == 1) {
				genderByIdCardNo = "男";
			} else {
				genderByIdCardNo = "女";
			}
		}
		return genderByIdCardNo;
	}

	/**
	 * 获得身份证最后一位识别码
	 * 
	 * @param IDStr
	 * @return
	 */
	public static String getLastNum(String IDStr) {
		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
				"3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
				"9", "10", "5", "8", "4", "2" };
		String Ai = "";

		if (IDStr.length() == 18) {
			Ai = IDStr.substring(0, 17);
		} else if (IDStr.length() == 15) {
			Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
		}
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi
					+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		return ValCodeArr[TotalmulAiWi % 11];
	}

	/**
	 * 身份证地区编码
	 * 
	 * @return
	 */
	private static Hashtable<String, String> getAreaCode() {
		Hashtable<String, String> hashtable = new Hashtable<String, String>();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙古");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙江");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
	}

	public static boolean checkNumeric(String idcard) {
		char a1 = idcard.toUpperCase().charAt(idcard.length() - 1);
		if (idcard.length() == 18 && a1 == 'X') {
			return !illegalNum(idcard.substring(0, 17));
		} else {
			return !illegalNum(idcard);
		}
	}

	private static boolean checkDate(String dateStr) {

		int year = Integer.parseInt(dateStr.substring(0, 4));
		if (year % 4 == 0 && !(year % 100 == 0) || year % 400 == 0) {
			String month = dateStr.substring(5, 7);
			int day = Integer.parseInt(dateStr.substring(8, 10));
			if ("02".equals(month)) {
				return day <= 29;
			} else if ("01".equals(month) || "03".equals(month)
					|| "05".equals(month) || "07".equals(month)
					|| "08".equals(month) || "10".equals(month)
					|| "12".equals(month)) {
				return day <= 31;
			} else if ("04".equals(month) || "06".equals(month)
					|| "09".equals(month) || "11".equals(month)) {
				return day <= 30;
			}

			return false;
		} else {
			String ereg = getPatternStr(dateStr);

			Pattern pattern = Pattern.compile(ereg);
			Matcher matcher = pattern.matcher(dateStr);

			return matcher.matches();
		}
	}

	public static boolean illegalNum(String text) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher numbericMatcher = pattern.matcher(text);
		return !numbericMatcher.matches();
	}

	/**
	 * @param dateStr
	 *            yyyy-MM-dd
	 * @return
	 */
	private static String getPatternStr(String dateStr) {
		String ereg = "";
		int year = Integer.parseInt(dateStr.substring(0, 4));
		if (!(year % 4 == 0 && !(year % 100 == 0) || year % 400 == 0)) {
			// "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";
			ereg = "((19|20)[0-9]{2})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";
		}
		return ereg;
	}

	public static boolean notLettersOrNum(String text) {
		if (!text.matches("^[A-Za-z0-9]+$")) {
			return true;
		}
		return false;
	}

	public static boolean endDateIsSameorBigForStartDate(Date endDate,
			Date startDate) {
		if (endDate.equals(startDate) || endDate.after(startDate)) {
			return true;
		}
		return false;
	}

	public static boolean endDateIsSameorBigForStartDateNotEqual(Date endDate,
			Date startDate) {
		if (endDate.after(startDate)) {
			return true;
		}
		return false;
	}

	public static boolean illegalNumeric(String str) {

		if (str.charAt(0) == '.' || str.charAt(str.length() - 1) == '.'
				|| (str.charAt(0) == '-' || str.charAt(0) == '+')
				&& str.charAt(1) == '.') {
			return true;
		} else {
			try {
				Double.parseDouble(str);
				return false;
			} catch (NumberFormatException ex) {
				return true;
			}
		}
	}

	public static boolean illegalNumericRange(String str, double min, double max) {
		try {
			double value = Double.parseDouble(str);
			return NumbericUtil.compare(value, min) < 0
					|| NumbericUtil.compare(value, max) > 0;
		} catch (NumberFormatException ex) {
			return true;
		}

	}

	public static boolean illegalDate(String strDate) {
		if (!StringUtils.isStringAvaliable(strDate)) {
			return false;
		}
		if (illegalLength(strDate)) {
			return true;
		}
		Date date = null;
		date = CalendarUtil.parseDate(strDate, "yyyy/MM/dd");

		if (null != date && getPrevFourString(strDate, "/") < 1900) {
			return true;
		}
		if (date == null) {
			date = CalendarUtil.parseDate(strDate, "yyyy-MM-dd");
			if (null != date && getPrevFourString(strDate, "-") < 1900) {
				return true;
			}
		}
		if (date == null) {
			date = CalendarUtil.parseDate(strDate, "yyyyMMdd");
			if (null != date && Integer.parseInt(strDate.substring(0,4)) < 1900) {
				return true;
			}
		}

		return date == null;
	}

	// 验证日期的长度
	public static boolean illegalLength(String strDate) {
		if (strDate.indexOf("-") != -1
				&& (strDate.substring(strDate.lastIndexOf("-") + 1).length() > 2 || strDate
						.substring(strDate.indexOf("-") + 1,
								strDate.lastIndexOf("-")).length() > 2)) {
			return true;
		} else if (strDate.indexOf("/") != -1
				&& (strDate.substring(strDate.lastIndexOf("/") + 1).length() > 2 || strDate
						.substring(strDate.indexOf("/") + 1,
								strDate.lastIndexOf("/")).length() > 2)) {
			return true;
		} else {
			return false;
		}
	}

	// 对年份的验证
	public static boolean illegaYear(String strYear) {
		if (!StringUtils.isStringAvaliable(strYear)) {
			return false;
		}
		Date year = null;
		year = CalendarUtil.parseDate(strYear, "yyyy");
		if (null != year
				&& (Integer.parseInt(strYear) < 1000 || Integer
						.parseInt(strYear) > 9999)) {
			return true;
		}
		return false;
	}

	/**
	 * 验证时（时间）24小时制 正确的返回false
	 * 
	 * @param hour
	 * @return
	 */
	public static boolean illegalHour(String strHour) {
		if (!StringUtils.isStringAvaliable(strHour) || illegalNumberZZ(strHour)) {
			return true;
		}
		if (Integer.parseInt(strHour) < 0 || Integer.parseInt(strHour) > 23) {
			return true;
		}
		return false;
	}

	/**
	 * 验证分（时间）
	 * 
	 * @param minute
	 * @return
	 */
	public static boolean illegalMinute(String strMinute) {
		if (!StringUtils.isStringAvaliable(strMinute)
				|| illegalNumberZZ(strMinute)) {
			return true;
		}
		if (Integer.parseInt(strMinute) < 0 || Integer.parseInt(strMinute) > 59) {
			return true;
		}
		return false;
	}

	public static boolean illegalBoolean(String text) {
		if ("是".equals(text) || "否".equals(text)) {
			return false;
		} else {
			return true;
		}
	}

	public static int getPrevFourString(String strDate, String style) {
		String str = strDate.substring(0, strDate.indexOf(style, 1));
		int result = Integer.parseInt(str);
		return result;
	}

	public static boolean illegalUserName(String text) {
		Pattern p = Pattern.compile("^[A-Za-z0-9_@]+$");
		Matcher m = p.matcher(text);
		return !m.find();
	}

	/**
	 * 验证是否输入特殊字符(数字,字母,中文字符)
	 */
	public static boolean illegalExculdeParticalChar(String text) {
		return !text.matches("^[A-Za-z0-9\u4E00-\u9FA5]+$");
	}

	/**
	 * 验证是否输入特殊字符(数字,字母,中文字符,空格,点)
	 */
	public static boolean illegalExculdeParticalChar2(String text) {
		return !text.matches("^[A-Za-z0-9\u4E00-\u9FA5\\s\\.]+$");
	}

	/**
	 * 验证英文名
	 */
	public static boolean illegalEnglishName(String text) {
		return !text.matches("[^\u4e00-\u9fa5]+$");
	}

	/**
	 * 验证英文名只能输入字母
	 */
	public static boolean illegalLetter(String text) {
		return !text.matches("^[A-Za-z]+$");
	}

	/**
	 * 正整数
	 * 
	 * @param text
	 * @return
	 */
	public static boolean illegalNumberZZ(String text) {
		Pattern pattern = Pattern.compile("[^(0|\\-|\\s)*][0-9]*$");
		Matcher numbericMatcher = pattern.matcher(text);
		return !numbericMatcher.matches();

	}

	/**
	 * 只能输入正数
	 * 
	 * @param text
	 * @return
	 */
	public static boolean illegalNumberZS(String text) {
		if (text.startsWith("0"))
			return false;
		Pattern pattern = Pattern.compile("[0-9]+(.[0-9]+)?");
		Matcher numbericMatcher = pattern.matcher(text);
		return !numbericMatcher.matches();

	}

	/**
	 * 校验带有1-2位小数的正实数
	 * 
	 * @param text
	 * @return
	 */
	public static boolean illegalPointNumber(String text) {
		if (text.startsWith("0"))
			return false;
		Pattern pattern = Pattern.compile("^[0-9]+(\\.[0-9]{1,2})?$");
		Matcher numbericMatcher = pattern.matcher(text);
		return !numbericMatcher.matches();
	}

	public static boolean illegalInteger(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher numbericMatcher = pattern.matcher(str);
		if (!numbericMatcher.matches()) {
			return true;
		} else {
			try {
				Integer.parseInt(str);
				return false;
			} catch (NumberFormatException ex) {
				return true;
			}
		}
	}

	/**
	 * 校验带有4位小数的正实数
	 * 
	 * @param text
	 * @return
	 */
	public static boolean illegalPointNumber4(String text) {
		if (text.startsWith("0"))
			return false;
		Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{1,4})?$");
		Matcher numbericMatcher = pattern.matcher(text);
		return !numbericMatcher.matches();
	}

	public static boolean dateIsnotNull(Date date) {
		if (null == date) {
			return false;
		}
		return true;
	}

	/**
	 * 只能输入3位正数
	 * 
	 * @param text
	 * @return
	 */
	public static boolean illegalNumber3(String text) {
		Pattern pattern = Pattern.compile("^[0-9]{3}$");
		Matcher numbericMatcher = pattern.matcher(text);
		return !numbericMatcher.matches();
	}

	/**
	 * 验证用户输入的是否包含js或vb脚本
	 * 
	 * @return
	 */
	public static boolean illegalScript(String text) {
		Pattern pattern = Pattern
				.compile("(vb|java)script|<\\s*script\\s*>|<\\s*/script\\s*>|(expression\\([\\s\\S]*\\))|(&lt;[\\s\\S]*script[\\s\\S]*&gt;)|(&lt;[\\s\\S]*/script[\\s\\S]*&gt;)|(alert|xss|XSS|《|》|~)");
		Matcher scriptMatcher = pattern.matcher(text.toLowerCase());
		return scriptMatcher.find();
	}

	/**
	 * 数字、字母、下划线
	 * 
	 * @return
	 */
	public static boolean illegalDigitStrAndUnderline(String text) {
		return !text.matches("^[A-Za-z0-9_]+$");
	}
	
	/**
     * 验证TA账号的序号是否合法
     * 
     * @return 不符合规则的返回true，正确的返回false
     */
    public static boolean illegalTAAccountSeq(String text) {
        return !text.matches("^[1-9]{1}[0-9]{7}$");
    }
}

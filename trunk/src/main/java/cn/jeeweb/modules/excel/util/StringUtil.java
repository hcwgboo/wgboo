package cn.jeeweb.modules.excel.util;

import java.io.CharConversionException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class StringUtil {
	private static final Log logger = LogFactory.getLog(StringUtil.class);

	private static final String[] ROOT_LIST = new String[] { "/src/",
			"/classes/" };

	private static final String[] SENTENCE_END_LIST = new String[] { ";", ":",
			"{", "}" };

	/**
	 * Boolean型をString型に変換した場合の値(true)
	 */
	public static final String STRING_TRUE_VALUE = "1";

	/**
	 * Boolean型をString型に変換した場合の値(false)
	 */
	public static final String STRING_FALSE_VALUE = "0";

	public static boolean string2Bool(String str) {
		return str.equals(STRING_TRUE_VALUE);
	}

	public static String boolean2String(boolean bool) {
		if (bool) {
			return STRING_TRUE_VALUE;
		} else {
			return STRING_FALSE_VALUE;
		}
	}

	public static String convert2String(Object obj) {
		String str = "";
		if (obj != null) {
			try {
				str = obj.toString();
			} catch (Exception e) {

			}
		}
		return str;
	}

	public static String clearRange(String str, int start, int length) {
		if (str == null || str.length() == 0) {
			return str;
		}
		if (start < 0 || start > str.length() - 1) {
			return str;
		}
		if (start + length > str.length()) {
			length = str.length() - start;
		}
		StringBuffer sb = new StringBuffer(str);
		String mask = StringUtil.getRepeatStr(" ", length);
		sb.replace(start, start + length, mask);

		return sb.toString();
	}

	/**
	 * オブジェクトがNULLまたはブランクなら、&nbsp;を返す。
	 * 
	 * @param obj
	 * 
	 * @return オブジェクトがNULLならまたはブランクそれ以外は 渡されたオブジェクトの文字列形
	 */
	public static String blankToNbsp(Object obj) {

		String str = StringUtil.nullToBlank(obj);

		if (str.equals("")) {
			return "&nbsp;";
		}
		return str;
	}

	/**
	 * 文字列が空文字列ならnullを返します。 空文字列でなければ渡された文字列を返します。
	 * 
	 * @param str
	 *            文字列
	 * @return 文字列が空文字列ならnullそれ以外は 渡された文字列
	 */
	public static String blankToNull(String str) {
		if (str != null && str.length() == 0) {
			return null;
		}
		return str;
	}

	public static int countOccurrences(String text, String findMe) {
		if (text == null)
			throw new NullPointerException(
					"Cannot count occurrences in a null text.");
		if (findMe == null)
			throw new NullPointerException(
					"Cannot count occurrences of a null substring.");
		if (text.length() == 0)
			return 0;
		if (findMe.length() == 0)
			throw new IllegalArgumentException(
					"Cannot count occurrences of an empty substring.");
		int result = 0;
		int index = -1;
		for (int offset = 0; (index = text.indexOf(findMe, offset)) >= 0; offset = index
				+ findMe.length())
			result++;

		return result;
	}

	public static String createInitializedString(char value, int len) {
		char result[] = new char[len];
		for (int i = 0; i < len; i++)
			result[i] = value;

		return new String(result);
	}

	/**
	 * 文字列の桁サイズを取得
	 * 
	 * @param str
	 *            文字列
	 * @return 文字列の桁サイズ
	 */
	public static int getByteLength(String str) {
		int len;
		try {
			len = str.getBytes("Windows-31J").length;
		} catch (UnsupportedEncodingException ex) {
			len = -1;
		}
		return len;
	}

	public static List<String> getInList(String str) {
		List<String> list = new ArrayList<String>();
		if (str == null || str.trim().length() == 0) {
			return list;
		}
		String[] itemList = str.split(",");
		for (String item : itemList) {
			list.add(item);
		}

		return list;
	}

	public static String getInside(String str, String start, String end,
			boolean withOuter, int offset) {
		if (str == null || str.length() == 0) {
			return str;
		}
		String strNew = "";

		int posStart = str.indexOf(start, offset);
		int posEnd = str.indexOf(end, posStart);
		if (posStart >= 0 && posEnd >= 0 && posEnd > posStart) {
			if (withOuter) {
				strNew = str.substring(posStart, posEnd + end.length());
			} else {
				strNew = str.substring(posStart + start.length(), posEnd);
			}
		}
		return strNew;
	}

	public static String[] getInsideList(String str, String start, String end,
			boolean withOuter, boolean ignoreCase, int offset) {
		if (str == null || str.length() == 0) {
			return null;
		}
		ArrayList<String> list = new ArrayList<String>();

		String strFound = (ignoreCase) ? str.toLowerCase() : str;
		start = (ignoreCase) ? start.toLowerCase() : start;
		end = (ignoreCase) ? end.toLowerCase() : end;

		int posStart = strFound.indexOf(start, offset);
		int posEnd = strFound.indexOf(end, posStart);
		boolean found = (posStart >= 0 && posEnd >= 0 && posEnd > posStart);
		while (found) {
			if (withOuter) {
				String strNew = str.substring(posStart, posEnd + end.length());
				list.add(strNew);
			} else {
				String strNew = str
						.substring(posStart + start.length(), posEnd);
				list.add(strNew);
			}
			// search next
			offset = posEnd;
			posStart = strFound.indexOf(start, offset);
			posEnd = strFound.indexOf(end, posStart);
			found = (posStart >= 0 && posEnd >= 0 && posEnd > posStart);
		}

		return (String[]) list.toArray(new String[0]);
	}

	public static String getInStr(String str) {
		if (str == null || str.trim().length() == 0) {
			return "";
		}

		String[] list = str.split(",");
		for (int i = 0; i < list.length; i++) {
			list[i] = "?";
		}

		return join(list, ",");
	}

	public static int getLineNo(String str, int pos) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		if (pos <= 0) {
			return 0;
		}

		int lineNo = getRepeatTimes(str, "\n", 0, pos);
		return lineNo;
	}

	public static String getRepeatStr(String str, int times) {
		if (str == null || str.length() == 0) {
			return "";
		}
		if (times <= 0) {
			return "";
		}
		String strRepeat = "";
		for (int i = 0; i < times; i++) {
			strRepeat += str;
		}
		return strRepeat;
	}

	public static int getRepeatTimes(String str, String sub) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		if (sub == null || sub.length() == 0) {
			return 0;
		}
		return getRepeatTimes(str, sub, 0, str.length());
	}

	public static int getRepeatTimes(String str, String sub, int start, int end) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		if (sub == null || sub.length() == 0) {
			return 0;
		}
		int times = 0;
		int index = str.indexOf(sub, start);
		while (index >= 0) {
			times++;
			start = index + 1;
			if (start >= end) {
				break;
			}
			index = str.indexOf(sub, start);
		}
		return times;
	}

	public static String getXMLInside(String str, String start, String end,
			String key) {
		if (str == null || str.length() == 0) {
			return str;
		}
		String strNew = "";

		Pattern ptn = Pattern.compile(start + "\\s*" + key + "\\s*" + end);
		Matcher mth = ptn.matcher(str);
		if (mth.find()) {
			strNew = mth.group(0);
		}
		return strNew;
	}

	public static String indent(String value, int digit) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < digit; i++) {
			sb.append(" ");
		}
		sb.append(value);
		return sb.toString();
	}

	public static boolean isNotBlank(Object obj) {
		return !isNullOrBlank(obj);
	}

	/**
	 * 文字列がNULLまたはブランクなら、Trueを返す。 オブジェクトがNULLなら、Trueを返す。
	 * 
	 * @param obj
	 *            Object
	 * @return
	 */
	public static boolean isNullOrBlank(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {
			String str = (String) obj;
			return isNullOrBlank(str);
		}
		return false;
	}

	/**
	 * 文字列がNULLまたはブランクなら、Trueを返す。
	 * 
	 * @param str
	 *            文字列
	 * @return
	 */
	public static boolean isNullOrBlank(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 文字列がNULLまたはブランク，Undefinedなら、Trueを返す。
	 * 
	 * @param str
	 *            文字列
	 * @return
	 */
	public static boolean isNullOrBlankOrUndefined(String str) {
		if (str == null || str.length() == 0
				|| str.trim().equalsIgnoreCase("undefined")) {
			return true;
		}
		return false;
	}

	public static boolean isNumber(String str, boolean onlyInt) {
		if (str == null || str.length() == 0) {
			return false;
		}

		// String ptn = (onlyInt) ? "[0-9]+" : "[0-9\\.]+";
		String ptn = (onlyInt) ? "[+\\-]?[0-9]+" : "([+\\-]?[0-9\\.]+)?";
		Pattern pattern = Pattern.compile(ptn);
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public static boolean isAToZ(String str) {
		if (str == null || str.length() == 0) {
			return false;
		}

		Pattern pattern = Pattern.compile("[A-Z]+");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public static boolean isTel(String str) {
		if (str == null || str.length() == 0) {
			return false;
		}

		Pattern pattern = Pattern.compile("[0-9\\-]+");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public static boolean isMail(String str) {
		if (str == null || str.length() == 0) {
			return false;
		}

		Pattern pattern = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public static String getNumberPart(String str) {
		if (str == null || str.length() == 0) {
			return "0";
		}
		int start = 0;
		int length = str.length();
		String number = "0123456789";
		char ch = str.charAt(start);
		while (number.indexOf(ch) < 0) {
			start++;
			if (start >= length) {
				return "0";
			}
			ch = str.charAt(start);
		}

		if (start == length - 1) {
			return str.substring(start);
		}

		int end = start + 1;
		ch = str.charAt(end);
		while (number.indexOf(ch) >= 0) {
			end++;
			if (end >= length) {
				return str.substring(start);
			}
			ch = str.charAt(end);
		}

		return str.substring(start, end);
	}

	public static boolean isStartWith(String str, String prefix) {
		if (str == null || str.length() == 0 || prefix == null
				|| prefix.length() == 0) {
			return false;
		}
		String compare = prefix.toUpperCase();
		String value = str.trim().toUpperCase();
		if (value.startsWith("(") && !value.startsWith(")", value.length() - 1)) {
			return true;
		}
		if (value.startsWith(compare) && value.length() > prefix.length()) {
			return true;
		}
		return false;
	}

	public static boolean isUpperAll(String str) {
		if (str == null || str.length() == 0) {
			return false;
		}

		return str.equals(str.toUpperCase());
	}

	public static boolean isUpperFirst(String str) {
		if (str == null || str.length() == 0) {
			return false;
		}
		str = str.substring(0, 1);

		return str.equals(str.toUpperCase());
	}

	public static boolean isUpperFirstWords(String str, boolean upFirstOnly) {
		if (str == null || str.length() == 0) {
			return false;
		}
		if (str.indexOf(" ") >= 0) {
			String[] words = str.split(" ");
			for (int i = 0; i < words.length; i++) {
				String first = words[i].substring(0, 1);
				if (first.equals(first.toUpperCase())
						&& first.equals(first.toLowerCase())) {
					continue;
				}

				boolean isUpper = first.equals(first.toUpperCase());
				if (upFirstOnly) {
					if (i == 0) {
						if (!isUpper) {
							return false;
						}
					} else {
						if (isUpper) {
							return false;
						}
					}
				} else {
					if (!isUpper) {
						return false;
					}
				}
			}
			return true;

		} else {
			String first = str.substring(0, 1);
			boolean isUpper = first.equals(first.toUpperCase());
			if (isUpper) {
				return true;
			}
		}

		return false;
	}

	public static String join(String[] list, String combine) {
		if (list == null || list.length == 0) {
			return "";
		}
		String str = "";
		for (int i = 0; i < list.length; i++) {
			if (i != 0) {
				str += combine;
			}
			str += list[i];
		}
		return str;
	}

	/**
	 * JavaScriptの文字列リテラルに出力する文字列に変換します。
	 * 
	 * @param str
	 *            文字列
	 * @return 変換後文字列
	 */
	public static String jsEscape(String str) {
		if (str == null) {
			return null;
		}

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			switch (ch) {
			case '\'':
			case '"':
			case '\\':
				buffer.append('\\');
				break;
			default:
				break;
			}
			buffer.append(ch);
		}
		return buffer.toString();
	}

	/**
	 * List 2次元配列から String 2次元配列に変換する。
	 * 
	 * @param list
	 * @return
	 */
	public static String[][] list2Array(List<List<String>> list) {

		List<String[]> tmp = new ArrayList<String[]>();
		for (int i = 0; i < list.size(); i++) {
			List<String> row = (List<String>) list.get(i);
			String[] rowArray = (String[]) row.toArray(new String[0]);
			tmp.add(rowArray);
		}

		String[][] dataArray = (String[][]) tmp
				.toArray(new String[list.size()][]);
		return dataArray;
	}

	public static String lowerFirst(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		String strNew = str.substring(0, 1).toLowerCase() + str.substring(1);
		return strNew;
	}

	public static String lowerFirstWord(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		String strNew = lowerFirst(str.trim());
		if (strNew.indexOf(" ") > 0) {
			String[] words = strNew.split(" ");
			StringBuffer sb = new StringBuffer();
			for (String word : words) {
				if (word.trim().length() > 0) {
					sb.append(" " + lowerFirst(word));
				}
			}
			strNew = sb.toString().trim();
		}

		return strNew;
	}

	/**
	 * 対象がnullなら空文字列を返します。 nullでなければ渡された対象から文字列に変換して、返します。
	 * 
	 * @param obj
	 *            Object
	 * @return 対象がnullなら空文字列それ以外は 渡された文字列
	 */
	public static String nullToBlank(Object obj) {
		if (obj == null) {
			return "";
		}
		return String.valueOf(obj);
	}

	/**
	 * 文字列がnullなら空文字列を返します。 nullでなければ渡された文字列を返します。
	 * 
	 * @param str
	 *            文字列
	 * @return 文字列がnullなら空文字列それ以外は 渡された文字列
	 */
	public static String nullToBlank(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}

	/**
	 * 文字列がnullなら0を返します。 nullでなければ渡された文字列を返します。
	 * 
	 * @param str
	 *            文字列
	 * @return 文字列がnullなら空文字列それ以外は 渡された文字列
	 */
	public static String nullToZero(String str) {
		if (str == null) {
			return "0";
		}
		return str;
	}

	/**
	 * 数値文字列カンマフォーマット。
	 * 
	 * @param number
	 *            数値文字列
	 * @param decimalCount
	 *            小数位数
	 * @return フォーマット文字列
	 */
	public static String numberFormat(Object number, int decimalCount) {

		String num = nullToBlank(number);
		if (isNullOrBlank(num)) {
			return "";
		}

		String strLeft = null;
		String strRight = null;
		int pos = num.indexOf(".");
		if (pos == -1) {
			return numberFormat(num) + ".0000";
		} else {
			strLeft = num.substring(0, pos);
			strRight = num.substring(pos, num.length());
		}

		if (strRight.length() > decimalCount) {
			strRight = strRight.substring(0, decimalCount + 1);
		} else {
			int zeroCount = decimalCount + 1 - strRight.length();
			for (int i = 0; i < zeroCount; i++) {
				strRight += "0";
			}
		}
		return numberFormat(strLeft) + strRight;
	}

	/**
	 * 数値文字列カンマフォーマット。
	 * 
	 * @param num
	 * @return
	 */
	public static String numberFormat(String num) {

		if (isNullOrBlank(num)) {
			return "";
		}

		String ret = null;
		String strLeft = null;
		String strRight = null;
		int pos = num.indexOf(".");
		if (pos == -1) {

			strLeft = num;

		} else {

			strLeft = num.substring(0, pos);
			strRight = num.substring(pos, num.length());

		}

		Long l = Long.valueOf(strLeft);

		DecimalFormat form = new DecimalFormat(
				"###,###,###,###,###,###,###,###,###,###");
		ret = form.format(l);
		if (pos == -1) {

		} else {

			ret = ret + strRight;

		}

		return ret;

	}

	public static String overwriteLeft(String text, String value) {
		if (text.length() == 0 || value.length() == 0)
			return text;
		StringBuffer results = new StringBuffer(text);
		int len = Math.min(text.length(), value.length());
		for (int i = 0; i < len; i++)
			results.setCharAt(i, value.charAt(i));

		return results.toString();
	}

	public static String overwriteRight(String text, String value) {
		if (text.length() == 0 || value.length() == 0)
			return text;
		int lastTextCharIndex = text.length() - 1;
		int lastValueCharIndex = value.length() - 1;
		int len = Math.min(text.length(), value.length());
		StringBuffer result = new StringBuffer(text);
		for (int i = 0; i < len; i++)
			result.setCharAt(lastTextCharIndex - i,
					value.charAt(lastValueCharIndex - i));

		return result.toString();
	}

	public static String padLeft(String str, char padding, int resultLength) {
		if (str == null)
			str = "";
		if (str.length() > resultLength) {
			throw new IllegalArgumentException(
					"Specified value is already longer than specified resultLength");
		} else {
			StringBuffer result = new StringBuffer();
			result.append(createInitializedString(padding,
					resultLength - str.length()));
			result.append(str);
			return result.toString();
		}
	}

	public static String padRight(String str, char padding, int resultLength) {
		if (str == null)
			str = "";
		if (str.length() > resultLength) {
			throw new IllegalArgumentException(
					"Specified value is already longer than specified resultLength");
		} else {
			StringBuffer result = new StringBuffer();
			result.append(str);
			result.append(createInitializedString(padding,
					resultLength - str.length()));
			return result.toString();
		}
	}

	/**
	 * parseDouble (nvl(d))
	 * 
	 * @param param
	 * @return
	 */
	public static double parseDouble(String d) {
		double ret = 0;
		if (isNullOrBlank(d)) {

		} else {
			ret = Double.parseDouble(d);
		}
		return ret;
	}

	public static String pathToPackage(String path) {
		if (path == null || path.length() == 0) {
			return path;
		}

		for (int i = 0; i < ROOT_LIST.length; i++) {
			int index = path.indexOf(ROOT_LIST[i]);
			if (index > 0) {
				path = path.substring(index + ROOT_LIST[i].length());
			}
		}
		String pack = path.replaceAll("/", ".");

		// remove first [.]
		if (pack.startsWith(".")) {
			pack = pack.substring(1);
		}
		return pack;
	}

	public static String replace(String text, String oldSubstring,
			String newSubstring) {
		int index = -1;
		int offset = 0;
		StringBuffer result = new StringBuffer();
		while ((index = text.indexOf(oldSubstring, offset)) >= 0) {
			result.append(text.substring(offset, index)).append(newSubstring);
			offset = index + oldSubstring.length();
		}
		result.append(text.substring(offset));
		return result.toString();
	}

	public static String replaceAll(String str, String from, String to) {
		if (str == null || str.length() == 0) {
			return str;
		}
		if (from == null || from.length() == 0) {
			return str;
		}
		if (to == null) {
			return str;
		}
		if (from.equals(to)) {
			return str;
		}

		String strNew = str;
		int index = strNew.indexOf(from);
		while (index >= 0) {
			String left = (index > 0) ? strNew.substring(0, index) : "";
			String right = strNew.substring(index + from.length());
			strNew = left + to + right;
			index = strNew.indexOf(from, index + to.length());
		}
		return strNew;
	}

	public static String replaceOnce(String str, String from, String to, int pos) {
		if (str == null || str.length() == 0) {
			return str;
		}
		if (from == null || from.length() == 0) {
			return str;
		}
		if (to == null) {
			return str;
		}
		if (from.equals(to)) {
			return str;
		}

		String strNew = str;
		int index = strNew.indexOf(from, pos);
		if (index >= 0) {
			String left = (index > 0) ? strNew.substring(0, index) : "";
			String right = strNew.substring(index + from.length());
			strNew = left + to + right;
			index = strNew.indexOf(from, index + to.length());
		}
		return strNew;
	}

	public static String replaceAll(String str, String[] from, String[] to) {
		if (str == null || str.length() == 0) {
			return str;
		}
		if (from == null || from.length == 0) {
			return str;
		}
		if (to == null || to.length == 0) {
			return str;
		}
		if (from.length != to.length) {
			return str;
		}
		String strNew = str;
		for (int i = 0; i < from.length; i++) {
			String from0 = from[i];
			String to0 = to[i];
			strNew = StringUtil.replaceAll(strNew, from0, to0);
		}

		return strNew;
	}

	public static String sentenceToUnder(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		// from (Aaaa Bbbb) or (Aaaa bbbb) to aaaa_bbbb
		String strNew = str;
		Pattern ptn = Pattern.compile(" ([A-Za-z0-9]{1})");
		Matcher mth = ptn.matcher(str);
		while (mth.find()) {
			String name = mth.group(0);
			String nameNew = "_" + mth.group(1).toLowerCase();
			strNew = replaceAll(strNew, name, nameNew);
		}
		strNew = lowerFirst(strNew);
		return strNew;
	}

	public static String sentenceToUpper(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		// from (Aaaa Bbbb) or (Aaaa bbbb) to aaaaBbbb
		String strNew = str;
		Pattern ptn = Pattern.compile(" ([A-Za-z0-9]{1})");
		Matcher mth = ptn.matcher(str);
		while (mth.find()) {
			String name = mth.group(0);
			String nameNew = mth.group(1).toUpperCase();
			strNew = replaceAll(strNew, name, nameNew);
		}
		strNew = lowerFirst(strNew);
		return strNew;
	}

	public static String spaces(int length) {
		return createInitializedString(' ', length);
	}

	public static String tabs(int length) {
		return createInitializedString('\t', length);
	}

	public static String spaceToTab(String str, int spaceCnt) {
		if (str == null || str.length() == 0) {
			return str;
		}
		if (spaceCnt <= 0) {
			return str;
		}
		String strSpace = "";
		for (int i = 0; i < spaceCnt; i++) {
			strSpace += " ";
		}
		String strNew = str.replaceAll(strSpace, "\t");
		return strNew;
	}

	/**
	 * 区切り文字で区切られた文字列から文字列配列を取得する。 （例：×××,○○○から×××と○○○を取り出す。）
	 * 
	 * @param commaVal
	 * @return
	 */
	public static String[] split(String val, String separateStr) {

		List<String> list = new ArrayList<String>();

		StringTokenizer st = new StringTokenizer(val, separateStr);
		// log.debug("-- トークンされたカウント" + st.countTokens());

		// 値を取得
		while (st.hasMoreTokens()) {

			list.add(st.nextToken());
		}

		return (String[]) list.toArray(new String[list.size()]);
	}

	/**
	 * requestからDouble値を取得
	 * 
	 * @param param
	 * @return
	 */
	public static Double toDoubleFromRequest(String param) {
		if (isNullOrBlank(param) || "null".equalsIgnoreCase(param)) {
			return null;
		} else {
			return new Double(param);
		}
	}

	/**
	 * requestからDouble値or 0(Nullの場合は)を取得
	 * 
	 * @param param
	 * @return
	 */
	public static Double toDoubleOrZeroFromRequest(String param) {
		if (isNullOrBlank(param)) {
			return new Double("0");
		} else {
			return new Double(param);
		}
	}

	public static int toInt(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}

		int result = 0;
		try {
			result = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * requestからInteger値or 0(Nullの場合は)を取得
	 * 
	 * @param param
	 *            文字列
	 * @return Integer値
	 */
	public static Integer toIntegerOrZeroFromRequest(String param) {
		if (isNullOrBlank(param)) {
			return new Integer("0");
		} else {
			return new Integer(param);
		}
	}

	public static long toLong(String str) {
		if (str == null || str.length() == 0) {
			return -1;
		}

		long result = -1;
		try {
			result = Long.parseLong(str);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * requestからLong値を取得
	 * 
	 * @param param
	 * @return
	 */
	public static Long toLongFromRequest(String param) {
		if (isNullOrBlank(param) || "null".equalsIgnoreCase(param)) {
			return null;
		} else {
			return new Long(param);
		}
	}

	/**
	 * requestからLong値 or 0(Nullの場合は) を取得
	 * 
	 * @param param
	 * @return
	 */
	public static Long toLongOrZeroFromRequest(String param) {
		if (isNullOrBlank(param)) {
			return new Long(0);
		} else {
			return new Long(param);
		}
	}

	public static String toStackTraceString(Throwable t) {
		StringWriter sw;
		PrintWriter pw;
		sw = new StringWriter();
		pw = new PrintWriter(sw);
		String s = "";
		try {
			t.printStackTrace(pw);
			pw.flush();
			s = sw.toString();
			pw.close();
		} catch (Exception e) {
			pw.close();
		}
		return s;
	}

	public static String trimDoubleByteSpaceNotNull(String s) {
		if (s == null)
			return "";
		int len = s.length();
		int st = 0;
		char val[];
		for (val = s.toCharArray(); st < len
				&& (val[st] <= ' ' || val[st] == '\u3000'); st++)
			;
		for (; st < len && (val[len - 1] <= ' ' || val[len - 1] == '\u3000'); len--)
			;
		return st <= 0 && len >= s.length() ? s : s.substring(st, len);
	}

	public static String trimLeadingChars(String s, char trimmed) {
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) != trimmed)
				return s.substring(i);

		return "";
	}

	public static String trimNotNull(String s) {
		return s != null ? s.trim() : "";
	}

	public static String trimToMaxBytes(int maxBytes, String value)
			throws CharConversionException, UnsupportedEncodingException {
		return trimToMaxBytes(maxBytes, value,
				System.getProperty("file.encoding"));
	}

	public static String trimToMaxBytes(int maxBytes, String value,
			String encoding) throws CharConversionException,
			UnsupportedEncodingException {
		if (value == null)
			return "";
		String results = value;
		for (byte valueBytes[] = results.getBytes(encoding); valueBytes.length > maxBytes; valueBytes = results
				.getBytes(encoding))
			results = results.substring(0, results.length() - 1);

		return results;
	}

	public static String underToSentence(String str, boolean upFirstOnly) {
		if (str == null || str.length() == 0) {
			return str;
		}
		// from aaaa_bbbb to (Aaaa Bbbb) or (Aaaa bbbb)
		String strNew = str;
		Pattern ptn = Pattern.compile("([a-z]{1})_([a-z]{1})");
		Matcher mth = ptn.matcher(str);
		while (mth.find()) {
			String name = mth.group(0);
			String nameNew = mth.group(1) + " ";
			if (upFirstOnly) {
				nameNew += mth.group(2);
			} else {
				nameNew += mth.group(2).toUpperCase();
			}
			strNew = replaceAll(strNew, name, nameNew);
		}
		strNew = upperFirst(strNew);
		return strNew;
	}

	public static String underToUpper(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		// from aaaa_bbbb to aaaaBbbb
		String strNew = str;
		// Pattern ptn = Pattern.compile("([a-zA-Z0-9]{1})_([a-zA-Z0-9]{1})");
		Pattern ptn = Pattern.compile("_([a-zA-Z0-9]{1})");
		Matcher mth = ptn.matcher(str);
		while (mth.find()) {
			String name = mth.group(0);
			String nameNew = mth.group(1).toUpperCase();
			strNew = replaceAll(strNew, name, nameNew);
		}
		return strNew;
	}

	public static String underToVar(String str, String prefix) {
		if (str == null || str.length() == 0) {
			return str;
		}
		String strNew = underToUpper(str.toLowerCase());
		strNew = prefix + upperFirst(strNew);
		return strNew;
	}

	public static String Unicode2Words(String unicodeStr) {
		if (unicodeStr == null)
			return null;
		StringBuffer retBuf = new StringBuffer();
		int maxLoop = unicodeStr.length();
		for (int i = 0; i < maxLoop; i++)
			if (unicodeStr.charAt(i) == '\\') {
				if (i < maxLoop - 5
						&& (unicodeStr.charAt(i + 1) == 'u' || unicodeStr
								.charAt(i + 1) == 'U'))
					try {
						retBuf.append((char) Integer.parseInt(
								unicodeStr.substring(i + 2, i + 6), 16));
						i += 5;
					} catch (NumberFormatException e) {
						retBuf.append(unicodeStr.charAt(i));
					}
				else
					retBuf.append(unicodeStr.charAt(i));
			} else {
				retBuf.append(unicodeStr.charAt(i));
			}

		return retBuf.toString();
	}

	public static String upperFirst(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		String strNew = str.substring(0, 1).toUpperCase() + str.substring(1);
		return strNew;
	}

	public static String upperFirstWord(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		String strNew = upperFirst(str.trim());
		if (strNew.indexOf(" ") > 0) {
			String[] words = strNew.split(" ");
			StringBuffer sb = new StringBuffer();
			for (String word : words) {
				if (word.trim().length() > 0) {
					sb.append(" " + upperFirst(word));
				}
			}
			strNew = sb.toString().trim();
		}

		return strNew;
	}

	public static String upperToSentence(String str, boolean upFirstOnly) {
		if (str == null || str.length() == 0) {
			return str;
		}
		// from aaaaBbbb to (Aaaa Bbbb) or (Aaaa bbbb)
		String strNew = str;
		Pattern ptn = Pattern.compile("([a-z0-9]{1})([A-Z]{1})");
		Matcher mth = ptn.matcher(str);
		while (mth.find()) {
			String name = mth.group(0);
			String nameNew = mth.group(1) + " ";
			if (upFirstOnly) {
				nameNew += mth.group(2).toLowerCase();
			} else {
				nameNew += mth.group(2);
			}
			strNew = replaceAll(strNew, name, nameNew);
		}

		Pattern ptn2 = Pattern.compile("([^0-9]{1})([0-9]{1})");
		Matcher mth2 = ptn2.matcher(strNew);
		while (mth2.find()) {
			String name = mth2.group(0);
			String nameNew = mth2.group(1) + " ";
			if (upFirstOnly) {
				nameNew += mth2.group(2).toLowerCase();
			} else {
				nameNew += mth2.group(2);
			}
			strNew = replaceAll(strNew, name, nameNew);
		}

		strNew = upperFirst(strNew);
		return strNew;
	}

	public static String upperToUnder(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		// from aaaaBbbb to aaaa_bbbb
		String strNew = str;
		Pattern ptn = Pattern.compile("([a-z0-9]{1})([A-Z]{1})");
		Matcher mth = ptn.matcher(str);
		while (mth.find()) {
			String name = mth.group(0);
			String nameNew = mth.group(1) + "_" + mth.group(2).toLowerCase();
			strNew = replaceAll(strNew, name, nameNew);
		}

		Pattern ptn2 = Pattern.compile("([^0-9]{1})([0-9]{1})");
		Matcher mth2 = ptn2.matcher(strNew);
		while (mth2.find()) {
			String name = mth2.group(0);
			String nameNew = mth2.group(1) + "_" + mth2.group(2).toLowerCase();
			strNew = replaceAll(strNew, name, nameNew);
		}
		return strNew;
	}

	public static String Words2Unicode(String uniStr) {

		StringBuffer ret = new StringBuffer();
		if (uniStr == null)
			return null;
		int maxLoop = uniStr.length();
		for (int i = 0; i < maxLoop; i++) {
			char character = uniStr.charAt(i);
			if (character <= '\177') {
				ret.append(character);
				continue;
			}
			ret.append("\\u");
			String hexStr = Integer.toHexString(character);
			int zeroCount = 4 - hexStr.length();
			for (int j = 0; j < zeroCount; j++)
				ret.append('0');

			ret.append(hexStr);
		}

		return ret.toString();
	}

	public static String zeros(int length) {
		return createInitializedString('0', length);
	}

	/**
	 * 指定サイズのIDを生成する。
	 * 
	 * @param prefix
	 *            前付け文字列
	 * @param code
	 *            ID
	 * @param length
	 *            サイズ
	 * @return 生成されたID
	 */
	public static String makeId(String prefix, long id, int length) {
		StringBuffer sb = new StringBuffer();
		if (isNullOrBlank(prefix)) {
			prefix = "";
		}
		sb.append(prefix);

		String idStr = "" + id;
		int idLen = idStr.length() + prefix.length();
		if (length > 0 && idLen < length) {
			String zeroStr = StringUtil.getRepeatStr("0", length - idLen);
			sb.append(zeroStr);
		}
		sb.append(idStr);

		return sb.toString();
	}

	/**
	 * 指定文字列のランダムで一つ文字を取得する。
	 * 
	 * @param str
	 *            指定文字列
	 * @return ランダム文字
	 */
	public static String randomOne(String str) {
		if (isNullOrBlank(str)) {
			return "";
		}
		long len = str.length();
		if (len > 0) {
			int pos = (int) randomNum(0, len - 1);
			return str.substring(pos, pos + 1);
		}
		return "";
	}

	/**
	 * 指定文字列リストからのランダムで一つ文字を取得する。
	 * 
	 * @param str
	 *            指定文字列リスト
	 * @return ランダム文字
	 */
	public static String randomOne(String[] str) {
		if (isNullOrBlank(str)) {
			return "";
		}

		long len = str.length;
		if (len > 0) {
			int pos = (int) randomNum(0, len - 1);
			return str[pos];
		}
		return "";
	}

	/**
	 * 指定数字範囲からのランダムで一つ数字を取得する。
	 * 
	 * @param from
	 *            開始数字
	 * @param to
	 *            終了数字
	 * @return ランダム数字
	 */
	public static long randomNum(long from, long to) {
		long range = to - from;
		if (range > 0) {
			return from + Math.round(range * Math.random());
		}
		return 0;
	}

	public static String getIndent(String txt, String str, int pos) {
		if (isNullOrBlank(txt) || isNullOrBlank(str)) {
			return "";
		}

		int index = txt.indexOf(str, pos);
		String sub = txt.substring(0, index);
		int posNewLine = sub.lastIndexOf("\n");
		if (posNewLine < 0) {
			posNewLine = 0;
		}
		String indent = sub.substring(posNewLine + 1);

		return indent;
	}

	public static String getFullSentence(String txt, String str, int pos) {
		if (isNullOrBlank(txt) || isNullOrBlank(str)) {
			return "";
		}

		int index = txt.indexOf(str, pos);
		if (index < 0) {
			return "";
		}
		String sub = txt.substring(0, index);
		int preNewLine = 0;
		boolean foundPrev = false;
		for (String endStr : SENTENCE_END_LIST) {
			int posEnd = sub.lastIndexOf(endStr);
			if (posEnd > preNewLine) {
				preNewLine = posEnd;
				foundPrev = true;
			}
		}
		if (foundPrev) {
			preNewLine = txt.indexOf("\n", preNewLine) + 1;
		}

		int nextNewLine = txt.indexOf("\n", index + str.length());
		if (nextNewLine < 0) {
			nextNewLine = txt.length();
		}

		String full = txt.substring(preNewLine, nextNewLine);

		return full;
	}

	public static String getFullLines(String txt, String str, int pos) {
		if (isNullOrBlank(txt) || isNullOrBlank(str)) {
			return "";
		}

		int index = txt.indexOf(str, pos);
		if (index < 0) {
			return "";
		}
		String sub = txt.substring(0, index);
		int preNewLine = sub.lastIndexOf("\n");
		if (preNewLine < 0) {
			preNewLine = 0;
		}
		int nextNewLine = txt.indexOf("\n", index + str.length());
		if (nextNewLine < 0) {
			nextNewLine = txt.length();
		}

		String full = txt.substring(preNewLine + 1, nextNewLine);

		return full;
	}

	public static String getIndent(String str) {
		Pattern ptn = Pattern.compile("^(\\s*)[^\\s]");
		Matcher mth = ptn.matcher(str);
		if (mth.find()) {
			String indent = mth.group(1);
			return indent;
		}
		return "";
	}

	public static String getAppendVarName(String content, String str, int pos) {
		String sub = content.substring(0, pos);
		int posFrom = sub.lastIndexOf(";");
		int posTo = pos;
		sub = content.substring(posFrom + 1, posTo);

		Pattern ptn = Pattern.compile("\\s([A-Za-z0-9_]+)\\.append\\(");
		Matcher mth = ptn.matcher(sub);
		if (mth.find()) {
			String var = mth.group(1);
			return var;
		}

		Pattern ptn2 = Pattern.compile("StringBuffer\\s+([A-Za-z0-9_]+)\\s*=");
		Matcher mth2 = ptn2.matcher(sub);
		if (mth2.find()) {
			String var = mth2.group(1);
			return var;
		}

		return "";
	}

	public static String splitAppend(String content) {
		String contentNew = content;

		Pattern ptn = Pattern.compile("\\)\\s*\\.append\\(");
		int index = 0;
		Matcher mth = ptn.matcher(content);
		while (mth.find()) {
			String strFound = mth.group(0);
			String strFull = getFullLines(content, strFound, index);
			index = content.indexOf(strFound, index);
			String var = getAppendVarName(content, strFull, index);
			String strSuffix = strFull.substring(strFull.indexOf(strFound)
					+ strFound.length());

			String indent = getIndent(strFull);

			StringBuffer sb = new StringBuffer();
			sb.append(");\r\n");
			sb.append(indent + " " + var + ".append(");
			sb.append(strSuffix);

			contentNew = StringUtil.replaceOnce(contentNew, strFound
					+ strSuffix, sb.toString(), 0);
			index += strFound.length();
		}

		return contentNew;
	}

	public static int compareString(String val1, String val2) {

		if (isNullOrBlank(val1)) {
			return -1;
		}
		if (isNullOrBlank(val2)) {
			return 1;
		}

		return val1.compareTo(val2);
	}

	public static boolean isMailAddress(String input) {
		String reg = "(\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3})";
		return input.matches(reg);
	}

	public static boolean isTelNo(String input) {
		String reg = "[0-9]{7,8}";
		return input.matches(reg);
	}

	public static String splitCardNo(String cardNo) {
		String regex = "(.{4})";
		cardNo = cardNo.replaceAll(regex, "$1 ");
		return cardNo;
	}

	/**
	 * 
	 * @param string
	 * @return 如果是合法字符串则返回true，否则返回false
	 */
	public static boolean isStringAvaliable(String string) {
		return string != null && !"".equals(string.trim());
	}
	
	/**
	 * 首字母转小写
	 * @param str
	 * @return
	 */
	public static String toLowerFirstCase(String str) {
		if (isStringAvaliable(str)) {
			str = str.substring(0, 1).toLowerCase() + str.substring(1);
		}
		return str;
	}
	/**
	 * 首字母转大写
	 * @param str
	 * @return
	 */
	public static String toUpperFirstCase(String str) {
        if (isStringAvaliable(str)) {
            str = str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return str;
    }

	/**
	 * 以逗号分隔的字符串中是否包含另一个字符串
	 * 
	 * @param arr
	 * @param targetValue
	 * @return
	 */
	public static boolean isContainsTarget(String source, String target) {
		if (!isStringAvaliable(source) || !isStringAvaliable(target)) {
			return false;
		}
		String[] arr = source.split(",");
		return Arrays.asList(arr).contains(target);
	}
	
	
	/**
	 * 
	 * 判断是否存在特殊字符(下划线_or减号-不是特殊字符)
	 * @param string
	 * @return true(是特殊字符) flase (否)
	 */
	public static boolean isConSpeCharacters(String string){ 
        if(replaceSpaceAll(string).replaceAll("[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*","").length()==0){ 
            //不包含特殊字符 
            return false; 
        } 
        return true; 
    }  
	
	/**
	 * 判断是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    Matcher isNum = pattern.matcher(str);
	    if( !isNum.matches() ){
	        return false; 
	    } 
	    return true; 
	 }
	
	/**
     * 去除中间全角半角空格
     * 
     * @param str
     * @return
     */
    public static String replaceSpaceAll(String str){
        if(str!=null){
            return str.replaceAll(" ", "").replaceAll("　", "");
        }
        return null;
    }
    
    /**
     * 判断是否是字母A-Z a-z
     * 
     * @param str
     * @return
     */
    public static boolean checkIsLetter(String str){
        Pattern p = Pattern.compile("^[A-Za-z]+$");
        Matcher m = p.matcher(str);
        boolean isValid = m.matches();
        return isValid;
    }
    
    /**
     * 保留中间存在一个空格
     * @param str 
     * @return
     */
    public static String retainCentreOneSpace(String str){
        str=str.replaceAll("　", " ").trim();
        String[] strs=str.split(" ");
        if(strs.length==2){
            return str;
        }
        if(strs.length>2){
            StringBuffer sb=new StringBuffer();
            for (int i=0;i<strs.length;i++) {
                if("".equals(strs[i])){
                    continue;
                }
                sb.append(strs[i]+" ");
            }
             sb.setLength(sb.length()-1);
             return sb.toString();
        }
        return str;
    }
    
    
    /**
     * 字符串左补零
     * @param str  
     * @param strLength
     * @return
     */
    public static String addZeroForNum(String str, int strLength) {  
        int strLen = str.length();  
            if (strLen < strLength) {  
                while (strLen < strLength) {  
                StringBuffer sb = new StringBuffer();  
                sb.append("0").append(str);//左补0  
                // sb.append(str).append("0");//右补0  
                str = sb.toString();  
                strLen = str.length();  
                }  
        }  
        return str;  
    };
    
    public static String investorTypeTranInvestorTypeCode(String investorTypeName){
        if("个人".equals(investorTypeName)){
            /*investorTypeName=CodeStringUtil.tranCsDescByCsCode(CodeStringConstant.CS_6007_STATUS_FREE);*/
        }
        return investorTypeName;
    }
    
    public static void main(String[] args) {
        
        
     /*   
        List<TInitialDataDto> transactionDtos=new ArrayList<TInitialDataDto>();
        TInitialDataDto a=new TInitialDataDto();
        a.setInvestor_name("张三");
        a.setIdentity_number("123");
        a.setIdentity_type("身份证");
        a.setInvestor_type("个人");
        a.setBank_account("5566");
        a.setBusiness_type("5");
        TInitialDataDto b1=new TInitialDataDto();
        b1.setInvestor_name("李四");
        b1.setIdentity_number("123");
        b1.setIdentity_type("身份证");
        b1.setInvestor_type("个人");
        b1.setBank_account("5566");
        TInitialDataDto b=new TInitialDataDto();
        b.setInvestor_name("张三");
        b.setIdentity_number("123");
        b.setIdentity_type("身份证");
        b.setInvestor_type("个人");
        b.setBank_account("5566");
        b.setBusiness_type("6");
        TInitialDataDto b2=new TInitialDataDto();
        b2.setInvestor_name("王五");
        b2.setIdentity_number("123");
        b2.setIdentity_type("身份证");
        b2.setInvestor_type("个人");
        b2.setBank_account("5566");
        TInitialDataDto b3=new TInitialDataDto();
        b3.setInvestor_name("王五");
        b3.setIdentity_number("123");
        b3.setIdentity_type("身份证");
        b3.setInvestor_type("个人");
        b3.setBank_account("5566");
        TInitialDataDto b4=new TInitialDataDto();
        b4.setInvestor_name("赵六");
        b4.setIdentity_number("123");
        b4.setIdentity_type("身份证");
        b4.setInvestor_type("个人");
        b4.setBank_account("5566");
        transactionDtos.add(a);
        transactionDtos.add(b);
        transactionDtos.add(b1);
        transactionDtos.add(b2);
        transactionDtos.add(b3);
        transactionDtos.add(b4);
        Map<String,List<TInitialDataDto>> map=new HashMap<String, List<TInitialDataDto>>();
        List<TInitialDataDto> list=new ArrayList<TInitialDataDto>();
        for (int i = 0; i < transactionDtos.size() - 1; i++) {
            boolean flag=false;
            for (int j = transactionDtos.size() - 1; j > i; j--) {
                if (transactionDtos.get(j).equals(transactionDtos.get(i))) {
                    list.add(transactionDtos.get(j));
                    transactionDtos.remove(j);
                    flag=true;
                }
            }
            if(flag){
              //  list.add(transactionDtos.get(i));
               // transactionDtos.remove(i);
              //  map.put(String.valueOf(i),list);
            }
        }
        
        System.out.println(list);
        System.out.println(transactionDtos);
        System.out.println(map);*/
        
        
      /*  Set<TInitialDataDto>  set = new HashSet<TInitialDataDto> ();
        set.addAll(transactionDtos);
        Collection<TInitialDataDto>   jjbj= CollectionUtils.intersection(transactionDtos, set);
        System.out.println("交集的补集:"+jjbj);*/
        
        
        
    }
    
}

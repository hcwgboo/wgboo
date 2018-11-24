package cn.jeeweb.modules.excel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.excel.validate.DetialsErrorInfo;
import cn.jeeweb.modules.excel.validate.ValidateHelper;
import cn.jeeweb.modules.excel.validate.ValidateResult;
import cn.jeeweb.modules.sys.utils.DictUtils;

@Component("excelImportHelper")
public class ExcelImportHelper {

	public final static Logger logger = LoggerFactory.getLogger(ExcelImportHelper.class);

	// 列的集合
	private static Map<String, String> dataColumMap = new HashMap<String, String>();
	// 错误数据集合
	private static List<String[]> errotDataList = new ArrayList<String[]>();
	// 错误信息
	private static List<String> errorMsgList = new ArrayList<String>();
	// 格式不正确错误信息
	private static List<String> errorFormatMsgList = new ArrayList<String>();
	// 一共处理的记录（有效记录）
	public static ThreadLocal<Integer> succNum = new ThreadLocal<Integer>();
	// 总数
	public static ThreadLocal<Integer> importDataNum = new ThreadLocal<Integer>();
	// 空行数据
	public static ThreadLocal<Integer> nullRowNum = new ThreadLocal<Integer>();
	public static ThreadLocal<Integer> realRow = new ThreadLocal<Integer>();
	// 插入行数
	public static ThreadLocal<Integer> insertNum = new ThreadLocal<Integer>();
	// 更新行数
	public static ThreadLocal<Integer> updateNum = new ThreadLocal<Integer>();
	// 更新和插入的数据主键
	private static List<String> pkList = new ArrayList<String>();

	public static List<DetialsErrorInfo> importOrderErrorInfo = new ArrayList<DetialsErrorInfo>();

	public static Object newProcImportDate(String[][] beanDatas, String[] cellValues, Object object,
			ValidateResult vr) {
		Map<String, String> mapMessages = new HashMap<String, String>();
		// 防止数组下标异常
		int length = beanDatas[0].length > cellValues.length ? cellValues.length : beanDatas[0].length;
		Boolean falg = false;
		for (int i = 0; i < length; i++) {

			putDataColumMap(beanDatas[0][i], i);
			if (!validateNull(beanDatas, cellValues, i, object, falg, mapMessages)) {
				continue;
			}
			if (beanDatas[0][i] == null || StringUtils.isEmpty(beanDatas[0][i]) || beanDatas[1][i] == null
					|| StringUtils.isEmpty(beanDatas[1][i])) {
				continue;
			}
			if (StringUtils.isEmpty(cellValues[i])) {
				continue;
			}
			if (!validateDate(beanDatas, cellValues, i, object, falg, mapMessages)) {
				continue;
			}
			if (!validateBoolean(beanDatas, cellValues, i, object, falg, mapMessages)) {
				continue;
			}
			if (!validateInteger(beanDatas, cellValues, i, object, falg, mapMessages)) {
				continue;
			}
			if (!validateDouble(beanDatas, cellValues, i, object, falg, mapMessages)) {
				continue;
			}
			if (!validateLong(beanDatas, cellValues, i, object, falg, mapMessages)) {
				continue;
			}
			if (!validateBigdecimal(beanDatas, cellValues, i, object, falg, mapMessages)) {
				continue;
			}
			if (!validateDict(beanDatas, cellValues, i, object, falg,
					mapMessages)) {
				continue;
			}
			if (!StringUtils.isEmpty(cellValues[i]) && !StringUtils.isEmpty(beanDatas[0][i]) && !falg) {
				setProperty(object, beanDatas[0][i], cellValues[i]);
			}
		}

		vr.setMapMessages(mapMessages);
		return object;
	}

	public static void putDataColumMap(String key, int value) {
		dataColumMap.put(key, String.valueOf(value));
	}

	public static void addErrotDataList(String[] data) {
		errotDataList.add(data);
	}

	public static List<String[]> getErrotDataList() {
		return errotDataList;
	}

	/**
	 * 验证是否可以为空
	 */
	public static boolean validateNull(String[][] beanDatas, String[] cellValues, int i, Object object, Boolean falg,
			Map<String, String> mapMessages) {
		String[] isPropertyNulls = beanDatas[3];
		if (StringUtils.isStringAvaliable(isPropertyNulls[i]) && "1".equals(isPropertyNulls[i])) {// 当字段是必须填写的
			if (!StringUtils.isStringAvaliable(cellValues[i])) {// 值为null 或者空字符串
				mapMessages.put(String.valueOf(i + 1), beanDatas[2][i] + "不能为null");
				logger.info(beanDatas[2][i] + "不能为null");
				return false;
			}
		}
		return true;
	}

	private static boolean validateDate(String[][] beanDatas, String[] cellValues, int i, Object object, Boolean falg,
			Map<String, String> mapMessages) {
		if (DataType.DATA_TYPE_DATE.equals(beanDatas[1][i])) {
			falg = true;
			try {
				if (ValidateHelper.illegalDate(cellValues[i])) {
					mapMessages.put(String.valueOf(i + 1), beanDatas[2][i] + "输入格式不正确");
					return false;
				}
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				// fateson add 修复（如果日期 输入 成2015-0 就会抱错 ，页面呈现 模板错误bug）
				mapMessages.put(String.valueOf(i + 1), beanDatas[2][i] + "输入格式不正确");
				return false;
			}
			setProperty(object, beanDatas[0][i], DataConvertionHelper.convertToDate(cellValues[i]));
			return false;
		}
		return true;
	}

	private static boolean validateBoolean(String[][] beanDatas, String[] cellValues, int i, Object object,
			Boolean falg, Map<String, String> mapMessages) {
		if (DataType.DATA_TYPE_BOOLEAN.equals(beanDatas[1][i])) {
			falg = true;
			if ((cellValues[i] != null && cellValues[i].toString().length() != 0) && !"是".equals(cellValues[i])
					&& !"有".equals(cellValues[i]) && !"否".equals(cellValues[i]) && !"无".equals(cellValues[i])) {
				mapMessages.put(String.valueOf(i + 1), beanDatas[2][i] + "输入格式不正确");
				return false;
			}
			if ("是".equals(cellValues[i]) || "有".equals(cellValues[i])) {
				setProperty(object, beanDatas[0][i], Long.valueOf("1"));
			} else {
				setProperty(object, beanDatas[0][i], Long.valueOf("0"));
			}
			return false;
		}
		return true;
	}

	private static boolean validateInteger(String[][] beanDatas, String[] cellValues, int i, Object object,
			boolean falg, Map<String, String> mapMessages) {
		if (DataType.DATA_TYPE_INTEGER.equals(beanDatas[1][i])) {
			falg = true;
			if (ValidateHelper.illegalInteger(cellValues[i])) {
				mapMessages.put(String.valueOf(i + 1), beanDatas[2][i] + "输入格式不正确");
				return false;
			}
			setProperty(object, beanDatas[0][i], DataConvertionHelper.convertToInteger(cellValues[i]));
			return false;
		}
		return true;

	}

	private static boolean validateDouble(String[][] beanDatas, String[] cellValues, int i, Object object, boolean falg,
			Map<String, String> mapMessages) {
		if (DataType.DATA_TYPE_DOUBLE.equals(beanDatas[1][i])) {
			falg = true;
			if (ValidateHelper.illegalPointNumber(cellValues[i])) {
				mapMessages.put(String.valueOf(i + 1), beanDatas[2][i] + "输入格式不正确");
				return false;
			}
			setProperty(object, beanDatas[0][i], DataConvertionHelper.convertToDouble(cellValues[i]));
			return false;
		}
		return true;

	}

	private static boolean validateLong(String[][] beanDatas, String[] cellValues, int i, Object object, boolean falg,
			Map<String, String> mapMessages) {
		if (DataType.DATA_TYPE_LONG.equals(beanDatas[1][i])) {
			falg = true;
			if (ValidateHelper.illegalNum(cellValues[i])) {
				mapMessages.put(String.valueOf(i + 1), beanDatas[2][i] + "输入格式不正确");
				return false;
			}
			setProperty(object, beanDatas[0][i], DataConvertionHelper.convertToLong(cellValues[i]));
			return false;
		}
		return true;
	}

	private static boolean validateBigdecimal(String[][] beanDatas, String[] cellValues, int i, Object object,
			Boolean falg, Map<String, String> mapMessages) {
		if (DataType.DATA_TYPE_BIGDECIMAL.equals(beanDatas[1][i])) {
			falg = true;
			BigDecimal value = null;
			try {
				value = DataConvertionHelper.convertToBigdecimal(cellValues[i]);
			} catch (Exception e) {
				e.printStackTrace();
				mapMessages.put(String.valueOf(i + 1), beanDatas[2][i] + "输入格式不正确");
			}
			setProperty(object, beanDatas[0][i], value);
			return false;
		}
		return true;
	}

	
	private static boolean validateDict(String[][] beanDatas,
			String[] cellValues, int i, Object object, Boolean falg,
			Map<String, String> mapMessages) {
		if (DataType.DATA_TYPE_DICT.equals(beanDatas[1][i])) {
			
			String dictValue = DictUtils.getDictValue(cellValues[i], beanDatas[4][i], "");
			
			if (!StringUtils.isEmpty(cellValues[i])) {
				if (StringUtils.isEmpty(dictValue)) {
					mapMessages.put(String.valueOf(i + 1), beanDatas[2][i]
							+ "输入不正确");
					return false;
				}
			}
			setProperty(object, beanDatas[0][i], dictValue);
			return false;
		}
		return true;
	}
	
	private static void setProperty(Object bean, String name, Object value) {
		try {
			BeanUtils.setProperty(bean, name, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取属性值
	 * 
	 * @param bean
	 * @param name
	 * @return
	 */
	public static String getProperty(Object bean, String name) {
		try {
			return BeanUtils.getProperty(bean, name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getDataColumMap(String key) {
		return dataColumMap.get(key);
	}

	public static void putDataColumMap(String key, String value) {
		dataColumMap.put(key, value);
	}

	public static String getColumNo(String key) {
		StringBuffer bf = new StringBuffer();
		if (!StringUtils.isEmpty(ExcelImportHelper.getDataColumMap(key))) {
			bf.append("第[").append(ExcelImportHelper.realRow.get()).append("]行");
			bf.append("第[").append(Integer.valueOf(ExcelImportHelper.getDataColumMap(key)) + 1).append("]列");
		} else {
			bf.append("");
		}
		return bf.toString();
	}

	public static List<String> getErrorMsgList() {
		return errorMsgList;
	}

	public static void setErrorMsgList(List<String> errorMsgList) {
		ExcelImportHelper.errorMsgList = errorMsgList;
	}

	public static List<String> getErrorFormatMsgList() {
		return errorFormatMsgList;
	}

	public static void addErrorFormatMsgList(String errorMsg) {
		ExcelImportHelper.errorFormatMsgList.add(errorMsg);
	}

	public static void addErrorMsg(String msg) {
		ExcelImportHelper.errorMsgList.add(msg);
	}

	public static String getErrorMsg() {
		return ExcelImportHelper.errorMsgList.toString();
	}

	public static List<String> getPkList() {
		return pkList;
	}

	public static void setPkList(List<String> pkList) {
		ExcelImportHelper.pkList = pkList;
	}

	public static void addPK(String msg) {
		ExcelImportHelper.pkList.add(msg);
	}

	public static String getPK() {
		return ExcelImportHelper.pkList.toString();
	}

	public static List<DetialsErrorInfo> getImportOrderErrorInfo() {
		return importOrderErrorInfo;
	}

	public static void setImportOrderErrorInfo(List<DetialsErrorInfo> importOrderErrorInfo) {
		ExcelImportHelper.importOrderErrorInfo = importOrderErrorInfo;
	}

}

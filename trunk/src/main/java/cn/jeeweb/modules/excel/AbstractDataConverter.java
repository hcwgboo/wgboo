package cn.jeeweb.modules.excel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.excel.validate.ValidateHelper;
import cn.jeeweb.modules.excel.validate.ValidateResult;

public abstract class AbstractDataConverter<T> implements DataConvert<T> {
	protected final static Logger logger = LoggerFactory
			.getLogger(AbstractDataConverter.class);

	public void persistenceDomain(List<T> data) {
	}

	public void updateDomain(List<T> data) {
	}

	private Class<T> getRuntimeType() {
		Class<T> entityClass = null;
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();

			entityClass = (Class<T>) parameterizedType[0];
		}
		return entityClass;
	}

	public T convertToDomain(String[] cellValues, ValidateResult result,
			String[][] beanDatas) {
		cellValues = ValidateHelper.cellValueTrim(cellValues);
		T returnObject = null;
		try {
			returnObject = getRuntimeType().newInstance();
			ExcelImportHelper.newProcImportDate(beanDatas, cellValues,
					returnObject, result);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return returnObject;
	}

	/**
	 * 通用验证特殊验证需要覆盖该方法
	 */
	public ValidateResult validate(T domain, int realRow, String[][] beanDatas) {
		ExcelImportHelper.realRow.set(realRow);
		String[] isPropertyNulls = beanDatas[3];
		// 防止数组下标异常
		int length = beanDatas[0].length > isPropertyNulls.length ? isPropertyNulls.length
				: beanDatas[0].length;
		ValidateResult result = new ValidateResult();
		for (int i = 0; i < length; i++) {
			if (StringUtils.isStringAvaliable(isPropertyNulls[i])
					&& "1".equals(isPropertyNulls[i])) {// 当字段是必须填写的
				String property = ExcelImportHelper.getProperty(domain,
						beanDatas[0][i]);
				if (!StringUtils.isStringAvaliable(property)) {// 值为null 或者空字符串
					result.addErrorMessage(getColumNo(beanDatas[0][i])
							+ beanDatas[2][i] + "不能为null");
				}

			}
		}
		return result;
	}

	public T convertToDomain(String[] cellValues) {
		return null;
	}

	public String getColumNo(String key) {
		StringBuffer bf = new StringBuffer();
		if (!StringUtils.isEmpty(ExcelImportHelper.getDataColumMap(key))
				&& ExcelImportHelper.realRow.get() != null) {
			bf.append("第[").append(ExcelImportHelper.realRow.get())
					.append("]行");
			bf.append("第[")
					.append(Integer.valueOf(ExcelImportHelper
							.getDataColumMap(key)) + 1).append("]列");
		} else {
			bf.append("");
		}
		return bf.toString();
	}

}

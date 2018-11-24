package cn.jeeweb.core.utils;


import com.alibaba.fastjson.JSONObject;

import cn.jeeweb.modules.excel.util.ExportTips;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2018/5/24
 * @author lx
 *
 */
public class JsonUtils {
	/**
	 * 把bean对象转换成为Json字符串
	 * @param jsonString
	 * @return
	 * @throws IOException
	 */
	public static<T> String parseObjectToStr(T jsonString) {
		if(ObjectUtils.isNullOrEmpty(jsonString)) return null;
		try {
			return JSONObject.toJSONString(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 把json字符串转成bean对象
	 * @param jsonString
	 * @return
	 * @return T
	 */
	public static <T>  T parseStrToObject(String jsonString, Class<T> valueType) {
		if(StringUtils.isEmpty(jsonString)) return null;
		try {
			return JSONObject.parseObject(jsonString, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 把json字符串转成List对象
	 * @param jsonString
	 * @return List<T>
	 */
	public static <T> List<T> parseStrToList(String jsonString, Class<T> valueType) {
		if(StringUtils.isEmpty(jsonString)) return null;
		try {
			return JSONObject.parseArray(jsonString, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 把List集合转换成json字符串
	 * @param list
	 * @return
	 * @throws IOException
	 */
	public static<T> String parseListToStr(List<T> list){
		if(ObjectUtils.isNullOrEmpty(list)) return null;
		try {
			return JSONObject.toJSONString(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把字符串转JSONObject对象
	 * @param jsonString
	 * @return
	 * @throws IOException
	 */
	public static JSONObject toparseJSONObject(String jsonString)  {
		if(StringUtils.isEmpty(jsonString)) return null;
		return (JSONObject) JSONObject.parse(jsonString);
	}

	public static Map<String, Object> jsonStrToObject(String jsonString) {
		Map<String, Object> map = parseStrToObject(jsonString, Map.class);
		if (map==null) map = new HashMap<>();
		ExportTips.setReportDate(map);
		return map;
	}
	
}

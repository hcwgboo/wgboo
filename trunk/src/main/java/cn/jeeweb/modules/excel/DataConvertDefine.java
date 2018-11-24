package cn.jeeweb.modules.excel;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @Description:配置对应的数据转换类
 * @author zhangyouwei1988@sina.cn
 * @date: 2016-5-4 下午6:42:48
 */
public final class DataConvertDefine {
	//conver
	static Map<String, String> defines = new HashMap<String, String>();
	//描述
	static Map<String, String> display = new HashMap<String, String>();
	//配置模板id
	static Map<String, String> templet = new HashMap<String, String>();
	//excel模板
	static Map<String, String> excel_templet = new HashMap<String, String>();
	
	
	
	public static final String HOUSE = "house";
	public static final String HOUSE_DISPLAY = "房源";
	public static final String HOUSE_TEMPLET = "house";
	public static final String HOUSE_CONVERTER = "houseConverter";
	public static final String HOUSE_TEMPLATE = "house.xlsx";
	
	public static final String STORE = "storeAndProject";
	public static final String STORE_DISPLAY = "分销";
	public static final String STORE_TEMPLET = "storeAndProject";
	public static final String STORE_CONVERTER = "storeAndProjectConverter";
	public static final String STORE_TEMPLATE = "storeAndProject.xlsx";
	
	public static final String updateHousePrice = "updateHousePrice";
	public static final String updateHousePrice_DISPLAY = "修改房源价格";
	public static final String updateHousePrice_TEMPLET = "updateHousePrice";
	public static final String updateHousePrice_CONVERTER = "updateHousePriceConverter";
	public static final String updateHousePrice_TEMPLATE = "updateHousePrice.xlsx";
	
	static {
		defines.put(HOUSE, HOUSE_CONVERTER);
		display.put(HOUSE, HOUSE_DISPLAY);
		templet.put(HOUSE, HOUSE_TEMPLET);
		excel_templet.put(HOUSE, HOUSE_TEMPLATE);
		
		defines.put(STORE, STORE_CONVERTER);
		display.put(STORE, STORE_DISPLAY);
		templet.put(STORE, STORE_TEMPLET);
		excel_templet.put(STORE, STORE_TEMPLATE);
		
		//修改房源价格
		defines.put(updateHousePrice, updateHousePrice_CONVERTER);
		display.put(updateHousePrice, updateHousePrice_DISPLAY);
		templet.put(updateHousePrice, updateHousePrice_TEMPLET);
		excel_templet.put(updateHousePrice, updateHousePrice_TEMPLATE);
	}

	public static String getConvertBeanDefine(String data_type) {
		return defines.get(data_type);
	}

	public static String getConvertBeanDisplay(String data_type) {
		return display.get(data_type);
	}
	public static String getTemplet(String data_type) {
		return templet.get(data_type);
	}
	public static String getExcelTemplet(String data_type) {
		return excel_templet.get(data_type);
	}
}

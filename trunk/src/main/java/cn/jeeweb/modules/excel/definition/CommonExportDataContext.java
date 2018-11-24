package cn.jeeweb.modules.excel.definition;

import java.util.HashMap;
import java.util.Map;

import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.excel.constants.ExcelColumArrayFormatter;

/**
 * 序号|对象属性|中文名|属性类型|类型|是否采用默认样式|合并行|合并列|是否锁定
 * 
 * @return
 */
public class CommonExportDataContext {
	
	static Map<String, Object> exportData = new HashMap<String, Object>();
	static Map<String, String> exportName = new HashMap<String, String>();
	
	static {
      exportData.put(ExcelColumArrayFormatter.ORDER,ExcelColumArrayFormatter.excelExportOrder());
	  exportName.put(ExcelColumArrayFormatter.ORDER,ExcelColumArrayFormatter.ORDER_NAME);
	  exportData.put(ExcelColumArrayFormatter.SIGN,ExcelColumArrayFormatter.excelExportSign());
	  exportName.put(ExcelColumArrayFormatter.SIGN,ExcelColumArrayFormatter.SIGN_NAME);
	  exportData.put(ExcelColumArrayFormatter.DISTRIBUTION,ExcelColumArrayFormatter.excelExportDistribution());
	  exportName.put(ExcelColumArrayFormatter.DISTRIBUTION,ExcelColumArrayFormatter.DISTRIBUTION_NAME);
	  exportData.put(ExcelColumArrayFormatter.CUSTOMERDETAILS,ExcelColumArrayFormatter.excelExportCustomerdetails());
	  exportName.put(ExcelColumArrayFormatter.CUSTOMERDETAILS,ExcelColumArrayFormatter.CUSTOMERDETAILS_NAME);
	  exportData.put(ExcelColumArrayFormatter.PROJECTREPORT,ExcelColumArrayFormatter.excelExportProjectReport());
	  exportName.put(ExcelColumArrayFormatter.PROJECTREPORT,ExcelColumArrayFormatter.PROJECTREPORT_NAME);
	  exportData.put(ExcelColumArrayFormatter.SERVERREPORT,ExcelColumArrayFormatter.excelExportServerReport());
	  exportName.put(ExcelColumArrayFormatter.SERVERREPORT,ExcelColumArrayFormatter.SERVERREPORT_NAME);
	  exportData.put(ExcelColumArrayFormatter.STOREREPORT,ExcelColumArrayFormatter.excelExportStoreReport());
	  exportName.put(ExcelColumArrayFormatter.STOREREPORT,ExcelColumArrayFormatter.STOREREPORT_NAME);
	  exportData.put(ExcelColumArrayFormatter.PROJECTSERVEREPORT,ExcelColumArrayFormatter.excelExportProjectServeReport());
	  exportName.put(ExcelColumArrayFormatter.PROJECTSERVEREPORT,ExcelColumArrayFormatter.PROJECTSERVEREPORT_NAME);
	  exportData.put(ExcelColumArrayFormatter.PROJECTSTOREREPORT,ExcelColumArrayFormatter.excelExportProjectStoreReport());
	  exportName.put(ExcelColumArrayFormatter.PROJECTSTOREREPORT,ExcelColumArrayFormatter.PROJECTSTOREREPORT_NAME);
	  exportData.put(ExcelColumArrayFormatter.PROJECTSERVESTOREREPORT,ExcelColumArrayFormatter.excelExportProjectserveStoreReport());
	  exportName.put(ExcelColumArrayFormatter.PROJECTSERVESTOREREPORT,ExcelColumArrayFormatter.PROJECTSERVESTOREREPORT_NAME);
	  exportData.put(ExcelColumArrayFormatter.SERVESTOREREPORT,ExcelColumArrayFormatter.excelExportServeStoreReport());
	  exportName.put(ExcelColumArrayFormatter.SERVESTOREREPORT,ExcelColumArrayFormatter.SERVESTOREREPORT_NAME);
	}
	
	
	public static String[][] excelExport(String dataType) {
		return getConvertBean(dataType);
	}

	public static String[][] getConvertBean(String dataType) {
		return (String[][])exportData.get(dataType);
	}
	
	public static String getConvertName(String dataType) {
		return exportName.get(dataType);
	}
	
	public static boolean checkDataType(String dataType){
		boolean flag = true;
		String[][] data = getConvertBean(dataType);
		String name = getConvertName(dataType);
		if (StringUtils.isEmpty(name)) {
			flag = false;
		}
		if (data==null || data.length==0) {
			flag = false;
		}
		return flag;
	}

}

package cn.jeeweb.modules.excel.constants;

import cn.jeeweb.modules.excel.DataType;
/**
 * excel数组格式
 * @author Administrator
 *
 */
public class ExcelColumArrayFormatter {
	/**
	 * 导出参数类型
	 */
	public static final String DISTRIBUTION = "distribution";
	public static final String DISTRIBUTION_NAME = "分销公司列表";
	
	public static final String ORDER = "order";
	public static final String ORDER_NAME = "订单列表";
	
	public static final String SIGN = "sign";
	public static final String SIGN_NAME = "签约楼盘列表";
	
	public static final String CUSTOMERDETAILS="customerdetails";
	public static final String CUSTOMERDETAILS_NAME = "成交客户明细报表";
	
	public static final String PROJECTREPORT="projectreport";
	public static final String PROJECTREPORT_NAME = "楼盘报表";
	
	public static final String SERVERREPORT="serverreport";
	public static final String SERVERREPORT_NAME = "经服报表";
	
	public static final String STOREREPORT="storereport";
	public static final String STOREREPORT_NAME = "分销报表";
	
	public static final String PROJECTSERVEREPORT="projectservereport";
	public static final String PROJECTSERVEREPORT_NAME = "楼盘经服报表";
	
	public static final String PROJECTSTOREREPORT="projectstorereport";
	public static final String PROJECTSTOREREPORT_NAME = "楼盘门店报表";
	
	public static final String PROJECTSERVESTOREREPORT="projectservestorereport";
	public static final String PROJECTSERVESTOREREPORT_NAME = "楼盘经服门店报表";
	
	public static final String SERVESTOREREPORT="servestorereport";
	public static final String SERVESTOREREPORT_NAME = "经服门店报表";
	/**
	 * excel 导出模板
	 * 自定义 序号|对象属性|中文名|属性类型|类型|是否采用默认样式|合并行|合并列|是否锁定
	 * @return
	 */
	public static String[][] excelExportOrder() {
		String[][] excelColumArray = { 
				{ "0", "", "订单", "", "", "true", "0", "8" },
				{ "0", "projectName", "项目名称", "", "", "true" },{ "1", "dealStatus", "交易状态", "", "", "true" }, 
				{ "2", "verify", "审核状态", "", "", "true" },{ "3", "appname", "经纪人姓名", "", "", "true" },
				{ "4", "appPhone", "经纪人电话", "", "", "true" },{ "5", "storeName", "分销名称", "", "", "true"}, 
				{ "6", "uname", "经服姓名", "", "", "true" },{ "7", "clientName", "客户姓名", "", "", "true" },  
				{ "8", "fullContacto", "客户联系方式", "", "", "true" },{ "9", "createDate", "报备时间", DataType.DATA_TYPE_DATE, "", "true" },  
				{ "10", "boardingPlane", "预计上客时间",DataType.DATA_TYPE_DATE, "", "true" },{ "11", "scanMan", "销售姓名", "", "", "true" },
				{ "12", "boardingTime", "上客时间", DataType.DATA_TYPE_DATE, "", "true" },{ "13", "dealPlane", "发起成交时间", DataType.DATA_TYPE_DATE, "", "true" },
				{ "14", "dealTime", "确认成交时间", DataType.DATA_TYPE_DATE, "", "true" },{ "15", "departureCity", "出发城市","", "", "true" },  
				{ "16", "partPersonNum", "出行人数", "", "", "true" },{ "17", "partWay", "出行方式","", "", "true" },
				{ "18", "lunchNum", "提供午餐人数", "", "", "true" },{ "19", "remarks", "拒绝原因","", "", "true" },
				{ "20", "transferFlag", "是否已流转成交客户","", "", "true" }
		};
		return excelColumArray;
	}
	public static String[][] excelExportSign() {
		String[][] excelColumArray = { 
				{ "0", "", "签约", "", "", "true", "0", "8" },
				{ "0", "companyName", "公司名称", "", "", "true" },{ "1", "projectName", "签约楼盘名称", "", "", "true" }, 
				{ "2", "distributionCompanyName", "分销名称", "", "", "true" },{ "3", "serverName", "签约经服", "", "", "true" },
				{ "4", "cityName", "城市", "", "", "true" },{ "5", "validityTimeStart", "有效期开始", DataType.DATA_TYPE_DATE, "", "true"}, 
				{ "6", "validityTimeEnd", "有效期结束",DataType.DATA_TYPE_DATE, "", "true" },{ "7", "status", "签约审核状态", "", "", "true" },  
				{ "8", "signStatus", "签约状态", "", "", "true" },{ "9", "signWarn", "续签提醒","", "", "true" },  
				{ "10", "remarks", "备注", "", "", "true" }
		};
		return excelColumArray;
	}
	public static String[][] excelExportDistribution() {
		String[][] excelColumArray = { 
				{ "0","","分销", "", "", "true", "0", "8" },
				{ "0", "companyName", "公司名称", "", "", "true" },{ "1", "name", "分销名称", "", "", "true" },
				{ "2", "addr", "公司地址", "", "", "true" },{ "3", "dutyName", "分销负责人", "", "", "true" },
				{ "4", "telphone", "联系方式", "", "", "true" },{ "5", "cityName", "所属城市", "", "", "true"},
				{ "6", "totalPeople", "分销中人数","", "", "true" },{ "7", "storeTypes", "分销类型", "", "", "true" },
				{ "8", "storeCode", "分销编码", "", "", "true" },{ "9", "num", "合作人数","", "", "true" }
		};
		return excelColumArray;
	}
	public static String[][] excelExportCustomerdetails() {
		String[][] excelColumArray = { 
				{ "0","","成交客户明细", "", "", "true", "0", "8" },
				{ "0", "project.name", "楼盘", "", "", "true" },{ "1", "customerName", "客户姓名", "", "", "true" },
				{ "2", "idCard", "身份证号码", "", "", "true" },{ "3", "phone", "电话", "", "", "true" },
				{ "4", "cardAddr", "身份证地址", "", "", "true" },{ "5", "financingTime", "认筹日期", DataType.DATA_TYPE_DATE, "", "true"},
				{ "6", "buyTime", "认购日期",DataType.DATA_TYPE_DATE, "", "true" },{ "7", "estimateSignTime", "预计签约日期", DataType.DATA_TYPE_DATE, "", "true" },
				{ "8", "signTime", "签约日期",DataType.DATA_TYPE_DATE, "", "true" },{ "9", "houseNo", "房号","", "", "true" },
				{ "10", "parkingNo", "车位号", "", "", "true" },{ "11", "area", "面积","", "", "true" },
				{ "12", "buyUnivalentPrice", "认购单价", "", "", "true" },{ "13", "buyTotalPrice", "认购总价","", "", "true" },
				{ "14", "agreementUnivalentPrice", "合同单价", "", "", "true" },{ "15", "agreementTotalPrice", "合同总价","", "", "true" },
				{ "16", "payType", "付款方式", "", "", "true" },{ "17", "loanBankName", "按揭银行","", "", "true" },
				{ "18", "downPaymentsProportion", "首付比例", "", "", "true" },{ "19", "downPaymentsFee", "首付金额","", "", "true" },
				{ "20", "payTypeFee", "贷款金额", "", "", "true" },{ "21", "grantTime", "放贷日期",DataType.DATA_TYPE_DATE, "", "true" },
				{ "22", "serviceFee", "服务费", "", "", "true" },{ "23", "buyFee", "定金","", "", "true" },
				{ "24", "examineStatus", "定单状态", "", "", "true" },{ "25", "customerType", "客户类型","", "", "true" },
				{ "26", "sourceType", "成交端口", "", "", "true" },{ "27", "companyName", "渠道公司","", "", "true" },
				{ "28", "server.realname", "经服", "", "", "true" },{ "29", "adviser.realname", "案场签单","", "", "true" },
				{ "30", "negotiation.realname", "案场谈判", "", "", "true" },{ "31", "favouredPolicy", "优惠政策","", "", "true" },
				{ "32", "customerReward", "客户奖品", "", "", "true" }
		};
		return excelColumArray;
	}
	public static String[][] excelExportProjectReport() {
		String[][] excelColumArray = { 
				{ "0","","楼盘报表", "", "", "true", "0", "8" },
				{ "0", "project.name", "楼盘", "", "", "true" },{ "1", "signStoreNum", "签约分销数", "", "", "true" },
				{ "2", "reportNum", "报备数", "", "", "true" },{ "3", "boardingNum", "上客数", "", "", "true" },
				{ "4", "dealNum", "成交数", "", "", "true" }
		};
		return excelColumArray;
	}
	public static String[][] excelExportServerReport() {
		String[][] excelColumArray = { 
				{ "0","","经服报表", "", "", "true", "0", "8" },
				{ "0", "org.name", "经服所属部门", "", "", "true" },{ "1", "user.realname", "经服", "", "", "true" },
				{ "2", "signStoreNum", "签约分销数", "", "", "true" },{ "3", "inStoreClockNum", "到店打卡次数", "", "", "true" },
				{ "4", "followNum", "跟进数", "", "", "true" },{ "5", "addStoreNum", "新增分销数", "", "", "true" },
				{ "6", "reportNum", "报备数", "", "", "true" },{ "7", "boardingNum", "上客数", "", "", "true" },
				{ "8", "dealNum", "成交数", "", "", "true" }
		};
		return excelColumArray;
	}
	public static String[][] excelExportStoreReport() {
		String[][] excelColumArray = { 
				{ "0","","分销报表", "", "", "true", "0", "8" },
				{ "0", "company.name", "总代分销", "", "", "true" },{ "1", "reportNum", "报备数", "", "", "true" },
				{ "2", "boardingNum", "上客数", "", "", "true" },{ "3", "dealNum", "成交数", "", "", "true" },
				{ "4", "realtorNum", "合作经纪人数", "", "", "true" }
		};
		return excelColumArray;
	}
	public static String[][] excelExportProjectServeReport() {
		String[][] excelColumArray = { 
				{ "0","","楼盘经服报表", "", "", "true", "0", "8" },
				{ "0", "project.name", "楼盘", "", "", "true" },{ "1", "server.realname", "经服", "", "", "true" },
				{ "2", "signStoreNum", "签约门店数", "", "", "true" },{ "3", "reportNum", "报备数", "", "", "true" },
				{ "4", "boardingNum", "上客数", "", "", "true" },{ "5", "dealNum", "成交数", "", "", "true" }
		};
		return excelColumArray;
	}
	public static String[][] excelExportProjectStoreReport() {
		String[][] excelColumArray = { 
				{ "0","","楼盘门店报表", "", "", "true", "0", "8" },
				{ "0", "project.name", "楼盘", "", "", "true" },{ "1", "company.name", "分销公司", "", "", "true" },
				{ "2", "reportNum", "报备数", "", "", "true" },{ "3", "boardingNum", "上客数", "", "", "true" },
				{ "4", "dealNum", "成交数", "", "", "true" }
		};
		return excelColumArray;
	}
	public static String[][] excelExportProjectserveStoreReport() {
		String[][] excelColumArray = { 
				{ "0","","楼盘经服门店报表", "", "", "true", "0", "8" },
				{ "0", "project.name", "楼盘", "", "", "true" },{ "1", "company.name", "门店", "", "", "true" },
				{ "2", "server.realname", "经服", "", "", "true" },{ "3", "reportNum", "报备数", "", "", "true" },
				{ "4", "boardingNum", "上客数", "", "", "true" },{ "5", "dealNum", "成交数", "", "", "true" }
		};
		return excelColumArray;
	}
	public static String[][] excelExportServeStoreReport() {
		String[][] excelColumArray = { 
				{ "0","","经服门店报表", "", "", "true", "0", "8" },
				{ "0", "org.name", "经服所属部门", "", "", "true" },{ "1", "server.realname", "经服", "", "", "true" },
				{ "2", "company.name", "分销公司", "", "", "true" },{ "3", "reportNum", "报备数", "", "", "true" },
				{ "4", "boardingNum", "上客数", "", "", "true" },{ "5", "dealNum", "成交数", "", "", "true" },
				{ "6", "realtorNum", "合作经纪人数", "", "", "true" }
		};
		return excelColumArray;
	}
}

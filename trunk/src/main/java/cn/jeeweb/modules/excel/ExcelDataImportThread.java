package cn.jeeweb.modules.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jeeweb.core.utils.FileUtil;
import cn.jeeweb.core.utils.SpringContextHolder;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.excel.validate.ValidateResult;

/**
 *
 * @Description:导入类（导入以及转换逻辑基本都放在这里）
 * @author zhangyouwei1988@sina.cn
 * @date: 2016-5-5 下午1:23:41
 */
public class ExcelDataImportThread {

	public final static Logger logger = LoggerFactory.getLogger(ExcelDataImportThread.class);

	private int firstDataRow; // 第一行数据的行数
	private File uploadFile; // 导入文件
	private String dataType; // bean类型根据类型获取转换类
	private DataConvert converter; // 数据转换类
	private FileInputStream file;
	private int totalRow; // 表格中的全部数据数量
	private String[][] totalDatas; // 导入表格的所有数据
	private String[][] beanDatas; // 对应表字段的数据
	private int importDataNum; // 导入数据的总数量(开始行算起)
	private Integer nullRowNum = 0; // 空行的数量
	private Integer succNum = 0; // 成功导入数量
	private String importNo; // 成功导入数量
	private Integer insertNum = 0; // 成功插入行数
	private Integer updateNum = 0; // 成功更新行数

	private int headerRowNo; // 表头数据行号

	/** 导入功能预留标示符 */
	private String moduleOne;
	/** 导入功能预留标示符 */
	private String moduleTow;
	/** 导入功能预留标示符 */
	private String moduleThree;

	public ExcelDataImportThread(int firstDataRow, File uploadFile, String dataType, String[][] beanDatas,
			String importNo, int headerRowNo, String moduleOne, String moduleTow, String moduleThree) throws Exception {
		this.firstDataRow = firstDataRow;
		this.headerRowNo = headerRowNo;
		this.uploadFile = uploadFile;
		this.dataType = dataType;
		this.importNo = importNo;
		this.beanDatas = beanDatas;
		this.moduleOne = moduleOne;
		this.moduleTow = moduleTow;
		this.moduleThree = moduleThree;
		init();

	}

	/**
	 * 初始化
	 *
	 * @throws Exception
	 */
	private void init() throws Exception {

		clearCommonData();
		validateFileType();
		file = new FileInputStream(uploadFile);

		totalDatas = ExcelReaderUtil.getExcelDate(uploadFile);
	}

	private void validateFileType() {
		if (!FileUtil.isExceFile(uploadFile)) {
			throw new RuntimeException("上传的文件不是excel文件，请核对！");
		}
	}

	private void clearCommonData() {
		ExcelImportHelper.succNum.remove();
		ExcelImportHelper.importDataNum.remove();
		ExcelImportHelper.nullRowNum.remove();
		ExcelImportHelper.realRow.remove();
		ExcelImportHelper.insertNum.remove();
		ExcelImportHelper.updateNum.remove();
		ExcelImportHelper.getErrorMsgList().clear();
		ExcelImportHelper.getPkList().clear();
		ExcelImportHelper.getErrotDataList().clear();
		ExcelImportHelper.getErrorFormatMsgList().clear();
		clearModules();

	}

	public void runImpDatas() {
		// 验证模板正确性
		if (!validateHeader()) {
			return;
		}
		// 获取转服类
		converter = getDataConvert(dataType);
		// 验证转换类
		if (isConverterNull()) {
			return;
		}

		// 处理数据
		proccessExcelDatas();

	}

	/**
	 *
	 * @Description:验证表头【确定模板是否正确】
	 * @param
	 * @return
	 */
	private boolean validateHeader() {
		String[] isPropertyNulls = beanDatas[2];
		// 防止数组下标异常
		int length = totalDatas.length > isPropertyNulls.length ? isPropertyNulls.length : totalDatas.length;
		if (headerRowNo > length) {
			this.logger.info("此导入的模板配置不匹配!");
			ExcelImportHelper.addErrorMsg("此导入的模板不正确请选择正确模板重新导入");
			return false;
		}
		for (int i = 0; i < length; i++) {
			if (StringUtils.isStringAvaliable(isPropertyNulls[i])) {
				if (!isPropertyNulls[i].equals(totalDatas[headerRowNo][i])) {
					this.logger
							.info("此导入的模板配置不匹配:" + isPropertyNulls[i] + "<<<<------>>>" + totalDatas[headerRowNo][i]);
					ExcelImportHelper.addErrorMsg("此导入的模板不正确请选择正确模板重新导入");
					return false;
				}
			}

		}
		return true;
	}

	/**
	 * 处理数据
	 */
	private void proccessExcelDatas() {

		importDataNum = totalDatas.length - firstDataRow;

		ExcelImportHelper.importDataNum.set(importDataNum);
		ExcelImportHelper.succNum.set(succNum);
		ExcelImportHelper.nullRowNum.set(nullRowNum);
		ExcelImportHelper.insertNum.set(insertNum);
		ExcelImportHelper.updateNum.set(updateNum);
		ExcelImportHelper.setPkList(new ArrayList<String>());
		handleDomains(totalDatas);
	}

	private void handleDomains(String[][] datas) {
		createModules();
		System.out.println("处理一条数据的开始时间：===================================="+System.currentTimeMillis());
		handleData(datas);
		System.out.println("处理一条数据的结束时间：===================================="+System.currentTimeMillis());
	}

	/**
	 * 导入预留字段设置
	 */
	private void createModules() {

		if (StringUtils.isStringAvaliable(moduleOne)) {
			ThreadVariable.setModuleOne(moduleOne);
		}
		if (StringUtils.isStringAvaliable(moduleTow)) {
			ThreadVariable.setModuleTow(moduleTow);
		}
		if (StringUtils.isStringAvaliable(moduleThree)) {
			ThreadVariable.setModuleThree(moduleThree);
		}

	}

	/**
	 * 导入预留字段设置
	 */
	private void clearModules() {

		ThreadVariable.setModuleOne(null);
		ThreadVariable.setModuleTow(null);
		ThreadVariable.setModuleThree(null);

	}

	private void handleData(String[][] dataTemp) {

		for (int rowIndex = 0; rowIndex < importDataNum; rowIndex++) {
			ValidateResult result = new ValidateResult();
			String[] rowData = totalDatas[rowIndex + firstDataRow];
			ExcelImportHelper.realRow.set(rowIndex + 1);
			if (!isBlankRow(rowData)) {
				ExcelImportHelper.succNum.set(ExcelImportHelper.succNum.get() + 1);
				handleValidData(rowData, result, rowIndex);
			} else {
				ExcelImportHelper.nullRowNum.set(ExcelImportHelper.nullRowNum.get() + 1);
				ExcelImportHelper.importDataNum.set(ExcelImportHelper.importDataNum.get() - 1);
				// handleNullData(rowData);
			}
		}
	}

	// 处理空数据
	private void handleNullData(String[] rowData) {
		// synchronized (ExcelDatasHandleThread.class) {
		nullRowNum++;
		// }
	}

	// 处理有效数据
	private void handleValidData(String[] rowData, ValidateResult result, int rowIndex) {
		Object domain = null;
		domain = converter.convertToDomain(rowData, result, beanDatas);
		if (result.getMapMessages().size() > 0) {
			ExcelImportHelper.addErrorMsg("第" + (rowIndex+firstDataRow + 1) + "行数据错误!" + result.getMapErrorMessages());
			// 保存格式错误信息
			/**
			 * if(result.getMapErrorMessages()!=null){
			 * ExcelImportHelper.addErrorFormatMsgList("第" + (rowIndex + 1) +
			 * "行数据错误!"+result.getMapErrorMessages()); }
			 **/
			// 成功的减一
			ExcelImportHelper.succNum.set(ExcelImportHelper.succNum.get() - 1);
			logger.error("第" + (rowIndex + 1) + "行数据错误!");
			logger.error(result.getMapErrorMessages());
			validateConvertData(result, rowData, domain, rowIndex);
		} else {
			// 处理数据时需要同步防止导入同步数据
			validateRowData(result, domain, rowIndex, rowData);
		}

	}

	// 数据验证
	private void validateRowData(ValidateResult result, Object domain, int rowIndex, String[] rowData) {
		ValidateResult vresult = null;
		try {
			// fateson add 在验证是否是 户籍还是流动人口的时候用
			vresult = converter.validate(domain, rowIndex+ firstDataRow + 1, beanDatas);
		} catch (Exception e) {
			logger.error("处理数据时发生错误！", e);
		}
		if (vresult == null || ("".equals(vresult.getMapErrorMessages()) && vresult.getMessages().size() < 1)) {
			if (handleCurrectData(domain) == null) {

				result.addErrorMessage(rowIndex + firstDataRow, "数据保存数据库时失败，请检查数据正确性！");
				handleErrorData(vresult, rowData);
				// 成功的减一
				ExcelImportHelper.succNum.set(ExcelImportHelper.succNum.get() - 1);
				ExcelImportHelper.addErrorMsg("第" + (rowIndex + 1) + "行数据保存数据库时失败!");
			}
		} else {
			result.addAllErrorMessage(vresult);
			logger.error(result.getErrorMessages());
			ExcelImportHelper.addErrorMsg(result.getErrorMessages());
			handleErrorData(result, rowData);
			// 成功的减一
			ExcelImportHelper.succNum.set(ExcelImportHelper.succNum.get() - 1);
		}
	}

	// TODO 处理错误数据
	private void handleErrorData(ValidateResult vresult, String[] rowData) {
		List listDate = ExcelImportHelper.getErrotDataList();
		if (listDate != null || listDate.size() > 0) {
			listDate.add(rowData);
		} else {
			listDate = new ArrayList();
			ExcelImportHelper.addErrotDataList(rowData);
		}
	}

	// 处理正确数据
	private Object handleCurrectData(Object domain) {
		// TODO 判断是新增还是修改待实现
		if (!converter.isRepeatData(domain)) {

			// 新增
			domain = converter.persistenceDomain(domain);
		} else {
			// 修改
			domain = converter.updateDomain(domain);
		}
		return domain;
	}

	private void validateConvertData(ValidateResult result, String[] rowData, Object domain, int rowIndex) {
		ValidateResult vresult = null;
		try {
			vresult = converter.validate(domain, rowIndex + 1, beanDatas);
		} catch (Exception e) {
			logger.error("处理数据时发生错误！");
		}
		if (vresult != null && StringUtils.isStringAvaliable(vresult.getErrorMessages())) {
			result.getMapMessages().putAll(vresult.getMapMessages());
		}
		handleErrorData(result, rowData);

	}

	private static boolean isBlankRow(String[] rowData) {
		if (rowData == null)
			return true;
		for (String data : rowData) {
			if (StringUtils.isStringAvaliable(data)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断转换类是否存在
	 *
	 * @return
	 */
	private boolean isConverterNull() {
		if (converter == null) {
			return true;
		}
		return false;
	}

	/**
	 * 获取转换类
	 *
	 * @param dataType
	 * @return
	 */
	private DataConvert getDataConvert(String dataType) {
		return (DataConvert) SpringContextHolder.getBean(DataConvertDefine.getConvertBeanDefine(dataType));
	}
}

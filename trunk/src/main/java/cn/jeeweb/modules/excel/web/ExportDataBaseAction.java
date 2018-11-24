package cn.jeeweb.modules.excel.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import cn.jeeweb.core.utils.FileUtil;
import cn.jeeweb.core.utils.JsonUtils;
import cn.jeeweb.modules.excel.definition.CommonExportDataContext;
import cn.jeeweb.modules.excel.util.ExportTips;
/**
 * 
 * @Description:导出数据基础action
 * @author zhangyouwei1988@sina.cn
 * @date: 2016-5-20 上午11:35:46
 */
public class ExportDataBaseAction {

	public static void downloadData(String fileName, String dataType,HttpServletResponse response)
			throws IOException {
		InputStream inputStream = new FileInputStream(FileUtil.getExcelTemporaryPath() + File.separator + fileName + ".xls");

		String downloadFileName = new String(CommonExportDataContext.getConvertName(dataType).getBytes("gbk"),"ISO8859-1")+ExportTips.getReportDate()+ ".xls";

		OutputStream outputStream = response.getOutputStream();
		response.setContentType("application/vnd.ms-excel;charset=GBK");
		response.addHeader("Content-Disposition",
				"attachment; filename=" + downloadFileName);
		int read;
		while ((read = inputStream.read()) != -1) {
			outputStream.write(read);
		}

	}
	
	public static void downloadQuotationExcel(String fileName, String excelName, HttpServletResponse response)
			throws IOException {
		InputStream inputStream = new FileInputStream(FileUtil.getExcelTemplatePath() + File.separator + fileName + ".xls");

		String downloadFileName = new String(excelName.getBytes("gbk"),"ISO8859-1")+ ".xls";

		OutputStream outputStream = response.getOutputStream();
		response.setContentType("application/vnd.ms-excel;charset=GBK");
		response.addHeader("Content-Disposition",
				"attachment; filename=" + downloadFileName);
		int read;
		while ((read = inputStream.read()) != -1) {
			outputStream.write(read);
		}

	}

	public static void downloadSalesFeedbackData(String filePath, String fileName, HttpServletResponse response)
			throws IOException {
		InputStream inputStream = new FileInputStream(filePath + fileName
				+ ".zip");
		String downloadFileName = new String(fileName.getBytes("gbk"),
				"ISO8859-1") + ".zip";
		OutputStream outputStream = response.getOutputStream();
		response.setContentType("application/vnd.ms-excel;charset=GBK");
		response.addHeader("Content-Disposition",
				"attachment; filename=" + downloadFileName);
		int read;
		while ((read = inputStream.read()) != -1) {
			outputStream.write(read);
		}
	}

	public static void downloadData(String fileName, String dataType,
			String excelName, HttpServletResponse response) throws IOException {
		InputStream inputStream = new FileInputStream(FileUtil.getExcelTemplatePath() + File.separator + fileName + ".xls");

		String downloadFileName = new String(CommonExportDataContext.getConvertName(dataType).getBytes("UTF-8"),"ISO8859-1")+ ".xls";

		OutputStream outputStream = response.getOutputStream();
		response.setContentType("application/vnd.ms-excel;charset=GBK");
		response.addHeader("Content-Disposition",
				"attachment; filename=" + downloadFileName);
		int read;
		while ((read = inputStream.read()) != -1) {
			outputStream.write(read);
		}

	}

}

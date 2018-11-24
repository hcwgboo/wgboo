package cn.jeeweb.modules.excel;

import java.io.File;

import cn.jeeweb.core.utils.FileUtil;

/**
 * 
 * @Description:Excel 读取工具类
 * @author zhangyouwei1988@sina.cn
 * @date: 2017-9-11 上午9:57:52
 */
public class ExcelReaderUtil {

	public static String[][] getExcelDate(File uploadFile) {
		String[][] totalDatas = null;
		if (FileUtil.isExceFile(uploadFile)) {
			if (FileUtil.isExcel2007(uploadFile)) {
				totalDatas = Excel2007Reader.readFile(uploadFile);
			} else {
				// ExcelData excelDatas = ExcelReader.readFile(is);
				// totalDatas = excelDatas.getSheetDatas(ExcelData.FIRST);
				totalDatas = Excel2003Reader.readFile(uploadFile);
			}
		}
		return totalDatas;
	}
}

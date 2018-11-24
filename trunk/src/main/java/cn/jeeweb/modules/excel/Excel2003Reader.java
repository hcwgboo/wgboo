package cn.jeeweb.modules.excel;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;

import cn.jeeweb.core.utils.FileUtil;

/**
 * excel2003版本解析
 * 
 * @author hp
 * 
 */
public class Excel2003Reader {

	private static final Log logger = LogFactory.getLog(Excel2003Reader.class);

	public static String[][] readFile(File file) {
		String[][] totalDatas = null;
		if (FileUtil.isExcel2003(file)) {
			// 构造 HSSFWorkbook对象，传入文件路径
			HSSFWorkbook hwb;
			HSSFFormulaEvaluator evaluator;
			try {
				hwb = new HSSFWorkbook(new FileInputStream(file));
				evaluator = new HSSFFormulaEvaluator(hwb);
				// 读取第一章表格内容
				HSSFSheet sheet = hwb.getSheetAt(0);
				// 定义 row、cell
				HSSFRow row;
				String cell = "";
				HSSFCell hssfCell;
				String[] datas = null;
				// 循环输出表格中的内容
				List<String[]> datasList = new ArrayList<String[]>();
				// 第二维的长度
				int twoDimension = 0;
				for (int i = 0; i <= sheet.getLastRowNum(); i++) {
					row = sheet.getRow(i);
					datas = null;
					if (row != null) {
						datas = new String[row.getLastCellNum()];
						for (int j = 0; j < row.getLastCellNum(); j++) {
							// 通过 row.getCell(j).toString() 获取单元格内容，
							cell = "";
							hssfCell = row.getCell(j);
							if (hssfCell != null) {
								switch (hssfCell.getCellType()) {

								case HSSFCell.CELL_TYPE_NUMERIC: // 数字
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
									DecimalFormat df = new DecimalFormat("0.####");
									// NumberFormat nf =
									// NumberFormat.getInstance();
									// nf.setGroupingUsed(false);

									// 判断是否为Excel2003内置的日期格式数据
									if (DateUtil.isCellDateFormatted(hssfCell)) {
										cell = sdf.format(hssfCell.getDateCellValue()); // 日期型
									} else {
										cell = df.format(hssfCell.getNumericCellValue()); // 数字
										if (cell == null) {
											cell = "";
										} else {
											cell = cell.trim();
										}
									}
									break;
								case HSSFCell.CELL_TYPE_STRING: // 字符串
									cell = hssfCell.getStringCellValue();
									if (cell == null)
										cell = "";
									else
										cell = cell.trim();
									break;

								case HSSFCell.CELL_TYPE_BLANK: // 空值
									cell = "";
									break;
								case HSSFCell.CELL_TYPE_FORMULA:
									CellValue tempCellValue = evaluator.evaluate(hssfCell);
									if (tempCellValue.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
										SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
										DecimalFormat df1 = new DecimalFormat("0.####");
										if (DateUtil.isCellDateFormatted(hssfCell)) {
											cell = sdf1.format(hssfCell.getDateCellValue()); // 日期型
										} else {
											cell = df1.format(hssfCell.getNumericCellValue()); // 数字
											if (cell == null) {
												cell = "";
											} else {
												cell = cell.trim();
											}
										}
										break;
									} else if (tempCellValue.getCellType() == XSSFCell.CELL_TYPE_STRING) {
										cell = hssfCell.getStringCellValue();
										if (cell == null)
											cell = "";
										else
											cell = cell.trim();
										break;
									} else if (tempCellValue.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
										cell = "";
										break;
									}

								}

							} else {
								cell = "";
							}
							datas[j] = cell;
							if (j > twoDimension) {
								twoDimension = j;
							}

						}
					}
					datasList.add(i, datas);
				}

				if (datasList != null && datasList.size() > 0) {
					totalDatas = new String[datasList.size()][twoDimension + 1];
					for (int i = 0; i < totalDatas.length; i++) {
						totalDatas[i] = datasList.get(i);
					}
				}

			} catch (Exception e) {
				logger.error("读取2003版excel出错，错误信息：", e);
				throw new RuntimeException("读取excel表格出错！");
			}
		}

		return totalDatas;
	}
}

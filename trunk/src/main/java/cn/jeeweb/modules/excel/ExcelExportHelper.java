package cn.jeeweb.modules.excel;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.ognl.Ognl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jeeweb.core.utils.CalendarUtil;
import cn.jeeweb.modules.excel.constants.StatisticsBankAccountConstants;
import cn.jeeweb.modules.excel.util.ExportTips;
import cn.jeeweb.modules.excel.util.StringUtil;


public class ExcelExportHelper {

	public final static Logger logger = LoggerFactory
			.getLogger(ExcelExportHelper.class);

	public static InputStream exportDateToExcel(String[][] excelDefines,
			List objectList, boolean isFilling) throws Exception {
		ExcelWriter writer = constructExcelWriter();
		writer.addWorkSheet(excelDefines[0][2]);
		writeHeaderToExcel(writer, excelDefines, objectList);
		writeDateToExcel(writer, excelDefines, objectList, isFilling);
		return new java.io.FileInputStream(writer.getExcelFile(UUID
				.randomUUID() + ".xls"));
	}

	/**
	 * 写头信息
	 * 
	 * @param writer
	 * @param helper
	 * @param excelColumArray
	 * @param objList
	 *            @
	 */
	public static void writeHeaderToExcel(ExcelWriter writer,
			String[][] excelColumArray, List objList) {
		int startRow = 0;
		int endRow = 0;
		int k = 1;
		boolean  isAdd =true;
		try {
			for (int j = 0; j < excelColumArray.length; j++) {
				if (j == 0) {// 写标题
					if(!(excelColumArray[j].length == 9 && excelColumArray[j][8].equals("true"))){
						writeTitle(writer, excelColumArray, j);
						isAdd =false;
						}
				} else {// 写头
					if (StringUtils.isEmpty(excelColumArray[j][1])) { // 写跨列头标题
						k++;
						startRow = Integer.parseInt(excelColumArray[j][0]);
						endRow = Integer.parseInt(excelColumArray[j][7]);
						writeIsMergerHeaderTitle(writer, excelColumArray, j,isAdd);
					} else { // 普通头信息
						if (startRow > 0
								&& (j - k >= startRow && j - k <= endRow)) {// 写跨列头信息
							writeIsMergerHeader(writer, excelColumArray, j,isAdd);
						} else {
							writeHeader(writer, excelColumArray, j,isAdd);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("类ExcelExportHelper的writeHeaderToExcel方法出现异常，原因：",
					"向excel模板写头信息出现错误", e);
		}
	}

	/**
	 * 写标题
	 * 
	 * @param writer
	 * @param excelColumArray
	 * @param j
	 */
	private static void writeTitle(ExcelWriter writer,
			String[][] excelColumArray, int j) {
		if ("true".equals(excelColumArray[j][5])) {// 采用默认格式
			appendTableHead(
					writer,
					0,
					0,
					excelColumArray[j][2]+ExportTips.getReportDate(),
					Integer.parseInt(excelColumArray[j][6]),
					Integer.parseInt(excelColumArray[j][7]),
					ExcelStyleConstant.defalTitleStyle[0],
					Integer.parseInt(ExcelStyleConstant.defalTitleStyle[1]),
					Boolean.parseBoolean(ExcelStyleConstant.defalTitleStyle[2]),
					Boolean.parseBoolean(ExcelStyleConstant.defalTitleStyle[3]),
					Boolean.parseBoolean(ExcelStyleConstant.defalTitleStyle[4]),
					Boolean.parseBoolean(ExcelStyleConstant.defalTitleStyle[5]),
					true,
					Boolean.parseBoolean(excelColumArray[j].length >= 9 ? excelColumArray[j][8]
							: ""));
		} else {
			appendTableHead(
					writer,
					0,
					0,
					excelColumArray[j][2],
					Integer.parseInt(excelColumArray[j][6]),
					Integer.parseInt(excelColumArray[j][7]),
					excelColumArray[j][8],
					Integer.parseInt(excelColumArray[j][9]),
					Boolean.parseBoolean(excelColumArray[j][10]),
					Boolean.parseBoolean(excelColumArray[j][11]),
					Boolean.parseBoolean(excelColumArray[j][12]),
					Boolean.parseBoolean(excelColumArray[j][13]),
					true,
					Boolean.parseBoolean(excelColumArray[j].length >= 9 ? excelColumArray[j][8]
							: ""));
		}
	}

	/**
	 * 写普通头信息
	 * 
	 * @param writer
	 * @param excelColumArray
	 * @param j
	 */
	private static void writeHeader(ExcelWriter writer,
			String[][] excelColumArray, int j,boolean  isAdd) {
		int  row  =1;
		if(isAdd){
			row=0;
		}
		if ("true".equals(excelColumArray[j][5])) {
			appendTableHead(
					writer,
					row,
					Integer.parseInt(excelColumArray[j][0]),
					excelColumArray[j][2],
					2,
					Integer.parseInt(excelColumArray[j][0]),
					ExcelStyleConstant.defalHeadStyle[0],
					Integer.parseInt(ExcelStyleConstant.defalHeadStyle[1]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[2]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[3]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[4]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[5]),
					false,
					Boolean.parseBoolean(excelColumArray[j].length >= 9 ? excelColumArray[j][8]
							: ""));
		} else {
			appendTableHead(
					writer,
					row,
					Integer.parseInt(excelColumArray[j][0]),
					excelColumArray[j][2],
					2,
					Integer.parseInt(excelColumArray[j][0]),
					excelColumArray[j][6],
					Integer.parseInt(excelColumArray[j][7]),
					Boolean.parseBoolean(excelColumArray[j][8]),
					Boolean.parseBoolean(excelColumArray[j][9]),
					Boolean.parseBoolean(excelColumArray[j][10]),
					Boolean.parseBoolean(excelColumArray[j][11]),
					false,
					Boolean.parseBoolean(excelColumArray[j].length >= 9 ? excelColumArray[j][8]
							: ""));
		}
	}

	/**
	 * 写普跨列头信息
	 * 
	 * @param writer
	 * @param excelColumArray
	 * @param j
	 */
	private static void writeIsMergerHeader(ExcelWriter writer,
			String[][] excelColumArray, int j,boolean isAdd) {
		
		int row =2;
		 if(isAdd){
			 row =1;
		 }
		if ("true".equals(excelColumArray[j][5])) {
			appendNoMergerTableHead(
					writer,
					row,
					Integer.parseInt(excelColumArray[j][0]),
					excelColumArray[j][2],
					ExcelStyleConstant.defalHeadStyle[0],
					Integer.parseInt(ExcelStyleConstant.defalHeadStyle[1]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[2]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[3]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[4]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[5]),
					Boolean.parseBoolean(excelColumArray[j].length >= 9 ? excelColumArray[j][8]
							: ""));
		} else {
			appendNoMergerTableHead(
					writer,
					row,
					Integer.parseInt(excelColumArray[j][0]),
					excelColumArray[j][2],
					excelColumArray[j][6],
					Integer.parseInt(excelColumArray[j][7]),
					Boolean.parseBoolean(excelColumArray[j][8]),
					Boolean.parseBoolean(excelColumArray[j][9]),
					Boolean.parseBoolean(excelColumArray[j][10]),
					Boolean.parseBoolean(excelColumArray[j][11]),
					Boolean.parseBoolean(excelColumArray[j].length >= 9 ? excelColumArray[j][8]
							: ""));
		}
	}

	/**
	 * 写跨列头标题
	 * 
	 * @param writer
	 * @param excelColumArray
	 * @param j
	 */
	private static void writeIsMergerHeaderTitle(ExcelWriter writer,
			String[][] excelColumArray, int j,boolean  isAdd) {
		int  row =1;
		if(isAdd){
			row=0;
		}
		
		if ("true".equals(excelColumArray[j][5])) {
			appendTableHead(
					writer,
					row,
					Integer.parseInt(excelColumArray[j][0]),
					excelColumArray[j][2],
					Integer.parseInt(excelColumArray[j][6]),
					Integer.parseInt(excelColumArray[j][7]),
					ExcelStyleConstant.defalHeadStyle[0],
					Integer.parseInt(ExcelStyleConstant.defalHeadStyle[1]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[2]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[3]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[4]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[5]),
					true,
					Boolean.parseBoolean(excelColumArray[j].length >= 9 ? excelColumArray[j][8]
							: ""));
		} else {
			appendTableHead(
					writer,
					row,
					Integer.parseInt(excelColumArray[j][0]),
					excelColumArray[j][2],
					Integer.parseInt(excelColumArray[j][6]),
					Integer.parseInt(excelColumArray[j][7]),
					excelColumArray[j][8],
					Integer.parseInt(excelColumArray[j][9]),
					Boolean.parseBoolean(excelColumArray[j][10]),
					Boolean.parseBoolean(excelColumArray[j][11]),
					Boolean.parseBoolean(excelColumArray[j][12]),
					Boolean.parseBoolean(excelColumArray[j][13]),
					true,
					Boolean.parseBoolean(excelColumArray[j].length >= 9 ? excelColumArray[j][8]
							: ""));
		}
	}

	private static void appendNoMergerTableHead(ExcelWriter writer, int row,
			int column, String propertys, String fontName, int size,
			boolean bold, boolean italic, boolean underLine,
			boolean deleteLine, boolean isLock) {
		writer.addCell(row, column, propertys, "", isLock).setFont(fontName,
				size, bold, italic, underLine, deleteLine);
	}

	private static void appendTableHead(ExcelWriter writer, int row,
			int column, String propertys, int endRow, int endColumn,
			String fontName, int size, boolean bold, boolean italic,
			boolean underLine, boolean deleteLine, boolean isMerger,
			boolean isLock) {
		if (isMerger) {
			writer.addCell(row, column, propertys, "", isLock)
					.mergeTo(endRow, endColumn)
					.setFont(fontName, size, bold, italic, underLine,
							deleteLine)
					.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		} else {
			writer.addCell(row, column, propertys, "", isLock)
					.mergeTo(endRow, endColumn)
					.setFont(fontName, size, bold, italic, underLine,
							deleteLine);
		}
	}

	/**
	 * 写数据
	 * 
	 * @param writer
	 * @param helper
	 * @param excelColumArray
	 * @param objList
	 *            @
	 * @throws NumberFormatException
	 */
	public static void writeDateToExcel(ExcelWriter writer,
			String[][] excelColumArray, List objList, boolean isFilling)
			throws NumberFormatException {
		if (objList==null || objList.size()==0) {
			return;
		}
		for (int i = 0; i < objList.size(); i++) {
			for (int j = 1; j < excelColumArray.length; j++) {

				appendRow(writer, objList, i,
						Integer.parseInt(excelColumArray[j][0]),
						excelColumArray[j][1], excelColumArray[j][3],
						objList.get(i), isFilling,Boolean.parseBoolean(excelColumArray[j].length >= 9?excelColumArray[j][8]:""));
			}
		}
	}

	/**
	 * 向excel模板写内容
	 * 
	 * @param writer
	 * @param helper
	 * @param excelColumArray
	 * @param objList
	 *            isFilling 需要填充并且需要有背景色
	 */
	private static void appendRow(ExcelWriter writer, List objList, int row,
			int seq, String propertyName, String propertyType, Object object,
			boolean isFilling, boolean isLock) {
		try {
			String background = "";
			if (isFilling) {
				background = String.valueOf(BeanUtils.getProperty(object,
						StatisticsBankAccountConstants.BACKGROUND));
			}

			if (!StringUtils.isEmpty(propertyType)
					&& DataType.DATA_TYPE_BOOLEAN.equals(propertyType)) {
				String data = "";
				if ("1".equals(String.valueOf(Ognl.getValue(propertyName,
						object)))) {
					data = "是";
				} else if ("0".equals(String.valueOf(Ognl.getValue(
						propertyName, object)))) {
					data = "否";
				} else {
					if (isFilling) {
						data = "无";
					}

				}
				writer.addCell((row + 3), seq, data, background, isLock);
			} else {
				if (!StringUtils.isEmpty(propertyName)) {

					String data = String.valueOf(BeanUtils.getProperty(object,
							propertyName));
					String specData = procSpecialTypeData(propertyName,
							propertyType, object);
					if (!StringUtils.isEmpty(specData)) {
						data = specData;
					}
					if (null != BeanUtils.getProperty(object, propertyName)) {
						writer.addCell((row + 3), seq, data, background, isLock);
					} else {
						if (isFilling) {
							writer.addCell((row + 3), seq, "无", background,
									isLock);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("类ExcelExportHelper的appendRow方法出现异常，原因：",
					"向excel模板写数据出现错误", e);
		}
	}

	private static String procSpecialTypeData(String propertyName,
			String propertyType, Object object) throws Exception {
		String data = "";
		if (DataType.DATA_TYPE_DATE.equals(propertyType)) {
			data = CalendarUtil.format((Date) Ognl.getValue(propertyName,
					object));
		}
		if (DataType.DATA_TYPE_BOOLEAN.equals(propertyType)) {
			if ("1".equals(String.valueOf(Ognl.getValue(propertyName, object)))
					|| "true".equals(String.valueOf(Ognl.getValue(propertyName,
							object)))) {
				data = "是";
			} else {
				data = "否";
			}
		}
		if (StringUtil.isNullOrBlank(propertyName)) {
			data = "无";
		}
		return data;
	}

	private static ExcelWriter constructExcelWriter() {
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		return writer;
	}

	public static ExcelWriter constructExcelWriterTemp() {
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		return writer;
	}

	public static void writeHeaderToExcelToStatisticsBankAccount(
			ExcelWriter writer) {
		// TODO Auto-generated method stub

	}
}

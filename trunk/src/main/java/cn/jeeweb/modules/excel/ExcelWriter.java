package cn.jeeweb.modules.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jeeweb.core.utils.FileUtil;
import cn.jeeweb.modules.excel.constants.StatisticsBankAccountConstants;


public class ExcelWriter {
	Logger logger = LoggerFactory.getLogger(ExcelWriter.class);
	private HSSFWorkbook workbook;
	private String currentSheetName;
	private ExcelWriteContext context;
	private int sheetCount = 0;
	private Font default_font = null;
	private CellStyle default_Text_Style = null;
	private Map map = new HashMap();

	private List<SheetSize> sheetSizes = new ArrayList<SheetSize>();

	public static final String DEFAULT_DATE_FORMAT = "m/d/yy";
	public static final String DEFAULT_DATETIME_FORMAT = "yy-m-d h:mm";
	public static final String DEFAULT_DOUBLE_FORMAT = "#0.00";
	public static final String DEFAULT_LONG_FORMAT = "#0";
	public static final String DEFAULT_CURRENCY_FORMAT = "#,##0.00";

	public static final short HORIZONTAL_ALIGN_CENTER = CellStyle.ALIGN_CENTER;
	public static final short HORIZONTAL_ALIGN_LEFT = CellStyle.ALIGN_LEFT;
	public static final short HORIZONTAL_ALIGN_RIGHT = CellStyle.ALIGN_RIGHT;
	public static final short VERTICAL_ALIGN_TOP = CellStyle.VERTICAL_TOP;
	public static final short VERTICAL_ALIGN_BOTTOM = CellStyle.VERTICAL_BOTTOM;
	public static final short VERTICAL_ALIGN_CENTER = CellStyle.VERTICAL_CENTER;

	// private static short XLS_ENCODING = HSSFWorkbook.ENCODING_UTF_16;

	public void addWorkSheet(String name) {
		if (emptyStringValue(name))
			throw new IllegalArgumentException("工作表名称不能为空");
		if (getWorkBook().getSheet(name) != null) {
			throw new IllegalArgumentException("[" + name + "]工作表已经存在");
		} else {
			getWorkBook().createSheet(name).protectSheet("edit");//protectSheet设置工作簿的保护属性,否则后面写多少setlocked(true)都无法锁定单元格
			currentSheetName = name;
			sheetCount++;
		}
	}
	
	public void addWorkSheetNotProtect(String name) {
		if (emptyStringValue(name))
			throw new IllegalArgumentException("工作表名称不能为空");
		if (getWorkBook().getSheet(name) != null) {
			throw new IllegalArgumentException("[" + name + "]工作表已经存在");
		} else {
			getWorkBook().createSheet(name);
			currentSheetName = name;
			sheetCount++;
		}
	}

	public CellConstraints addCell(int row, int column, String value,
			String background,boolean isLock ) {
		HSSFCell cell = getCell(row, column);
		CellStyle cellStyle=getDefaultCellStyle();
		cell.setCellStyle(cellStyle);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(getWorkBook().getCreationHelper()
				.createRichTextString(value));
		//CellStyle style = getDefaultCellStyle();
		
		if (StatisticsBankAccountConstants.green.equals(background)) {
			HSSFCellStyle style = getWorkBook().createCellStyle();
			style.setLocked(isLock);//解除单元格锁定,false解锁,true锁定
			style.setFillForegroundColor(HSSFColor.GREEN.index);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(style);
		} else if (StatisticsBankAccountConstants.yellow.equals(background)) {
			HSSFCellStyle style = getWorkBook().createCellStyle();
			style.setLocked(isLock);//解除单元格锁定,false解锁,true锁定
			style.setFillForegroundColor(HSSFColor.YELLOW.index);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(style);
		} else if (StatisticsBankAccountConstants.red.equals(background)) {
			HSSFCellStyle style = getWorkBook().createCellStyle();
			style.setLocked(isLock);//解除单元格锁定,false解锁,true锁定
			style.setFillForegroundColor(HSSFColor.RED.index);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(style);
		}
		setSheetSize(currentSheetName, row, column);
		return constructCellConstraints(row, column);
	}

	public CellConstraints addCell(int row, int column, Date value) {
		return addCell(row, column, value, DEFAULT_DATE_FORMAT);
	}

	public CellConstraints addCell(int row, int column, Date value,
			String format) {
		HSSFCell cell = getCell(row, column);
		if (value != null) {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(value);
		}
		cell.setCellStyle(getCellStyle(format));
		setSheetSize(currentSheetName, row, column);
		return constructCellConstraints(row, column);
	}

	public CellConstraints addCell(int row, int column, double value) {
		return addCell(row, column, value, DEFAULT_DOUBLE_FORMAT);
	}

	public CellConstraints addCell(int row, int column, double value,
			String format) {
		HSSFCell cell = getCell(row, column);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		CellStyle style = getCellStyle(format);
		style.setAlignment(HORIZONTAL_ALIGN_RIGHT);
		cell.setCellStyle(style);
		setSheetSize(currentSheetName, row, column);
		return constructCellConstraints(row, column);
	}

	public CellConstraints addCell(int row, int column, long value) {
		HSSFCell cell = getCell(row, column);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		CellStyle style = getCellStyle(DEFAULT_LONG_FORMAT);
		style.setAlignment(HORIZONTAL_ALIGN_RIGHT);
		cell.setCellStyle(style);
		setSheetSize(currentSheetName, row, column);
		return constructCellConstraints(row, column);
	}

	public CellConstraints addCell(int row, int column, Number value,
			String format) {
		HSSFCell cell = getCell(row, column);
		if (value != null) {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(value.doubleValue());
		}
		CellStyle style = getCellStyle(format);
		style.setAlignment(HORIZONTAL_ALIGN_RIGHT);
		cell.setCellStyle(style);
		setSheetSize(currentSheetName, row, column);
		return constructCellConstraints(row, column);
	}

	public CellConstraints addCell(int row, int column, boolean value) {
		return addCell(row, column, Boolean.valueOf(value));
	}

	public CellConstraints addCell(int row, int column, Boolean value) {
		return addCell(row, column, (String) (value == null ? null
				: getDefaultBooleanText(value)), "",false);
		// HSSFCell cell=getCell(row,column);
		// cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		// if(value!=null){
		// cell.setCellValue(getDefaultBooleanText(value));
		// }
		// return constructCellConstraints(row,column);
	}

	public void setDefaultCellBorder(boolean border) {
		ExcelCellStyleHelper.setCellStyleBorder(getDefaultCellStyle(), border);
	}

	public void setDefaultFont(String fontName, short size, boolean bold,
			boolean italic, boolean underLine, boolean deleteLine) {
		default_font = ExcelCellStyleHelper.createFont(getWorkBook(), fontName,
				size, bold, italic, underLine, deleteLine);
		getDefaultCellStyle().setFont(default_font);
	}

	public void setDefaultHorizontalAlignment(short alignment) {
		getDefaultCellStyle().setAlignment(alignment);

	}

	public void setDefaultVerticalAlignment(short alignment) {
		getDefaultCellStyle().setVerticalAlignment(alignment);

	}

	public File getExcelFile(String fileName) throws Exception {
		File file = createStoreFile(fileName);
		// File file=new File("d:/test.xls");
		FileOutputStream fileOut = new FileOutputStream(file);
		autoFillBlankCell();
		autoWorkBookColumnSize();
		getWorkBook().write(fileOut);
		fileOut.close();
		return file;
	}

	private void autoFillBlankCell() {
		for (SheetSize size : sheetSizes) {
			autoFillBlankCell(size);
		}
	}

	private void autoFillBlankCell(SheetSize size) {
		HSSFSheet sheet = getWorkBook().getSheet(size.sheetName);
		currentSheetName = size.sheetName;
		if (sheet != null) {
			for (int row = 0; row <= size.rowCount; row++) {
				for (int column = 0; column <= size.columnCount; column++) {
					if (!cellExisted(row, column)) {
						addCell(row, column, "", "",false);
					}
				}
			}
		}
	}

	private void autoWorkBookColumnSize() {
		for (SheetSize size : sheetSizes) {
			autoWorkSheetColumnSize(size);
		}
	}

	private void autoWorkSheetColumnSize(SheetSize size) {
		HSSFSheet sheet = getWorkBook().getSheet(size.sheetName);
		if (sheet != null) {
			for (int index = 0; index <= size.columnCount; index++) {
				sheet.autoSizeColumn(index, true);
			}
		}
	}

	// public static void main(String[] arg) throws Exception{
	// ExcelWriter write=new ExcelWriter();
	// write.setDefaultCellBorder(true);
	// write.addWorkSheet("测试1");
	// write.addCell(0, 0, "ceshi shuju").setFont("宋体", 20, true,
	// true,true,true);
	// // write.addCell(0, 1, Calendar.getInstance().getTime());
	// // write.addCell(0, 2, (Integer)null,ExcelWriter.DEFAULT_LONG_FORMAT);
	// write.addCell(0, 2, (Boolean)null);
	// write.addCell(0, 3, 1234567890.98765);
	// write.addCell(0, 4, true);
	// write.addCell(0, 5, Boolean.FALSE);
	// write.addCell(1, 0, "ceshi shuju").setFont("宋体", 10, false,
	// true,true,true);
	// // write.addCell(2, 1, Calendar.getInstance().getTime());
	// write.addCell(3, 2, -1234567L);
	// write.addCell(4, 3, 1234567890.98765).setBorder(true);
	// write.addCell(4, 4, false);
	// write.addCell(5, 5,
	// "mergedString").mergeTo(8,12).setBorder(true).setFont("宋体", 20, true,
	// true,true,true);
	// write.addCell(5, 1,
	// -1234567L).setBorder(true).mergeTo(6,1).setBorder(false);
	// write.addCell(5, 2,
	// 1234567890.98765,"#,##0.0000").setBorder(true).mergeTo(6,2);
	// write.addCell(5, 3, false).mergeTo(6,3);
	// write.addCell(8, 1, "aaaaaaa");
	// write.addWorkSheet("测试2");
	// write.addCell(0, 0, "ceshi shuju");
	// // write.addCell(0, 1, Calendar.getInstance().getTime());
	// write.addCell(0, 2, 1234567L);
	// write.addCell(0, 3, 1234567890.98765);
	// write.getExcelFile("test.xls");
	// }

	private String getDefaultBooleanText(boolean value) {
		return value ? "是" : "否";
	}

	private HSSFWorkbook getWorkBook() {
		if (workbook == null) {
			workbook = new HSSFWorkbook();
			context = new ExcelWriteContext(workbook);
		}
		return workbook;
	}

	private HSSFCell getCell(int row, int column) {
		return getCell(getRow(row), column);
	}

	private HSSFCell getCell(HSSFRow rowData, int column) {
		if (rowData == null)
			throw new IllegalArgumentException("指定行不存在，无法定位单元格");
		HSSFCell result = rowData.getCell(column);
		if (result == null) {
			result = rowData.createCell(column);
			
			result.setCellType(HSSFCell.CELL_TYPE_STRING);
		}
		return result;
	}

	private boolean cellExisted(int row, int column) {
		HSSFRow rowData = getCurrentWorksheet().getRow(row);
		if (rowData == null)
			return false;
		HSSFCell cell = rowData.getCell(column);
		return cell != null;
	}

	private HSSFRow getRow(int row) {
		HSSFRow result = getCurrentWorksheet().getRow(row);
		if (result == null) {
			result = getCurrentWorksheet().createRow(row);
		}
		return result;
	}

	private HSSFSheet getCurrentWorksheet() {
		if (emptyStringValue(currentSheetName))
			throw new IllegalStateException("尚未创建工作表，请先创建相应的工作表");
		return getWorkBook().getSheet(currentSheetName);
	}

	private boolean emptyStringValue(String value) {
		return value == null || value.trim().length() == 0;
	}

	private CellStyle getCellStyle(String format) {
		CellStyle cellStyle = (CellStyle) map.get(format);
		if (cellStyle == null) {
			cellStyle = getWorkBook().createCellStyle();
			cellStyle.cloneStyleFrom(getDefaultCellStyle());
			cellStyle.setDataFormat(getWorkBook().getCreationHelper()
					.createDataFormat().getFormat(format));
			map.put(format, cellStyle);
		}
		return cellStyle;
	}

	private CellStyle getDefaultCellStyle() {
		if (default_Text_Style == null) {
			default_Text_Style = getWorkBook().createCellStyle();
			HSSFDataFormat format = getWorkBook().createDataFormat();
			default_Text_Style.setDataFormat(format.getFormat("@"));
		}
		return default_Text_Style;
	}

	private CellConstraints constructCellConstraints(int row, int column) {
		return new CellConstraints(context, currentSheetName, row, column);
	}

	public File createZipFile(String fileName) throws Exception {
		File storedFile = new File(FileUtil.getExcelTemporaryPath() + File.separator + fileName);
		if (!storedFile.exists()) {
			storedFile.createNewFile();
		}
		return storedFile;
	}

	private File createStoreFile(String fileName) throws Exception {
		File storedFile = new File(FileUtil.getExcelTemporaryPath() + File.separator + fileName);
		if (!storedFile.exists()) {
			storedFile.createNewFile();
		}
		return storedFile;
	}

	private final static String uploadFilePath = "downloadFile";

	/*private String createStoreFilePath() {
		String storedFilePath = uploadFilePath + File.separator
				+ getFileSuffix().substring(1) + File.separator
				+ CalendarUtil.format(CalendarUtil.today());
		File file = new File(FileUtil.getWebRoot() + File.separator
				+ "uploadFile" + File.separator + storedFilePath);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		logger.debug("下载文件目录：" + storedFilePath);
		return storedFilePath;
	}*/

	/*public String getStoreFilePath() {
		return "uploadFile" + File.separator + createStoreFilePath()
				+ File.separator;
	}*/

	private String getFileSuffix() {
		return ".xls";
	}

	private void setSheetSize(String sheetName, int row, int column) {
		SheetSize sheetsize = lookupSheeiSize(sheetName);
		sheetsize.rowCount = Math.max(sheetsize.rowCount, row);
		sheetsize.columnCount = Math.max(sheetsize.columnCount, column);
	}

	private SheetSize lookupSheeiSize(String sheetName) {
		for (SheetSize size : sheetSizes) {
			if (size.sheetName != null && size.sheetName.equals(sheetName)) {
				return size;
			}
		}
		SheetSize result = constructSheetSize(sheetName);
		sheetSizes.add(result);
		return result;
	}

	private SheetSize constructSheetSize(String sheetName) {
		return new SheetSize(sheetName);
	}

	class SheetSize {
		private int columnCount = 0;
		private int rowCount = 0;
		private String sheetName;

		SheetSize(String sheetName) {
			this.sheetName = sheetName;
		}

	}

	public String getCurrentSheetName() {
		return currentSheetName;
	}

}

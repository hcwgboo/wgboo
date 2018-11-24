package cn.jeeweb.modules.excel;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelWriteContext {
	// private Map<String,HSSFSheet> worksheets=new HashMap<String,HSSFSheet>();
	// private Map <String ,CellStyle> cellStyles=new HashMap<String ,CellStyle>();
	private List<Font> fonts = new ArrayList<Font>();
	private HSSFWorkbook workbook;

	public ExcelWriteContext(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public Sheet getWorkSheet(String name) {
		return workbook.getSheet(name);
	}

	public void createRowIfNecceessary(String workSheetName, int startRow, int endRow) {
		Sheet sheet = getWorkSheet(workSheetName);
		for (int index = startRow; index <= endRow; index++) {
			if (sheet.getRow(index) == null) {
				sheet.createRow(index);
			}
		}
	}

	public CellStyle cloneCellStyle(String workSheetName, int row, int column) {
		Cell cell = getCell(workSheetName, row, column);
		CellStyle result = workbook.createCellStyle();
		result.cloneStyleFrom(cell.getCellStyle());
		return result;
	}

	public void setCellStyle(String sheetName, int row, int column, CellStyle style) {
		Cell cell = getCell(sheetName, row, column);
		cell.setCellStyle(style);
	}

	public Font getFont(String fontName, short size, boolean bold, boolean italic,
			boolean underLine, boolean deleteLine) {
		Font result = findExistedFont(fontName, size, bold, italic);
		if (result == null) {
			result = createFont(fontName, size, bold, italic, underLine, deleteLine);
			fonts.add(result);
		}
		return result;
	}

	private Font findExistedFont(String fontName, short size, boolean bold, boolean italic) {
		for (Font _font : fonts) {
			if (_font.getFontHeightInPoints() == size && _font.getStrikeout() == bold
					&& _font.getItalic() == italic) {
				return _font;
			}
		}
		return null;
	}

	private Font createFont(String fontName, short size, boolean bold, boolean italic,
			boolean underLine, boolean deleteLine) {
		Font result = workbook.createFont();
		result.setFontName(fontName);
		result.setFontHeightInPoints(size);
		result.setItalic(italic);
		result.setStrikeout(deleteLine);
		result.setUnderline(underLine ? Font.U_SINGLE : Font.U_NONE);
		result.setBoldweight(bold ? Font.BOLDWEIGHT_BOLD : Font.BOLDWEIGHT_NORMAL);

		return result;
	}

	private Cell getCell(String workSheetName, int row, int column) {
		Cell result = workbook.getSheet(workSheetName).getRow(row).getCell(column);
		if (result == null)
			result = workbook.getSheet(workSheetName).getRow(row).createCell(column);
		return result;
	}

}

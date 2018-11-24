package cn.jeeweb.modules.excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;


public class CellConstraints {
	protected ExcelWriteContext context;
	protected int startRow;
	protected int startColumn;
	protected String sheetName;

	public CellConstraints(ExcelWriteContext context, String sheetName, int startRow,
			int startColumn) {
		this.context = context;
		this.startRow = startRow;
		this.startColumn = startColumn;
		this.sheetName = sheetName;
	}

	public CellConstraints setBorder(boolean border) {
		CellStyle style = context.cloneCellStyle(sheetName, startRow, startColumn);
		ExcelCellStyleHelper.setCellStyleBorder(style, border);
		context.setCellStyle(sheetName, startRow, startColumn, style);
		return this;
	}

	public CellConstraints setFont(String fontName, int size, boolean bold, boolean italic,
			boolean underLine, boolean deleteLine) {
		CellStyle style = context.cloneCellStyle(sheetName, startRow, startColumn);
		style.setFont(context.getFont(fontName, (short) size, bold, italic, underLine, deleteLine));
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		context.setCellStyle(sheetName, startRow, startColumn, style);
		return this;
	}

	public CellConstraints mergeTo(int endRow, int endColumn) {
		context.createRowIfNecceessary(sheetName, startRow, endRow);
		context.getWorkSheet(sheetName).addMergedRegion(
				new CellRangeAddress(startRow, endRow, startColumn, endColumn));
		return new MergedCellConstraints(context, sheetName, startRow, startColumn, endRow,
				endColumn);
	}

	public CellConstraints setHorizontalAlignment(short alignment) {
		CellStyle style = context.cloneCellStyle(sheetName, startRow, startColumn);
		style.setAlignment(alignment);
		context.setCellStyle(sheetName, startRow, startColumn, style);
		return this;
	}

	public CellConstraints setVerticalAlignment(short alignment) {
		CellStyle style = context.cloneCellStyle(sheetName, startRow, startColumn);
		style.setVerticalAlignment(alignment);
		context.setCellStyle(sheetName, startRow, startColumn, style);
		return this;
	}

}

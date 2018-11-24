package cn.jeeweb.modules.excel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

public class MergedCellConstraints extends CellConstraints {
	protected int endRow;
	protected int endColumn;

	// TODO 需要对bord重写

	public MergedCellConstraints(ExcelWriteContext context, String sheetName,
			int startRow, int startColumn, int endRow, int endColumn) {
		super(context, sheetName, startRow, startColumn);
		this.endRow = endRow;
		this.endColumn = endColumn;
		resetBord();
	}

	public CellConstraints setBorder(boolean border) {
		for (int rowIndex = startRow; rowIndex <= endRow; rowIndex++) {
			for (int columnIndex = startColumn; columnIndex <= endColumn; columnIndex++) {
				CellStyle style = context.cloneCellStyle(sheetName, rowIndex,
						columnIndex);
				if (isTopCell(rowIndex, columnIndex)) {
					style.setBorderTop(CellStyle.BORDER_THIN);
					style.setBorderBottom(CellStyle.BORDER_NONE);
					style.setTopBorderColor(IndexedColors.BLACK.getIndex());
				} else {
					style.setBorderTop(CellStyle.BORDER_NONE);
				}
				if (isLeftCell(rowIndex, columnIndex)) {
					style.setBorderLeft(CellStyle.BORDER_THIN);
					style.setBorderRight(CellStyle.BORDER_NONE);
					style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
				} else {
					style.setBorderLeft(CellStyle.BORDER_NONE);
				}
				if (isRightCell(rowIndex, columnIndex)) {
					style.setBorderRight(CellStyle.BORDER_THIN);
					style.setRightBorderColor(IndexedColors.BLACK.getIndex());
				} else {
					style.setBorderRight(CellStyle.BORDER_NONE);
				}
				if (isBottomCell(rowIndex, columnIndex)) {
					style.setBorderBottom(CellStyle.BORDER_THIN);
					style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
				} else {
					style.setBorderBottom(CellStyle.BORDER_NONE);
				}
				context.setCellStyle(sheetName, rowIndex, columnIndex, style);
			}
		}
		return this;
	}

	private void resetBord() {
		CellStyle style = context.cloneCellStyle(sheetName, startRow, endRow);
		setBorder(style.getBorderTop() == CellStyle.BORDER_THIN);

	}

	private boolean isTopCell(int rowIndex, int columnIndex) {
		return rowIndex == startRow;
	}

	private boolean isLeftCell(int rowIndex, int columnIndex) {
		return columnIndex == startColumn;
	}

	private boolean isRightCell(int rowIndex, int columnIndex) {
		return columnIndex == endColumn;
	}

	private boolean isBottomCell(int rowIndex, int columnIndex) {
		return rowIndex == endRow;
	}

}

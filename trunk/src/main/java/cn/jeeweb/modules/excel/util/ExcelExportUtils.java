package cn.jeeweb.modules.excel.util;

import java.util.ArrayList;
import java.util.List;

import cn.jeeweb.modules.excel.DataType;
import cn.jeeweb.modules.excel.ExcelExportHelper;
import cn.jeeweb.modules.excel.ExcelWriter;
import cn.jeeweb.modules.excel.definition.ExportFieldBean;
import cn.jeeweb.modules.excel.definition.ExportParent;


public class ExcelExportUtils {

	public static void handleExport(List tempList, boolean isFilling,
			String fileName, String[][] excelDefines) throws Exception {
		ExcelWriter writer = constructExcelWriter();
		writer.addWorkSheet(excelDefines[0][2]);
		ExcelExportHelper.writeHeaderToExcel(writer, excelDefines, tempList);
		ExcelExportHelper.writeDateToExcel(writer, excelDefines, tempList,
				isFilling);
		writer.getExcelFile(fileName + ".xls");
	}
	
	public static void handleExportNotProtect(List tempList, boolean isFilling,
			String fileName, String[][] excelDefines) throws Exception {
		ExcelWriter writer = constructExcelWriter();
		writer.addWorkSheetNotProtect(excelDefines[0][2]);
		ExcelExportHelper.writeHeaderToExcel(writer, excelDefines, tempList);
		ExcelExportHelper.writeDateToExcel(writer, excelDefines, tempList,
				isFilling);
		writer.getExcelFile(fileName + ".xls");
	}
	
	private static ExcelWriter constructExcelWriter() {
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		return writer;
	}


	public static String[][] getExcelDefines(ExportParent parent){

		List<ExportFieldBean> list = parent.initColumn();//获取泛型中类里面的注解
		List<ExportFieldBean> header = parent.initHeader();
		//输出结果

		List<List<String>> raisedBatchList = new ArrayList<List<String>>();
		for(ExportFieldBean h : header){
			List<String> hs = new ArrayList<String>();
			//System.out.println("\t注解描述："+h.getHeader().name());
			hs.add(String.valueOf(h.getHeader().seqNum()));
			hs.add("");
			hs.add(h.getHeader().name());
			hs.add("");
			hs.add("");
			hs.add(String.valueOf(h.getHeader().defaultStyle()));
			hs.add(String.valueOf(h.getHeader().start()));
			hs.add(String.valueOf(h.getHeader().end()));
			raisedBatchList.add(hs);
		}

		for(ExportFieldBean l : list){
			String type = DataType.DATA_TYPE_STRING;
			if("Date".equals(l.getType().getSimpleName())){
				type = DataType.DATA_TYPE_DATE;
			}else if("Boolean".equals(l.getType().getSimpleName())){
				type = DataType.DATA_TYPE_BOOLEAN;
			}else {
				type = DataType.DATA_TYPE_STRING;
			}
			List<String> strings = new ArrayList<String>();
			strings.add(String.valueOf(l.getColumn().seqNum()));
			strings.add(l.getName());
			strings.add(l.getColumn().name());
			strings.add(type);
			strings.add("");
			strings.add(String.valueOf(l.getColumn().defaultStyle()));
			raisedBatchList.add(strings);
		}

		String[][] raisedBatchArray = new String[raisedBatchList.size()][];
		for(int i=0;i<raisedBatchList.size();i++){
			int size = raisedBatchList.get(i).size();
			String[] columns = new String[size];
			raisedBatchList.get(i).toArray(columns);
			raisedBatchArray[i] = columns;
		}
		return raisedBatchArray;
	}

}

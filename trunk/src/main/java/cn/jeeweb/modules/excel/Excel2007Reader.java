package cn.jeeweb.modules.excel;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.jeeweb.core.utils.FileUtil;

/**
 * 
 * @Description:2007版本解析数据
 * @author zhangyouwei1988@sina.cn
 * @date: 2016-5-17 下午8:02:33
 */
public class Excel2007Reader {
	private static final Log logger = LogFactory.getLog(Excel2007Reader.class);
	public static String[][] readFile(File file) {
		String totalDatas[][] = null;
		if (FileUtil.isExcel2007(file)) {

			// 构造 XSSFWorkbook 对象，strPath 传入文件路径
			XSSFWorkbook xwb;
			XSSFFormulaEvaluator   evaluator  ;  
			try {
				xwb = new XSSFWorkbook(new FileInputStream(file));
				evaluator= new XSSFFormulaEvaluator(xwb);
				// 读取第一章表格内容
				XSSFSheet sheet = xwb.getSheetAt(0);
				// 定义 row、cell
				XSSFRow row;
				String cell = "";
				XSSFCell xssfCell;
				String[] datas = null;
				// 循环输出表格中的内容
				List<String[]> datasList = new ArrayList<String[]>();
				// 第二维的长度
				int twoDimension = 0;
				for (int i = 0; i <= sheet.getLastRowNum(); i++) {
					row = sheet.getRow(i);
					datas=null;
					if (row != null) {
					    if(row.getLastCellNum()!=-1){
    						datas = new String[row.getLastCellNum()];
    						for (int j = 0; j < row.getLastCellNum(); j++) {
    							// 通过 row.getCell(j).toString() 获取单元格内容，
    							cell = "";
    							xssfCell = row.getCell(j);
    							if (xssfCell != null) {
    								switch (xssfCell.getCellType()) {
    
    								case XSSFCell.CELL_TYPE_NUMERIC: // 数字
    									SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    									DecimalFormat df = new DecimalFormat("0.####");
    									//NumberFormat nf = NumberFormat.getInstance();
    							        //nf.setGroupingUsed(false);
    									
    									//判断是否为Excel2007内置的日期格式数据
    									if(DateUtil.isCellDateFormatted(xssfCell)) {  
    		                                cell = sdf.format(xssfCell.getDateCellValue()); //日期型  
    		                            } else {  
    		                                cell = df.format(xssfCell.getNumericCellValue()); //数字  
    		                                if(cell==null){
    										    cell="";
    		                                } else {
    											cell=cell.trim();
    		                                }
    		                            }  
    									break;
    								case XSSFCell.CELL_TYPE_STRING: // 字符串
    									cell = xssfCell.getStringCellValue();
    									if(cell==null)
                                            cell="";
    									else 
    										cell=cell.trim();
    									break;
    
    								case XSSFCell.CELL_TYPE_BLANK: // 空值
    									cell = "";
    									break;
    									
    								case 	XSSFCell.CELL_TYPE_FORMULA:
    								    CellValue tempCellValue = evaluator.evaluate(xssfCell);  
    								    if(tempCellValue.getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
    								        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
                                            DecimalFormat df1 = new DecimalFormat("0.####");
                                            //判断是否为Excel2007内置的日期格式数据
                                            if(DateUtil.isCellDateFormatted(xssfCell)) {  
                                                cell = sdf1.format(xssfCell.getDateCellValue()); //日期型  
                                            } else {  
                                                cell = df1.format(xssfCell.getNumericCellValue()); //数字  
                                                if(cell==null){
                                                    cell="";
                                                } else {
                                                    cell=cell.trim();
                                                }
                                            }  
                                            break;
    								    }else if(tempCellValue.getCellType()==XSSFCell.CELL_TYPE_STRING){
    								        cell = xssfCell.getStringCellValue();
    								        if(cell==null)
                                                cell="";
                                            else 
                                                cell=cell.trim();
                                            break;
    								    }else if(tempCellValue.getCellType()==XSSFCell.CELL_TYPE_BLANK){
    								        cell = "";
                                            break;
    								    }
    								}
    							}else{
    							    cell="";  
    							}
    							datas[j] = cell;
    							if (j > twoDimension) {
    								twoDimension = j;
    							}
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
				logger.error("读取2007版excel出错，错误信息：",e);
				throw new RuntimeException("读取excel表格出错！");
			}
		}

		return totalDatas;
	}
}

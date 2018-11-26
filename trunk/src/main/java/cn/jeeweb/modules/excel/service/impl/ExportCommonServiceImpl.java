package cn.jeeweb.modules.excel.service.impl;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.exception.ExceptionResultInfo;
import cn.jeeweb.modules.excel.definition.CommonExportDataContext;
import cn.jeeweb.modules.excel.service.IExportCommonService;
import cn.jeeweb.modules.excel.util.ExcelExportUtils;
import cn.jeeweb.modules.excel.web.ExportDataBaseAction;
@Transactional(rollbackFor = Exception.class)
@Service
public class ExportCommonServiceImpl<T> implements IExportCommonService<T>{

	@Override
	public void excelExport(HttpServletResponse response, String dataType, List<T> list) throws Exception {
		boolean flag = CommonExportDataContext.checkDataType(dataType);
		if (!flag) throw new ExceptionResultInfo("参数错误");
		String fileName = UUID.randomUUID().toString().replace("-", "");
		// 获取行情导出模版
		String[][] excelDefines = CommonExportDataContext.excelExport(dataType);
		// 写入excel
		ExcelExportUtils.handleExport(list, false, fileName, excelDefines);
		// 下载excel文件
		ExportDataBaseAction.downloadData(fileName, dataType, response);
	}
}

package cn.jeeweb.modules.excel.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public interface IExportCommonService<T> {

	/**
	 * excel导出
	 * @param response
	 * @param dataType
	 * @param list
	 * @throws Exception
	 */
	void excelExport(HttpServletResponse response, String dataType, List<T> list) throws Exception;
}

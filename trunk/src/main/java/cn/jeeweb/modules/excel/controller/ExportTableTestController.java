package cn.jeeweb.modules.excel.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jeeweb.core.exception.ExceptionResultInfo;
import cn.jeeweb.modules.excel.ExportTest;
import cn.jeeweb.modules.excel.definition.CommonExportDataContext;
import cn.jeeweb.modules.excel.service.IExportCommonService;

/**
 * excel导出测试
 * 
 */
@Controller
@RequestMapping("/export")
public class ExportTableTestController {
	private Logger logger = Logger.getLogger(ExportTableTestController.class);
	@Autowired
	private IExportCommonService<ExportTest> iExportCommonService;

	/**
	 * 导出测试
	 * @param dataType
	 * @param response
	 */
	@RequestMapping("/exportTest")
	@ResponseBody
	public void exportQuotationModel(String dataType, HttpServletResponse response) {
		try {
			List<ExportTest> list = creatData();
			iExportCommonService.excelExport(response, dataType, list);
		} catch (Exception e) {
			logger.error("导出错误", e);
			e.printStackTrace();
		}
	}
	private List<ExportTest> creatData() {
		List<ExportTest> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Date date = new Date();
			ExportTest test = new ExportTest();
			test.setAccumulated_net_asset_value("累计净值" + i);
			test.setInterest_float_rate("浮动收益" + i);
			test.setInterest_rate("固定收益" + i);
			test.setKind_name("产品分类" + i);
			test.setNet_asset_value("产品净值" + i);
			test.setProduct_code("产品编号" + i);
			test.setProject_name("产品名称" + i);
			test.setProduct_quotation_date(date);
			test.setTerm_sub_id("产品小类" + i);
			list.add(test);
		}
		return list;
	}
	
}

package cn.jeeweb.modules.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.excel.entity.ImportMappingConfiguration;

/**
 * 解析出excel模板规则帮助类
 * 
 * @Description:
 * @author zhangyouwei1988@sina.cn
 * @date: 2016-5-5 下午1:25:38
 */
public class AnalysisRuleHelper {

	/** javaBean 属性和数据类型 */
	public static final String BEAN_DATAS = "BEAN_DATAS";
	/** 数据开始行 */
	public static final String START_ROW = "START_ROW";
	/** 头数据行 */
	public static final String HEADER_ROW_NO = "HEADER_ROW_NO";

	/**
	 * 解析出对应的模板规则
	 * 
	 * @说明：1、配置数据库 COLUMN_NO字段必须是从0开始递增的数字 2、START_ROW_NO 必须相等 且必须从0开始
	 *             3、BANKROLL_SERIAL_ITEM_EN_NAME 是导入对应bean 的属性名（通过内省设置值）
	 * @param resultList
	 * @return
	 */
	public static Map<String, Object> analysisRule(List<ImportMappingConfiguration> resultList) {
		Map<String, Object> map = null;
		if (resultList != null && resultList.size() > 0) {
			map = new HashMap<String, Object>();
			String startRow = "";
			String headerRow = "";
			int maxColumn = 0;

			for (ImportMappingConfiguration dto : resultList) {
				if (Integer.parseInt(dto.getColumn_no()) > maxColumn) {
					maxColumn = Integer.parseInt(dto.getColumn_no());
				}
			}

			String[][] beanDatas = new String[5][maxColumn + 1];
			for (ImportMappingConfiguration dto : resultList) {
				startRow = dto.getStart_row_no();
				headerRow = dto.getHeader_row_no();
				beanDatas[0][Integer.valueOf(dto.getColumn_no())] = dto.getProperty_ename();
				beanDatas[1][Integer.valueOf(dto.getColumn_no())] = dto.getItem_type();
				beanDatas[2][Integer.valueOf(dto.getColumn_no())] = dto.getProperty_cname();
				beanDatas[3][Integer.valueOf(dto.getColumn_no())] = dto.getIs_null();
				beanDatas[4][Integer.valueOf(dto.getColumn_no())] = dto.getCode();
			}
			if (StringUtils.isStringAvaliable(startRow)) {
				map.put(START_ROW, Integer.parseInt(startRow));
			}
			if (StringUtils.isStringAvaliable(headerRow)) {
				map.put(HEADER_ROW_NO, Integer.parseInt(headerRow));
			}
			map.put(BEAN_DATAS, beanDatas);
		}
		return map;
	}
}

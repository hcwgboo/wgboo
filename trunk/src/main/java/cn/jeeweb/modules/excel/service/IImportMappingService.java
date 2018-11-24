package cn.jeeweb.modules.excel.service;

import java.util.List;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.modules.excel.entity.ImportMapping;
import cn.jeeweb.modules.excel.entity.ImportMappingConfiguration;

/**   
 * @Title: 导入配置主表
 * @Description: 导入配置主表
 * @author zhangyouwei
 * @date 2018-02-14 09:37:17
 * @version V1.0   
 *
 */
public interface IImportMappingService extends ICommonService<ImportMapping> {

	List<ImportMappingConfiguration> queryImportMappingConfigurationByMappingTempletId(ImportMapping importMapping);

}


package cn.jeeweb.modules.excel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.excel.entity.ImportMapping;
import cn.jeeweb.modules.excel.entity.ImportMappingConfiguration;
import cn.jeeweb.modules.excel.mapper.ImportMappingMapper;
import cn.jeeweb.modules.excel.service.IImportMappingService;

/**
 * @Title: 导入配置主表
 * @Description: 导入配置主表
 * @author zhangyouwei
 * @date 2018-02-14 09:37:17
 * @version V1.0
 *
 */
@Transactional
@Service("importMappingService")
public class ImportMappingServiceImpl extends CommonServiceImpl<ImportMappingMapper, ImportMapping>
		implements IImportMappingService {

	@Override
	public List<ImportMappingConfiguration> queryImportMappingConfigurationByMappingTempletId(
			ImportMapping importMapping) {
		if (importMapping == null || StringUtils.isEmpty(importMapping.getMappingTemplet())) {
			return null;
		}
		return this.baseMapper.queryImportMappingConfigurationByMappingTempletId(importMapping.getMappingTemplet());
	}

}

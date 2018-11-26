package cn.jeeweb.modules.excel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.excel.entity.ImportColumnMapping;
import cn.jeeweb.modules.excel.mapper.ImportColumnMappingMapper;
import cn.jeeweb.modules.excel.service.IImportColumnMappingService;

/**   
 * @Title: 导入列配置表
 * @Description: 导入列配置表
 * @author zhangyouwei
 * @date 2018-02-14 09:59:27
 * @version V1.0   
 *
 */
@Transactional(rollbackFor = Exception.class)
@Service("importColumnMappingService")
public class ImportColumnMappingServiceImpl  extends CommonServiceImpl<ImportColumnMappingMapper,ImportColumnMapping> implements  IImportColumnMappingService {

}

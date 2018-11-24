package cn.jeeweb.modules.excel.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import cn.jeeweb.modules.excel.entity.ImportMapping;
import cn.jeeweb.modules.excel.entity.ImportMappingConfiguration;
 
/**   
 * @Title: 导入配置主表数据库控制层接口
 * @Description: 导入配置主表数据库控制层接口
 * @author zhangyouwei
 * @date 2018-02-14 09:37:17
 * @version V1.0   
 *
 */
public interface ImportMappingMapper extends BaseMapper<ImportMapping> {

	List<ImportMappingConfiguration> queryImportMappingConfigurationByMappingTempletId(String mappingTemplet);
    
}
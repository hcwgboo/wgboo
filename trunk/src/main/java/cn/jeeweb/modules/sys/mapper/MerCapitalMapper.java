package cn.jeeweb.modules.sys.mapper;


import cn.jeeweb.modules.sys.dto.MerCapitalDto;
import cn.jeeweb.modules.sys.entity.MerCapital;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * @Title: 上架资金数据库控制层接口
 * @Description: 上架资金数据库控制层接口
 * @author java
 * @date 2018-11-12 20:01:10
 * @version V1.0   
 *
 */
public interface MerCapitalMapper extends BaseMapper<MerCapital> {

    List<MerCapitalDto> selectMerCaptialPage(Page<MerCapitalDto> page, Map<String, Object> map);
    
}
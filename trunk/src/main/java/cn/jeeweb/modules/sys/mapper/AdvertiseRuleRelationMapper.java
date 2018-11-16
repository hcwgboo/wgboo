package cn.jeeweb.modules.sys.mapper;


import cn.jeeweb.modules.sys.dto.AdvertiseRuleRelationDto;
import cn.jeeweb.modules.sys.entity.AdvertiseRuleRelation;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * @Title: 广告规则数据库控制层接口
 * @Description: 广告规则数据库控制层接口
 * @author cql
 * @date 2018-11-12 19:55:20
 * @version V1.0   
 *
 */
public interface AdvertiseRuleRelationMapper extends BaseMapper<AdvertiseRuleRelation> {

    /**
     * 分页
     * @param page
     * @param map
     * @return
     */
    List<AdvertiseRuleRelationDto> selectAdvRuleRelationPage(Page<AdvertiseRuleRelationDto> page, Map<String, Object> map);

    /**
     * 查询
     * @param id
     * @return
     */
    AdvertiseRuleRelationDto selectAdvRuleById(String id);

    /**
     * 根据商家查询规则
     * @param merchantId
     * @return
     */
    List<AdvertiseRuleRelationDto> selectRuleByMercharntId(String merchantId);

}
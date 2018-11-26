package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.PageImpl;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.parse.QueryToWrapper;
import cn.jeeweb.modules.sys.dto.AdvertiseRuleRelationDto;
import cn.jeeweb.modules.sys.entity.AdvertiseRuleRelation;
import cn.jeeweb.modules.sys.mapper.AdvertiseRuleRelationMapper;
import cn.jeeweb.modules.sys.service.IAdvertiseRuleRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**   
 * @Title: 广告规则
 * @Description: 广告规则
 * @author cql
 * @date 2018-11-12 19:55:20
 * @version V1.0   
 *
 */
@Transactional(rollbackFor = Exception.class)
@Service("advertiseRuleRelationService")
public class AdvertiseRuleRelationServiceImpl  extends CommonServiceImpl<AdvertiseRuleRelationMapper, AdvertiseRuleRelation>
        implements IAdvertiseRuleRelationService {

    @Override
    public Page<AdvertiseRuleRelationDto> selectAdvRuleRelationPage(Queryable queryable, HttpServletRequest request) {

        QueryToWrapper<AdvertiseRuleRelation> queryToWrapper = new QueryToWrapper<>();
        Map<String, Object> map = queryToWrapper.conditionToMap(queryable);

        Pageable pageable = queryable.getPageable();
        com.baomidou.mybatisplus.plugins.Page<AdvertiseRuleRelationDto> page = new com.baomidou.mybatisplus.plugins.Page<>(
                pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(baseMapper.selectAdvRuleRelationPage(page, map));
        return new PageImpl<>(page.getRecords(), queryable.getPageable(), page.getTotal());
    }

    @Override
    public AdvertiseRuleRelationDto selectAdvRuleById(String id) {
        return baseMapper.selectAdvRuleById(id);
    }

    @Override
    public List<AdvertiseRuleRelationDto> selectRuleByMercharntId(String merchantId) {
        return baseMapper.selectRuleByMercharntId(merchantId);
    }
}

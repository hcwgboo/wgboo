package cn.jeeweb.modules.sys.service;


import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.sys.dto.AdvertiseRuleRelationDto;
import cn.jeeweb.modules.sys.entity.AdvertiseRuleRelation;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Title: 广告规则
 * @Description: 广告规则
 * @author cql
 * @date 2018-11-12 19:55:20
 * @version V1.0   
 *
 */
public interface IAdvertiseRuleRelationService extends ICommonService<AdvertiseRuleRelation> {

    /**
     * 分页
     * @param queryable
     * @param request
     * @return
     */
    Page<AdvertiseRuleRelationDto> selectAdvRuleRelationPage(Queryable queryable, HttpServletRequest request);


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


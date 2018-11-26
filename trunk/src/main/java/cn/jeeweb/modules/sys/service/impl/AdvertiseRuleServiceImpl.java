package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.entity.AdvertiseRule;
import cn.jeeweb.modules.sys.mapper.AdvertiseRuleMapper;
import cn.jeeweb.modules.sys.service.IAdvertiseRuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: 广告规则
 * @Description: 广告规则
 * @author cql
 * @date 2018-11-12 19:57:01
 * @version V1.0   
 *
 */
@Transactional(rollbackFor = Exception.class)
@Service("advAdvertiseRuleService")
public class AdvertiseRuleServiceImpl  extends CommonServiceImpl<AdvertiseRuleMapper, AdvertiseRule> implements IAdvertiseRuleService {

}

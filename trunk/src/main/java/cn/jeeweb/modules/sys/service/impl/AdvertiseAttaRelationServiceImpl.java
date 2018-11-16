package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.entity.AdvertiseAttaRelation;
import cn.jeeweb.modules.sys.mapper.AdvertiseAttaRelationMapper;
import cn.jeeweb.modules.sys.service.IAdvertiseAttaRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 广告
 * @Description: 广告
 * @author java
 * @date 2018-11-12 19:52:25
 * @version V1.0   
 *
 */
@Transactional
@Service("advertiseAttaRelationService")
public class AdvertiseAttaRelationServiceImpl  extends CommonServiceImpl<AdvertiseAttaRelationMapper, AdvertiseAttaRelation> implements IAdvertiseAttaRelationService {

}

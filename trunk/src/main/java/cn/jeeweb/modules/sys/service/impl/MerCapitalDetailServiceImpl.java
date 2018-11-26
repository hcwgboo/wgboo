package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.entity.MerCapitalDetail;
import cn.jeeweb.modules.sys.mapper.MerCapitalDetailMapper;
import cn.jeeweb.modules.sys.service.IMerCapitalDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 商家资金明细
 * @Description: 商家资金明细
 * @author java
 * @date 2018-11-12 20:01:38
 * @version V1.0   
 *
 */
@Transactional(rollbackFor = Exception.class)
@Service("merCapitalDetailService")
public class MerCapitalDetailServiceImpl  extends CommonServiceImpl<MerCapitalDetailMapper, MerCapitalDetail> implements IMerCapitalDetailService {

}

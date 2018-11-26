package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.entity.MemberCapitalDetail;
import cn.jeeweb.modules.sys.mapper.MemberCapitalDetailMapper;
import cn.jeeweb.modules.sys.service.IMemberCapitalDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 会员资金
 * @Description: 会员资金
 * @author java
 * @date 2018-11-12 19:53:29
 * @version V1.0   
 *
 */
@Transactional(rollbackFor = Exception.class)
@Service("memberCapitalDetailService")
public class MemberCapitalDetailServiceImpl  extends CommonServiceImpl<MemberCapitalDetailMapper, MemberCapitalDetail> implements IMemberCapitalDetailService {

}

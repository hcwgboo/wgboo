package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.entity.MemberCapital;
import cn.jeeweb.modules.sys.mapper.MemberCapitalMapper;
import cn.jeeweb.modules.sys.service.IMemberCapitalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 会员资金
 * @Description: 会员资金
 * @author cql
 * @date 2018-11-12 19:54:58
 * @version V1.0   
 *
 */
@Transactional(rollbackFor = Exception.class)
@Service("memberCapitalService")
public class MemberCapitalServiceImpl  extends CommonServiceImpl<MemberCapitalMapper, MemberCapital> implements IMemberCapitalService {

}

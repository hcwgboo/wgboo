package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.entity.Member;
import cn.jeeweb.modules.sys.mapper.MemberMapper;
import cn.jeeweb.modules.sys.service.IMemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 分销会员
 * @Description: 分销会员
 * @author java
 * @date 2018-11-12 19:57:41
 * @version V1.0   
 *
 */
@Transactional
@Service("memberService")
public class MemberServiceImpl  extends CommonServiceImpl<MemberMapper, Member> implements IMemberService {

}

package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.entity.MicroRadioCapitalDetail;
import cn.jeeweb.modules.sys.mapper.MicroRadioCapitalDetailMapper;
import cn.jeeweb.modules.sys.service.IMicroRadioCapitalDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 平台资金
 * @Description: 平台资金
 * @author java
 * @date 2018-11-12 19:54:35
 * @version V1.0   
 *
 */
@Transactional
@Service("microRadioCapitalDetailService")
public class MicroRadioCapitalDetailServiceImpl  extends CommonServiceImpl<MicroRadioCapitalDetailMapper, MicroRadioCapitalDetail> implements IMicroRadioCapitalDetailService {

}

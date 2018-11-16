package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.entity.MerCapital;
import cn.jeeweb.modules.sys.mapper.MerCapitalMapper;
import cn.jeeweb.modules.sys.service.IMerCapitalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 上架资金
 * @Description: 上架资金
 * @author java
 * @date 2018-11-12 20:01:10
 * @version V1.0   
 *
 */
@Transactional
@Service("merCapitalService")
public class MerCapitalServiceImpl  extends CommonServiceImpl<MerCapitalMapper, MerCapital> implements IMerCapitalService {

}

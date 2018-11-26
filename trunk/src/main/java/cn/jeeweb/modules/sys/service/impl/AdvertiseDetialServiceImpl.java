package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.entity.AdvertiseDetial;
import cn.jeeweb.modules.sys.mapper.AdvertiseDetialMapper;
import cn.jeeweb.modules.sys.service.IAdvertiseDetialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 广告详情
 * @Description: 广告详情
 * @author java
 * @date 2018-11-12 19:53:06
 * @version V1.0   
 *
 */
@Transactional(rollbackFor = Exception.class)
@Service("advertiseDetialService")
public class AdvertiseDetialServiceImpl  extends CommonServiceImpl<AdvertiseDetialMapper, AdvertiseDetial> implements IAdvertiseDetialService {

}

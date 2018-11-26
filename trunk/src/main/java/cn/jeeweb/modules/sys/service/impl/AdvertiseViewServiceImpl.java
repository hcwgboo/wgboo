package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.entity.AdvertiseView;
import cn.jeeweb.modules.sys.mapper.AdvertiseViewMapper;
import cn.jeeweb.modules.sys.service.IAdvertiseViewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 广告浏览
 * @Description: 广告浏览
 * @author cql
 * @date 2018-11-12 19:54:09
 * @version V1.0   
 *
 */
@Transactional(rollbackFor = Exception.class)
@Service("advertiseViewService")
public class AdvertiseViewServiceImpl  extends CommonServiceImpl<AdvertiseViewMapper, AdvertiseView> implements IAdvertiseViewService {

}

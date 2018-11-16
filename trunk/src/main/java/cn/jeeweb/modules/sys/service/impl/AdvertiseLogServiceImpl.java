package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.entity.AdvertiseLog;
import cn.jeeweb.modules.sys.mapper.AdvertiseLogMapper;
import cn.jeeweb.modules.sys.service.IAdvertiseLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 广告日志
 * @Description: 广告日志
 * @author cql
 * @date 2018-11-12 19:57:24
 * @version V1.0   
 *
 */
@Transactional
@Service("advertiseLogService")
public class AdvertiseLogServiceImpl  extends CommonServiceImpl<AdvertiseLogMapper, AdvertiseLog> implements IAdvertiseLogService {

}

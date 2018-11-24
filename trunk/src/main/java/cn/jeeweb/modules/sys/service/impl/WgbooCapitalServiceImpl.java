package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.mapper.WgbooCapitalMapper;
import cn.jeeweb.modules.sys.entity.WgbooCapital;
import cn.jeeweb.modules.sys.service.IWgbooCapitalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 平台资金总表
 * @Description: 平台资金总表
 * @author cql
 * @date 2018-11-19 22:41:36
 * @version V1.0   
 *
 */
@Transactional
@Service("wgbooCapitalService")
public class WgbooCapitalServiceImpl  extends CommonServiceImpl<WgbooCapitalMapper,WgbooCapital> implements  IWgbooCapitalService {

}

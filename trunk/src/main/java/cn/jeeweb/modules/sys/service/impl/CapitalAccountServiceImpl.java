package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sys.entity.CapitalAccount;
import cn.jeeweb.modules.sys.mapper.CapitalAccountMapper;
import cn.jeeweb.modules.sys.service.ICapitalAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 资金账号
 * @Description: 资金账号
 * @author java
 * @date 2018-11-12 19:56:39
 * @version V1.0   
 *
 */
@Transactional(rollbackFor = Exception.class)
@Service("capitalAccountService")
public class CapitalAccountServiceImpl  extends CommonServiceImpl<CapitalAccountMapper, CapitalAccount> implements ICapitalAccountService {

}

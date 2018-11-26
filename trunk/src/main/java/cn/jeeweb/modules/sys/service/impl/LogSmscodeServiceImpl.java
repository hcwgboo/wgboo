package cn.jeeweb.modules.sys.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.utils.ObjectUtils;
import cn.jeeweb.modules.sys.mapper.LogSmscodeMapper;
import cn.jeeweb.modules.sms.util.SmsCode;
import cn.jeeweb.modules.sys.entity.LogSmscode;
import cn.jeeweb.modules.sys.service.ILogSmscodeService;
import cn.jeeweb.modules.sys.utils.UserUtils;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;

/**   
 * @Title: 短信日志表
 * @Description: 短信日志表
 * @author yuanw
 * @date 2018-04-13 09:52:00
 * @version V1.0   
 *
 */
@Transactional
@Service("logSmscodeService")
public class LogSmscodeServiceImpl  extends CommonServiceImpl<LogSmscodeMapper,LogSmscode> implements  ILogSmscodeService {

	@Override
	public boolean sendSmsCode(LogSmscode sms) {
		 //发送验证码
        SendSmsResponse ssr = SmsCode.sendSms(sms);
        if (ObjectUtils.isNullOrEmpty(ssr)) 
        	return false;
        
        sms.setResult(JSON.toJSONString(ssr));
        sms.setCreateDate(new Date());
        sms.setCreateBy(UserUtils.getUser());
        baseMapper.insert(sms);

        return true;
	}

	@Override
	public String getSmsCode(LogSmscode sms) {
		// TODO Auto-generated method stub
		return null;
	}

}

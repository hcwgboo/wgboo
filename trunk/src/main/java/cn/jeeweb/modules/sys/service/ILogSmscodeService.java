package cn.jeeweb.modules.sys.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.modules.sys.entity.LogSmscode;

/**   
 * @Title: 短信日志表
 * @Description: 短信日志表
 * @author yuanw
 * @date 2018-04-13 09:52:00
 * @version V1.0   
 *
 */
public interface ILogSmscodeService extends ICommonService<LogSmscode> {

	 /**
     * 发送验证码
     * @return
     */
    public boolean sendSmsCode(LogSmscode sms);

    /**
     * 获取验证码对应的key
     * @return
     */
    public String getSmsCode(LogSmscode sms);
}


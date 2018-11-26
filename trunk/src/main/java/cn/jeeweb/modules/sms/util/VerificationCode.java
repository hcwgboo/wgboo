package cn.jeeweb.modules.sms.util;

import java.util.Random;

/**
 * @author yuanw
 * @version V1.0
 * @Title: 验证码
 * @Description:
 * @date 2018-04-13 10:16
 */
public interface VerificationCode {

    public static String getCode(int length){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i=0;i<length;i++){
            sb.append( random.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 
     * @Description:获取随机短信验证码
     * @date:2018年4月14日 上午9:11:38
     * @param:
     * @return
     * @throws
     *
     */
    public static String getDefCode(){
    	return getCode(6);
    }
    
}

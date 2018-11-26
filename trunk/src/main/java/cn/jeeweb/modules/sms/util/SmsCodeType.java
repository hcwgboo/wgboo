package cn.jeeweb.modules.sms.util;

/**
 * @author yuanw
 * @version V1.0
 * @Title:
 * @Description:
 * @date 2018-04-14 12:39
 */
public interface SmsCodeType {
    /**
     * 注册
     */
    public static final int  REGISTER  = 1;

    /**
     * 修改手机号
     */
    public static final int EDIT_PHONE = 2;
    
    
    /**
     * 修改密码
     */
    public static final int EDIT_PASSWORD = 3;
    
    /**
     * 解锁
     */
    public static final int UNLOCK = 4;
    
    /**
     * 更换分销
     */
    public static final int CHANGE_STORE = 5;
    
    
    /**
     * 更换分销或分销认证区分
     * */
    //分销认证
    public static final String STORE_TYPE = "1";
    //更换分销
    public static final String CHANGE_STORE_TYPE = "2";
    



}

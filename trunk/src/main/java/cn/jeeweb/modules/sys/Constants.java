package cn.jeeweb.modules.sys;

import cn.jeeweb.core.utils.JeewebPropertiesUtil;

public class Constants {
	// 字典缓存KEY
	public static final String CACHE_DICT_MAP = "cacheDictMap";
	public static final String CURRENT_USER = "systemuser";
	public static final String CURRENT_USERNAME = "systemusername";
	public static final String SESSION_FORCE_LOGOUT_KEY = "session.force.logout";
	/** 缓存命名空间 */
	static final String SYSTEM_CACHE_NAMESPACE = "S:conch:";

	public static final String ERROR = "error";
	public static final String SUCCESS = "error";
	public static final String MESSAGE = "message";
	/**
	 * 分销编码生成时间key
	 */
	public static final String STORE_CODE_DATE = "STORE_CODE_DATE";

	/**
	 * 每天生成分销的个数key
	 */
	public static final String GEN_STORE_NUM = "GEN_STORE_NUM";

	/** 定时任务 */
	public static final String SYSTEM = "SYSTEM";
	
	/** TOKEN */
	public static final String TOKEN_KEY = SYSTEM_CACHE_NAMESPACE + "TOKEN_KEY";
	
	/**图片压缩-是*/
	public static final Boolean COMPRESS_TRUE = true;
	
	/**图片压缩-否*/
	public static final Boolean COMPRESS_FALSE = false;
	/**公司名称*/
	public static final String PAYER_SHOW_NAME = "浙江房喜喜数据科技有限公司";
	/**支付备注*/
	public static final String PAY_REMARK = "";
	
	public static final String PAYER_ERROR_INFO = "支付异常，提现资金退回";
	
	public static final String PAYER_SUCCESS_INFO = "提现成功";
	
	public static final String USER_MENU = JeewebPropertiesUtil.getConfig("pc.redis.key") + "USER_MENU";
	
	public static final String JPUSH_REDIS_KEY = JeewebPropertiesUtil.getConfig("pc.redis.key") + "JPUSH_KEY";
	
	/**短信验证码key*/
	public static final String SMS_CODE = JeewebPropertiesUtil.getConfig("pc.redis.key") + "SMSCODE_";

	/**生成短信验证码key*/
	public static final String  DEF_CODE = "PC_DEF_CODE_";
	/**生成短信验证码有效时间*/
	public static final int DEF_CODE_TIME = 60*60;

	/** 注册 */
	public static final String REGISTER = "1";

	/**
	 * 随机生成用户名 key
	 */
	public static final String PC_GENETER_USERNAME_KEY = "PC_GENETER_USERNAME_KEY_";

	/**
	 * 随机生成用户名 时间(一个月)
	 */
	public static final int PC_GENETER_USERNAME_TIME = 60*60*24*30;

}

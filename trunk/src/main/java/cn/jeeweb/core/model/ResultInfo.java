package cn.jeeweb.core.model;

public class ResultInfo {
	/** 未知系统错误*/
	public static final String TYPE_RESULT_FAIL = "0";
	
	

	private int code;
	private String message;
	private String type;
	private String param;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "ResultInfo [code=" + code + ", msg=" + message + "]";
	}
}

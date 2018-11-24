package cn.jeeweb.core.exception;

import cn.jeeweb.core.model.ResultInfo;

public class ExceptionResultInfo extends Exception {
    
    private ResultInfo resultInfo;
    
    private String message;

    public ResultInfo getResultInfo() {
        return resultInfo;
    }
 
    public void setResultInfo(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
    }
    
 
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ExceptionResultInfo(ResultInfo resultInfo){
        //调用父类Exception的构造函数将resultInfo.getMessage()当做新的异常信息构造
        super(resultInfo.getMessage()); 
        this.resultInfo=resultInfo;
    }
    
    public ExceptionResultInfo(String message){
        super(message); 
        this.message =  message;
    }
}

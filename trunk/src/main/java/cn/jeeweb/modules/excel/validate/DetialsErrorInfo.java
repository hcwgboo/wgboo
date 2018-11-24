package cn.jeeweb.modules.excel.validate;

public class DetialsErrorInfo {
    //错误行
    private int errorRow;
    //错误信息
    private String errorMsg;
    
    public DetialsErrorInfo(int errorRow,String errorMsg){
        this.setErrorRow(errorRow);
        this.setErrorMsg(errorMsg);
    }
    
    public DetialsErrorInfo(){
        
    }
    
    public int getErrorRow() {
        return errorRow;
    }
    public void setErrorRow(int errorRow) {
        this.errorRow = errorRow;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    @Override
    public String toString() {
        return "第"+getErrorRow()+"行,"+"错误信息为:"+getErrorMsg();
    }
}

package cn.jeeweb.modules.sys.dto;

public enum  AttachmentEnum {

    condition1(1, "请重新上传：686*380的图片"),condition2(2, "请重新上传：200*200的图片");

    private Integer type;
    private String msg;

    AttachmentEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static String getValue(Integer type) {
        for (AttachmentEnum pt : AttachmentEnum.values()) {
            if (pt.getType()==type) {
                return pt.getMsg();
            }
        }
        return "";
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

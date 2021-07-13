package kr.co.maptics.mapticslogin.model;

public enum MapTicsResultCode {
    OK("2000"),
    NEED_ACCESS_TOKEN("4010"),
    INVALID_ACCESS_TOKEN("4011"),
    INSUFFICIENT_PERMISSION("4030"),
    APPLICATION_ERROR("5000");
    private String code;
    MapTicsResultCode(String code) {
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}

package com.bays.utils;

public enum ReturnCode {

    //TODO： 工具类 全局返回信息完善
    SUCCESS("0000","SUCCESS"),
    FAILD("0001","FAILED"),
    EXCEPTION("0002","EXCEPTION"),
    ERROR("0003","ERROR"),

    LOGIN_FAIL("LOGIN_FAIL","用户名或密码错误"),
    USER_EXISTS("USER_EXISTS","用户名已经存在"),
    EMAIL_VALID("EMAIL_VALID","注册成功!请前往邮箱激活您的账户,有效期三天！"),
    REGISTER_FALI("REGISTER_FALI","注册失败！！");

    String Code;
    String Msg;
    ReturnCode(String Code,String Msg) {
        this.Code=Code;
        this.Msg=Msg;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String respCode) {
        this.Code = respCode;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }
}
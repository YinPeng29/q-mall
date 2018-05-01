package com.bays.utils;

import com.alibaba.fastjson.JSONObject;

public enum ReturnCode {

    //TODO： 工具类 全局返回信息完善
    SUCCESS("0000","SUCCESS"),
    FAILD("0001","FAILED"),
    EXCEPTION("0002","EXCEPTION"),
    ERROR("0003","ERROR"),

    LOGIN_FAIL("0004","用户名或密码错误"),
    USER_EXISTS("0005","用户名已经存在"),
    EMAIL_VALID("0006","注册成功!请前往邮箱激活您的账户,有效期三天！"),
    REGISTER_FALI("0007","注册失败！！"),
    ERROR_EMPTY_IMG("0008","文件为空"),
    FIRST_SAVE("0009","请先保存信息");

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

    public JSONObject toJsonObject(JSONObject jsonObject){
        if(jsonObject==null) jsonObject = new JSONObject();
        jsonObject.put(Field.Common.RESP_CODE,Code);
        jsonObject.put(Field.Common.RESP_MSG,Msg);
        return jsonObject;
    }
}
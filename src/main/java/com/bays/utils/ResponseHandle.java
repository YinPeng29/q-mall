package com.bays.utils;

import com.alibaba.fastjson.JSONObject;

import javax.json.Json;

public class ResponseHandle {

    public JSONObject setResponse(String respcode,String respmsg) {
        JSONObject json = new JSONObject();
        json.put(Field.Common.RESP_CODE,respcode);
        json.put(Field.Common.RESP_MSG,respmsg);
        return json;
    }
    public JSONObject setResponse(ReturnCode returncode){
        JSONObject json = new JSONObject();
        json.put(Field.Common.RESP_CODE,returncode.getCode());
        json.put(Field.Common.RESP_MSG,returncode.getMsg());
        return json;
    }
    public JSONObject setResponse(String respcode,String respmsg,JSONObject data){
        JSONObject json = this.setResponse(respcode, respmsg);
        json.put(Field.Common.DATAS,data);
        return json;
    }
    public JSONObject setResponse(ReturnCode returnCode,JSONObject data){
        JSONObject json = this.setResponse(returnCode);
        json.put(Field.Common.DATAS,data);
        return json;
    }
}

package com.bays.utils.JwtBuilder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bays.utils.Field;
import com.bays.utils.SysUtil;

import javax.json.Json;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yinpeng on 2017/11/17.
 * JWT: java web token generator
 */
public class JwtBuilder {
    private String userId;
    private Date iat;    //签发时间登录当前时间
    private Date exptime;   //过期时间
    private String typ = "JWT";
    private String alg = "DEC";
    public JwtBuilder(String userId,Date iat,long ttlMillis){
        this.userId = userId;
        this.iat = iat;
        if (ttlMillis >= 0) {
            long expMillis = iat.getTime() + ttlMillis;
            Date exp = new Date(expMillis);
            this.exptime = exp;
        }
    }

    private String encodeHeader() throws Exception {
        JSONObject header = new JSONObject();
        header.put("typ",typ);
        header.put("alg",alg);
        String s = SysUtil.base64Encode(header.toJSONString());
        return s;
    }
    private String encodePayLoad() throws Exception {
        JSONObject payload = new JSONObject();
        payload.put("userId",userId);
        payload.put("iat",iat);
        payload.put("exptime",exptime);
        return SysUtil.base64Encode(payload.toJSONString());
    }

    /**
     * jwt 加密header+payload
     * @return
     * @throws Exception
     */
    public String compactJWT() throws Exception {
        String str = encodeHeader() + Field.spot + encodePayLoad();
        String encrypt = SysUtil.encrypt(str, Field.SECRETKEY);
        return encrypt;
    }

    /**
     * jwt 拆分方法 拆分加密code
     * @param data
     * @return
     * @throws Exception
     */
    public Map<String,JSONObject> inCompactJwt(String data) throws Exception {
        Map<String,JSONObject> map = new HashMap<String,JSONObject>();
        String decrypt = SysUtil.decrypt(data, Field.SECRETKEY);
        String[] split = decrypt.split("\\"+Field.spot);  //这里注意 直接使用 . 分割失败，要加上 \\
        JSONObject jsonHead = JSON.parseObject(SysUtil.base64Decode(split[0]));
        JSONObject jsonPayLoad = JSON.parseObject(SysUtil.base64Decode(split[1]));
        map.put("header",jsonHead);
        map.put("payload",jsonPayLoad);
        return map;
    }

}

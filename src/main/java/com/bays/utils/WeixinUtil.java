package com.bays.utils;

import com.bays.model.AccessToken;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by yinpeng on 2018/5/11.
 */
public class WeixinUtil {
    private static final String APPID = "wx1afa86abafa378a2";
    private static final String APPSECRET= "e1de0cceb53bd94e6e909d3c538a8065";
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public static JSONObject doGetStr(String url) throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if(entity!=null){
            String result = EntityUtils.toString(entity,"UTF-8");
            jsonObject = JSONObject.fromObject(result);
        }
        return jsonObject;
    }

    public static JSONObject doPostStr(String url,String outStr) throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
        HttpResponse execute = client.execute(httpPost);
        String result = EntityUtils.toString(execute.getEntity(), "UTF-8");
        jsonObject = JSONObject.fromObject(result);
        return jsonObject;
    }

    public static AccessToken getAccessToken() throws IOException {
        AccessToken accessToken = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID",APPID).replace("APPSECRET",APPSECRET);
        JSONObject jsonObject = doGetStr(url);
        if(jsonObject!=null){
            accessToken.setToken(jsonObject.getString("access_token"));
            accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
        }
        return accessToken;
    }

}
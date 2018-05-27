package com.bays.service.menu.impl;

import com.bays.model.AccessToken;
import com.bays.service.menu.MenuService;
import com.bays.utils.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by yinpeng on 2018/5/11.
 */
@Service
public class MenuServiceImpl implements MenuService {

    public static String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    public String CreatMenu(String jsonMenu) throws IOException {
        String resultStr = "";
        // 调用接口获取token
//        String token = WeixinUtil.getAccessToken().getToken();
        AccessToken accessToken = WeixinUtil.getAccessToken();
        String token = accessToken.getToken();
        if (token != null) {
            // 调用接口创建菜单
            int result = createMenu(jsonMenu, token);
            // 判断菜单创建结果
            if (0 == result) {
                resultStr = "菜单创建成功";
            } else {
                resultStr = "菜单创建失败，错误码：" + result;
            }
        }
        return resultStr;
    }

    public static int createMenu(String jsonMenu, String accessToken) throws IOException {
        int result = 0;
        // 拼装创建菜单的url
        String url = MENU_CREATE.replace("ACCESS_TOKEN", accessToken);
        // 调用接口创建菜单
        JSONObject jsonObject = WeixinUtil.doPostStr(url, jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
            }
        }
        return result;
    }
}
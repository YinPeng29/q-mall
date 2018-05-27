package com.bays.controller;

import com.bays.model.AccessToken;
import com.bays.service.core.CoreService;
import com.bays.service.menu.MenuService;
import com.bays.utils.Constant;
import com.bays.utils.SignUtil;
import com.bays.utils.WeixinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by yinpeng on 2018/5/11.
 */
@Controller
@RequestMapping("/weixin")
public class WeChatController {

    @Autowired
    CoreService coreService;
    @Autowired
    MenuService menuService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response){
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                System.out.println("校验...");
                System.out.println(echostr);
                out.print(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            out = null;
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void dopost(HttpServletRequest request, HttpServletResponse response){
        System.out.println("接收用户消息...");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        String respmessage = coreService.processRequest(request);
        PrintWriter out = null;
        try{
            out = response.getWriter();
            out.print(respmessage);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            out.close();
            out = null;
        }
    }

    @RequestMapping("/test")
    public String test() throws IOException {
        AccessToken accessToken = WeixinUtil.getAccessToken();
        System.out.println("token: "+accessToken.getToken());
        System.out.println("expires_in:"+accessToken.getExpiresIn());
        String jsonMenu = Constant.MENU;
        String s = menuService.CreatMenu(jsonMenu);
        System.out.println(s);
        return accessToken.toString();
    }
}


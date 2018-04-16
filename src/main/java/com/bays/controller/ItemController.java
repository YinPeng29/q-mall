package com.bays.controller;

import com.alibaba.fastjson.JSONObject;
import com.bays.model.itemInfo;
import com.bays.utils.ResponseHandle;
import com.bays.utils.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/item")
public class ItemController extends ResponseHandle {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @ResponseBody
    @RequestMapping(value = "/additem")
    public String addItem(itemInfo itemInfo, HttpServletRequest request){
        System.out.println(itemInfo.toString());
        String logo = request.getParameter("logo");
        System.out.println("respmsg: "+this.setResponse(ReturnCode.SUCCESS).toJSONString());
        JSONObject json = new JSONObject();
        json.put("respcode","0000");
        json.put("respmsg","123");
        return json.toString();
    }
}

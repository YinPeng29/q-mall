package com.bays.controller;

import com.alibaba.fastjson.JSONObject;
import com.bays.model.itemInfo;
import com.bays.service.item.ItemService;
import com.bays.utils.ResponseHandle;
import com.bays.utils.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/item")
public class ItemController extends ResponseHandle {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/additem")
    public String addItem(itemInfo itemInfo, HttpServletRequest request){
        System.out.println("itemInfo: "+super.toJsonString(itemInfo));
        int i = itemService.addItem(itemInfo);
        if(i>0){
            return setResponse(ReturnCode.SUCCESS).toJSONString();
        }
        return setResponse(ReturnCode.FAILD).toJSONString();
    }
}

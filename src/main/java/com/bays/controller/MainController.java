package com.bays.controller;

import com.alibaba.fastjson.JSONObject;
import com.bays.service.item.ItemService;
import com.bays.utils.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/queryItem")
    public String queryItemList(){
        List<Map> maps = itemService.queryItem();
        JSONObject json = new JSONObject();
        json.put("list",maps);
        json.put("total",maps.size());
        System.out.println(ReturnCode.SUCCESS.toJsonObject(json).toJSONString());
        return ReturnCode.SUCCESS.toJsonObject(json).toJSONString();
    }
}

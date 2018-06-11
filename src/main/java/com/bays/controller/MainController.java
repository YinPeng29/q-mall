package com.bays.controller;

import com.alibaba.fastjson.JSONObject;
import com.bays.service.item.ItemService;
import com.bays.utils.Field;
import com.bays.utils.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @ResponseBody
    @RequestMapping("/itemDetail")
    public JSONObject itemDetail(@RequestParam("id") String id, HttpServletRequest request){
        Map map = itemService.queryItemById(id);
        List<Map> maps = itemService.queryPicPath(id);
        JSONObject js = new JSONObject();
        js.put("itemInfo",map);
        for(int i=0;i<maps.size();i++){
            js.put("pic"+i,maps.get(i).get("img_path"));
        }
        js.put(Field.Common.RESP_CODE,"0000");
        js.put(Field.Common.RESP_MSG,"成功");
        System.out.println(ReturnCode.SUCCESS.toJsonObject(js).toJSONString());
        return js;
    }
}

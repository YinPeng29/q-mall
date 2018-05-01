package com.bays.controller;

import com.alibaba.fastjson.JSONObject;
import com.bays.model.ItemInfo;
import com.bays.service.item.ItemService;
import com.bays.utils.ResponseHandle;
import com.bays.utils.ReturnCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/item")
public class ItemController extends ResponseHandle {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    ItemService itemService;
    @Value("${windows.filepath}")
    private String windows_filepath;
    @Value("${mac.filepath}")
    private String mac_filepath;

    @ResponseBody
    @RequestMapping(value = "/additem")
    public String addItem(ItemInfo itemInfo, HttpServletRequest request){
        System.out.println("itemInfo: "+super.toJsonString(itemInfo));
        int i = itemService.addItem(itemInfo);
        if(i>0){
            return setResponse(ReturnCode.SUCCESS).toJSONString();
        }
        return setResponse(ReturnCode.FAILD).toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/queryItem")
    public String queryItemList(){
        List<Map> maps = itemService.queryItem();
        JSONObject json = new JSONObject();
        json.put("datas",maps);
        json.put("total",maps.size());
        System.out.println(ReturnCode.SUCCESS.toJsonObject(json).toJSONString());
        return ReturnCode.SUCCESS.toJsonObject(json).toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/uploadImg")
    public String uploadHeadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String itemId = request.getParameter("itemId");
        if(StringUtils.isBlank(itemId)){
            return setResponse(ReturnCode.FIRST_SAVE).toJSONString();
        }
        String path = "";
        if(file.isEmpty()){
            return setResponse(ReturnCode.ERROR_EMPTY_IMG).toJSONString();
        }
        String originalFilename = file.getOriginalFilename();
        int size = (int)file.getSize();
        System.out.println(originalFilename+"-->" + size);
        System.out.println("system_type: "+ System.getProperty("os.name"));
        if("Mac OS X".equals(System.getProperty("os.name"))){  //判断当前操作系统类型
            path = mac_filepath;
        }else{
            path = windows_filepath;
        }
        File dest = new File(path+"/"+originalFilename);
        if(dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
            return setResponse(ReturnCode.SUCCESS).toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
            return setResponse(ReturnCode.FAILD).toJSONString();
        }
    }
}
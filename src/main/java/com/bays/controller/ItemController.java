package com.bays.controller;

import com.alibaba.fastjson.JSONObject;
import com.bays.model.ItemInfo;
import com.bays.service.item.ItemService;
import com.bays.utils.Field;
import com.bays.utils.ResponseHandle;
import com.bays.utils.ReturnCode;
import com.bays.utils.SysUtil;
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
    @Value("${dir.path}")
    private String dir_path;

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
        System.out.println("file size: " + originalFilename+"-->" + size);
        System.out.println("system_type: "+ System.getProperty("os.name"));
        System.out.println("real path: "+ System.getProperty("path.webapp"));

        if("Mac OS X".equals(System.getProperty("os.name"))){  //判断当前操作系统类型
            path = mac_filepath;
        }else{
            path = windows_filepath;
        }
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String newFileName = SysUtil.randomUUID()+Field.spot+suffix; //重组文件名
        String picPath = dir_path+newFileName;
        System.out.println("后缀名: "+suffix+" 新文件名: "+newFileName+" 路径: "+picPath);
        File dest = new File(path+picPath); //防止图片名重复生成随机数
        if(dest.getParentFile().exists()){
            //  问题就是.mkdirs(); 这个方法只能生成一层一层的文件夹，
            // 不能生成文件，而你的file对象路径是直接到文件那一层的，
            // 不用getParentFile()获得父目录的话，
            // 就会想你说的那样生成两个文件夹而不是你想要的文件，
            // 所以要先调用getParentFile()获得父目录，
            // 用.mkdirs()生成父目录文件夹，最后把你想要的文件生成到这个文件夹下面，就是想要的结果。
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
            //将图片路径保存到数据库中path=dir_path+originalFilename
            int i = itemService.updatePic(picPath, itemId);
            if(i>0){
                return setResponse(ReturnCode.SUCCESS).toJSONString();
            }
            return setResponse(ReturnCode.FAILD).toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
            return setResponse(ReturnCode.FAILD).toJSONString();
        }
    }
}
package com.bays.service.item.impl;

import com.bays.dao.ItemMapper;
import com.bays.model.ItemInfo;
import com.bays.service.item.ItemService;
import com.bays.utils.SysUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemMapper itemMapper;

    public int addItem(ItemInfo itemInfo) {
        int i = itemMapper.addItem(itemInfo);
        return i;
    }

    public int updateItem(ItemInfo itemInfo) {
        int i = itemMapper.updateItem(itemInfo);
        return i;
    }

    public List<Map> queryItem() {
        List<Map> itemMap = itemMapper.queryItem();
        System.out.println(itemMap.size());
        return itemMap;
    }
    public int addPic(String path,String itemId) {
        Map map = this.queryItemById(itemId);
//        String pic_path = (String) map.get("pic_path");
        if(StringUtils.isBlank((String)map.get("pic_path")) ){
            int i = itemMapper.updatePic(path, itemId);
            if(i==0){
                return 0;
            }
        }
        int i = itemMapper.addPic(path,itemId);
        return i;
    }

    public Map queryItemById(String itemId) {
        Map map = itemMapper.queryItemById(itemId);
        return map;
    }

//    public Map queryItemDetail(String id) {
//        Map map = itemMapper.queryItemDetail(id);
//        return map;
//    }

    public List<Map> queryPicPath(String itemId) {
        List<Map> maps = itemMapper.queryItemPic(itemId);
        return maps;
    }
}

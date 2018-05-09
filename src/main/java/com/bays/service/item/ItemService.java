package com.bays.service.item;

import com.bays.model.ItemInfo;

import java.util.List;
import java.util.Map;

public interface ItemService {
    int addItem(ItemInfo itemInfo);
    int updateItem(ItemInfo itemInfo);
    List<Map> queryItem();
    int addPic(String path,String itemId);
}

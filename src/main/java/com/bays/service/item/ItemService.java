package com.bays.service.item;

import com.bays.model.ItemInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ItemService {
    int addItem(ItemInfo itemInfo);
    int updateItem(ItemInfo itemInfo);
    List<Map> queryItem();
    Map queryItemById(String itemId);
    int addPic(String path,String itemId);
//    int updatePic(String picPath,String ItemId);
//    Map queryItemDetail(String id);
    List<Map> queryPicPath(String itemId);

    //cart
    int addCart(String id,String userId,String itemId,String num,Date createdate);
    List<Map> queryCartInfo(String userId);
}

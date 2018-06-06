package com.bays.dao;
import com.bays.model.ItemInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yinpeng on 2018/4/17.
 */

@Repository
public interface ItemMapper {
    /**
     * 添加商品
     */
    int addItem(@Param("item")ItemInfo itemInfo);
    int updateItem(@Param("item") ItemInfo itemInfo);
    List<Map> queryItem();
    Map queryItemById(@Param("itemId") String itemId);
    int addPic(@Param("img_path") String path,@Param("itemId") String itemId);
    int updatePic(@Param("pic_path") String pic_path,@Param("itemId") String itemId);
}


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
    int updatePic(@Param("item_path") String path,@Param("itemId") String itemId);
}


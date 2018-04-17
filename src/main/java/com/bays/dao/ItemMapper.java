package com.bays.dao;
import com.bays.model.itemInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by yinpeng on 2018/4/17.
 */

@Repository
public interface ItemMapper {
    /**
     * 添加商品
     */
    int addItem(@Param("item")itemInfo itemInfo);
}


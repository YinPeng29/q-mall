package com.bays.service.item.impl;

import com.bays.dao.ItemMapper;
import com.bays.model.itemInfo;
import com.bays.service.item.ItemService;
import com.bays.utils.SysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemMapper itemMapper;
    public int addItem(itemInfo itemInfo) {
        itemInfo.setId(SysUtil.randomUUID());
        int i = itemMapper.addItem(itemInfo);
        return i;
    }
}

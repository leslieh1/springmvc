package com.emuii.ssm.service;

import com.emuii.ssm.po.ItemsCustom;
import com.emuii.ssm.po.ItemsQueryVo;

import java.util.List;

/**
 * Create by Leslie on 2018\1\8 0008.<br>
 */
public interface ItemsService {

    // 查询所有订单
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

    // 根据订单查询商品
    public ItemsCustom findItemsById(Integer id) throws Exception;

    // 修改订单
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;
}

package com.emuii.ssm.mapper;

import com.emuii.ssm.po.ItemsCustom;
import com.emuii.ssm.po.ItemsQueryVo;

import java.util.List;

/**
 * Create by Leslie on 2018\1\8 0008.<br>
 */
public interface ItemsMapperCustom {

    // 查询所有订单
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}

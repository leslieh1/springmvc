package com.emuii.ssm.service.impl;

import com.emuii.ssm.exception.CustomException;
import com.emuii.ssm.mapper.ItemsMapper;
import com.emuii.ssm.mapper.ItemsMapperCustom;
import com.emuii.ssm.po.Items;
import com.emuii.ssm.po.ItemsCustom;
import com.emuii.ssm.po.ItemsQueryVo;
import com.emuii.ssm.service.ItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Create by Leslie on 2018\1\8 0008.<br>
 */
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;
    @Autowired
    private ItemsMapper itemsMapper;


    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {

        return itemsMapperCustom.findItemsList(itemsQueryVo);
    }

    @Override
    public ItemsCustom findItemsById(Integer id) throws Exception {

        // 通过逆向工程获得的mapper返回的是pojo类型
        Items items = itemsMapper.selectByPrimaryKey(id);

        if(items == null){
            throw new CustomException("没有查询的商品");
    }
        // 在这里随着需求的变化，需要查询商品的其他相关信息，返回到controller
        ItemsCustom itemsCustom = new ItemsCustom();

        // 将items的属性拷贝到itemsCustom中
        BeanUtils.copyProperties(items, itemsCustom);

        return itemsCustom;
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {

        // 对于关键数据的非空校验
        if(id == null){
            // 抛出异常，提示调用接口的用户，id不能为空
            // ...
        }
//        itemsMapper.updateByPrimaryKey(itemsCustom);
        // 更新带有大文本数据的对象
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }
}

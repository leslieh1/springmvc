package com.emuii.ssm.po;

import java.util.List;

/**
 * Create by Leslie on 2018\1\8 0008.<br>
 */
public class ItemsQueryVo {

    private ItemsCustom itemsCustom;

    private List<ItemsCustom> itemsCustoms;

    public List<ItemsCustom> getItemsCustoms() {
        return itemsCustoms;
    }

    public void setItemsCustoms(List<ItemsCustom> itemsCustoms) {
        this.itemsCustoms = itemsCustoms;
    }

    public ItemsCustom getItemsCustom() {
        return itemsCustom;
    }

    public void setItemsCustom(ItemsCustom itemsCustom) {
        this.itemsCustom = itemsCustom;
    }
}

package com.altiora.tracking.client.service;

import com.altiora.tracking.vo.ItemVo;

import java.util.Collection;

/**
 * @author jyepez on 7/9/2024
 */
public interface IItemService {

    /**
     * Create Item
     *
     * @param itemVo ItemVo
     * @return ItemVo
     */
    ItemVo create(ItemVo itemVo);

    /**
     * Find Item By ItemCode
     *
     * @param itemCode String
     * @return ItemVo
     */
    ItemVo findByItemCode(String itemCode);

    /**
     * Find All Items
     *
     * @return Collection ItemVo
     */
    Collection<ItemVo> findAll();
}

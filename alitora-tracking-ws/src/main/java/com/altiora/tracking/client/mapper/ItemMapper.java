package com.altiora.tracking.client.mapper;

import com.altiora.tracking.client.entity.ItemEntity;
import com.altiora.tracking.vo.ItemVo;
import org.mapstruct.Mapper;

import java.util.Collection;

/**
 * @author jyepez on 7/9/2024
 */
@Mapper(componentModel = "spring")
public interface ItemMapper {

    /**
     * To Entity
     *
     * @param itemVo ItemVo
     * @return ItemEntity
     */
    ItemEntity toEntity(ItemVo itemVo);

    /**
     * To ItemVo
     *
     * @param item ItemEntity
     * @return ItemVo
     */
    ItemVo toItemVo(ItemEntity item);

    /**
     * To Collection ItemVo
     *
     * @param items Collection ItemEntity
     * @return Collection ItemVo
     */
    Collection<ItemVo> toCollectionItemVo(Collection<ItemEntity> items);
}

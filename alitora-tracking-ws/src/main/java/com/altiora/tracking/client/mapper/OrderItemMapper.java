package com.altiora.tracking.client.mapper;

import com.altiora.tracking.client.entity.OrderItemEntity;
import com.altiora.tracking.vo.OrderItemVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;

/**
 * @author jyepez on 8/9/2024
 */
@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemEntity toOrderItem(OrderItemVo orderItemVo);

    @Mappings({
            @Mapping(target = "itemCode", source = "item.itemCode"),
            @Mapping(target = "name", source = "item.name"),
            @Mapping(target = "unitPrice", source = "item.unitPrice"),
    })
    OrderItemVo toOrderItemVo(OrderItemEntity orderItemEntity);

    Collection<OrderItemVo> toCollectionOrderItemVo(Collection<OrderItemEntity> orderItemEntity);
}

package com.altiora.tracking.client.mapper;

import com.altiora.tracking.client.entity.OrderEntity;
import com.altiora.tracking.vo.OrderVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

/**
 * @author jyepez on 8/9/2024
 */
@Mapper(componentModel = "spring", uses = {
        CustomerMapper.class,
        ItemMapper.class,
        OrderItemMapper.class
})
public interface OrderMapper {

    @Mapping(target = "orderItems", source = "orderItems")
    OrderVo toOrderVo(OrderEntity order);

    Collection<OrderVo> toCollectionOrderVo(Collection<OrderEntity> orders);

    @Mapping(target = "orderItems", ignore = true)
    OrderEntity toOrder(OrderVo orderVo);
}

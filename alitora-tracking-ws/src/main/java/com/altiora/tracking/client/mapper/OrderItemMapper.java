package com.altiora.tracking.client.mapper;

import com.altiora.tracking.client.entity.OrderItemEntity;
import com.altiora.tracking.vo.OrderItemVo;
import org.mapstruct.Mapper;

/**
 * @author jyepez on 8/9/2024
 */
@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemEntity toOrderItem(OrderItemVo orderItemVo);

    OrderItemVo toOrderItemVo(OrderItemEntity orderItemEntity);
}

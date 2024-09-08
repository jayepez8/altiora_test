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
        CustomerMapper.class
})
public interface OrderMapper {

    OrderVo toOrderVo(OrderEntity order);

    Collection<OrderVo> toCollectionOrderVo(Collection<OrderEntity> orders);

    OrderEntity toOrder(OrderVo orderVo);
}

package com.altiora.tracking.client.service;

import com.altiora.tracking.vo.OrderVo;

import java.util.Collection;

/**
 * @author jyepez on 8/9/2024
 */
public interface IOrderService {

    /**
     * Create order
     *
     * @param orderVo OrderVo
     * @return OrderVo
     */
    OrderVo create(OrderVo orderVo);

    /**
     * Find Order By OrderCode
     *
     * @param orderCode String
     * @return OrderVo
     */
    OrderVo findByOrderCode(String orderCode);

    /**
     * Find All Orders
     *
     * @return Collection OrderVo
     */
    Collection<OrderVo> findAll();
}

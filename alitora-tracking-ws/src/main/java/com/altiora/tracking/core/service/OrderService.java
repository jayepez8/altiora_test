package com.altiora.tracking.core.service;

import com.altiora.tracking.client.entity.CustomerEntity;
import com.altiora.tracking.client.entity.OrderEntity;
import com.altiora.tracking.client.exception.ExistException;
import com.altiora.tracking.client.exception.NotFoundException;
import com.altiora.tracking.client.exception.PersistException;
import com.altiora.tracking.client.mapper.OrderMapper;
import com.altiora.tracking.client.repository.IOrderRepository;
import com.altiora.tracking.client.service.ICustomerService;
import com.altiora.tracking.client.service.IOrderService;
import com.altiora.tracking.vo.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * @author jyepez on 8/9/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ICustomerService customerService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OrderVo create(OrderVo orderVo) {
        if (this.orderRepository.existsByOrderCode(orderVo.getOrderCode())) {
            throw new ExistException("Error the order already exists");
        }
        try {
            CustomerEntity customer = this.customerService.findCustomerByIdentification(orderVo.getIdentification());
            OrderEntity order = this.orderMapper.toOrder(orderVo);
            order.setCustomer(customer);
            OrderEntity orderSave = this.orderRepository.save(order);
            return this.orderMapper.toOrderVo(orderSave);
        } catch (Exception e) {
            throw new PersistException("A problem occurred, the order could not be saved");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrderVo findByOrderCode(String orderCode) {
        OrderEntity order = this.orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new NotFoundException("No order found with the order code " + orderCode));
        return this.orderMapper.toOrderVo(order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<OrderVo> findAll() {
        Collection<OrderEntity> orders = this.orderRepository.findAll();
        return this.orderMapper.toCollectionOrderVo(orders);
    }
}

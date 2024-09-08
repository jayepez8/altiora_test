package com.altiora.tracking.core.service;

import com.altiora.tracking.client.common.TrackingUtils;
import com.altiora.tracking.client.entity.CustomerEntity;
import com.altiora.tracking.client.entity.ItemEntity;
import com.altiora.tracking.client.entity.OrderEntity;
import com.altiora.tracking.client.entity.OrderItemEntity;
import com.altiora.tracking.client.exception.ExistException;
import com.altiora.tracking.client.exception.NotFoundException;
import com.altiora.tracking.client.exception.PersistException;
import com.altiora.tracking.client.mapper.OrderItemMapper;
import com.altiora.tracking.client.mapper.OrderMapper;
import com.altiora.tracking.client.repository.IOrderItemRepository;
import com.altiora.tracking.client.repository.IOrderRepository;
import com.altiora.tracking.client.service.ICustomerService;
import com.altiora.tracking.client.service.IItemService;
import com.altiora.tracking.client.service.IOrderService;
import com.altiora.tracking.vo.NextCodeVo;
import com.altiora.tracking.vo.OrderItemVo;
import com.altiora.tracking.vo.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

import static com.altiora.tracking.client.common.TrackingConstants.PREFIX_ORDER;

/**
 * @author jyepez on 8/9/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IOrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ICustomerService customerService;
    private final IItemService iItemService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OrderVo create(OrderVo orderVo) {
        if (this.orderRepository.existsByOrderCode(orderVo.getOrderCode())) {
            throw new ExistException("Error the order already exists");
        }
        if(CollectionUtils.isEmpty(orderVo.getOrderItems())){
            throw new PersistException("A problem occurred, there are no items in the order");
        }
        try {
            CustomerEntity customer = this.customerService.findCustomerByIdentification(orderVo.getIdentification());
            OrderEntity order = this.orderMapper.toOrder(orderVo);
            order.setCustomer(customer);
            OrderEntity orderSave = this.orderRepository.save(order);

            Collection<OrderItemEntity> orderItems = new ArrayList<>();

            for(OrderItemVo orderItemVo : orderVo.getOrderItems()){
                OrderItemEntity orderItem = this.orderItemMapper.toOrderItem(orderItemVo);
                orderItem.setOrder(orderSave);
                ItemEntity item = this.iItemService.findItemEntityByItemCode(orderItemVo.getItemCode());
                orderItem.setItem(item);
                orderItems.add(orderItem);
            }
            this.orderItemRepository.saveAll(orderItems);
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
        Collection<OrderEntity> orders = this.orderRepository.findAllByOrderByOrderDateDesc();
        return this.orderMapper.toCollectionOrderVo(orders);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NextCodeVo getNextOrderCode() {
        String code = TrackingUtils.generateRandomStringCode(PREFIX_ORDER);
        return NextCodeVo.builder().code(code).build();
    }
}

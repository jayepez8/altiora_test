package com.altiora.tracking.client.repository;

import com.altiora.tracking.client.entity.OrderItemEntity;
import com.altiora.tracking.client.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jyepez on 7/9/2024
 */
@Repository
public interface IOrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemId> {
}

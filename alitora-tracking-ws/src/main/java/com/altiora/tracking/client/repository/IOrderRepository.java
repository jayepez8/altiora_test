package com.altiora.tracking.client.repository;

import com.altiora.tracking.client.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author jyepez on 7/9/2024
 */
@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    /**
     * Exists By OrderCode
     *
     * @param orderCode String
     * @return Boolean
     */
    Boolean existsByOrderCode(String orderCode);

    /**
     * Find By OrderCode
     *
     * @param orderCode String
     * @return Optional OrderEntity
     */
    Optional<OrderEntity> findByOrderCode(String orderCode);
}

package com.altiora.tracking.client.repository;

import com.altiora.tracking.client.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jyepez on 7/9/2024
 */
@Repository
public interface IItemRepository extends JpaRepository<ItemEntity, Long> {
}

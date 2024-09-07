package com.altiora.tracking.client.repository;

import com.altiora.tracking.client.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author jyepez on 7/9/2024
 */
@Repository
public interface IItemRepository extends JpaRepository<ItemEntity, Long> {

    /**
     * Exists By ItemCode
     *
     * @param itemCode String
     * @return Boolean
     */
    Boolean existsByItemCode(String itemCode);

    /**
     * Find Item By ItemCode
     *
     * @param itemCode String
     * @return Optional ItemEntity
     */
    Optional<ItemEntity> findByItemCode(String itemCode);
}

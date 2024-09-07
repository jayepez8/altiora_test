package com.altiora.tracking.client.repository;

import com.altiora.tracking.client.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author jyepez on 7/9/2024
 */
@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

    /**
     * Exists By Identification
     *
     * @param identification String
     * @return Boolean
     */
    Boolean existsByIdentification(String identification);

    /**
     * Find By Identification
     *
     * @param identification String
     * @return Optional CustomerEntity
     */
    Optional<CustomerEntity> findByIdentification(String identification);
}

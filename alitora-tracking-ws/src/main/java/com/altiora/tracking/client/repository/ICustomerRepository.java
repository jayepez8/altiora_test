package com.altiora.tracking.client.repository;

import com.altiora.tracking.client.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * @author jyepez on 7/9/2024
 */
@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

    /**
     * Exists Customer By Identification
     *
     * @param identification String
     * @return Boolean
     */
    Boolean existsByIdentification(String identification);

    /**
     * Find Customer By Identification
     *
     * @param identification String
     * @return Optional CustomerEntity
     */
    Optional<CustomerEntity> findByIdentification(String identification);

    /**
     * Find All Customers Order By CreateDate Desc
     *
     * @return Collection CustomerEntity
     */
    Collection<CustomerEntity> findAllByOrderByCreateDateDesc();
}

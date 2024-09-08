package com.altiora.tracking.client.service;

import com.altiora.tracking.client.entity.CustomerEntity;
import com.altiora.tracking.vo.CustomerVo;

import java.util.Collection;

/**
 * @author jyepez on 7/9/2024
 */
public interface ICustomerService {

    /**
     * Create Customer
     *
     * @param customerVo CustomerVo
     * @return CustomerVo
     */
    CustomerVo create(CustomerVo customerVo);

    /**
     * Find Customer By Identification
     *
     * @param identification String
     * @return CustomerVo
     */
    CustomerVo findByIdentification(String identification);

    /**
     * Find All Customers
     *
     * @return Collection CustomerVo
     */
    Collection<CustomerVo> findAll();

    /**
     * Find Customer By Identification
     *
     * @param identification String
     * @return CustomerEntity
     */
    CustomerEntity findCustomerByIdentification(String identification);
}

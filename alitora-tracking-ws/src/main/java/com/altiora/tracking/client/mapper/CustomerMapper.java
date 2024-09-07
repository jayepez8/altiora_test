package com.altiora.tracking.client.mapper;

import com.altiora.tracking.client.entity.CustomerEntity;
import com.altiora.tracking.vo.CustomerVo;
import org.mapstruct.Mapper;

import java.util.Collection;

/**
 * @author jyepez on 7/9/2024
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    /**
     * To Customer
     *
     * @param customerVo CustomerVo
     * @return CustomerEntity
     */
    CustomerEntity toCustomer(CustomerVo customerVo);

    /**
     * To CustomerVo
     *
     * @param customer CustomerEntity
     * @return CustomerVo
     */
    CustomerVo toCustomerVo(CustomerEntity customer);

    /**
     * To Collection CustomerVo
     *
     * @param customers Collection CustomerEntity
     * @return Collection CustomerVo
     */
    Collection<CustomerVo> toCollectionCustomerVo(Collection<CustomerEntity> customers);
}

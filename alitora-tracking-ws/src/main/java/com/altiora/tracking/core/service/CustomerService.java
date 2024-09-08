package com.altiora.tracking.core.service;

import com.altiora.tracking.client.entity.CustomerEntity;
import com.altiora.tracking.client.exception.ExistException;
import com.altiora.tracking.client.exception.NotFoundException;
import com.altiora.tracking.client.exception.PersistException;
import com.altiora.tracking.client.mapper.CustomerMapper;
import com.altiora.tracking.client.repository.ICustomerRepository;
import com.altiora.tracking.client.service.ICustomerService;
import com.altiora.tracking.vo.CustomerVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * @author jyepez on 7/9/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public CustomerVo create(CustomerVo customerVo) {
        if (this.customerRepository.existsByIdentification(customerVo.getIdentification())) {
            throw new ExistException("Error the customer already exists");
        }
        try {
            CustomerEntity customer = this.customerRepository.save(this.customerMapper.toCustomer(customerVo));
            return this.customerMapper.toCustomerVo(customer);
        } catch (Exception e) {
            throw new PersistException("A problem occurred, the customer could not be saved");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerVo findByIdentification(String identification) {
        CustomerEntity customer = findCustomerByIdentification(identification);
        return this.customerMapper.toCustomerVo(customer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<CustomerVo> findAll() {
        Collection<CustomerEntity> customers = this.customerRepository.findAll();
        return this.customerMapper.toCollectionCustomerVo(customers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerEntity findCustomerByIdentification(String identification) {
        return this.customerRepository.findByIdentification(identification)
                .orElseThrow(() -> new NotFoundException("No customer found with the identification " + identification));
    }
}

package com.altiora.tracking.controller;

import com.altiora.tracking.client.service.ICustomerService;
import com.altiora.tracking.vo.CustomerVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Collection;

import static com.altiora.tracking.client.common.TrackingConstants.V1_API_VERSION;

/**
 * @author jyepez on 7/9/2024
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("customer" + V1_API_VERSION)
public class CustomerController {

    private final ICustomerService customerService;

    /**
     * Create Customer
     *
     * @param customerVo CustomerVo
     * @return CustomerVo
     */
    @PostMapping()
    public ResponseEntity<CustomerVo> create(@RequestBody @Valid CustomerVo customerVo){
        CustomerVo response =this.customerService.create(customerVo);
        return ResponseEntity.ok().body(response);
    }

    /**
     * Find All Customers
     *
     * @return Collection CustomerVo
     */
    @GetMapping()
    public ResponseEntity<Collection<CustomerVo>> findAll() {
        Collection<CustomerVo> response = this.customerService.findAll();
        return ResponseEntity.ok().body(response);
    }

    /**
     * Find Customer By Identification
     *
     * @param identification String
     * @return CustomerVo
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerVo> findByIdentification(@PathVariable("id") String identification){
        CustomerVo response = this.customerService.findByIdentification(identification);
        return ResponseEntity.ok().body(response);
    }
}

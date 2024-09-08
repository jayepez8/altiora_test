package com.altiora.tracking.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author jyepez on 8/9/2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderVo {

    @NotEmpty(message = "Order Code is required")
    private String orderCode;
    @NotNull(message = "Order Date is required")
    private Date orderDate;
    @NotEmpty(message = "Identification is required")
    private String identification;
    private CustomerVo customer;
}

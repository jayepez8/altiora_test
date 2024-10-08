package com.altiora.tracking.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
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
    @NotEmpty(message = "Identification is required")
    private String identification;
    @NotNull(message = "Total order is required")
    private double totalOrder;
    private Date orderDate;
    private CustomerVo customer;
    private Collection<OrderItemVo> orderItems;
}

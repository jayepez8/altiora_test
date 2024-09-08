package com.altiora.tracking.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author jyepez on 8/9/2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemVo {

    @NotEmpty(message = "Order Code is required")
    private String orderCode;
    @NotEmpty(message = "Item code is required")
    private String itemCode;
    @NotNull(message = "Quantity is required")
    private int quantity;
    @NotNull(message = "Total price is required")
    private double totalPrice;

    private String name;
    private double unitPrice;
}

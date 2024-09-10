package com.altiora.tracking.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author jyepez on 7/9/2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemVo {

    @NotEmpty(message = "Item code is required")
    private String itemCode;
    @NotEmpty(message = "Name is required")
    private String name;
    @NotNull(message = "Unit price is required")
    private double unitPrice;
    @NotNull(message = "Stock is required")
    private int stock;
}

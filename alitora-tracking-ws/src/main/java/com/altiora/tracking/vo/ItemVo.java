package com.altiora.tracking.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @author jyepez on 7/9/2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemVo {

    private String itemCode;
    private String name;
    private double unitPrice;
}

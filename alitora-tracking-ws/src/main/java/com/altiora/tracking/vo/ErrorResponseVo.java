package com.altiora.tracking.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * @author jyepez on 7/9/2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseVo {

    private Integer code;
    private String message;
    private Collection<String> errors;
}

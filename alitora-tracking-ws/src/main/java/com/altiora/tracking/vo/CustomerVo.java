package com.altiora.tracking.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author jyepez on 7/9/2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVo {

    @NotEmpty(message = "Identification is required")
    private String identification;
    @NotEmpty(message = "First Name is required")
    private String firstName;
    @NotEmpty(message = "Last Name is required")
    private String lastName;
}

package com.altiora.tracking.client.exception;

import lombok.Getter;

/**
 * @author jyepez on 7/9/2024
 */
@Getter
public class NotFoundException extends RuntimeException  {

    public NotFoundException(String message) {
        super(message);
    }
}

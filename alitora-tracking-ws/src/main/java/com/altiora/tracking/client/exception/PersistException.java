package com.altiora.tracking.client.exception;

import lombok.Getter;

/**
 * @author jyepez on 7/9/2024
 */
@Getter
public class PersistException extends RuntimeException {

    public PersistException(String message) {
        super(message);
    }
}

package com.altiora.tracking.client.common;

import java.util.Random;

/**
 * @author jyepez on 8/9/2024
 */
public final class TrackingUtils {

    /**
     * Constructor.
     */
    private TrackingUtils(){

    }

    /**
     * Generate Random string codes
     *
     * @param prefix String
     * @return String
     */
    public static String generateRandomStringCode(String prefix) {
        Random random = new Random();
        int randomNumber = random.nextInt(10000);
        return String.format(prefix+"%04d", randomNumber);
    }
}

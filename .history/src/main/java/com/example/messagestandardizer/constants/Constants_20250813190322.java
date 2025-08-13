package com.example.messagestandardizer.constants;

/**
 * Application-wide constants.
 *
 * Author: Yash Londhe
 * Created: 2025-08-13T13:32:17Z
 */
public class Constants {
    
    public enum StandardMessageType {
        ODDS_CHANGE,
        BET_SETTLEMENT;        
    }

    public enum Providers {
        alpha, beta
    }
    
    // Other constants can be added here in the future
    public static final String DEFAULT_VERSION = "1.0";
}

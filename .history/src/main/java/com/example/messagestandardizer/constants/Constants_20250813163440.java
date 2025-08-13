package com.example.messagestandardizer.constants;

public class Constants {
    
    public enum StandardMessageType {
        ODDS_CHANGE("ODDS_CHANGE"),
        BET_SETTLEMENT("BET_SETTLEMENT");
        
        private final String value;
        
        StandardMessageType(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }
    
    // Other constants can be added here in the future
    public static final String DEFAULT_VERSION = "1.0";
}

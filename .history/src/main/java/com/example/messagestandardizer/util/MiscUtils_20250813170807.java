package com.example.messagestandardizer.util;

import com.example.messagestandardizer.model.common.StandardResponse;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class MiscUtils {
    
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    
    public static String generateMessageId() {
        return UUID.randomUUID().toString();
    }
    
    public static String getCurrentTimestamp() {
        return Instant.now().atOffset(ZoneOffset.UTC).format(ISO_FORMATTER);
    }
    
    public static String mapAlphaOutcomeToStandard(String alphaOutcome) {
        switch (alphaOutcome) {
            case "1":
                return "home";
            case "X":
                return "draw";
            case "2":
                return "away";
            default:
                throw new IllegalArgumentException("Invalid alpha outcome: " + alphaOutcome);
        }
    }
    
    public static StandardResponse getOkResponse(String message) {
        return new StandardResponse("OK", message);
    }
    
    public static StandardResponse getGenericResponse(String status, String message) {
        return new StandardResponse(status, message);
    }
}

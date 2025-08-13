package com.example.messagestandardizer.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Standard odds change message representation.
 *
 * Author: Yash Londhe
 * Created: 2025-08-13T13:32:17Z
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StandardOddsMessage extends StandardMessage {
    
    private OddsData data;

    @Data
    @NoArgsConstructor
    public static class OddsData {
        private Double home;
        private Double draw;
        private Double away;
    }
}

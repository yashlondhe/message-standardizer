package com.example.messagestandardizer.model.beta;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Beta odds update message.
 *
 * @author Yash Londhe
 * @since 2025-08-13T13:32:17Z
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BetaOddsMessage extends BetaMessage {
    
    private BetaOdds odds;

    @Data
    @NoArgsConstructor
    public static class BetaOdds {
        private Double home;
        private Double draw;
        private Double away;
    }
}

package com.example.messagestandardizer.model.beta;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Beta settlement message.
 *
 * @author Yash Londhe
 * @since 2025-08-13T13:32:17Z
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BetaSettlementMessage extends BetaMessage {
    
    private String result;
}

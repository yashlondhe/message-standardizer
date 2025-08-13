package com.example.messagestandardizer.model.alpha;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Map;

/**
 * Alpha odds update message.
 *
 * @author Yash Londhe
 * @since 2025-08-13T13:32:17Z
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AlphaOddsMessage extends AlphaMessage {
    
    private Map<String, Double> values;
}

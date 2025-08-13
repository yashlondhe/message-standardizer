package com.example.messagestandardizer.model.alpha;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Map;

/**
 * Alpha odds update message.
 *
 * Author: Yash Londhe
 * Created: 2025-08-13T13:32:17Z
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AlphaOddsMessage extends AlphaMessage {
    
    private Map<String, Double> values;
}

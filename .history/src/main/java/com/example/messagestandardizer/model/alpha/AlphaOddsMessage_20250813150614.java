package com.example.messagestandardizer.model.alpha;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@NoArgsConstructor
public class AlphaOddsMessage {
    
    @JsonProperty("msg_type")
    private String msgType;
    
    @JsonProperty("event_id")
    private String eventId;
    
    private Map<String, Double> values;
}

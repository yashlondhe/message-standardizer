package com.example.messagestandardizer.model.beta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Base class for all Beta provider messages.
 *
 * Author: Yash Londhe
 * Created: 2025-08-13T13:32:17Z
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = BetaOddsMessage.class, name = "ODDS"),
    @JsonSubTypes.Type(value = BetaSettlementMessage.class, name = "SETTLEMENT")
})
@Data
@NoArgsConstructor
public abstract class BetaMessage {
    
    private String type;
    
    @JsonProperty("event_id")
    private String eventId;
}

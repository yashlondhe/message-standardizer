/* 
 * This class is the base class for all Alpha messages.
 * It is used to deserialize the Alpha messages from the JSON payload.
 * It is also used to serialize the Alpha messages to the JSON payload.
 * It is also used to validate the Alpha messages.
 * It is also used to transform the Alpha messages to the StandardMessage.
 */

package com.example.messagestandardizer.model.alpha;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "msg_type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = AlphaOddsMessage.class, name = "odds_update"),
    @JsonSubTypes.Type(value = AlphaSettlementMessage.class, name = "settlement")
})
@Data
@NoArgsConstructor
public abstract class AlphaMessage {
    
    @JsonProperty("msg_type")
    private String msgType;
    
    @JsonProperty("event_id")
    private String eventId;
}

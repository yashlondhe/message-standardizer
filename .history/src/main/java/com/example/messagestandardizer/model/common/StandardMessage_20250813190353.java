package com.example.messagestandardizer.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Base class for all standardized messages used internally.
 *
 * Author: Yash Londhe
 * Created: 2025-08-13T13:32:17Z
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "message_type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = StandardOddsMessage.class, name = "ODDS_CHANGE"),
    @JsonSubTypes.Type(value = StandardSettlementMessage.class, name = "BET_SETTLEMENT")
})
@Data
@NoArgsConstructor
public abstract class StandardMessage {
    
    private String version;
    
    @JsonProperty("message_id")
    private String messageId;
    
    private String timestamp;
    
    @JsonProperty("message_type")
    private String messageType;
    
    @JsonProperty("provider_message_id")
    private String providerMessageId;

    @JsonProperty("provider")
    private String provider;
}

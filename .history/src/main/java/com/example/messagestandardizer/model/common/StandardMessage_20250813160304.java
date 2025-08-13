package com.example.messagestandardizer.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    
    @JsonProperty("client_message_id")
    private String clientMessageId;
}

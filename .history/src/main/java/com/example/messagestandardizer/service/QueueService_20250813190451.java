package com.example.messagestandardizer.service;

import com.example.messagestandardizer.model.common.StandardMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Simulated queue service for sending messages to a message broker.
 *
 * Author: Yash Londhe
 * Created: 2025-08-13T13:32:17Z
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class QueueService {
    
    private final ObjectMapper objectMapper;
    
    public void sendToQueue(Object message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            log.info("=== MESSAGE SENT TO QUEUE ===");
            log.info("{}", jsonMessage);
            log.info("=============================");
        } catch (Exception e) {
            log.error("Error serializing message to queue: {}", e.getMessage());
        }
    }
    
    public void sendStandardMessageToQueue(StandardMessage standardMessage) {
        log.info("Sending message to queue - Type: {}, Client ID: {}, Message ID: {}",
                standardMessage.getMessageType(), 
                standardMessage.getProviderMessageId(), 
                standardMessage.getMessageId());
        sendToQueue(standardMessage);
    }
}

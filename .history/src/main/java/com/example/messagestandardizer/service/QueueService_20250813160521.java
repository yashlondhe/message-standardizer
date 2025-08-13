package com.example.messagestandardizer.service;

import com.example.messagestandardizer.model.common.StandardMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueueService {
    
    private final ObjectMapper objectMapper;
    
    public void sendToQueue(Object message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            System.out.println("=== MESSAGE SENT TO QUEUE ===");
            System.out.println(jsonMessage);
            System.out.println("=============================");
        } catch (Exception e) {
            System.err.println("Error serializing message to queue: " + e.getMessage());
        }
    }
    
    public void sendStandardMessageToQueue(StandardMessage standardMessage) {
        log.info("Sending message to queue - Type: {}, Client ID: {}, Message ID: {}",
                standardMessage.getMessageType(), 
                standardMessage.getClientMessageId(), 
                standardMessage.getMessageId());
        sendToQueue(standardMessage);
    }
}

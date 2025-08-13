package com.example.messagestandardizer.controller;

import com.example.messagestandardizer.constants.Constants;
import com.example.messagestandardizer.model.beta.BetaMessage;
import com.example.messagestandardizer.model.common.StandardResponse;
import com.example.messagestandardizer.model.common.StandardMessage;
import com.example.messagestandardizer.service.MessageTransformationService;
import com.example.messagestandardizer.service.MessageTransformationServiceFactory;
import com.example.messagestandardizer.service.QueueService;
import com.example.messagestandardizer.util.MiscUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Beta provider message ingestion.
 *
 * Author: Yash Londhe
 * Created: 2025-08-13T13:32:17Z
 */
@RestController
@RequestMapping("/provider-beta")
public class ProviderBetaController {
    @Autowired
    MessageTransformationServiceFactory transformationServiceFactory;

    @Autowired
    QueueService queueService;

    @PostMapping("/feed")
    public ResponseEntity<StandardResponse> processFeed(@RequestBody BetaMessage betaMessage) {
        try {
            MessageTransformationService transformationService = transformationServiceFactory
                .getTransformationService(Constants.Providers.beta);
            
            StandardMessage standardMessage = ((MessageTransformationService) transformationService)
                .transform(betaMessage);
            
            queueService.sendStandardMessageToQueue(standardMessage);
            
            String eventId = betaMessage.getEventId();
            return ResponseEntity.ok(MiscUtils.getOkResponse("Beta message processed successfully for event: " + eventId));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MiscUtils.getGenericResponse("ERROR", "Error processing message: " + e.getMessage()));
        }
    }
}

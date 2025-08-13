package com.example.messagestandardizer.controller;

import com.example.messagestandardizer.constants.Constants;
import com.example.messagestandardizer.model.alpha.AlphaMessage;
import com.example.messagestandardizer.model.alpha.AlphaOddsMessage;
import com.example.messagestandardizer.model.alpha.AlphaSettlementMessage;
import com.example.messagestandardizer.model.common.StandardOddsMessage;
import com.example.messagestandardizer.model.common.StandardSettlementMessage;
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

@RestController
@RequestMapping("/provider-alpha")
public class ProviderAlphaController {

    @Autowired
    MessageTransformationServiceFactory transformationServiceFactory;

    @Autowired
    QueueService queueService;

    @PostMapping("/feed")
    public ResponseEntity<StandardResponse> processFeed(@RequestBody AlphaMessage alphaMessage) {
        try {
            MessageTransformationService transformationService = transformationServiceFactory
                .getTransformationService(Constants.Providers.alpha);
            
            StandardMessage standardMessage = ((MessageTransformationService) transformationService)
                .transform(alphaMessage);
            
            queueService.sendStandardMessageToQueue(standardMessage);
            
            String messageType = alphaMessage instanceof AlphaOddsMessage ? "Odds" : "Settlement";
            return ResponseEntity.ok(MiscUtils.getOkResponse(messageType + " message processed successfully"));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MiscUtils.getGenericResponse("ERROR", "Error processing message: " + e.getMessage()));
        }
    }
}

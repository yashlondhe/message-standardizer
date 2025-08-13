package com.example.messagestandardizer.controller;

import com.example.messagestandardizer.model.beta.BetaMessage;
import com.example.messagestandardizer.model.beta.BetaOddsMessage;
import com.example.messagestandardizer.model.beta.BetaSettlementMessage;
import com.example.messagestandardizer.model.common.StandardOddsMessage;
import com.example.messagestandardizer.model.common.StandardSettlementMessage;
import com.example.messagestandardizer.model.common.StandardResponse;
import com.example.messagestandardizer.model.common.StandardMessage;
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
@RequestMapping("/provider-beta")
public class ProviderBetaController {
    @Autowired
    MessageTransformationServiceFactory transformationServiceFactory;

    @Autowired
    QueueService queueService;

    @PostMapping("/feed")
    public ResponseEntity<StandardResponse> processFeed(@RequestBody BetaMessage betaMessage) {
        try {
            MessageTransformationService<?> transformationService = transformationServiceFactory
                .getTransformationService("beta");
            
            StandardMessage standardMessage = ((MessageTransformationService<BetaMessage>) transformationService)
                .transform(betaMessage);
            
            queueService.sendStandardMessageToQueue(standardMessage);
            
            String messageType = betaMessage instanceof BetaOddsMessage ? "Odds" : "Settlement";
            return ResponseEntity.ok(MiscUtils.getOkResponse(messageType + " message processed successfully"));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MiscUtils.getGenericResponse("ERROR", "Error processing message: " + e.getMessage()));
        }
    }
}

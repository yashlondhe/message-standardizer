package com.example.messagestandardizer.service;

import com.example.messagestandardizer.model.alpha.AlphaMessage;
import com.example.messagestandardizer.model.beta.BetaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageTransformationServiceFactory {
    
    private final AlphaMessageTransformationService alphaTransformationService;
    private final BetaMessageTransformationService betaTransformationService;
    
    public MessageTransformationService<AlphaMessage> getAlphaTransformationService() {
        return alphaTransformationService;
    }
    
    public MessageTransformationService<BetaMessage> getBetaTransformationService() {
        return betaTransformationService;
    }
    
    // Alternative factory method approach
    public MessageTransformationService<?> getTransformationService(String providerType) {
        switch (providerType.toLowerCase()) {
            case "alpha":
                return alphaTransformationService;
            case "beta":
                return betaTransformationService;
            default:
                throw new IllegalArgumentException("Unknown provider type: " + providerType);
        }
    }
}

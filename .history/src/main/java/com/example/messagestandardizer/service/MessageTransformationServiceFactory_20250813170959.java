package com.example.messagestandardizer.service;

import com.example.messagestandardizer.constants.Constants;
import com.example.messagestandardizer.model.alpha.AlphaMessage;
import com.example.messagestandardizer.model.beta.BetaMessage;
import com.example.messagestandardizer.service.impl.AlphaMessageTransformationServiceImpl;
import com.example.messagestandardizer.service.impl.BetaMessageTransformationServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageTransformationServiceFactory {
    
    @Autowired
    AlphaMessageTransformationServiceImpl alphaTransformationService;
   
    @Autowired
    BetaMessageTransformationServiceImpl betaTransformationService;
    
   // Alternative factory method approach
    public MessageTransformationService getTransformationService(Constants.Providers providerType) {
        switch (providerType) {
            case alpha:
                return alphaTransformationService;
            case beta:
                return betaTransformationService;
            default:
                throw new IllegalArgumentException("Unknown provider type: " + providerType);
        }
    }
}

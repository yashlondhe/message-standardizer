package com.example.messagestandardizer.service.impl;

import com.example.messagestandardizer.constants.Constants;
import com.example.messagestandardizer.model.beta.BetaMessage;
import com.example.messagestandardizer.model.beta.BetaOddsMessage;
import com.example.messagestandardizer.model.beta.BetaSettlementMessage;
import com.example.messagestandardizer.model.common.StandardMessage;
import com.example.messagestandardizer.model.common.StandardOddsMessage;
import com.example.messagestandardizer.model.common.StandardSettlementMessage;
import com.example.messagestandardizer.service.MessageTransformationService;
import com.example.messagestandardizer.util.MiscUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BetaMessageTransformationServiceImpl implements MessageTransformationService {
    
    @Override
    public StandardMessage transform(Object message) {
        if (message instanceof BetaOddsMessage) {
            return transformOdds((BetaOddsMessage) message);
        } else if (message instanceof BetaSettlementMessage) {
            return transformSettlement((BetaSettlementMessage) message);
        } else {
            throw new IllegalArgumentException("Unknown Beta message type: " + message.getClass().getSimpleName());
        }
    }
    
    private StandardOddsMessage transformOdds(BetaOddsMessage betaMessage) {
        log.info("Transforming Beta odds message for event: {}", betaMessage.getEventId());
        StandardOddsMessage standardMessage = new StandardOddsMessage();
        standardMessage.setVersion(Constants.DEFAULT_VERSION);
        standardMessage.setMessageId(MiscUtils.generateMessageId());
        standardMessage.setTimestamp(MiscUtils.getCurrentTimestamp());
        standardMessage.setMessageType(Constants.StandardMessageType.ODDS_CHANGE.name());
        standardMessage.setProviderMessageId(betaMessage.getEventId());
        standardMessage.setProvider(Constants.Providers.beta.name());
        
        StandardOddsMessage.OddsData data = new StandardOddsMessage.OddsData();
        data.setHome(betaMessage.getOdds().getHome());
        data.setDraw(betaMessage.getOdds().getDraw());
        data.setAway(betaMessage.getOdds().getAway());
        standardMessage.setData(data);
        
        return standardMessage;
    }
    
    private StandardSettlementMessage transformSettlement(BetaSettlementMessage betaMessage) {
        log.info("Transforming Beta settlement message for event: {}", betaMessage.getEventId());
        StandardSettlementMessage standardMessage = new StandardSettlementMessage();
        standardMessage.setVersion(Constants.DEFAULT_VERSION);
        standardMessage.setMessageId(MiscUtils.generateMessageId());
        standardMessage.setTimestamp(MiscUtils.getCurrentTimestamp());
        standardMessage.setMessageType(Constants.StandardMessageType.BET_SETTLEMENT.name());
        standardMessage.setProviderMessageId(betaMessage.getEventId());
        standardMessage.setProvider(Constants.Providers.beta.name());
        standardMessage.setResult(betaMessage.getResult()); // Beta already uses home/draw/away format
        
        return standardMessage;
    }
}

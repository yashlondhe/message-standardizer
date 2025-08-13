package com.example.messagestandardizer.service.impl;

import com.example.messagestandardizer.constants.Constants;
import com.example.messagestandardizer.model.alpha.AlphaMessage;
import com.example.messagestandardizer.model.alpha.AlphaOddsMessage;
import com.example.messagestandardizer.model.alpha.AlphaSettlementMessage;
import com.example.messagestandardizer.model.common.StandardMessage;
import com.example.messagestandardizer.model.common.StandardOddsMessage;
import com.example.messagestandardizer.model.common.StandardSettlementMessage;
import com.example.messagestandardizer.service.MessageTransformationService;
import com.example.messagestandardizer.util.MiscUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AlphaMessageTransformationServiceImpl implements MessageTransformationService {
    
    @Override
    public StandardMessage transform(Object message) {
        if (message instanceof AlphaOddsMessage) {
            return transformOdds((AlphaOddsMessage) message);
        } else if (message instanceof AlphaSettlementMessage) {
            return transformSettlement((AlphaSettlementMessage) message);
        } else {
            throw new IllegalArgumentException("Unknown Alpha message type: " + message.getClass().getSimpleName());
        }
    }
    
    private StandardOddsMessage transformOdds(AlphaOddsMessage alphaMessage) {
        log.info("Transforming Alpha odds message for event: {}", alphaMessage.getEventId());
        StandardOddsMessage standardMessage = new StandardOddsMessage();
        standardMessage.setVersion(Constants.DEFAULT_VERSION);
        standardMessage.setMessageId(MiscUtils.generateMessageId());
        standardMessage.setTimestamp(MiscUtils.getCurrentTimestamp());
        standardMessage.setMessageType(Constants.StandardMessageType.ODDS_CHANGE.name());
        standardMessage.setProviderMessageId(alphaMessage.getEventId());
        standardMessage.setProvider(Constants.Providers.alpha.name());
        
        StandardOddsMessage.OddsData data = new StandardOddsMessage.OddsData();
        data.setHome(alphaMessage.getValues().get("1"));
        data.setDraw(alphaMessage.getValues().get("X"));
        data.setAway(alphaMessage.getValues().get("2"));
        standardMessage.setData(data);
        
        return standardMessage;
    }
    
    private StandardSettlementMessage transformSettlement(AlphaSettlementMessage alphaMessage) {
        log.info("Transforming Alpha settlement message for event: {}", alphaMessage.getEventId());
        StandardSettlementMessage standardMessage = new StandardSettlementMessage();
        standardMessage.setVersion(Constants.DEFAULT_VERSION);
        standardMessage.setMessageId(MiscUtils.generateMessageId());
        standardMessage.setTimestamp(MiscUtils.getCurrentTimestamp());
        standardMessage.setMessageType(Constants.StandardMessageType.BET_SETTLEMENT.name());
        standardMessage.setProviderMessageId(alphaMessage.getEventId());
        standardMessage.setProvider(Constants.Providers.alpha.name());
        standardMessage.setResult(MiscUtils.mapAlphaOutcomeToStandard(alphaMessage.getOutcome()));
        
        return standardMessage;
    }
}

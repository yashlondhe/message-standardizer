package com.example.messagestandardizer.service;

import com.example.messagestandardizer.model.common.StandardMessage;

/**
 * Service interface for transforming provider messages to standard messages.
 *
 * Author: Yash Londhe
 * Created: 2025-08-13T13:32:17Z
 */
public interface MessageTransformationService {
    /**
     * Transform a provider-specific message into a standard message.
     *
     * @param message provider-specific message payload
     * @return standardized message
     */
    StandardMessage transform(Object message);
}

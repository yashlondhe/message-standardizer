package com.example.messagestandardizer.service;

import com.example.messagestandardizer.model.common.StandardMessage;

public interface MessageTransformationServiceInterface<T> {
    StandardMessage transform(T message);
}

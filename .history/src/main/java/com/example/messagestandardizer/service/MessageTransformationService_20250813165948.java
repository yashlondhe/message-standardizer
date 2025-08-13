package com.example.messagestandardizer.service;

import com.example.messagestandardizer.model.common.StandardMessage;

public interface MessageTransformationService {
    StandardMessage transform(Object message);
}

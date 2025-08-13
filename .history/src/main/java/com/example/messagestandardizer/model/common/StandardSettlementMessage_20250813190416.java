package com.example.messagestandardizer.model.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Standard bet settlement message representation.
 *
 * Author: Yash Londhe
 * Created: 2025-08-13T13:32:17Z
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StandardSettlementMessage extends StandardMessage {
    
    private String result;
}

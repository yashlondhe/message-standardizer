package com.example.messagestandardizer.model.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Standard API response wrapper.
 *
 * @author Yash Londhe
 * @since 2025-08-13T13:32:17Z
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponse {
    private String status;
    private String message;
}

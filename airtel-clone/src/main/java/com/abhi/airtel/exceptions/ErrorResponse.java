package com.abhi.airtel.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {

    private String message;
    private int code;
    private String details;
    private List<String> errors;
    private LocalDateTime timestamp;

}

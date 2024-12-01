package com.Pranav.RedBus.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ErrorDetails {
    private LocalDateTime localDateTime;
    private String message;
    private String errorCode;
    private String path;
}

package com.davidlima.trixlogtechnicalchallenge.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ValidationException(
    String message,
    LocalDateTime timestamp,
    Integer status,
    Map<String, String> fields
) {}

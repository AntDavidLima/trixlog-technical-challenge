package com.davidlima.trixlogtechnicalchallenge.exception;

import java.time.LocalDateTime;

public record ApplicationException(
    String message,
    LocalDateTime timestamp,
    Integer status,
    String error
) {}

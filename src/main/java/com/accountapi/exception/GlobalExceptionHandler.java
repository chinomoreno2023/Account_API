package com.accountapi.exception;

import com.accountapi.exception.kafka.KafkaSendException;
import com.accountapi.exception.kafka.NonRetryableException;
import com.accountapi.exception.kafka.RetryableException;
import com.accountapi.exception.user.UserNotFoundException;
import jakarta.validation.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(ValidationException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Validation error", ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEntityNotFoundException(EntityNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Entity not found", ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "User not found", ex.getMessage());
    }

    @ExceptionHandler(KafkaSendException.class)
    public ResponseEntity<Map<String, Object>> handleKafkaSendException(KafkaSendException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Kafka send error", ex.getMessage());
    }

    @ExceptionHandler(NonRetryableException.class)
    public ResponseEntity<Map<String, Object>> handleNonRetryable(NonRetryableException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Non-retryable error", ex.getMessage());
    }

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<Map<String, Object>> handleRetryable(RetryableException ex) {
        return buildResponse(HttpStatus.SERVICE_UNAVAILABLE, "Retryable error", ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleGenericRuntime(RuntimeException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Runtime error", ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String error, String message) {
        return ResponseEntity.status(status).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status.value(),
                "error", error,
                "message", message
        ));
    }
}

package io.github.doggymentor.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApiError {

    private int errorCode;

    private LocalDateTime timestamp;

    private String message;

    private List<ApiSubError> errors;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(int errorCode, Throwable ex) {
        this();
        this.errorCode = errorCode;
        this.message = ex.getMessage();
        this.errors = new ArrayList<>();
    }

    public ApiError(int errorCode, String message) {
        this();
        this.errorCode = errorCode;
        this.message = message;
        this.errors = new ArrayList<>();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public List<ApiSubError> getErrors() {
        return errors;
    }
}
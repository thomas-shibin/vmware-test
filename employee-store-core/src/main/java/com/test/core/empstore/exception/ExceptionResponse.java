package com.test.core.empstore.exception;

import java.time.LocalDateTime;

/**
 * Created by shibthom on 7/11/2020.
 */
public class ExceptionResponse {

    public ExceptionResponse(LocalDateTime timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    private LocalDateTime timeStamp;
    private String message;
    private String details;

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

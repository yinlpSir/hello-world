package com.hngy.lms.exception;

public class LmsException extends Exception {
    public LmsException(String message) {
        super(message);
    }
    public LmsException(String message, Throwable cause) {
        super(message, cause);
    }
}

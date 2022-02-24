package com.laptrinhjavaweb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is used to process error relate to data validation
 * Author: Nhu Quang Le
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data missing!!")
public class BindException extends RuntimeException {
//    /**
//     * Constructs a new exception with the specified detail message.  The
//     * cause is not initialized, and may subsequently be initialized by
//     * a call to {@link #initCause}.
//     *
//     * @param message the detail message. The detail message is saved for
//     *                later retrieval by the {@link #getMessage()} method.
//     */
//    public BindException(String message) {
//        super(message);
//    }
}

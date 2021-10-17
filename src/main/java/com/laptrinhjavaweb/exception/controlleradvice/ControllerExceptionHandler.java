package com.laptrinhjavaweb.exception.controlleradvice;

import com.laptrinhjavaweb.dto.response.ResponseErrorData;
import com.laptrinhjavaweb.exception.BindException;
import com.laptrinhjavaweb.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<Object> dataValidationException(BindException ex, WebRequest request) {
        logger.error("Runtime error: "+ ex.getMessage());

//        List<String> errors = new ArrayList<>();
//        errors.add(ex.getMessage());
        ResponseErrorData body = new ResponseErrorData();
        body.setStatus("Failure!");
        body.setData(ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNodataFoundException(NotFoundException ex, WebRequest request) {
        logger.error("Runtime error: "+ ex.getMessage());

        ResponseErrorData body = new ResponseErrorData();
        body.setStatus("Failure!");
        body.setData(ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}

package com.chu.appgestionpatientchu.exceptions.common;

import com.chu.appgestionpatientchu.exceptions.EmptyParamArrayException;
import com.chu.appgestionpatientchu.exceptions.InvalidSearchCriteriaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(EmptyParamArrayException.class)
    public ResponseEntity<String> handleEmptyParamArrayException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidSearchCriteriaException.class)
    public ResponseEntity<String> handleInvalidSearchCriteriaException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), null, HttpStatus.BAD_REQUEST);
    }

}

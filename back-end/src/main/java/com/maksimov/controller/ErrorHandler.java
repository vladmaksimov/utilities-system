package com.maksimov.controller;

import com.maksimov.exceptions.NoUniqueException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 10.04.2016.
 */
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public Map<Object, Object> handleNoUniqueException(NoUniqueException e) {
        return prepareErrorMessage(e.getMessage());
    }

    private Map<Object, Object> prepareErrorMessage(String message) {
        Map<Object, Object> data = new HashMap<>();

        data.put("error", true);
        data.put("message", message);
        return data;
    }
}

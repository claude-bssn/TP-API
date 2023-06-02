package com.api.tp.exceptions;

import com.api.tp.models.ErrorValidation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class RestApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ErrorValidation> errors = new ArrayList<>();
        BindingResult result = ex.getBindingResult();
        List<FieldError> filedErrors = result.getFieldErrors();
        for (FieldError error : filedErrors) {
            errors.add(new ErrorValidation(error.getField(), error.getDefaultMessage()));
        }
        return this.handleExceptionInternal(ex, errors, headers, status, request);
    }
}

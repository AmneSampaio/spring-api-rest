package com.concrete.projeto.refactor.exception;


import com.concrete.projeto.refactor.exception.ResponseExceptions.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        var bodies = new ArrayList<Body>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            bodies.add(new ResponseExceptions.Body(name, message));
        }

        var errors = new ResponseExceptions();
        errors.setStatus(status.value());
        errors.setMessage("Invalid fields");
        errors.setDate(OffsetDateTime.now());
        errors.setBodies(bodies);
        return super.handleExceptionInternal(ex, errors, headers, status, request);
    }
}

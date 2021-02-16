package com.desafio.api.spring_boot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Token inválido")
public class InvalidTokenException extends ResponseStatusException {

    public InvalidTokenException(String message) {

        super(HttpStatus.UNAUTHORIZED, message);

    }
}

package com.desafio.api.spring_boot.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Senha vazia ou comprimento insuficiente")
public class InvalidPasswordLengthException extends ResponseStatusException {

    public InvalidPasswordLengthException(String message) {

        super(HttpStatus.BAD_REQUEST, message);

    }
}

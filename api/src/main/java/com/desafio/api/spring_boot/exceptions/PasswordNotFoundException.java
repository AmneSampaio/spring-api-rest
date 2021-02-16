package com.desafio.api.spring_boot.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Usuário e/ou senha inválidos")
public class PasswordNotFoundException extends ResponseStatusException {

    public PasswordNotFoundException(String message) {

        super(HttpStatus.NOT_FOUND, message);
    }
}

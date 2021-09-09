package com.br.curso.algaworks.domain.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException (String message) {
        super(message);
    }
}

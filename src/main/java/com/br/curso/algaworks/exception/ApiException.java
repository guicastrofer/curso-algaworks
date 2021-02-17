package com.br.curso.algaworks.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//Toda exceção lançada na Controller chamará essa classe através da anotação abaixo.
@ControllerAdvice
public class ApiException extends ResponseEntityExceptionHandler {
}

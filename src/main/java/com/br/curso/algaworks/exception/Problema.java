package com.br.curso.algaworks.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Problema {
    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;

    @Getter
    @Setter
    public static class Campo {
        private String nome;
        private String mensagem;
    }
}

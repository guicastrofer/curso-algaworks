package com.br.curso.algaworks.api.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ComentarioInput {

    @NotNull
    private String descricao;

}

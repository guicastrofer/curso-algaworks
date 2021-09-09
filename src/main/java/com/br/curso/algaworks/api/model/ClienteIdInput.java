package com.br.curso.algaworks.api.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClienteIdInput {

    @NotNull
    private Long id;

}

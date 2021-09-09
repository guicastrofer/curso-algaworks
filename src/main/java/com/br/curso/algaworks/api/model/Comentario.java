package com.br.curso.algaworks.api.model;

import com.br.curso.algaworks.domain.model.OrdemServico;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OrdemServico ordemServico;

    private String descricao;
    private OffsetDateTime dataEnvio;

}

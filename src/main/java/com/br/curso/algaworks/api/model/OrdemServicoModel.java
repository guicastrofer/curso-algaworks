package com.br.curso.algaworks.api.model;

import com.br.curso.algaworks.domain.enums.StatusOrdemServico;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class OrdemServicoModel {
    private Long id;
    private String nomeCliente;
    private ClienteResumoModel cliente;
    private String descricao;
    private BigDecimal preco;
    private StatusOrdemServico status;
    private OffsetDateTime dataAbertura;
    private OffsetDateTime dataFinalizacao;

}

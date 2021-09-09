package com.br.curso.algaworks.domain.model;

import com.br.curso.algaworks.domain.enums.StatusOrdemServico;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @NotNull
    @ManyToOne
    private Cliente cliente;

    @NotNull
    private BigDecimal preco;

    @NotBlank
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    private OffsetDateTime dataAbertura;

    private OffsetDateTime dataFinalizacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdemServico that = (OrdemServico) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

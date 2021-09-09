package com.br.curso.algaworks.domain.model;

import com.br.curso.algaworks.api.model.Comentario;
import com.br.curso.algaworks.domain.enums.StatusOrdemServico;
import com.br.curso.algaworks.domain.exception.NegocioException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "ordemServico")
    private List<Comentario> comentarios = new ArrayList<>();

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

    public void finalizar() {
        if(!getStatus().equals(StatusOrdemServico.ABERTA)) {
            throw new NegocioException("Ordem de serviço não pode ser finalizada.");
        }
        setStatus(StatusOrdemServico.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }

}

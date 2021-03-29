package com.br.curso.algaworks.domain.repository;

import com.br.curso.algaworks.domain.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico,Long> {
}

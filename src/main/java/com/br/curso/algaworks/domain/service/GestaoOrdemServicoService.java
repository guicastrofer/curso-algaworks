package com.br.curso.algaworks.domain.service;

import com.br.curso.algaworks.domain.enums.StatusOrdemServico;
import com.br.curso.algaworks.domain.exception.NegocioException;
import com.br.curso.algaworks.domain.model.Cliente;
import com.br.curso.algaworks.domain.model.OrdemServico;
import com.br.curso.algaworks.domain.repository.ClienteRepository;
import com.br.curso.algaworks.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GestaoOrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public OrdemServico criar(OrdemServico ordemServico) {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());
        return ordemServicoRepository.save(ordemServico);
    }

}

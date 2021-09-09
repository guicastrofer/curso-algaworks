package com.br.curso.algaworks.domain.service;

import com.br.curso.algaworks.api.model.OrdemServicoModel;
import com.br.curso.algaworks.domain.enums.StatusOrdemServico;
import com.br.curso.algaworks.domain.exception.NegocioException;
import com.br.curso.algaworks.domain.exception.NotFoundException;
import com.br.curso.algaworks.domain.model.OrdemServico;
import com.br.curso.algaworks.domain.repository.ClienteRepository;
import com.br.curso.algaworks.domain.repository.OrdemServicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestaoOrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;

    private final ClienteRepository clienteRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public GestaoOrdemServicoService(OrdemServicoRepository ordemServicoRepository, ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    public OrdemServico criar(OrdemServico ordemServico) {
        var cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());
        return ordemServicoRepository.save(ordemServico);
    }

    public List<OrdemServicoModel> findAll() {
        var ordemServicoList = ordemServicoRepository.findAll();
        return toCollectionModel(ordemServicoList);
    }

    public List <OrdemServicoModel> toCollectionModel(List<OrdemServico> ordemServicoList) {
        return ordemServicoList.stream().map(ordemServico -> toModel(ordemServico)).collect(Collectors.toList());
    }

    public OrdemServicoModel toModel(OrdemServico ordemServico) {
        return modelMapper.map(ordemServico, OrdemServicoModel.class);
    }


    public OrdemServicoModel findById(Long ordemServicoId) {
        var ordemServico = ordemServicoRepository.findById(ordemServicoId);
        if (!ordemServico.isPresent()) {
            return null;
        }
        return toModel(ordemServico.get());
    }
}

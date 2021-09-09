package com.br.curso.algaworks.domain.service;

import com.br.curso.algaworks.api.model.Comentario;
import com.br.curso.algaworks.api.model.ComentarioModel;
import com.br.curso.algaworks.api.model.OrdemServicoInput;
import com.br.curso.algaworks.api.model.OrdemServicoModel;
import com.br.curso.algaworks.domain.enums.StatusOrdemServico;
import com.br.curso.algaworks.domain.exception.EntidadeNaoEncontradaException;
import com.br.curso.algaworks.domain.exception.NegocioException;
import com.br.curso.algaworks.domain.model.OrdemServico;
import com.br.curso.algaworks.domain.repository.ClienteRepository;
import com.br.curso.algaworks.domain.repository.ComentarioRepository;
import com.br.curso.algaworks.domain.repository.OrdemServicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestaoOrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;

    private final ComentarioRepository comentarioRepository;

    private final ClienteRepository clienteRepository;

    private final ModelMapper modelMapper;



    @Autowired
    public GestaoOrdemServicoService(OrdemServicoRepository ordemServicoRepository, ComentarioRepository comentarioRepository, ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.comentarioRepository = comentarioRepository;
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    public OrdemServicoModel criar(OrdemServicoInput ordemServicoInput) {
        OrdemServico ordemServico = toEntity(ordemServicoInput);
        var cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());
        ordemServico.setCliente(cliente);
        return toModel(ordemServicoRepository.save(ordemServico));
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

    public OrdemServico toEntity(OrdemServicoInput ordemServicoInput){
        return modelMapper.map(ordemServicoInput, OrdemServico.class);
    }

    public ComentarioModel adicionarComentario(Long ordemServicoId, String descricao) {

        var ordemServico = buscarOrdemServicoPorId(ordemServicoId);
        var comentario = new Comentario();
        comentario.setDataEnvio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);
        return toModel(comentarioRepository.save(comentario));
    }

    public ComentarioModel toModel(Comentario comentario) {
        return modelMapper.map(comentario, ComentarioModel.class);
    }

    public List<ComentarioModel> findAllComentarios(Long ordemServicoId) {
        var ordemServico = buscarOrdemServicoPorId(ordemServicoId);
        return toCollectionModelComentarios(ordemServico.getComentarios());
    }
    public List <ComentarioModel> toCollectionModelComentarios(List<Comentario> comentarios) {
        return comentarios.stream().map(comentario -> toModel(comentario)).collect(Collectors.toList());
    }

    private OrdemServico buscarOrdemServicoPorId(Long ordemServicoId) {
        return ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(()-> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada."));
    }

    public void finalizar(Long ordemServicoId){
        var ordemServico = buscarOrdemServicoPorId(ordemServicoId);
        ordemServico.finalizar();
        ordemServicoRepository.save(ordemServico);
    }
}

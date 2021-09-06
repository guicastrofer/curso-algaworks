package com.br.curso.algaworks.api.controller;

import com.br.curso.algaworks.domain.model.OrdemServico;
import com.br.curso.algaworks.domain.repository.OrdemServicoRepository;
import com.br.curso.algaworks.domain.service.GestaoOrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    private final GestaoOrdemServicoService gestaoOrdemServico;

    private final OrdemServicoRepository ordemServicoRepository;

@Autowired
    public OrdemServicoController(GestaoOrdemServicoService gestaoOrdemServico, OrdemServicoRepository ordemServicoRepository) {
        this.gestaoOrdemServico = gestaoOrdemServico;
        this.ordemServicoRepository = ordemServicoRepository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico){
        return gestaoOrdemServico.criar(ordemServico);
    }
    @GetMapping
    public List<OrdemServico> listarClientes() {
        return ordemServicoRepository.findAll();
    }

    @GetMapping("/{ordemServicoId}")
    public ResponseEntity<OrdemServico> buscar(@PathVariable Long ordemServicoId) {
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);
        if (ordemServico.isPresent()) {
            return ResponseEntity.ok(ordemServico.get());
        }
        return ResponseEntity.notFound().build();
    }

}

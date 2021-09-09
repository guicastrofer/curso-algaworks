package com.br.curso.algaworks.api.controller;

import com.br.curso.algaworks.api.model.OrdemServicoInput;
import com.br.curso.algaworks.api.model.OrdemServicoModel;
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

    private final GestaoOrdemServicoService gestaoOrdemServicoService;

    private final OrdemServicoRepository ordemServicoRepository;

@Autowired
    public OrdemServicoController(GestaoOrdemServicoService gestaoOrdemServicoService, OrdemServicoRepository ordemServicoRepository) {
        this.gestaoOrdemServicoService = gestaoOrdemServicoService;
        this.ordemServicoRepository = ordemServicoRepository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServicoModel criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput){
        return gestaoOrdemServicoService.criar(ordemServicoInput);
    }
    @GetMapping
    public List<OrdemServicoModel> listarClientes() {
        return gestaoOrdemServicoService.findAll();
    }

    @GetMapping("/{ordemServicoId}")
    public ResponseEntity<OrdemServicoModel> buscar(@PathVariable Long ordemServicoId) {
        var ordemServico = gestaoOrdemServicoService.findById(ordemServicoId);
        if (ordemServico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ordemServico);
    }

}

package com.br.curso.algaworks.api.controller;

import com.br.curso.algaworks.api.model.Comentario;
import com.br.curso.algaworks.api.model.ComentarioInput;
import com.br.curso.algaworks.api.model.ComentarioModel;
import com.br.curso.algaworks.domain.service.GestaoOrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {


    private final GestaoOrdemServicoService gestaoOrdemServicoService;

    @Autowired
    public ComentarioController(GestaoOrdemServicoService gestaoOrdemServicoService) {
        this.gestaoOrdemServicoService = gestaoOrdemServicoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioModel adicionar(@PathVariable Long ordemServicoId, @Valid @RequestBody ComentarioInput comentarioInput) {
            return gestaoOrdemServicoService.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());
    }

    @GetMapping
    public List<ComentarioModel> listar(@PathVariable Long ordemServicoId){
        return gestaoOrdemServicoService.findAllComentarios(ordemServicoId);
    }

}

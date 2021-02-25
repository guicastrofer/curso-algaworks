package com.br.curso.algaworks.api.controller;

import com.br.curso.algaworks.domain.model.Cliente;
import com.br.curso.algaworks.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/clientes")
public class ClienteController {


    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ExceptionHandler({ InvocationTargetException.class })
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente inserirCliente(@Valid @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping(value = "/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @DeleteMapping(value = "/{clienteId}")
    public ResponseEntity<Void> deletar(@Valid @PathVariable Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(clienteId);
        //Retorna um 204 - Significa que a operação foi efetuada com sucesso(Sem nenhuma mensagem).
        return ResponseEntity.noContent().build();
    }
}

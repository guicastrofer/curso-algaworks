package com.br.curso.algaworks.domain.repository;

import com.br.curso.algaworks.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public List<Cliente> findByNome(String nome);
    public Cliente findByEmail(String email);
}

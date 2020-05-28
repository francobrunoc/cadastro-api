package com.empresa.cadastro.repository;

import com.empresa.cadastro.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    Iterable<Cliente> findClientesByNomeContaining(String nome);
}

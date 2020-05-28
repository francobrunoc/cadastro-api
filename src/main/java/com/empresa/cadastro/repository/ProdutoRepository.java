package com.empresa.cadastro.repository;

import com.empresa.cadastro.model.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

    Iterable<Produto> findProdutosByNomeContaining(String nome);
}

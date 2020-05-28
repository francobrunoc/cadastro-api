package com.empresa.cadastro.repository;

import com.empresa.cadastro.model.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {
}

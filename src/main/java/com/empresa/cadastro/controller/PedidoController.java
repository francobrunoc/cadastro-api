package com.empresa.cadastro.controller;

import com.empresa.cadastro.messages.ClienteException;
import com.empresa.cadastro.messages.PedidoException;
import com.empresa.cadastro.messages.ProdutoException;
import com.empresa.cadastro.model.Pedido;
import com.empresa.cadastro.model.PedidoViewModel;
import com.empresa.cadastro.repository.ClienteRepository;
import com.empresa.cadastro.model.Mapper;
import com.empresa.cadastro.repository.PedidoRepository;
import com.empresa.cadastro.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;
    private Mapper mapper;

    public PedidoController(PedidoRepository pedidoRepository,
                            ClienteRepository clienteRepository,
                            ProdutoRepository produtoRepository,
                            Mapper mapper) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.mapper = mapper;
    }

    @GetMapping("/list")
    public Iterable<Pedido> list() {
        return this.pedidoRepository.findAll();
    }

    @PostMapping("/save")
    public Pedido save(@Valid @RequestBody PedidoViewModel pedido) {
        if (this.clienteRepository.findById(pedido.getCliente().getId()).isPresent()) {
            for (Long id : pedido.getProdutosId()) {
                if (this.produtoRepository.findById(id).isEmpty()) {
                    throw new ProdutoException(id);
                }
            }
            return this.pedidoRepository.save(this.mapper.convertToPedidoEntity(pedido));
        } else throw new ClienteException(pedido.getCliente().getId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            pedidoRepository.deleteById(id);
        } catch (Exception e) {
            throw new PedidoException(id);
        }
    }
}

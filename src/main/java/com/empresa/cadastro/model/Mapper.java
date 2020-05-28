package com.empresa.cadastro.model;

import com.empresa.cadastro.repository.ClienteRepository;
import com.empresa.cadastro.repository.PedidoRepository;
import com.empresa.cadastro.repository.ProdutoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Component
public class Mapper {
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;

    public static String convertDate(LocalDate date) {
        return String.valueOf(date.getDayOfMonth()) + "/" +
                (date.getMonthValue() >= 10 ? "" : "0") +
                String.valueOf(date.getMonthValue()) + "/" +
                String.valueOf(date.getYear());
    }

    public Mapper(ClienteRepository clienteRepository, ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public Cliente convertToClienteEntity(ClienteViewModel viewModel) throws DateTimeParseException {
        Cliente entity = new Cliente();
        entity.setId(viewModel.getId());
        entity.setNome(viewModel.getNome());
        entity.setCpf(viewModel.getCpf());
        entity.setDataNascimento(LocalDate.parse(viewModel.getDataNascimento(),
                DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return entity;
    }

    public Produto convertToProdutoEntity(ProdutoViewModel viewModel) {
        Produto produto = new Produto();
        produto.setId(viewModel.getId());
        produto.setNome(viewModel.getNome());
        produto.setDescricao(viewModel.getDescricao());
        produto.setSku(viewModel.getSku());
        produto.setPreco(viewModel.getPreco());
        produto.setQuantidade(viewModel.getQuantidade());
        return produto;
    }

    public Pedido convertToPedidoEntity(PedidoViewModel viewModel) throws DateTimeParseException {
        Pedido entity = new Pedido();
        entity.setId(viewModel.getId());
        entity.setCliente(this.clienteRepository.findById(viewModel.getCliente().getId()).get());
        entity.setDataCompra(viewModel.getDataCompra());
        entity.setProdutos((List<Produto>) this.produtoRepository.findAllById(viewModel.getProdutosId()));
        entity.setTotalCompra(viewModel.getTotalCompra());
        return entity;
    }

}

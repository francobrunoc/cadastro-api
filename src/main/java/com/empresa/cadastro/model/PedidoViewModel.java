package com.empresa.cadastro.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoViewModel {

    private Long id;
    private ClienteViewModel cliente;
    private BigDecimal totalCompra;
    private Date dataCompra;
    private Iterable<ProdutoViewModel> produtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteViewModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteViewModel cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(BigDecimal totalCompra) {
        this.totalCompra = totalCompra;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Iterable<ProdutoViewModel> getProdutos() {
        return produtos;
    }

    public Iterable<Long> getProdutosId() {
        List<Long> list = new ArrayList<>();
        this.produtos.forEach(obj ->
                list.add(obj.getId())
        );
        return list;
    }

    public void setProdutos(Iterable<ProdutoViewModel> produtos) {
        this.produtos = produtos;
    }
}

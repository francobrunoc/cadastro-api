package com.empresa.cadastro.messages;

public class PedidoException extends RuntimeException {

    public PedidoException(Long id) {
        super("O pedido com o código " + id + " não existe");
    }

}

package com.empresa.cadastro.messages;

import org.springframework.dao.DataIntegrityViolationException;

public class ProdutoException extends RuntimeException {

    public ProdutoException(Long id) {
        super ("O produto com o código " + id + " não existe!");
    }

    public ProdutoException(DataIntegrityViolationException e) {
        super ("O produto não pode ser exclúído pois já existem registros vinculados a ele");
    }

}

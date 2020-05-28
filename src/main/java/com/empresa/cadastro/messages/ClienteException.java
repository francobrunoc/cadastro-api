package com.empresa.cadastro.messages;

import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;

public class ClienteException extends RuntimeException {

    public ClienteException (Long id) {
        super ("O cliente com o código " + id + " não existe ou não pode ser excluído!");
    }

    public ClienteException (Class<LocalDate> localDateClass, String value) {
        super ("Erro ao converter a Data de Nascimento: " + value + "para" + localDateClass);
    }

    public ClienteException (DataIntegrityViolationException e) {
        super (e);
    }

    public ClienteException (String message) {
        super (message);
    }

}

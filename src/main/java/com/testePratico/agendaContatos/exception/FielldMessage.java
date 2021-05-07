package com.testePratico.agendaContatos.exception;

import lombok.Data;

import java.io.Serializable;
@Data
public class FielldMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;

    public FielldMessage(String fieldName, String message){
        super();
        this.fieldName = fieldName;
        this.message = message;
    }
}

package br.com.systemit.erp.exceptions;

public class RegistroDuplicadoException extends RuntimeException{
    public RegistroDuplicadoException(String mensagem) {
        super(mensagem);
    }
}

package br.com.systemit.erp.auth.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(int status, String mensagem, List<ErroCampo> erros) {

    public static ErroResposta respostaPadrão(String mensagem){
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
    }

}

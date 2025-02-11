package br.com.systemit.erp.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PesquisaPerfilDTO(

        @NotBlank(message = "Campo obrigatório")
        @Size(min = 2, max = 50, message = "campo fora do tamanho padrão")
        String nome,

        @NotBlank(message = "Campo obrigatório")
        @Size(min = 2, max = 100, message = "campo fora do tamanho padrão")
        String discriminacao,

        @NotBlank(message = "Campo obrigatório")
        @Size(min = 2, max = 50, message = "campo fora do tamanho padrão")
        String role,

        @NotNull
        ModuloDTO modulo) {

}

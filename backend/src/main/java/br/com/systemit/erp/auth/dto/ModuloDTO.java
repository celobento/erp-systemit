package br.com.systemit.erp.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ModuloDTO(

        Integer id,

        @NotBlank(message = "Campo obrigatório")
        @Size(min = 2, max = 50, message = "campo fora do tamanho padrão")
        String nome
        ) {

}

package br.com.systemit.erp.auth.dto;

import br.com.systemit.erp.auth.model.Modulo;
import br.com.systemit.erp.auth.model.Perfil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(name = "Perfil")
public record PerfilDTO(

        @NotBlank(message = "Campo obrigatório")
        @Size(min = 2, max = 50, message = "campo fora do tamanho padrão")
        String nome,

        @NotBlank(message = "Campo obrigatório")
        @Size(min = 2, max = 100, message = "campo fora do tamanho padrão")
        @Schema(name = "descricao")
        String discriminacao,

        @NotBlank(message = "Campo obrigatório")
        @Size(min = 2, max = 50, message = "campo fora do tamanho padrão")
        String role,

        @NotNull
        Integer modulo) {

}

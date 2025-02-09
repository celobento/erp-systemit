package br.com.systemit.erp.auth.dto;

import br.com.systemit.erp.auth.model.Modulo;
import br.com.systemit.erp.auth.model.Perfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PerfilDTO(

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
        Integer modulo) {

    public Perfil mapearParaPerfil() {
        Perfil perfil = new Perfil();
        perfil.setNome(this.nome);
        perfil.setRole(this.role);
        perfil.setDiscriminacao(this.discriminacao);
        Modulo moduloTemp = new Modulo();
        moduloTemp.setId(this.modulo);
        perfil.setModulo(moduloTemp);
        return perfil;
    }


}

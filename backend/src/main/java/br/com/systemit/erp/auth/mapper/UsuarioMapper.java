package br.com.systemit.erp.auth.mapper;

import br.com.systemit.erp.auth.dto.UsuarioDTO;
import br.com.systemit.erp.auth.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

    UsuarioDTO toDto(Usuario usuario);
}

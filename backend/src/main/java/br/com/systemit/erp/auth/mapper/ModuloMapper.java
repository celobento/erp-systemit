package br.com.systemit.erp.auth.mapper;

import br.com.systemit.erp.auth.dto.ModuloDTO;
import br.com.systemit.erp.auth.dto.PerfilDTO;
import br.com.systemit.erp.auth.model.Modulo;
import br.com.systemit.erp.auth.model.Perfil;
import br.com.systemit.erp.auth.repository.ModuloRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface ModuloMapper {

    Modulo toEntity(ModuloDTO dto);

    @Mapping(source = "nome", target = "nome")
    ModuloDTO toDto(Modulo modulo);

}

package br.com.systemit.erp.auth.mapper;

import br.com.systemit.erp.auth.dto.PerfilDTO;
import br.com.systemit.erp.auth.dto.PesquisaPerfilDTO;
import br.com.systemit.erp.auth.model.Perfil;
import br.com.systemit.erp.auth.repository.ModuloRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = ModuloMapper.class)
public abstract class PerfilMapper {

    @Autowired
    ModuloRepository moduloRepository;

    @Mapping(target = "modulo", expression = "java( moduloRepository.findById(dto.modulo()).orElse(null)) ")
    public abstract Perfil toEntity(PerfilDTO dto);

    public abstract PesquisaPerfilDTO toDto(Perfil perfil);

}

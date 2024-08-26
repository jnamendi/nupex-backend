package com.nupex.nupex_backend.mapper;

import org.mapstruct.Mapper;

import com.nupex.nupex_backend.dto.ClientDTO;
import com.nupex.nupex_backend.model.Client;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {
	ClientDTO toDTO(Client entity);
	
	Client toEntity(ClientDTO dto);

	@Mapping(target = "id", ignore = true)
	void updateEntityFromDTO(ClientDTO dto, @MappingTarget Client entity);
}


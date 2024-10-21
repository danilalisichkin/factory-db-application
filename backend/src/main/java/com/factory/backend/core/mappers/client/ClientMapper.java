package com.factory.backend.core.mappers.client;

import com.factory.backend.core.dto.client.ClientAddingDTO;
import com.factory.backend.core.dto.client.ClientDTO;
import com.factory.backend.entities.sql.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO entityToDto(Client client);

    Client dtoToEntity(ClientDTO dto);

    Client addingDtoToEntity(ClientAddingDTO dto);
}

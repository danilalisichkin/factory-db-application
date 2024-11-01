package com.factory.backend.core.mappers.client;

import com.factory.backend.core.dto.client.ClientAddingDTO;
import com.factory.backend.core.dto.client.ClientDTO;
import com.factory.backend.entities.nosql.MongoClient;
import com.factory.backend.entities.sql.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO entityToDto(Client client);

    Client dtoToEntity(ClientDTO dto);

    Client addingDtoToEntity(ClientAddingDTO dto);

    @Mapping(target = "id", ignore = true)
    MongoClient entityToMongo(Client entity);
}

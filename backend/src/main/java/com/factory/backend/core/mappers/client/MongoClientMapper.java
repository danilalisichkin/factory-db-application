package com.factory.backend.core.mappers.client;

import com.factory.backend.core.dto.client.ClientAddingDTO;
import com.factory.backend.core.dto.client.ClientDTO;
import com.factory.backend.entities.nosql.MongoClient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MongoClientMapper {
    ClientDTO entityToDto(MongoClient client);

    MongoClient dtoToEntity(ClientDTO dto);

    MongoClient addingDtoToEntity(ClientAddingDTO dto);
}

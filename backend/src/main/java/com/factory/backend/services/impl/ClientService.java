package com.factory.backend.services.impl;

import com.factory.backend.core.dto.client.ClientAddingDTO;
import com.factory.backend.core.dto.client.ClientDTO;
import com.factory.backend.core.mappers.category.CategoryMapper;
import com.factory.backend.core.mappers.client.ClientMapper;
import com.factory.backend.entities.Category;
import com.factory.backend.entities.Client;
import com.factory.backend.exceptions.ResourceNotFoundException;
import com.factory.backend.repository.CategoryRepository;
import com.factory.backend.repository.ClientRepository;
import com.factory.backend.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientByPhone(String phone) {
        return clientMapper.entityToDto(
                clientRepository.findById(phone).orElseThrow(
                        () -> new ResourceNotFoundException("Client with phone=%s not found", phone)
                )
        );
    }

    @Override
    public ClientDTO saveClient(ClientAddingDTO clientDTO) {
        Client client = clientRepository.save(clientMapper.addingDtoToEntity(clientDTO));

        return clientMapper.entityToDto(client);
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        if (clientRepository.findById(clientDTO.getPhoneNumber()).isEmpty()) {
            throw new ResourceNotFoundException("Client with phone=%s not found", clientDTO.getPhoneNumber());
        }

        return clientMapper.entityToDto(
                clientRepository.save(clientMapper.dtoToEntity(clientDTO))
        );
    }

    @Override
    public void deleteClientByPhone(String phone) {
        if (clientRepository.findById(phone).isEmpty()) {
            throw new ResourceNotFoundException("Client with phone=%s not found", phone);
        }
        clientRepository.deleteById(phone);
    }

    @Override
    public void deleteAllClients() {
        if (clientRepository.count() == 0) {
            throw new ResourceNotFoundException("No clients found");
        } else {
            clientRepository.deleteAll();
        }
    }
}

package com.factory.backend.services.impl;

import com.factory.backend.core.dto.client.ClientAddingDTO;
import com.factory.backend.core.dto.client.ClientDTO;
import com.factory.backend.services.IClientService;

import java.util.List;

public class ClientService implements IClientService {
    @Override
    public List<ClientDTO> getAllClients() {
        return List.of();
    }

    @Override
    public ClientDTO getClientByPhone(Long id) {
        return null;
    }

    @Override
    public ClientDTO saveClient(ClientAddingDTO clientDTO) {
        return null;
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        return null;
    }

    @Override
    public void deleteClientByPhone(String phone) {

    }

    @Override
    public void deleteAllClients() {

    }
}

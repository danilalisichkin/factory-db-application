package com.factory.backend.services;

import com.factory.backend.core.dto.client.ClientAddingDTO;
import com.factory.backend.core.dto.client.ClientDTO;

import java.util.List;

public interface IClientService {
    List<ClientDTO> getAllClients();

    ClientDTO getClientByPhone(String phone);

    ClientDTO saveClient(ClientAddingDTO clientDTO);

    ClientDTO updateClient(ClientDTO clientDTO);

    void deleteClientByPhone(String phone);

    void deleteAllClients();
}

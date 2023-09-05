package org.example.services;

import lombok.Data;
import org.example.exceptions.AppBankException;
import org.example.model.Client;
import org.example.repository.ClientRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
@Data
public class ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepo clientRepository;

    public ClientService(ClientRepo clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client create(Client client) throws AppBankException {
        if (client == null) {
            throw new AppBankException("Клиент не может быть null");
        }

        return clientRepository.create(client);
    }

    public List<Client> getAll() {
        return new ArrayList<>(clientRepository.getAll());
    }

    public void delete(Long id) throws AppBankException {
        clientRepository.delete(id);
    }

    public Client update(Client client) throws AppBankException {
        if (client == null) {
            throw new AppBankException("Клиент не должен быть null");
        }
        return clientRepository.update(client);
    }

    public Client getById(Long clientId) throws AppBankException {
        if (clientId <= 0) {
            throw new AppBankException("id должно быть больше нуля");
        }
        return clientRepository.getById(clientId);
    }

    public List<Long> allClientIds() {
        return clientRepository.getAllIds();
    }
}

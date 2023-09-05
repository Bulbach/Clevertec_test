package org.example.controllers;

import org.example.exceptions.AppBankException;
import org.example.model.Client;
import org.example.services.ClientService;
import org.example.view.ClientView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final ClientService clientService;
    private final ClientView clientView;

    public ClientController(ClientService clientService, ClientView clientView) {
        this.clientService = clientService;
        this.clientView = clientView;
    }

    public void create() {
        Client client = null;
        try {
            client = clientView.createClient();
            Client clientTemp = clientService.create(client);
            logger.trace("Создан клиент " + client);
            clientView.printingInformation(client);
        } catch (AppBankException e) {
            logger.error(e.getMessage(), e);
            clientView.printingInformation(e.getMessage());
        } catch (Exception e) {
            String message = "Не удалось создать клиента" + client;
            logger.error(message, e);
            clientView.printingInformation(message);
        } finally {
            nextStep();
        }
    }

    public void update() {
        Client client = null;
        try {
            client = clientView.updateClient();
            clientService.update(client);
            String message = client.getFirst_name() + "  Изменен ";
            logger.trace(message);
            clientView.printingInformation(message);
        } catch (AppBankException e) {
            logger.error(e.getMessage(), e);
            clientView.printingInformation(e.getMessage());
        } catch (Exception e) {
            String message = "Не удалось обновить клиента" + client;
            logger.error(message, e);
            clientView.printingInformation(message);
        } finally {
            nextStep();
        }
    }

    public void delete() {

        try {
            Long clientId = clientView.deleteClient();
            clientService.delete(clientId);
        } catch (Exception e) {
            String message = "Проблема при удалении клиента";
            logger.error(message, e);
            clientView.printingInformation(message);
        } finally {
            nextStep();
        }
    }

    public void getAll() {
        clientView.printingInformation(clientService.getAll());
    }

    public void getById() {
        Long clientId = 0L;
        try {
            List<Client> clientIds = clientService.getAll();
            clientId = clientView.selectClient(clientIds);
            logger.trace("Выбран клиент с ID " + clientId);
            clientView.printingInformation(clientService.getById(clientId));
        } catch (AppBankException e) {
            logger.error(e.getMessage(), e);
            clientView.printingInformation(e.getMessage());
        } catch (Exception e) {
            String message = "Не удалось получить клиента по ID=" + clientId;
            logger.error(message, e);
            clientView.printingInformation(message);
        } finally {
            nextStep();
        }
    }

    private void nextStep() {
        clientView.printingInformation("Выберите нужный пункт меню");
    }

}


package org.example.menu;

import org.example.controllers.ClientController;

public class ClientMenu extends Menu {
    private final ClientController clientController;

    public ClientMenu(ClientController clientController) {
        this.clientController = clientController;
        addPoint(
                1,
                new PointOfMenu(
                        "Добавление клиента",
                        () -> {
                            clientController.create();
                            return true;
                        })
        );
        addPoint(
                2,
                new PointOfMenu(
                        "Получения списка клиентов",
                        () -> {
                            clientController.getAll();
                            return true;
                        })
        );
        addPoint(
                3,
                new PointOfMenu(
                        "Удаления клиента из списка",
                        () -> {
                            clientController.getAll();
                            clientController.delete();
                            return true;
                        })
        );
        addPoint(
                4,
                new PointOfMenu(
                        "Изменение данных клиента",
                        () -> {
                            clientController.getAll();
                            clientController.update();
                            return true;
                        })
        );
        addPoint(
                5,
                new PointOfMenu(
                        "Получение данных о клиента по её ID",
                        () -> {
                            clientController.getById();
                            return true;
                        })
        );

        addPoint(
                0,
                new PointOfMenu(
                        "Выйти в основное меню",
                        () -> false
                )
        );
    }
}


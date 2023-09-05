package org.example.menu;

import org.example.controllers.BankController;

public class BankMenu extends Menu{
    private final BankController bankController;

    public BankMenu(BankController bankController) {
        this.bankController = bankController;

        addPoint(
                1,
                new PointOfMenu(
                        "Добавление банка",
                        () -> {
                            bankController.create();
                            return true;
                        })
        );
        addPoint(
                2,
                new PointOfMenu(
                        "Получения списка банков",
                        () -> {
                            bankController.getAll();
                            return true;
                        })
        );
        addPoint(
                3,
                new PointOfMenu(
                        "Удаления банка из списка",
                        () -> {
                            bankController.getAll();
                            bankController.delete();
                            return true;
                        })
        );
        addPoint(
                4,
                new PointOfMenu(
                        "Изменение данных банка",
                        () -> {
                            bankController.getAll();
                            bankController.update();
                            return true;
                        })
        );
        addPoint(
                5,
                new PointOfMenu(
                        "Получение данных о банке по её ID",
                        () -> {
                            bankController.getById();
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

package org.example.menu;

import org.example.controllers.BankAccountController;

public class BankAccountMenu extends Menu{
    private final BankAccountController bankAccountController;

    public BankAccountMenu(BankAccountController bankAccountController) {
        this.bankAccountController = bankAccountController;

        addPoint(
                1,
                new PointOfMenu(
                        "Добавление счета",
                        () -> {
                            bankAccountController.create();
                            return true;
                        })
        );
        addPoint(
                2,
                new PointOfMenu(
                        "Получения списка счетов",
                        () -> {
                            bankAccountController.getAll();
                            return true;
                        })
        );
        addPoint(
                3,
                new PointOfMenu(
                        "Удаления счета из списка",
                        () -> {
                            bankAccountController.getAll();
                            bankAccountController.delete();
                            return true;
                        })
        );
        addPoint(
                4,
                new PointOfMenu(
                        "Изменение данных счета",
                        () -> {
                            bankAccountController.getAll();
                            bankAccountController.update();
                            return true;
                        })
        );
        addPoint(
                5,
                new PointOfMenu(
                        "Получение данных о счете по его ID",
                        () -> {
                            bankAccountController.getById();
                            return true;
                        })
        );
        addPoint(
                6,
                new PointOfMenu(
                        "Пополнение счета",
                        () -> {
                            bankAccountController.deposit();
                            return true;
                        })
        );
        addPoint(
                7,
                new PointOfMenu(
                        "Снятие со счета",
                        () -> {
                            bankAccountController.withdrawal();
                            return true;
                        })
        );
        addPoint(
                8,
                new PointOfMenu(
                        "Перевод средств на другой счет",
                        () -> {
                            bankAccountController.transferringFunds();
                            return true;
                        })
        );
        addPoint(
                9,
                new PointOfMenu(
                        "Выписка со счета",
                        () -> {
                            bankAccountController.accountStatement();
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

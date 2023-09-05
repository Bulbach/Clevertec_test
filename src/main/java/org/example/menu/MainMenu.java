package org.example.menu;

import org.example.controllers.MainMenuController;

public class MainMenu extends Menu {


    private MainMenuController mainMenuController;

    public MainMenu(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        addPoint(
                1,
                new PointOfMenu(
                        "Меню клиентов",
                        () -> {
                            mainMenuController.clientsMenu();
                            return true;
                        })
        );
        addPoint(
                2,
                new PointOfMenu(
                        "Меню банков",
                        () -> {
                            mainMenuController.banksMenu();
                            return true;
                        })
        );
        addPoint(
                3,
                new PointOfMenu(
                        "Меню счетов",
                        () -> {

                                mainMenuController.banksAccountMenu();
                                return true;
                        }
                )
        );

        addPoint(
                0,
                new PointOfMenu(
                        "Выйти из программы",
                        () -> {
                            mainMenuController.closeApp();
                            return false;
                        }
                )
        );
    }
}

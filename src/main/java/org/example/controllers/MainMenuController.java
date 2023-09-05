package org.example.controllers;

import org.example.menu.BankAccountMenu;
import org.example.menu.BankMenu;
import org.example.menu.ClientMenu;
import org.example.services.BankAccountService;
import org.example.services.BankService;
import org.example.services.ClientService;
import org.example.util.FillBase;
import static org.example.constant.ConstantApp.*;
public class MainMenuController {

    private final ClientMenu clientMenu;
    private final BankMenu bankMenu;
    private final BankAccountMenu bankAccountMenu;

    private final ClientService clientService;
    private final BankService bankService;
    private final BankAccountService bankAccountService;

    public MainMenuController(ClientMenu clientMenu, BankMenu bankMenu, BankAccountMenu bankAccountMenu,
                              ClientService clientService, BankService bankService, BankAccountService bankAccountService) {
        this.clientMenu = clientMenu;
        this.bankMenu = bankMenu;
        this.bankAccountMenu = bankAccountMenu;
        this.clientService = clientService;
        this.bankService = bankService;
        this.bankAccountService = bankAccountService;
    }

    public void clientsMenu() {
        clientMenu.showMenu();
    }

    public void banksMenu() {
        bankMenu.showMenu();
    }

    public void banksAccountMenu() {
        bankAccountMenu.showMenu();
    }

    public void closeApp(){
        FillBase.deleteBase(DDL_INITIALIZATION_DROP_PATH);
    }
  }

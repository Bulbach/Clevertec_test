package org.example;

import org.example.controllers.BankAccountController;
import org.example.controllers.BankController;
import org.example.controllers.ClientController;
import org.example.controllers.MainMenuController;
import org.example.jdbc.mapper.impl.BankAccountResultSetMapper;
import org.example.jdbc.mapper.impl.TransactionResultSetMapper;
import org.example.jdbc.query.impl.BankAccountSqlQueryHolderImpl;
import org.example.jdbc.query.impl.TransactionSqlQueryHolderImpl;
import org.example.jdbc.statement.impl.BankAccountStatementInitializer;
import org.example.jdbc.statement.impl.TransactionStatementInitializer;
import org.example.menu.BankAccountMenu;
import org.example.menu.BankMenu;
import org.example.menu.ClientMenu;
import org.example.menu.MainMenu;
import org.example.repository.BankAccountRepo;
import org.example.repository.BankRepo;
import org.example.repository.ClientRepo;
import org.example.repository.jdbc.BankAccountDaoJdbc;
import org.example.repository.jdbc.BankDaoJdbc;
import org.example.repository.jdbc.ClientDaoJdbc;
import org.example.services.BankAccountService;
import org.example.services.BankService;
import org.example.services.ClientService;
import org.example.util.FillBase;
import org.example.view.BankAccountView;
import org.example.view.BankView;
import org.example.view.ClientView;

import static org.example.constant.ConstantApp.*;

public class Runner {

    ClientService clientService;
    BankService bankService;

    BankAccountService bankAccountService;
    MainMenuController mainMenuController;

    private MainMenu mainMenu;

    public Runner() {
        try {
            FillBase.deleteBase(DDL_INITIALIZATION_DROP_PATH);
            FillBase.createDDl(DDL_INITIALIZATION_SCRIPT_PATH);
            FillBase.createDMl(DML_INITIALIZATION_SCRIPT_PATH);
            createObjects();
        } catch (RuntimeException e) {
            FillBase.deleteBase(DDL_INITIALIZATION_DROP_PATH);
            throw new RuntimeException("Ошибка инициализации базы ", e);
        }
    }

    private void createObjects() {
        ClientView clientView = new ClientView();
        BankView bankView = new BankView();
        BankAccountView bankAccountView = new BankAccountView();

        ClientRepo clientRepository = new ClientDaoJdbc();
        BankRepo bankRepository = new BankDaoJdbc();
        BankAccountSqlQueryHolderImpl bankAccountSqlQueryHolder = new BankAccountSqlQueryHolderImpl();
        BankAccountStatementInitializer bankAccountStatementInitializer = new BankAccountStatementInitializer();
        BankAccountResultSetMapper bankAccountResultSetMapper = new BankAccountResultSetMapper();
        TransactionSqlQueryHolderImpl transactionSqlQueryHolder = new TransactionSqlQueryHolderImpl();
        TransactionStatementInitializer transactionStatementInitializer = new TransactionStatementInitializer();
        TransactionResultSetMapper transactionResultSetMapper = new TransactionResultSetMapper();
        BankAccountRepo bankAccountRepository = new BankAccountDaoJdbc(bankAccountSqlQueryHolder, bankAccountStatementInitializer,
                bankAccountResultSetMapper, transactionSqlQueryHolder, transactionResultSetMapper, transactionStatementInitializer);


        clientService = new ClientService(clientRepository);
        bankService = new BankService(bankRepository);
        bankAccountService = new BankAccountService(bankAccountRepository);

        ClientController clientController = new ClientController(clientService, clientView);
        BankController bankController = new BankController(bankService, bankView);
        BankAccountController bankAccountController =
                new BankAccountController(bankAccountService, bankAccountView, clientService, bankService);

        ClientMenu clientMenu = new ClientMenu(clientController);
        BankMenu bankMenu = new BankMenu(bankController);
        BankAccountMenu bankAccountMenu = new BankAccountMenu(bankAccountController);

        mainMenuController = new MainMenuController(clientMenu, bankMenu, bankAccountMenu, clientService, bankService, bankAccountService);

        mainMenu = new MainMenu(mainMenuController);
    }

    ;

    public void run() {

        mainMenu.showMenu();
    }
}

package org.example.controllers;

import lombok.Data;
import org.example.exceptions.AppBankException;
import org.example.model.Bank;
import org.example.model.BankAccount;
import org.example.model.Client;
import org.example.services.BankAccountService;
import org.example.services.BankService;
import org.example.services.ClientService;
import org.example.view.BankAccountView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class BankAccountController {

    private static final Logger logger = LoggerFactory.getLogger(BankAccountController.class);

    private final BankAccountService accountService;
    private final BankAccountView accountView;

    private final ClientService clientService;
    private final BankService bankService;

    public BankAccountController(BankAccountService accountService, BankAccountView accountView,
                                 ClientService clientService, BankService bankService) {
        this.accountService = accountService;
        this.accountView = accountView;
        this.clientService = clientService;
        this.bankService = bankService;

    }

    public void create() {
        BankAccount bankAccount = null;
        try {
            bankAccount = getBankAccount();
            accountService.create(bankAccount);
            logger.trace("Создан заказ " + bankAccount);
            accountView.printingInformation(bankAccount);
        } catch (AppBankException e) {
            logger.error(e.getMessage(), e);
            accountView.printingInformation(e.getMessage());
        } catch (Exception e) {
            String message = "Не удалось создать счет " + bankAccount;
            logger.error(message, e);
            accountView.printingInformation(message);
        } finally {
            nextStep();
        }
    }

    private BankAccount getBankAccount() throws AppBankException {
        Bank bank = selectBank();
        Client client = selectClient();
        String accountNumber = selectAccountNumber();
        String currency = selectCurrency();
        LocalDate openDate = LocalDate.parse(accountView.selectDateFrom());
        Long accountBalance = depositAmount();
        return BankAccount.builder()
                .bankOwner(bank)
                .accountOwner(client)
                .accountNumber(accountNumber)
                .currency(currency)
                .openDate(openDate)
                .accountBalance(accountBalance)
                .build();
    }


    public void update() {
        BankAccount bankAccount = null;
        try {
            bankAccount = getBankAccount();
            accountService.update(bankAccount);
            String message = "Изменен заказ " + bankAccount;
            logger.trace(message);
            accountView.printingInformation(message);
        } catch (AppBankException e) {
            logger.error(e.getMessage(), e);
            System.out.println(e.getMessage());
        } catch (Exception e) {
            String message = "Не удалось обновить заказ " + bankAccount;
            logger.error(message, e);
            accountView.printingInformation(message);
        } finally {
            nextStep();
        }
    }

    public void delete() {

        try {
            Long accountId = accountView.deleteAccount();
            accountService.delete(accountId);
        } catch (Exception e) {
            String message = "Проблема при удалении заказа";
            logger.error(message, e);
            accountView.printingInformation(message);
        } finally {
            nextStep();
        }
    }

    public void getAll() {
        accountView.printingInformation(accountService.getAll());
    }

    public void getById() {
        Long accountId = 0L;
        try {
            List<BankAccount> accounts = accountService.getAll();
            accountId = accountView.selectBankAccount(accounts);
            logger.trace("Выбран заказ с ID " + accountId);
            accountView.printingInformation(accountService.getById(accountId));
        } catch (AppBankException e) {
            logger.error(e.getMessage(), e);
            accountView.printingInformation(e.getMessage());
        } catch (Exception e) {
            String message = "Не удалось получить клиента по ID= " + accountId;
            logger.error(message, e);
            accountView.printingInformation(message);
        } finally {
            nextStep();
        }
    }

    private Bank selectBank() throws AppBankException {
        List<Bank> banks = bankService.getAll();
        Long roomId = accountView.selectBank(banks);
        return bankService.getById(roomId);
    }

    private Client selectClient() throws AppBankException {
        List<Client> clients = clientService.getAll();
        Long clientId = accountView.selectClient(clients);
        return clientService.getById(clientId);
    }

    private String selectAccountNumber() {
        return accountView.selectAccountNumber();
    }

    private BankAccount selectAccount() throws AppBankException {
        List<BankAccount> accounts = accountService.getAll();
        Long id = accountView.selectBankAccount(accounts);
        return accountService.getById(id);
    }

    private String selectCurrency() {
        ArrayList<String> currencyList = createCurrencyList();
        int selectCurrency = accountView.selectCurrency(currencyList);
        return currencyList.get(selectCurrency);
    }

    private Long depositAmount() {
        return accountView.inputAmount();
    }

    private ArrayList<String> createCurrencyList() {
        ArrayList<String> currencies = new ArrayList<>();
        currencies.add("USD");
        currencies.add("EUR");
        currencies.add("RUB");
        currencies.add("BYN");
        return currencies;
    }

    private void nextStep() {
        accountView.printingInformation("Выберите нужный пункт меню");
    }

    public void deposit() {
        BankAccount bankAccount = selectAccount();
        Long transferAmount = accountView.inputAmount();
        accountService.updateDeposit(bankAccount, transferAmount);

    }

    public void withdrawal() {

        BankAccount bankAccount = selectAccount();
        Long transferAmount = -accountView.inputAmount();
        accountService.updateDeposit(bankAccount, transferAmount);

    }

    public void transferringFunds() {
        BankAccount fromBankAccount = selectAccount();
        BankAccount toBankAccount = selectAccount();
        Long transferAmount = accountView.inputAmount();
        accountService.transfer(fromBankAccount,toBankAccount,transferAmount);
    }

    public void accountStatement() {
        BankAccount bankAccount = selectAccount();
        accountService.getAllTransactionForBankAccount(bankAccount);
    }
}

package org.example.services;

import org.example.exceptions.AppBankException;
import org.example.model.BankAccount;
import org.example.repository.BankAccountRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BankAccountService {

    private static final Logger logger = LoggerFactory.getLogger(BankAccountService.class);

    private final BankAccountRepo accountRepository;

    public BankAccountService(BankAccountRepo accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<BankAccount> getAll() {
        return new ArrayList<>(accountRepository.getAll());
    }

    public BankAccount getById(Long id) throws AppBankException {
        if (id <= 0) {
            throw new AppBankException("Id должен быть больше нуля");
        }
        return accountRepository.getById(id);
    }

    public BankAccount create(BankAccount account) throws AppBankException {
        if (account == null) {
            throw new AppBankException("Счет не может быть null");
        }
        return accountRepository.create(account);
    }

    public BankAccount update(BankAccount account) throws AppBankException {
        if (account == null) {
            throw new AppBankException("Счет не может быть null");
        }
        return accountRepository.update(account);
    }

    public void delete(Long id) throws AppBankException {
        accountRepository.delete(id);
    }

    public void updateDeposit(BankAccount bankAccount, Long transferAmount) {
        verifyDepositAmount(bankAccount, transferAmount);
        accountRepository.updateDepositAmount(bankAccount, transferAmount);
    }

    private static void verifyDepositAmount(BankAccount bankAccount, Long transferAmount) {
        if (bankAccount == null || transferAmount == null) {
            throw new AppBankException("Обновить счет невозможно,объект счета или суммы вклада равно null");
        }

        if (transferAmount < 0 && bankAccount.getAccountBalance() + transferAmount < 0) {
            throw new AppBankException("Недостаточно средств на счете");
        }
    }

    public void transfer(BankAccount fromBankAccount, BankAccount toBankAccount, Long transferAmount) {
        verifyTransfer(fromBankAccount, toBankAccount, transferAmount);
        accountRepository.transferAmount(fromBankAccount,toBankAccount,transferAmount);
    }

    private static void verifyTransfer(BankAccount fromBankAccount, BankAccount toBankAccount, Long transferAmount) {

        if (fromBankAccount == null || toBankAccount == null || transferAmount == null) {
            throw new AppBankException("Перевод невозможен,объект счета или суммы вклада равно null");
        }
        if ((fromBankAccount.equals(toBankAccount))) {
            throw new AppBankException("Нельзя перевести на один и тот же счет");
        }
        if (transferAmount <= 0 ) {
            throw new AppBankException("Сумма должна быть больше нуля");
        }
        if(fromBankAccount.getAccountBalance() < transferAmount ){
            throw new AppBankException("Недостаточно средств на счете");
        }
    }

    public void getAllTransactionForBankAccount(BankAccount bankAccount) {

    }
}

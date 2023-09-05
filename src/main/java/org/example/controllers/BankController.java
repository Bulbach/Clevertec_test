package org.example.controllers;

import org.example.exceptions.AppBankException;
import org.example.model.Bank;
import org.example.services.BankService;
import org.example.view.BankView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BankController {

    private static final Logger logger = LoggerFactory.getLogger(BankController.class);
    private BankService bankService;
    private BankView bankView;

    public BankController(BankService bankService, BankView bankView) {
        this.bankService = bankService;
        this.bankView = bankView;
    }

    public void create() {
        Bank bank = null;
        try {
            bank = bankView.createBank();
            logger.trace("Создан банк " + bank);
            bankView.printingInformation(bankService.create(bank));
        } catch (AppBankException e) {
            logger.error(e.getMessage(), e);
            bankView.printingInformation(e.getMessage());
        } catch (Exception e) {
            String message = "Не удалось создать банк" + bank;
            logger.error(message, e);
            bankView.printingInformation(message);
        } finally {
            nextStep();
        }
    }

    public void update() {
        Bank bank = null;
        try {
            bank = bankView.updateBank();
            bankService.update(bank);
            String message = bank.getBrand() + "Изменён";
            logger.trace(message);
            bankView.printingInformation(message);
        } catch (AppBankException e) {
            logger.error(e.getMessage(), e);
            bankView.printingInformation(e.getMessage());
        } catch (Exception e) {
            String message = "Не удалось обновить информацыю о банке " + bank;
            logger.error(message, e);
            bankView.printingInformation(message);
        } finally {
            nextStep();
        }
    }

    public void delete() {

        try {
            Long clientId = bankView.deleteBank();
            bankService.delete(clientId);
        } catch (Exception e) {
            String message = "Проблема при удалении банка";
            logger.error(message, e);
            bankView.printingInformation(message);
        } finally {
            nextStep();
        }
    }

    public void getAll() {
        bankView.printingInformation(bankService.getAll());
    }

    public void getById() {
        Long bankId = 0L;
        try {
            List<Bank> banks = bankService.getAll();
            bankId = bankView.selectBank(banks);
            logger.trace("Выбрана банк с ID=" + bankId);
            bankView.printingInformation(bankService.getById(bankId));
        } catch (AppBankException e) {
            logger.error(e.getMessage(), e);
            bankView.printingInformation(e.getMessage());
        } catch (Exception e) {
            String message = "Не удалось найти комнату с ID= " + bankId;
            logger.error(message, e);
            bankView.printingInformation(message);
        } finally {
            nextStep();
        }
    }

     private void nextStep() {
        bankView.printingInformation("Выберите нужный пункт меню");
    }

}

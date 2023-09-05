package org.example.services;

import lombok.Data;
import org.example.exceptions.AppBankException;
import org.example.model.Bank;
import org.example.repository.BankRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Data
public class BankService {

    private static final Logger logger = LoggerFactory.getLogger(BankService.class);
    private final BankRepo bankRepository;

    public BankService( BankRepo bankRepository ) {
        this.bankRepository = bankRepository;

    }

    public List<Bank> getAll() {
        return new ArrayList<>(bankRepository.getAll());
    }
     public Bank getById(Long id) throws AppBankException {
        if (id <= 0) {
            throw new AppBankException("Id должен быть больше нуля");
        }
        return bankRepository.getById(id);
    }

    public Bank create(Bank bank) throws AppBankException {
        if (bank == null) {
            throw new AppBankException("Банк не может быть null");
        }
        return bankRepository.create(bank);
    }

    public Bank update(Bank bank) throws AppBankException {
        if (bank == null) {
            throw new AppBankException("Банк не может быть null");
        }
        return bankRepository.update(bank);
    }

    public void delete(Long id) throws AppBankException {
        bankRepository.delete(id);
    }

}

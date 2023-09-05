package org.example.repository;

import org.example.model.BankAccount;

public interface BankAccountRepo extends GenericAbstractRepository<BankAccount>{

   void updateDepositAmount(BankAccount bankAccount, Long transferAmount);

    void transferAmount(BankAccount fromBankAccount, BankAccount toBankAccount, Long transferAmount);

}

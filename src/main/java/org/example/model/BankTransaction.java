package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BankTransaction implements Model {

    private Long id;
    private LocalDateTime timeOperation;
    private String typeTransaction;
    private String senderAccount;
    private Long senderBalanceBefore;
    private String senderBank;
    private String recipientAccount;
    private Long recipientBalanceBefore;
    private String recipientBank;
    private Long amountTransaction;

    @Override
    public String toString() {
        return String.format("Банковский чек \n" +
                        "ID: %d\n" +
                        "Дата: %s\n" +
                        "Операция: %s\n" +
                        "Счет отправителя: %s\n" +
                        "Баланс до выполнения операции: %d\n" +
                        "Банк отправителя: %s\n" +
                        "Счет получателя: %s\n" +
                        "Баланс до выполнения операции: %d\n" +
                        "Банк отправителя: %s\n" +
                        "Сумма операции: %d",
                id, timeOperation, typeTransaction, senderAccount, senderBalanceBefore, senderBank,
                recipientAccount, recipientBalanceBefore, recipientBank, amountTransaction);
    }
}

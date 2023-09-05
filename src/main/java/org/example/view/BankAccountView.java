package org.example.view;

import org.example.model.Bank;
import org.example.model.BankAccount;
import org.example.model.Client;

import java.util.*;

public class BankAccountView {
    private Scanner scanner;

    public Long deleteAccount() {
        scanner = new Scanner(System.in);

        System.out.println("Введите ID заказа который требуется удалить");
        Long id = scanner.nextLong();

        return id;
    }


    public Long selectClient(List<Client> clients) {
        scanner = new Scanner(System.in);

        Optional<String> idStr = clients.stream()
                .map(client -> new AbstractMap.SimpleEntry<>(client.getSurname(), client.getId()))
                .map(Object::toString)
                .reduce((l, r) -> String.join(", ", l, r));
        System.out.println(idStr.orElse("Клиенты ещё не созданы"));
        System.out.println("Укажите Id клиента ");

        return scanner.nextLong();
    }

    public Long selectBank(List<Bank> banks) {
        scanner = new Scanner(System.in);
        Optional<String> idStr = banks.stream()
                .map(Object::toString)
                .reduce((l, r) -> String.join(", ", l, r));
        System.out.println(idStr.orElse("Банки ещё не созданы"));
        System.out.println("Укажите Id банка , для работы");

        return scanner.nextLong();
    }

    public Long selectBankAccount(List<BankAccount> bankAccounts) {
        scanner = new Scanner(System.in);

        Optional<String> idStr = bankAccounts.stream()
                .map(Object::toString)
                .reduce((l, r) -> String.join(", ", l, r));
        System.out.println(idStr.orElse("Счета ещё не созданы"));
        System.out.println("Укажите Id счета, для работы");

        return scanner.nextLong();
    }

    private String selectDate() {
        scanner = new Scanner(System.in);

        System.out.println("Введите день(пример 01)");
        String day = scanner.nextLine();

        System.out.println("Введите месяц (пример: 01)");
        String month = scanner.nextLine();

        System.out.println("Введите год полностью(пример: 2020)");
        String year = scanner.nextLine();

        return year + "-" + month + "-" + day;
    }

    public String selectDateFrom() {
        System.out.println("Укажите дату создания счета");
        return selectDate();
    }

    public String selectAccountNumber() {
        scanner = new Scanner(System.in);
        System.out.println("Введите номер счета");
        return scanner.nextLine();
    }
//    public int selectCurrency(ArrayList )

    public void printingInformation(Object o) {
        System.out.println(o);
    }

    public int selectCurrency(ArrayList<String> currencyList) {
        scanner = new Scanner(System.in);
        System.out.println("Выберите валюту:");
        int index = 1;
        for (String currency : currencyList) {
            System.out.println(index + ". " + currency);
            index++;
        }
        return scanner.nextInt();
    }

    public Long inputAmount() {
        scanner = new Scanner(System.in);
        System.out.println("Введите сумму");
        return scanner.nextLong();
    }

//    public void showListTransaction(List<BankAccount> bankAccounts) {
//        Long checkIdAccount = selectBankAccount(bankAccounts);
//
//    }
}

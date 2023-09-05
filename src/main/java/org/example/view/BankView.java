package org.example.view;

import org.example.model.Bank;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BankView {

    private Scanner scanner;


    public Bank createBank() {
        scanner = new Scanner(System.in);
        System.out.println("Введите название банка");
        String brand = scanner.nextLine();
        return Bank.builder()
                .brand(brand)
                .build();
    }

    public Bank updateBank() {
        scanner = new Scanner(System.in);

        System.out.println("Введите ID банка в котором требуются изменения");
        String idBank = scanner.nextLine();
        Long id = Long.parseLong(idBank);

        System.out.println("Введите новое название банка");
        String brand = scanner.nextLine();

        return Bank.builder()
                .id(id)
                .brand(brand)
                .build();
    }

    public Long deleteBank() {
        scanner = new Scanner(System.in);

        System.out.println("Введите ID банка который требуется удалить");
        Long id = scanner.nextLong();

        return id;
    }

    public Long getByID() {
        scanner = new Scanner(System.in);

        System.out.println("Введите ID банка о котором требуется информация ");
        Long id = scanner.nextLong();

        return id;
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

    public String currentDate() {
        scanner = new Scanner(System.in);

        System.out.println("Введите день");
        String day = scanner.nextLine();

        System.out.println("Введите месяц");
        String month = scanner.nextLine();

        System.out.println("Введите год полностью(пример: 2020)");
        String year = scanner.nextLine();

        return year + month + day;
    }
    public void printingInformation(Object o) {

        System.out.println(o);
    }
}

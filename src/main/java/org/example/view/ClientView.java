package org.example.view;

import org.example.model.Client;

import java.util.AbstractMap;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientView extends View {

    private Scanner scanner;

    public Client createClient() {
        scanner = new Scanner(System.in);

        System.out.println("Введите имя");
        String first_name = scanner.nextLine();

        System.out.println("Введите фамилию");
        String surname = scanner.nextLine();

        System.out.println("Введите персональный номер");
        String personalIdentifier = scanner.nextLine();

        return Client.builder()
                .personalIdentifier(personalIdentifier)
                .first_name(first_name)
                .surname(surname)
                .build();
    }

    public Client updateClient() {
        scanner = new Scanner(System.in);

        System.out.println("Введите ID клиента у которого требуются изменения");
        String idStr = scanner.nextLine();
        Long id = Long.parseLong(idStr);

        System.out.println("Введите имя");
        String first_name = scanner.nextLine();

        System.out.println("Введите фамилию");
        String surname = scanner.nextLine();

        System.out.println("Введите персональный номер");
        String personalIdentifier = scanner.nextLine();

        return Client.builder()
                .id(id)
                .first_name(first_name)
                .surname(surname)
                .personalIdentifier(personalIdentifier)
                .build();
    }

    public Long deleteClient() {
        scanner = new Scanner(System.in);

        System.out.println("Введите ID клиента которого требуется удалить");
        Long id = scanner.nextLong();

        return id;
    }

    public Long getByID() {
        scanner = new Scanner(System.in);

        System.out.println("Ведите Id");
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
    public void printingInformation(Object o) {
        System.out.println(o);
    }
}

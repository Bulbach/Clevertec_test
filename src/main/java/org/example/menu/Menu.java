package org.example.menu;

import org.example.exceptions.AppBankException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private final PointOfMenu defaultPoint;
    private final Map<Integer, PointOfMenu> points = new HashMap<>();
    private final Scanner scan = new Scanner(System.in);

    public Menu(PointOfMenu defaultPoint) {
        this.defaultPoint = defaultPoint;
    }

    public Menu() {
        this(new PointOfMenu("Такого пункта не существует", () -> true));
    }

    public void showMenu() {
        int choice;
        boolean repeat;
        do {

            points.forEach((key, value) -> System.out.println(key.toString() + ". " + value.getText()));
            choice = scan.nextInt();
            try {
                repeat = points.getOrDefault(choice, defaultPoint).getAction().get();
            } catch (AppBankException e) {
                System.out.println(e.getMessage());
                repeat = false;
            }
        } while (repeat);
    }

    public void addPoint(Integer id, PointOfMenu point) {
        points.put(id, point);
    }

}

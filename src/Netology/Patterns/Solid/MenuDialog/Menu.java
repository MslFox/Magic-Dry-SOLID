package Netology.Patterns.Solid.MenuDialog;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private final List<MenuItem> menuItems = new ArrayList<>();

    public Menu(String nameMenu) {
        menuItems.add(new MenuItem(nameMenu + "Выберите пункт меню:",
                System.out::println));
    }

    protected Menu addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
        return this;
    }

    public void menuChoice() {
        printMenu(menuItems);
        int choice = 0;
        scanner.reset();
        try {
            choice = scanner.nextInt();
            if (choice < 1 || choice > menuItems.size())
                throw new InputMismatchException();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Введите корректный номер пункта меню!");
            menuChoice();
        }
        menuItems.get(choice).execute();
    }

    private void printMenu(List<MenuItem> menuItems) {
        menuItems.forEach(item ->
                System.out.println(menuItems.indexOf(item) == 0 ?
                        item.getMenuItemName() :
                        menuItems.indexOf(item) + ". " + item.getMenuItemName())
        );
    }
}

package ru.job4j.ood.isp.menu;

import java.util.Scanner;

/**
 * 6. Создайте простенький класс TodoApp. Этот класс будет представлять собой консольное приложение, которое позволяет:
 * Добавить элемент в корень меню;
 * Добавить элемент к родительскому элементу;
 * Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
 * например, ActionDelete DEFAULT_ACTION = () -> System.out.println("Some action") и указывать при добавлении элемента в меню);
 * Вывести меню в консоль.
 */
public class TodoApp {
    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");
        Scanner sc = new Scanner(System.in);
        String separator = System.lineSeparator();
        boolean isRunning = true;
        String start = "Menu: "
                + separator
                + "1. add new root element"
                + separator
                + "2. add new element to root"
                + separator
                + "3. execute action"
                + separator
                + "4. show menu"
                + separator
                + "0. exit";
        while (isRunning) {
            System.out.println(start);
            int chooseAction = Integer.parseInt(sc.nextLine());
            if (chooseAction == 1) {
                System.out.print("enter new root name: ");
                String childName = sc.nextLine();
                menu.add(null, childName, DEFAULT_ACTION);
            } else if (chooseAction == 2) {
                System.out.print("enter root name: ");
                String rootName = sc.nextLine();
                System.out.print("enter child name: ");
                String childName = sc.nextLine();
                menu.add(rootName, childName, DEFAULT_ACTION);
            } else if (chooseAction == 3) {
                DEFAULT_ACTION.delegate();
            } else if (chooseAction == 4) {
                new SimpleMenuPrinter().print(menu);
            } else if (chooseAction == 0) {
                isRunning = false;
            } else {
                System.out.println("enter valid data");
            }
        }
    }
}

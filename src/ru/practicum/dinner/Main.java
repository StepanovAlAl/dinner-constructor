package ru.practicum.dinner;

import java.util.*;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Неизвестная команда. Пожалуйста, выберите снова.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        // добавьте новое блюдо
        dc.addDish(dishType, dishName);
        System.out.println("Блюдо добавлено.");
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        List<String> types = new ArrayList<>();
        while (true) {
            String nextItem = scanner.nextLine();
            if (nextItem.isEmpty()) {
                break;
            }
            if (!dc.checkType(nextItem)) {
                System.out.println("Тип блюда '" + nextItem + "' не существует. Пожалуйста, введите другой тип.");
                continue;
            }
            types.add(nextItem);
        }

        if (types.isEmpty()) {
            System.out.println("Не введено ни одного типа блюда.");
            return;
        }
        // сгенерируйте комбинации блюд и выведите на экран
        List<List<String>> combinations = dc.generateCombinations(numberOfCombos, types);
        if (combinations.isEmpty()) {
            System.out.println("Не удалось сгенерировать комбинации.");
        } else {
            System.out.println("Сгенерированные комбинации:");
            for (List<String> combo : combinations) {
                System.out.println(combo);
            }
        }
    }
}

package util;

import entity.Bus;
import entity.Student;
import entity.User;
import sorting.BinarySearch;
import sorting.Strategy;
import util.handler.EntityHandler;
import util.handler.EntityInputHandler;
import util.handler.FileHandler;
import util.validate.InputValidator;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final EntityContainer entityContainer = new EntityContainer();
    private final Strategy<String> strategy = new BinarySearch<>();

    public void run() {
        while (true) {
            int mainChoice = InputValidator.validateNumber(scanner, returnMainOptions());
            switch (mainChoice) {
                case 1 -> printAddOptions();
                case 2 -> printSortOptions();
                case 3 -> printWriteOptions();
                case 4 -> printClearOptions();
                case 5 -> printListOptions(); // Вывод списков
                case 6 -> printSearchOptions(); // Поиск
                case 7 -> System.exit(0); // Выход
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
    private String returnMainOptions() {
        return """
                Выберите категорию действий:
                1. Добавление
                2. Сортировка
                3. Запись в файл
                4. Очистка файла
                5. Вывод списков
                6. Поиск
                7. Выход
                """;
    }
    private void printSearchOptions() {
        boolean running = true;
        while (running) {
            int choice = InputValidator.validateNumber(scanner, InputValidator.entityOption());

            switch (choice) {
                case 1 -> searchBuses();
                case 2 -> searchUsers();
                case 3 -> searchStudents();
                case 4 -> running = false;
                case 5 -> run();
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void searchBuses() {
        if (entityContainer.buses == null || entityContainer.buses.length == 0) {
            System.out.println("Массив автобусов пуст! Сначала нужно добавить данные!");
            return;
        }
        System.out.print("Введите номер автобуса для поиска: ");
        String busNumber = scanner.nextLine().trim();
        int index = strategy.search(
                Arrays.stream(entityContainer.buses).map(Bus::getNumber).toArray(String[]::new), busNumber);

        if (index >= 0) {
            System.out.println("Найден автобус: " + entityContainer.buses[index]);
        } else {
            System.out.println("Автобус с номером " + busNumber + " не найден. Попробуйте отсортировать массив.");
        }
    }

    private void searchUsers() {
        if (entityContainer.users == null || entityContainer.users.length == 0) {
            System.out.println("Массив пользователей пуст! Сначала нужно добавить данные!");
            return;
        }
        System.out.print("Введите email пользователя для поиска: ");
        String userEmail = scanner.nextLine().trim();
        int index = strategy.search(
                Arrays.stream(entityContainer.users).map(User::getEmail).toArray(String[]::new), userEmail);

        if (index >= 0) {
            System.out.println("Найден пользователь: " + entityContainer.users[index]);
        } else {
            System.out.println("Пользователь с email " + userEmail + " не найден. Попробуйте отсортировать массив.");
        }
    }

    private void searchStudents() {
        if (entityContainer.students == null || entityContainer.students.length == 0) {
            System.out.println("Массив студентов пуст! Сначала нужно добавить данные!");
            return;
        }
        System.out.print("Введите номер группы студента для поиска: ");
        String groupNumber = scanner.nextLine().trim();
        int index = strategy.search(
                Arrays.stream(entityContainer.students).map(Student::getGroupNumber).toArray(String[]::new), groupNumber);

        if (index >= 0) {
            System.out.println("Найден студент: " + entityContainer.students[index]);
        } else {
            System.out.println("Студент с номером группы " + groupNumber + " не найден.");
        }
    }

    private void printListOptions() {
        boolean running = true;
        while (running) {
            int choice = InputValidator.validateNumber(scanner, InputValidator.entityOption());

            switch (choice) {
                case 1 -> printBuses();
                case 2 -> printUsers();
                case 3 -> printStudents();
                case 4 -> running = false;
                case 5 -> run();
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void printBuses() {
        if (entityContainer.buses == null || entityContainer.buses.length == 0) {
            System.out.println("Список автобусов пуст.");
        } else {
            System.out.println("Список автобусов:");
            for (Bus bus : entityContainer.buses) {
                if (bus != null) {
                    System.out.println(bus);
                }
            }
        }
    }

    private void printUsers() {
        if (entityContainer.users == null || entityContainer.users.length == 0) {
            System.out.println("Список пользователей пуст.");
        } else {
            System.out.println("Список пользователей:");
            for (User user : entityContainer.users) {
                if (user != null) {
                    System.out.println(user);
                }
            }
        }
    }

    private void printStudents() {
        if (entityContainer.students == null || entityContainer.students.length == 0) {
            System.out.println("Список студентов пуст.");
        } else {
            System.out.println("Список студентов:");
            for (Student student : entityContainer.students) {
                if (student != null) {
                    System.out.println(student);
                }
            }
        }
    }

    private void printAddOptions() {
        boolean running = true;
        while (running) {
            String inputOption = """
                    Выберите способ добавления:
                    1. Вручную
                    2. Из файла
                    3. Случайно
                    4. Назад
                    5. Выход в главное меню
                    """;
            int choice = InputValidator.validateNumber(scanner, inputOption);

            switch (choice) {
                case 1 -> EntityInputHandler.addEntityManually(scanner, entityContainer);
                case 2 -> EntityInputHandler.addEntityFromFile(scanner, entityContainer);
                case 3 -> EntityInputHandler.addEntityRandomly(scanner, entityContainer);
                case 4 -> running = false;
                case 5 -> run();
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void printSortOptions() {
        boolean running = true;
        while (running) {
            String sortOption = """
                    Выберите действие:
                    1. Сортировать автобусы
                    2. Сортировать пользователей
                    3. Сортировать студентов
                    4. Назад
                    5. Выход в главное меню
                    """;
            int choice = InputValidator.validateNumber(scanner, sortOption);
            switch (choice) {
                case 1 -> {
                    if (entityContainer.buses == null) {
                        System.out.println("Массив пуст, сначала заполните массив.");
                    } else {
                        EntityHandler.sortAndPrint(entityContainer.buses, Bus.numberComparator);
                    }
                }
                case 2 -> {
                    if (entityContainer.users == null) {
                        System.out.println("Массив пуст, сначала заполните массив.");
                    } else {
                        EntityHandler.sortAndPrint(entityContainer.users, User.emailComparator);
                    }
                }
                case 3 -> {
                    if (entityContainer.students == null) {
                        System.out.println("Массив пуст, сначала заполните массив.");
                    } else {
                        EntityHandler.sortAndPrint(entityContainer.students, Student.averageScoreComparator);
                    }
                }
                case 4 -> running = false;
                case 5 -> run();
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void printWriteOptions() {
        boolean running = true;
        while (running) {
            String writeOption = """
                    Выберите действие:
                    1. Записать отсортированные автобусы в файл
                    2. Записать отсортированных пользователей в файл
                    3. Записать отсортированных студентов в файл
                    4. Назад
                    5. Выход в главное меню
                    """;
            int choice = InputValidator.validateNumber(scanner, writeOption);

            switch (choice) {
                case 1 -> {
                    if (entityContainer.buses == null) {
                        System.out.println("Массив пуст, записывать нечего!");
                    } else {
                        FileHandler.writeToFile(entityContainer.buses, "sorted_buses.txt");
                    }
                }
                case 2 -> {
                    if (entityContainer.users == null) {
                        System.out.println("Массив пуст, записывать нечего!");
                    } else {
                        FileHandler.writeToFile(entityContainer.users, "sorted_users.txt");
                    }
                }
                case 3 -> {
                    if (entityContainer.students == null) {
                        System.out.println("Массив пуст, записывать нечего!");
                    } else {
                        FileHandler.writeToFile(entityContainer.students, "sorted_students.txt");
                    }
                }
                case 4 -> running = false;
                case 5 -> run();
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void printClearOptions() {
        boolean running = true;
        while (running) {
            String clearOption = """
                    Выберите действие:
                    1. Очистить файл автобусов
                    2. Очистить файл пользователей
                    3. Очистить файл студентов
                    4. Назад
                    5. Выход в главное меню
                    """;
            int choice = InputValidator.validateNumber(scanner, clearOption);

            switch (choice) {
                case 1 -> FileHandler.clearFile("sorted_buses.txt");
                case 2 -> FileHandler.clearFile("sorted_users.txt");
                case 3 -> FileHandler.clearFile("sorted_students.txt");
                case 4 -> running = false;
                case 5 -> run();
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}


package util;

import entity.Bus;
import entity.Student;
import entity.User;
import sorting.BinarySearch;
import sorting.Strategy;
import util.handler.EntityHandler;
import util.handler.EntityInputHandler;
import util.handler.FileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Bus> buses = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();
    private Strategy sortable;

    public void run() {
        while (true) {
            printMainOptions();
            int mainChoice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (mainChoice) {
                case 1 -> printAddOptions();
                case 2 -> printSortOptions();
                case 3 -> printWriteOptions();
                case 4 -> printClearOptions();
                case 5 -> printListOptions(); // Добавляем вывод списков
                case 6 -> printSearchOptions(); // Добавляем поиск
                case 7 -> System.exit(0); // Выход
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }


    private void printMainOptions() {
        System.out.println("""
                Выберите категорию действий:
                1. Добавление
                2. Сортировка
                3. Запись в файл
                4. Очистка файла
                5. Вывод списков
                6. Поиск
                7. Выход
                """);
    }

    private void printSearchOptions() {
        System.out.println("""
                Выберите тип сущности для поиска:
                1. Автобус
                2. Пользователь
                3. Студент
                4. Назад
                """);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        switch (choice) {
            case 1 -> searchBuses();
            case 2 -> searchUsers();
            case 3 -> searchStudents();
            case 4 -> { /* Назад в главное меню */ }
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void searchBuses() {
        System.out.print("Введите номер автобуса для поиска: ");
        String busNumber = scanner.nextLine().trim();
        int index = sortable.search(buses, busNumber); // Используем ваш класс BinarySearch

        if (index >= 0) {
            Bus foundBus = buses.get(index); // Получаем объект по индексу
            System.out.println("Найден автобус: " + foundBus);
        } else {
            System.out.println("Автобус с номером " + busNumber + " не найден.");
        }
    } //todo придумать как реализовать поиск по какому-то параметру он тут выдаёт ClassCastException

    private void searchUsers() {
        System.out.print("Введите email пользователя для поиска: ");
        String userEmail = scanner.nextLine().trim();
        int index = sortable.search(users, userEmail); // Используем ваш класс BinarySearch

        if (index >= 0) {
            User foundUser = users.get(index); // Получаем объект по индексу
            System.out.println("Найден пользователь: " + foundUser);
        } else {
            System.out.println("Пользователь с email " + userEmail + " не найден.");
        }
    } //todo придумать как реализовать поиск по какому-то параметру он тут выдаёт ClassCastException

    private void searchStudents() {
        System.out.print("Введите номер группы студента для поиска: ");
        String groupNumber = scanner.nextLine().trim();
        int index = sortable.search(students, groupNumber); // Используем ваш класс BinarySearch

        if (index >= 0) {
            Student foundStudent = students.get(index); // Получаем объект по индексу
            System.out.println("Найден студент: " + foundStudent);
        } else {
            System.out.println("Студент с номером группы " + groupNumber + " не найден.");
        }
    } //todo придумать как реализовать поиск по какому-то параметру он тут выдаёт ClassCastException

    private void printListOptions() {
        System.out.println("""
                Выберите список для вывода:
                1. Автобусы
                2. Пользователи
                3. Студенты
                4. Назад
                """);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        switch (choice) {
            case 1 -> printBuses();
            case 2 -> printUsers();
            case 3 -> printStudents();
            case 4 -> { /* Назад в главное меню */ }
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void printBuses() {
        if (buses.isEmpty()) {
            System.out.println("Список автобусов пуст.");
        } else {
            System.out.println("Список автобусов:");
            for (Bus bus : buses) {
                System.out.println(bus); // Предполагается, что метод toString() переопределен в классе Bus
            }
        }
    }

    private void printUsers() {
        if (users.isEmpty()) {
            System.out.println("Список пользователей пуст.");
        } else {
            System.out.println("Список пользователей:");
            for (User user : users) {
                System.out.println(user); // Предполагается, что метод toString() переопределен в классе User
            }
        }
    }

    private void printStudents() {
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст.");
        } else {
            System.out.println("Список студентов:");
            for (Student student : students) {
                System.out.println(student); // Предполагается, что метод toString() переопределен в классе Student
            }
        }
    }

    private void printAddOptions() {
        System.out.println("""
                Выберите способ добавления:
                1. Вручную
                2. Из файла
                3. Случайно
                4. Назад
                """);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        switch (choice) {
            case 1 -> EntityInputHandler.addEntityManually(scanner, buses, users, students);
            case 2 -> EntityInputHandler.addEntityFromFile(scanner, buses, users, students);
            case 3 -> EntityInputHandler.addEntityRandomly(scanner, buses, users, students);
            case 4 -> { /* Назад в главное меню */ }
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void printSortOptions() {
        System.out.println("""
                Выберите действие:
                1. Сортировать автобусы
                2. Сортировать пользователей
                3. Сортировать студентов
                4. Назад
                """);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        switch (choice) {
            case 1 -> EntityHandler.sortAndPrint(buses, Bus.mileageComparator);
            case 2 -> EntityHandler.sortAndPrint(users, User.emailComparator);
            case 3 -> EntityHandler.sortAndPrint(students, Student.averageScoreComparator);
            case 4 -> { /* Назад в главное меню */ }
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void printWriteOptions() {
        System.out.println("""
                Выберите действие:
                1. Записать отсортированные автобусы в файл
                2. Записать отсортированных пользователей в файл
                3. Записать отсортированных студентов в файл
                4. Назад
                """);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        switch (choice) {
            case 1 -> FileHandler.writeToFile(buses, "sorted_buses.txt");
            case 2 -> FileHandler.writeToFile(users, "sorted_users.txt");
            case 3 -> FileHandler.writeToFile(students, "sorted_students.txt");
            case 4 -> { /* Назад в главное меню */ }
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void printClearOptions() {
        System.out.println("""
                Выберите действие:
                1. Очистить файл автобусов
                2. Очистить файл пользователей
                3. Очистить файл студентов
                4. Назад
                """);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        switch (choice) {
            case 1 -> FileHandler.clearFile("sorted_buses.txt");
            case 2 -> FileHandler.clearFile("sorted_users.txt");
            case 3 -> FileHandler.clearFile("sorted_students.txt");
            case 4 -> { /* Назад в главное меню */ }
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }
}
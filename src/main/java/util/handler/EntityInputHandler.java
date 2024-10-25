package util.handler;

import entity.Bus;
import entity.Student;
import entity.User;

import java.util.*;

public class EntityInputHandler {
    public static void addEntityManually(Scanner scanner, List<Bus> buses, List<User> users, List<Student> students) {
        System.out.println("""
            Выберите тип сущности для добавления вручную:
            1. Автобус
            2. Пользователь
            3. Студент
            4. Назад
            """);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        switch (choice) {
            case 1 -> EntityHandler.addBus(scanner, buses);
            case 2 -> EntityHandler.addUser(scanner, users);
            case 3 -> EntityHandler.addStudent(scanner, students);
            case 4 -> { /* Назад в подменю добавления */ }
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    public static void addEntityFromFile(Scanner scanner, List<Bus> buses, List<User> users, List<Student> students) {
        System.out.println("""
        Выберите тип сущности для добавления из файла:
        1. Автобус
        2. Пользователь
        3. Студент
        4. Назад
        """);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        String filePath;

        switch (choice) {
            case 1 -> {
                System.out.print("Введите путь к файлу автобусов: ");
                filePath = scanner.nextLine();
                buses.addAll(FileHandler.readFromFile(filePath, Bus::fromString));
            }
            case 2 -> {
                System.out.print("Введите путь к файлу пользователей: ");
                filePath = scanner.nextLine();
                users.addAll(FileHandler.readFromFile(filePath, User::fromString));
            }
            case 3 -> {
                System.out.print("Введите путь к файлу студентов: ");
                filePath = scanner.nextLine();
                students.addAll(FileHandler.readFromFile(filePath, Student::fromString));
            }
            case 4 -> { /* Назад в подменю добавления */ }
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    public static void addEntityRandomly(Scanner scanner, List<Bus> buses, List<User> users, List<Student> students) {
        System.out.println("""
            Выберите тип сущности для случайного добавления:
            1. Автобус
            2. Пользователь
            3. Студент
            4. Назад
            """);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        switch (choice) {
            case 1 -> buses.add(RandomEntityGenerator.createRandomBus());
            case 2 -> users.add(RandomEntityGenerator.createRandomUser());
            case 3 -> students.add(RandomEntityGenerator.createRandomStudent());
            case 4 -> { /* Назад в подменю добавления */ }
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

}


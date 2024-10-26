package util.handler;

import entity.Bus;
import entity.Student;
import entity.User;
import util.EntityContainer;

import java.util.*;

public class EntityInputHandler {
    public static void addEntityManually(Scanner scanner, Bus[] buses, User[] users, Student[] students) {
        boolean running = true;
        while (running) {
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
                case 1 -> {
                    if (buses == null) {
                        System.out.println("Введите необходимое количество элементов массива: ");
                        buses = new Bus[scanner.nextInt() + 1];
                        scanner.nextLine();
                        EntityHandler.addBus(scanner, buses);
                    } else {
                        EntityHandler.addBus(scanner, buses);
                    }
                }
                case 2 -> {
                    if (users == null) {
                        System.out.println("Введите необходимое количество элементов массива: ");
                        users = new User[scanner.nextInt()];
                        scanner.nextLine();
                        EntityHandler.addUser(scanner, users);
                    } else {
                        EntityHandler.addUser(scanner, users);
                    }
                }
                case 3 -> {
                    if (students == null) {
                        System.out.println("Введите необходимое количество элементов массива: ");
                        students = new Student[scanner.nextInt() + 1];
                        scanner.nextLine();
                        EntityHandler.addStudent(scanner, students);
                    } else {
                        EntityHandler.addStudent(scanner, students);
                    }
                }
                case 4 -> running = false; /* Назад в подменю добавления */
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
//    public static void addEntityFromFile(Scanner scanner, EntityContainer container) {
//        boolean running = true;
//        while (running) {
//            System.out.println("""
//                Выберите тип сущности для добавления из файла:
//                1. Автобус
//                2. Пользователь
//                3. Студент
//                4. Назад
//                """);
//            int choice;
//            if (scanner.hasNextInt()) {
//                choice = scanner.nextInt();
//                scanner.nextLine();
//            } else {
//                System.out.println("Ошибка: ожидалось целое число.");
//                scanner.nextLine(); // Очистка буфера
//                continue;
//            }
//
//            String filePath;
//
//            switch (choice) {
//                case 1 -> {
//                    filePath = "buses.csv";
//                    if (container.buses == null) {
//                        System.out.println("Введите необходимое количество элементов массива: ");
//                        if (scanner.hasNextInt()) {
//                            int arraySize = scanner.nextInt() + 1;
//                            scanner.nextLine();
//                            container.buses = new Bus[arraySize];
//                            System.out.println("Массив автобусов инициализирован с размером: " + arraySize);
//                        } else {
//                            System.out.println("Ошибка: ожидалось целое число.");
//                            scanner.nextLine();
//                            continue;
//                        }
//                    }
//                    try {
//                        container.buses = FileHandler.readFromFile(filePath, Bus::fromString, container.buses.length).toArray(new Bus[0]);
//                        System.out.println("Данные успешно считаны из файла: " + filePath);
//                    } catch (Exception e) {
//                        System.out.println("Ошибка при чтении файла: " + e.getMessage());
//                    }
//                }
//                case 2 -> {
//                    filePath = "users.csv";
//                    if (container.users == null) {
//                        System.out.println("Введите необходимое количество элементов массива: ");
//                        if (scanner.hasNextInt()) {
//                            int arraySize = scanner.nextInt() + 1;
//                            scanner.nextLine();
//                            container.users = new User[arraySize];
//                            System.out.println("Массив пользователей инициализирован с размером: " + arraySize);
//                        } else {
//                            System.out.println("Ошибка: ожидалось целое число.");
//                            scanner.nextLine();
//                            continue;
//                        }
//                    }
//                    try {
//                        container.users = FileHandler.readFromFile(filePath, User::fromString, container.users.length).toArray(new User[0]);
//                        System.out.println("Данные успешно считаны из файла: " + filePath);
//                    } catch (Exception e) {
//                        System.out.println("Ошибка при чтении файла: " + e.getMessage());
//                    }
//                }
//                case 3 -> {
//                    filePath = "students.csv";
//                    if (container.students == null) {
//                        System.out.println("Введите необходимое количество элементов массива: ");
//                        if (scanner.hasNextInt()) {
//                            int arraySize = scanner.nextInt() + 1;
//                            scanner.nextLine();
//                            container.students = new Student[arraySize];
//                            try {
//                                container.students = FileHandler.readFromFile(filePath, Student::fromString, container.students.length).toArray(new Student[0]);
//                                System.out.println("Данные успешно считаны из файла: " + filePath);
//                            } catch (Exception e) {
//                                System.out.println("Ошибка при чтении файла: " + e.getMessage());
//                            }
//                        } else {
//                            System.out.println("Ошибка: ожидалось целое число.");
//                            scanner.nextLine();
//                            continue;
//                        }
//                    }
//                }
//                case 4 -> running = false; // Назад в подменю добавления
//                default -> System.out.println("Неверный выбор. Попробуйте снова.");
//            }
//        }
//    }

    public static void addEntityFromFile(Scanner scanner, Bus[] buses, User[] users, Student[] students) {
        boolean running = true;
        while (running) {
            System.out.println("""
                    Выберите тип сущности для добавления из файла:
                    1. Автобус
                    2. Пользователь
                    3. Студент
                    4. Назад
                    """);

            int choice = scanner.nextInt();
            scanner.nextLine();


            String filePath;

            switch (choice) {
                case 1 -> {
                    filePath = "buses.csv";
                    if (buses == null) {
                        System.out.println("Введите необходимое количество элементов массива: ");
                        if (scanner.hasNextInt()) {
                            int arraySize = scanner.nextInt() + 1;
                            scanner.nextLine();
                            buses = new Bus[arraySize];
                            System.out.println("Массив автобусов инициализирован с размером: " + arraySize);
                        } else {
                            System.out.println("Ошибка: ожидалось целое число.");
                            continue;
                        }
                        scanner.nextLine();
                    }
                    try {
                        buses =  FileHandler.readFromFile(filePath, Bus::fromString, buses.length).toArray(new Bus[0]);
                        System.out.println(Arrays.toString(buses));
                        System.out.println("Данные успешно считаны из файла: " + filePath);
                    } catch (Exception e) {
                        System.out.println("Ошибка при чтении файла: " + e.getMessage());
                    }
                    return;
                }
                case 2 -> {
                    filePath = "users.csv";
                    if (users == null) {
                        System.out.println("Введите необходимое количество элементов массива: ");
                        if (scanner.hasNextLine()) {
                            int arraySize = scanner.nextInt() + 1;
                            users = new User[arraySize];
                            System.out.println("Массив пользователей инициализирован с размером: " + arraySize);
                        } else {
                            System.out.println("Ошибка: ожидалось целое число.");
                            scanner.nextLine();
                            return;
                        }
                        try {
                            users = FileHandler.readFromFile(filePath, User::fromString, users.length).toArray(new User[0]);
                            System.out.println("Данные успешно считаны из файла: " + filePath);
                        } catch (Exception e) {
                            System.out.println("Ошибка при чтении файла: " + e.getMessage());
                        }
                    }
                }
                case 3 -> {
//                    System.out.print("Введите путь к файлу студентов: ");
                    filePath = "students.csv";
                    if (students == null) {
                        System.out.println("Введите необходимое количество элементов массива: ");
                        students = new Student[scanner.nextInt() + 1];
                        students = FileHandler.readFromFile(filePath, Student::fromString, students.length).toArray(new Student[0]);
                    }
                }
                case 4 ->  running = false;/* Назад в подменю добавления */
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    public static void addEntityRandomly(Scanner scanner, Bus[] buses, User[] users, Student[] students) {
        boolean running = true;
        while (running) {
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
                case 1 -> {
                    if (buses == null) {
                        System.out.println("Введите необходимое количество элементов массива: ");
                        buses = new Bus[scanner.nextInt() + 1];
                        scanner.nextLine();
                        for (int i = 0; i < buses.length; i++) {
                            buses[i] = RandomEntityGenerator.createRandomBus();
                        }
                    } else {
                        for (int i = 0; i < buses.length; i++) {
                            buses[i] = RandomEntityGenerator.createRandomBus();
                        }
                    }
                }
                case 2 -> {
                    if (users == null) {
                        System.out.println("Введите необходимое количество элементов массива: ");
                        users = new User[scanner.nextInt() + 1];
                        scanner.nextLine();
                    }
                    for (int i = 0; i < users.length; i++) {
                        users[i] = RandomEntityGenerator.createRandomUser();
                    }
                }
                case 3 -> {
                    if (students == null) {
                        System.out.println("Введите необходимое количество элементов массива: ");
                        students = new Student[scanner.nextInt() + 1];
                        scanner.nextLine();
                    }
                    for (int i = 0; i < students.length; i++) {
                        students[i] = RandomEntityGenerator.createRandomStudent();
                    }
                }
                case 4 -> running = true;/* Назад в подменю добавления */
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}


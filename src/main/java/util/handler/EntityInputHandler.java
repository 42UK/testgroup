package util.handler;

import entity.Bus;
import entity.Student;
import entity.User;
import util.EntityContainer;
import util.Menu;
import util.validate.InputValidator;

import java.lang.reflect.Array;
import java.util.*;

public class EntityInputHandler {
    private static final Menu menu = new Menu();
    public static void addEntityManually(Scanner scanner, EntityContainer entityContainer) {
        boolean running = true;
        while (running) {
            int choice = InputValidator.validateNumber(scanner, InputValidator.entityOption());

            switch (choice) {
                case 1 -> {
                    entityContainer.buses = initializeArrayIfNull(scanner, entityContainer.buses, Bus.class);
                    EntityHandler.addBus(scanner, entityContainer.buses);
                }
                case 2 -> {
                    entityContainer.users = initializeArrayIfNull(scanner, entityContainer.users, User.class);
                    EntityHandler.addUser(scanner, entityContainer.users);
                }
                case 3 -> {
                    entityContainer.students = initializeArrayIfNull(scanner, entityContainer.students, Student.class);
                    EntityHandler.addStudent(scanner, entityContainer.students);
                }
                case 4 -> running = false;
                case 5 -> menu.run();
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    public static void addEntityFromFile(Scanner scanner, EntityContainer entityContainer) {
        boolean running = true;
        while (running) {
            int choice = InputValidator.validateNumber(scanner, InputValidator.entityOption());

            String filePath;
            switch (choice) {
                case 1 -> {
                    filePath = "buses.csv";
                    entityContainer.buses = initializeArrayIfNull(scanner, entityContainer.buses, Bus.class);
                    try {
                        entityContainer.buses = FileHandler.readFromFile(filePath, Bus::fromString, entityContainer.buses.length)
                                .toArray(new Bus[0]);
                        System.out.println("Данные успешно считаны из файла: " + filePath);
                    } catch (Exception e) {
                        System.out.println("Ошибка при чтении файла: " + e.getMessage());
                    }
                }
                case 2 -> {
                    filePath = "users.csv";
                    entityContainer.users = initializeArrayIfNull(scanner, entityContainer.users, User.class);
                    try {
                        entityContainer.users = FileHandler.readFromFile(filePath, User::fromString, entityContainer.users.length)
                                .toArray(new User[0]);
                        System.out.println("Данные успешно считаны из файла: " + filePath);
                    } catch (Exception e) {
                        System.out.println("Ошибка при чтении файла: " + e.getMessage());
                    }
                }
                case 3 -> {
                    filePath = "students.csv";
                    entityContainer.students = initializeArrayIfNull(scanner, entityContainer.students, Student.class);
                    try {
                        entityContainer.students = FileHandler.readFromFile(filePath, Student::fromString, entityContainer.students.length)
                                .toArray(new Student[0]);
                        System.out.println("Данные успешно считаны из файла: " + filePath);
                    } catch (Exception e) {
                        System.out.println("Ошибка при чтении файла: " + e.getMessage());
                    }
                }
                case 4 -> running = false;
                case 5 -> menu.run();
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    public static void addEntityRandomly(Scanner scanner, EntityContainer entityContainer) {
        boolean running = true;
        while (running) {
            int choice = InputValidator.validateNumber(scanner, InputValidator.entityOption());

            switch (choice) {
                case 1 -> {
                    entityContainer.buses = initializeArrayIfNull(scanner, entityContainer.buses, Bus.class);
                    for (int i = 0; i < entityContainer.buses.length; i++) {
                        entityContainer.buses[i] = RandomEntityGenerator.createRandomBus();
                    }
                }
                case 2 -> {
                    entityContainer.users = initializeArrayIfNull(scanner, entityContainer.users, User.class);
                    for (int i = 0; i < entityContainer.users.length; i++) {
                        entityContainer.users[i] = RandomEntityGenerator.createRandomUser();
                    }
                }
                case 3 -> {
                    entityContainer.students = initializeArrayIfNull(scanner, entityContainer.students, Student.class);
                    for (int i = 0; i < entityContainer.students.length; i++) {
                        entityContainer.students[i] = RandomEntityGenerator.createRandomStudent();
                    }
                }
                case 4 -> running = false;
                case 5 -> menu.run();
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    // Метод для инициализации массива, если он не был ранее создан
    private static <T> T[] initializeArrayIfNull(Scanner scanner, T[] array, Class<T> clazz) {
        if (array == null) {
            System.out.print("Введите необходимое количество элементов массива: ");
            int size = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера
            if (size > 0) {
                @SuppressWarnings("unchecked")
                T[] newArray = (T[]) Array.newInstance(clazz, size);
                System.out.println("Массив инициализирован с размером: " + size);
                return newArray;
            } else {
                System.out.println("Ошибка: размер массива должен быть положительным.");
            }
        }
        return array;
    }

}


package util.handler;

import entity.Bus;
import entity.Student;
import entity.User;
import sorting.QuickSort;
import sorting.Strategy;

import java.util.*;

    public class EntityHandler {
        private static int busesCurrSize = 0;
        private static int studentsCurrSize = 0;
        private static int usersCurrSize = 0;

        //TODO - Добавить вывод о том что массивы не полностью заполнены в случае неправильно
        // ввода данных, а так всё работает, добавляет, просто немного не хватает информации
        // - не знаю имеет ли смысл это реализовывать, просто когда будет введено не правильное, его выкидывает в меню обратно
        public static void addBus(Scanner scanner, Bus[] buses) {
            System.out.print("Введите номер: ");
            String busNumber = scanner.nextLine().trim();
            if (busNumber.isEmpty()) {
                System.out.println("Ошибка: номер автобуса не может быть пустым.");
                return;
            }

            System.out.print("Введите модель: ");
            String busModel = scanner.nextLine().trim();
            if (busModel.isEmpty()) {
                System.out.println("Ошибка: модель автобуса не может быть пустой.");
                return;
            }

            System.out.print("Введите пробег: ");
            int busMileage;
            try {
                busMileage = Integer.parseInt(scanner.nextLine().trim());
                if (busMileage < 0) {
                    System.out.println("Ошибка: пробег не может быть отрицательным.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: пробег должен быть числом.");
                return;
            }

            Bus bus = new Bus.Builder().setNumber(busNumber).setModel(busModel).setMileage(busMileage).build();

            if (busesCurrSize < buses.length) {
                buses[busesCurrSize++] = bus;
                System.out.println("Вы добавили: " + bus);
            } else {
                System.out.println("Массив уже полон, вы не можете добавлять больше элементов");
            }
        }

        public static void addUser(Scanner scanner, User[] users) {
            System.out.print("Введите имя: ");
            String userName = scanner.nextLine().trim();
            if (userName.isEmpty()) {
                System.out.println("Ошибка: имя не может быть пустым.");
                return;
            }

            System.out.print("Введите пароль: ");
            String userPassword = scanner.nextLine().trim();
            if (userPassword.isEmpty()) {
                System.out.println("Ошибка: пароль не может быть пустым.");
                return;
            }

            System.out.print("Введите почту: ");
            String userEmail = scanner.nextLine().trim();
            if (!isValidEmail(userEmail)) {
                System.out.println("Ошибка: введен некорректный адрес электронной почты.");
                return;
            }

            User user = new User.Builder().setName(userName).setPassword(userPassword).setEmail(userEmail).build();

            if (usersCurrSize < users.length) {
                users[usersCurrSize++] = user;
                System.out.println("Вы добавили: " + user);
            } else {
                System.out.println("Массив уже полон, вы не можете добавлять больше элементов");
            }
        }

        public static void addStudent(Scanner scanner, Student[] students) {
            System.out.print("Введите номер группы: ");
            String groupNumber = scanner.nextLine().trim();
            if (groupNumber.isEmpty()) {
                System.out.println("Ошибка: номер группы не может быть пустым.");
                return;
            }

            System.out.print("Введите средний балл: ");
            double averageScore;
            try {
                averageScore = Double.parseDouble(scanner.nextLine().trim());
                if (averageScore < 0 || averageScore > 5) {
                    System.out.println("Ошибка: средний балл должен быть в диапазоне от 0 до 5.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: средний балл должен быть числом.");
                return;
            }

            Student student = new Student.Builder().setGroupNumber(groupNumber).setAverageScore(averageScore).build();

            if (studentsCurrSize < students.length) {
                students[studentsCurrSize++] = student;
                System.out.println("Вы добавили: " + student);
            } else {
                System.out.println("Массив уже полон, вы не можете добавлять больше элементов");
            }
        }

        private static boolean isValidEmail(String email) {
            return email.contains("@") && email.contains(".");
        }

        public static <T> void sortAndPrint(T[] array, Comparator<T> comparator) {
            if (array.length <= 1) {
                System.out.println("Массив должен быть больше одного элемента!");
                return;
            }
            Strategy<T> sorter = new QuickSort<>(comparator);
            sorter.sort(array);
            Arrays.stream(array).forEach(System.out::println);
        }
    }





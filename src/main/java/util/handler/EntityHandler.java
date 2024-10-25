package util.handler;

import entity.Bus;
import entity.Student;
import entity.User;
import sorting.QuickSort;
import sorting.Strategy;

import java.util.*;

public class EntityHandler {

        public static void addBus(Scanner scanner, List<Bus> buses) {
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

            buses.add(new Bus.Builder().setNumber(busNumber).setModel(busModel).setMileage(busMileage).build());
        }

        public static void addUser(Scanner scanner, List<User> users) {
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

            users.add(new User.Builder().setName(userName).setPassword(userPassword).setEmail(userEmail).build());
        }

        public static void addStudent(Scanner scanner, List<Student> students) {
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
                if (averageScore < 0 || averageScore > 5) { // Предположим, что средний балл от 0 до 5
                    System.out.println("Ошибка: средний балл должен быть в диапазоне от 0 до 5.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: средний балл должен быть числом.");
                return;
            }

            students.add(new Student.Builder().setGroupNumber(groupNumber).setAverageScore(averageScore).build());
        }

        private static boolean isValidEmail(String email) {
            // Простая проверка на наличие '@' и '.'
            return email.contains("@") && email.contains(".");
        }
    public static <T> void sortAndPrint(List<T> list, Comparator<T> comparator) {
        Strategy<T> sorter = new QuickSort<>(comparator);
        sorter.sort(list);
        list.forEach(System.out::println);
    }
    }





package util.handler;

import entity.Bus;
import entity.Student;
import entity.User;
import sorting.QuickSort;
import sorting.Strategy;
import util.validate.InputValidator;

import java.util.*;

    public class EntityHandler {
        private static int busesCurrSize = 0;
        private static int studentsCurrSize = 0;
        private static int usersCurrSize = 0;

        public static void addBus(Scanner scanner, Bus[] buses) {
            String busNumber = InputValidator.readNonEmptyString(scanner, "Введите номер: ");

            String busModel = InputValidator.readNonEmptyString(scanner, "Введите модель: ");

            int busMileage  = InputValidator.validateNumber(scanner, "Введите пробег: ");

            Bus bus = new Bus.Builder().setNumber(busNumber).setModel(busModel).setMileage(busMileage).build();

            if (busesCurrSize < buses.length) {
                buses[busesCurrSize++] = bus;
                System.out.println("Вы добавили: " + bus);
            } else {
                System.out.println("Массив уже полон, вы не можете добавлять больше элементов");
            }
        }

        public static void addUser(Scanner scanner, User[] users) {
            String userName = InputValidator.readNonEmptyString(scanner, "Введите имя: ");

            String userPassword = InputValidator.readNonEmptyString(scanner, "Введите пароль: ");

            String userEmail = InputValidator.readValidEmail(scanner, "Введите почту: ");

            User user = new User.Builder().setName(userName).setPassword(userPassword).setEmail(userEmail).build();

            if (usersCurrSize < users.length) {
                users[usersCurrSize++] = user;
                System.out.println("Вы добавили: " + user);
            } else {
                System.out.println("Массив уже полон, вы не можете добавлять больше элементов");
            }
        }

        public static void addStudent(Scanner scanner, Student[] students) {
            String groupNumber = InputValidator.readNonEmptyString(scanner, "Введите номер группы: ");

            double averageScore = InputValidator.readDoubleInRange(scanner, "Введите средний балл: ", 0, 5);

            Student student = new Student.Builder().setGroupNumber(groupNumber).setAverageScore(averageScore).build();

            if (studentsCurrSize < students.length) {
                students[studentsCurrSize++] = student;
                System.out.println("Вы добавили: " + student);
            } else {
                System.out.println("Массив уже полон, вы не можете добавлять больше элементов");
            }
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





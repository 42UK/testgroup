package util.validate;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    public static int validateNumber(Scanner scanner, String prompt) {
        int choice;
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextInt() ) {
                choice = scanner.nextInt();
                if (choice <= 0) {
                    System.out.println("Ошибка: ожидалось число выше 0. Вы ввели \"" + choice + "\". Попробуйте снова.");
                    scanner.nextLine();
                    continue;
                }
                scanner.nextLine();
                return choice;
            } else {
                String invalidInput = scanner.nextLine();
                System.out.println("Ошибка: ожидалось целое число. Вы ввели \"" + invalidInput + "\". Попробуйте снова.");
            }
        }
    }
    public static String readNonEmptyString(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.println(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Ошибка: ввод не может быть пустым. Попробуйте снова.");
            }
        }
    }
    public static double readDoubleInRange(Scanner scanner, String prompt, double min, double max) {
        double number;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                number = Double.parseDouble(input);
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println("Ошибка: число должно быть в диапазоне от " + min + " до " + max + ". Вы ввели \"" + number + "\". Попробуйте снова.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: ожидалось число с плавающей точкой. Вы ввели \"" + input + "\". Попробуйте снова.");
            }
        }
    }

    public static String readValidEmail(Scanner scanner, String prompt) {
        String email;
        while (true) {
            System.out.print(prompt);
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("Ошибка: адрес электронной почты не может быть пустым. Попробуйте снова.");
                continue;
            }
            if (isValidEmail(email)) {
                return email;
            } else {
                System.out.println("Ошибка: введен некорректный адрес электронной почты. Попробуйте снова.");
            }
        }
    }
    private static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
    public static String entityOption() {
        return """
                    Выберите список для вывода:
                    1. Автобусы
                    2. Пользователи
                    3. Студенты
                    4. Назад
                    """;
    }
}

package util.validate;

import java.util.Scanner;

public class InputValidator {

    public static int validateNumber(Scanner scanner, String prompt) {
        int choice;
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            } else {
                String invalidInput = scanner.nextLine();
                System.out.println("Ошибка: ожидалось целое число. Вы ввели \"" + invalidInput + "\". Попробуйте снова.");
            }
        }
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

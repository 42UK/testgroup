package util.handler;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class FileHandler {

    //todo - не было директории write - создал, теперь создаёт(файл) и записывает, так же очищает

    // Метод для записи списка объектов в файл
    public static <T> void writeToFile(T[] list, String fileName) {
        try (FileWriter writer = new FileWriter("data/write/"+fileName , true)) { // true для добавления данных
            for (T item : list) {
                writer.write(item.toString() + "\n"); // Используем toString для вывода информации об объекте
            }
            System.out.println("Данные успешно записаны в файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    // Метод для очистки содержимого файла
    public static void clearFile(String fileName) {
        File file = new File("data/write/" + fileName);

        // Проверка на существование файла и его размер
        if (!file.exists()) {
            System.err.println("Ошибка: файл не найден: " + fileName);
            return;
        }

        if (file.length() == 0) {
            System.out.println("Файл пуст, очищать нечего: " + fileName);
            return;
        }

        try (FileWriter writer = new FileWriter(file, false)) { // false для перезаписи файла
            writer.write(""); // Записываем пустую строку, чтобы очистить файл
            System.out.println("Файл успешно очищен: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при очистке файла: " + e.getMessage());
        }
    }

    // Метод для чтения с файла используется в моменте рандомного создания сущностей
    public static <T> List<T> readFromFile(String fileName, Function<String, T> fromString, int size) {
        List<T> list = new ArrayList<>();
        boolean validFile = false;
        Scanner scanner = new Scanner(System.in);

        while (!validFile) {
            try (BufferedReader reader = new BufferedReader(new FileReader("data/read/" + fileName))) {
                String line;
                while ((line = reader.readLine()) != null && list.size() < size) {
                    list.add(fromString.apply(line));
                }
                validFile = true; // Файл успешно прочитан
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден: " + fileName + "\n" + "Введите название файла заново (или введите 'exit' для выхода): ");
                fileName = scanner.nextLine(); // Запрос нового пути к файлу

                // Проверка на выход
                if (fileName.equalsIgnoreCase("exit")) {
                    System.out.println("Вы вышли из ввода названия файла.");
                    return Collections.emptyList(); // Возвращаем пустой список
                }
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
                validFile = true; // Выход из цикла при других ошибках
            }
        }

        return list;
    /* todo исправить когда спрашивает чтение из файла можно выйти
      - 27.09.2024 Уже не актуально, так как он не спрашивает название файла для чтения */

}
}

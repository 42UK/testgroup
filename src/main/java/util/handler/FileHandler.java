package util.handler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class FileHandler {

    // Метод для записи списка объектов в файл
    public static <T> void writeToFile(List<T> list, String fileName) {
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
        try (FileWriter writer = new FileWriter("data/write/"+fileName, false)) { // false для перезаписи файла
            writer.write(""); // Записываем пустую строку, чтобы очистить файл
            System.out.println("Файл успешно очищен: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при очистке файла: " + e.getMessage());
        }
    }

    // Метод для чтения с файла используется в моменте рандомного создания сущностей
    public static <T> List<T> readFromFile(String fileName, Function<String, T> fromString) {
        List<T> list = new ArrayList<>();
        boolean validFile = false;
        while (!validFile) {
            try (BufferedReader reader = new BufferedReader(new FileReader("data/read"+fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    list.add(fromString.apply(line));
                }
                validFile = true; // Файл успешно прочитан
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден: " + fileName + "\n" +"Введите путь к файлу заново: ");
                Scanner scanner = new Scanner(System.in);
                fileName = scanner.nextLine(); // Запрос нового пути к файлу
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
                validFile = true; // Выход из цикла при других ошибках
            }
        }

        return list;
    }

}

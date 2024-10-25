package sorting;

import java.util.Collections;
import java.util.List;

public class BinarySearch<T extends Comparable<T>> implements Strategy<T> {

    @Override
    public void sort(List<T> list) {
        Collections.sort(list); // Используем встроенный метод сортировки
    }


    public int search(List<T> list, T key) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (list.get(mid).compareTo(key) == 0) {
                return mid; // Найден элемент
            }
            if (list.get(mid).compareTo(key) < 0) {
                left = mid + 1; // Ищем в правой части
            } else {
                right = mid - 1; // Ищем в левой части
            }
        }
        return -1; // Элемент не найден
    }
}
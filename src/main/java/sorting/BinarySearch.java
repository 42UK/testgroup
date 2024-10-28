package sorting;


public class BinarySearch<T extends Comparable<T>> implements Strategy<T> {

    @Override
    public void sort(T[] array) {
        Strategy<T> quickSort = new QuickSort<>();
        quickSort.sort(array);
    }


    public int search(T[] array, T key) {
        sort(array);
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid].compareTo(key) == 0) {
                return mid; // Найден элемент
            }
            if (array[mid].compareTo(key) < 0) {
                left = mid + 1; // Ищем в правой части
            } else {
                right = mid - 1; // Ищем в левой части
            }
        }
        return -1; // Элемент не найден
    }
}
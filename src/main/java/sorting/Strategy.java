package sorting;

import java.util.List;

public interface Strategy<T> {
    void sort(List<T> list);

    default int search (List<T> list, T key) {
        return 0;
    }
}

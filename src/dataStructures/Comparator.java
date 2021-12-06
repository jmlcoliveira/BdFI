package dataStructures;

import java.io.Serializable;

public interface Comparator<T> extends Serializable {
    int compare(T o1, T o2);
}

package BdFI;

import dataStructures.Comparator;

import java.io.Serializable;

public class ComparatorByShowName implements Comparator<Show>, Serializable {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    @Override
    public int compare(Show s1, Show s2) {
        return s1.getTitle().compareToIgnoreCase(s2.getTitle());
    }
}

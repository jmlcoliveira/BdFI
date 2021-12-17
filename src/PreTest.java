import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.orderedDictionaries.AVLTree;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PreTest {

    @Test
    public void leftInsRotation() {
        AVLTree<Integer, Integer> tmp = new AVLTree<>(Integer::compareTo);
        tmp.insert(100, 100);
        tmp.insert(98, 98);
        assertEquals("100, 98", formatMap(tmp));
        tmp.insert(95, 95);
        assertEquals("98, 95, 100", formatMap(tmp));
        tmp.insert(93, 93);
        tmp.insert(94, 94);
        assertEquals("98, 94, 100, 93, 95", formatMap(tmp));
    }

    @Test
    public void rightInsRotation() {
        AVLTree<Integer, Integer> tmp = new AVLTree<>(Integer::compareTo);
        tmp.insert(100, 100);
        tmp.insert(105, 105);
        tmp.insert(110, 110);
        assertEquals("105, 100, 110", formatMap(tmp));
        tmp.insert(115, 115);
        tmp.insert(111, 111);
        assertEquals("105, 100, 111, 110, 115", formatMap(tmp));
        tmp.insert(120, 120);
        assertEquals("111, 105, 115, 100, 110, 120", formatMap(tmp));
    }

    @Test
    public void remRotation1() {
        AVLTree<Integer, Integer> tmp = new AVLTree<>(Integer::compareTo);
        insertAVL(new int[]{100, 95, 105, 80, 103, 110, 107}, tmp);
        assertTrue(95 == tmp.remove(95));
        assertEquals("105, 100, 110, 80, 103, 107", formatMap(tmp));
        assertTrue(107 == tmp.remove(107));
        assertEquals("105, 100, 110, 80, 103", formatMap(tmp));
        assertTrue(110 == tmp.remove(110));
        assertEquals("100, 80, 105, 103", formatMap(tmp));
        tmp.insert(75, 75);
        assertEquals("100, 80, 105, 75, 103", formatMap(tmp));
        tmp.insert(106, 106);
        assertEquals("100, 80, 105, 75, 103, 106", formatMap(tmp));
        tmp.insert(101, 101);
        tmp.insert(104, 104);
        assertEquals("100, 80, 105, 75, 103, 106, 101, 104", formatMap(tmp));
        assertTrue(80 == tmp.remove(80));
        assertEquals("103, 100, 105, 75, 101, 104, 106", formatMap(tmp));
    }

    @Test
    public void remRotation2() {
        AVLTree<Integer, Integer> avl = new AVLTree<>(Integer::compareTo);
        insertAVL(new int[]{103, 100, 105, 75, 101, 104, 106}, avl);
        avl.remove(100);
        avl.remove(101);
        avl.remove(75);
        assertEquals("105, 103, 106, 104", formatMap(avl));
        avl.insert(107, 107);
        avl.insert(102, 102);
        avl.insert(101, 101);
        assertEquals("105, 103, 106, 102, 104, 107, 101", formatMap(avl));
        avl.remove(107);
        assertEquals("103, 102, 105, 101, 104, 106", formatMap(avl));
        avl.remove(103);
        assertEquals("104, 102, 105, 101, 106", formatMap(avl));
        avl.remove(104);
        assertEquals("105, 102, 106, 101", formatMap(avl));
        avl.insert(103, 103);
        avl.insert(107, 107);
        avl.insert(104, 104);
        assertEquals("105, 102, 106, 101, 103, 107, 104", formatMap(avl));
        avl.remove(106);
        assertEquals("103, 102, 105, 101, 104, 107", formatMap(avl));
    }


    public void insertAVL(int[] values, AVLTree<Integer, Integer> tmp) {
        for (int i = 0; i < values.length; i++)
            tmp.insert(values[i], values[i]);
    }

    private String formatMap(AVLTree<Integer, Integer> tmp) {
        Iterator<Entry<Integer, Integer>> it = tmp.breadthIterator();
        final String separator = ", ";
        StringBuilder builder = new StringBuilder();
        while (it.hasNext()) {
            builder.append(it.next().getKey());
            if (it.hasNext())
                builder.append(separator);
        }
        return builder.toString();
    }
}

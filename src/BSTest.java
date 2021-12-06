import dataStructures.*;
import org.junit.Test;

    public class BSTest {

        @Test
        public void insertTest() {
            OrderedDictionary<Integer, String> dic = new AVLTree<>();
            dic.insert(5, "a");
            dic.insert(3, "b");
            dic.insert(7, "c");
            dic.insert(10, "d");
            dic.insert(4, "e");

            Iterator<Entry<Integer, String>> it = ((AVLTree) dic).breadthIterator();
            assert (it.next().getValue().equals("a"));
            assert (it.next().getValue().equals("b"));
            assert (it.next().getValue().equals("c"));
            assert (it.next().getValue().equals("e"));
            assert (it.next().getValue().equals("d"));

        }

        public void remove1NodeTest() {
            OrderedDictionary<Integer, Integer> dic = new AVLTree<>();
            dic.insert(15, 15);
            dic.insert(8, 8);
            dic.insert(28, 28);
            dic.insert(16, 16);
            dic.insert(32, 32);
            dic.insert(45, 45);

            Iterator<Entry<Integer, String>> it = ((AVLTree) dic).breadthIterator();

            assert (it.next().getValue().equals(15));
            assert (it.next().getValue().equals(8));
            assert (it.next().getValue().equals(28));
            assert (it.next().getValue().equals(4));
            assert (it.next().getValue().equals(16));
            assert (it.next().getValue().equals(32));
            assert (it.next().getValue().equals(45));

            dic.remove(8);

            it = ((AVLTree) dic).breadthIterator();

            assert (it.next().getValue().equals(15));
            assert (it.next().getValue().equals(4));
            assert (it.next().getValue().equals(28));
            assert (it.next().getValue().equals(16));
            assert (it.next().getValue().equals(32));
            assert (it.next().getValue().equals(45));

        }

        public void remove2NodeTest() {
            OrderedDictionary<Integer, Integer> dic = new AVLTree<>();
            dic.insert(15, 15);
            dic.insert(8, 8);
            dic.insert(28, 28);
            dic.insert(16, 16);
            dic.insert(32, 32);
            dic.insert(30, 30);
            dic.insert(45, 45);

            Iterator<Entry<Integer, String>> it = ((AVLTree) dic).breadthIterator();

            assert (it.next().getValue().equals(15));
            assert (it.next().getValue().equals(8));
            assert (it.next().getValue().equals(28));
            assert (it.next().getValue().equals(4));
            assert (it.next().getValue().equals(16));
            assert (it.next().getValue().equals(32));
            assert (it.next().getValue().equals(30));
            assert (it.next().getValue().equals(45));

            dic.remove(28);

            it = ((AVLTree) dic).breadthIterator();

            assert (it.next().getValue().equals(15));
            assert (it.next().getValue().equals(8));
            assert (it.next().getValue().equals(30));
            assert (it.next().getValue().equals(4));
            assert (it.next().getValue().equals(16));
            assert (it.next().getValue().equals(32));
            assert (it.next().getValue().equals(45));
        }
    }

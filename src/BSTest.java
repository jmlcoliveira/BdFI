import dataStructures.AVLTree;
import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.OrderedDictionary;
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

        @Test
        public void balancedRemoveLeft() {
            AVLTree<Integer, Integer> dic = new AVLTree<>();
            dic.insert(20, 20);
            dic.insert(15, 15);
            dic.insert(25, 25);
            dic.insert(10, 10);
            dic.insert(17, 17);
            dic.insert(23, 23);
            dic.insert(30, 30);
            dic.insert(9, 9);
            dic.insert(11, 11);
            dic.insert(16, 16);
            dic.insert(18, 18);
            dic.insert(21, 21);
            dic.insert(24, 24);
            dic.insert(26, 26);
            dic.insert(31, 31);

            dic.remove(15);

            Iterator<Entry<Integer, Integer>> it = dic.breadthIterator();
            assert (it.next().getValue().equals(20));
            assert (it.next().getValue().equals(16));
            assert (it.next().getValue().equals(25));
            assert (it.next().getValue().equals(10));
            assert (it.next().getValue().equals(17));
            assert (it.next().getValue().equals(23));
            assert (it.next().getValue().equals(30));
            assert (it.next().getValue().equals(9));
            assert (it.next().getValue().equals(11));
            assert (it.next().getValue().equals(18));
            assert (it.next().getValue().equals(21));
            assert (it.next().getValue().equals(24));
            assert (it.next().getValue().equals(26));
            assert (it.next().getValue().equals(31));
        }

        @Test
        public void balanceRemoveRightE() {
            AVLTree<Integer, Integer> dic = new AVLTree<>();
            dic.insert(10, 10);
            dic.insert(5, 5);
            dic.insert(20, 20);
            dic.insert(6, 6);
            dic.insert(4, 4);
            dic.insert(11, 11);
            dic.insert(30, 30);
            dic.insert(3, 3);
            dic.insert(15, 15);
            dic.insert(25, 25);

            dic.remove(20);

            Iterator<Entry<Integer, Integer>> it = dic.breadthIterator();

            assert (it.next().getValue().equals(10));
            assert (it.next().getValue().equals(5));
            assert (it.next().getValue().equals(25));
            assert (it.next().getValue().equals(4));
            assert (it.next().getValue().equals(6));
            assert (it.next().getValue().equals(11));
            assert (it.next().getValue().equals(30));
            assert (it.next().getValue().equals(3));
            assert (it.next().getValue().equals(15));
        }

        @Test
        public void balanceRemoveRightR() {
            AVLTree<Integer, Integer> dic = new AVLTree<>();
            dic.insert(10, 10);
            dic.insert(5, 5);
            dic.insert(12, 12);
            dic.insert(4, 4);
            dic.insert(11, 11);
            dic.insert(14, 14);
            dic.insert(13, 13);

            dic.remove(12);

            Iterator<Entry<Integer, Integer>> it = dic.breadthIterator();

            assert (it.next().getValue().equals(10));
            assert (it.next().getValue().equals(5));
            assert (it.next().getValue().equals(13));
            assert (it.next().getValue().equals(4));
            assert (it.next().getValue().equals(11));
            assert (it.next().getValue().equals(14));


        }

        @Test
        public void balanceRemoveRightL() {
            AVLTree<Integer, Integer> dic = new AVLTree<>();
            dic.insert(10, 10);
            dic.insert(5, 5);
            dic.insert(20, 20);
            dic.insert(6, 6);
            dic.insert(4, 4);
            dic.insert(11, 11);
            dic.insert(30, 30);
            dic.insert(3, 3);

            dic.remove(20);

            Iterator<Entry<Integer, Integer>> it = dic.breadthIterator();

            assert (it.next().getValue().equals(10));
            assert (it.next().getValue().equals(5));
            assert (it.next().getValue().equals(30));
            assert (it.next().getValue().equals(4));
            assert (it.next().getValue().equals(6));
            assert (it.next().getValue().equals(11));
            assert (it.next().getValue().equals(3));


        }

    }

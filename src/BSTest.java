import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.orderedDictionaries.AVLTree;
import org.junit.Test;

    public class BSTest {

        @Test
        public void insertTest() {
            AVLTree<Integer, String> dic = new AVLTree<>();
            dic.insert(5, "a");
            dic.insert(3, "b");
            dic.insert(7, "c");
            dic.insert(10, "d");
            dic.insert(4, "e");

            Iterator<Entry<Integer, String>> it = dic.breadthIterator();
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
        public void rrRemove() {
            AVLTree<Integer, Integer> dic = new AVLTree<>();
            dic.insert(100, 100);
            dic.insert(95, 95);
            dic.insert(200, 200);
            dic.insert(90, 90);
            dic.insert(150, 150);
            dic.insert(205, 205);
            dic.insert(202, 202);
            dic.insert(210, 210);
            dic.remove(90);

            Iterator<Entry<Integer, Integer>> it = dic.breadthIterator();

            assert (it.next().getValue().equals(200));
            assert (it.next().getValue().equals(100));
            assert (it.next().getValue().equals(205));
            assert (it.next().getValue().equals(95));
            assert (it.next().getValue().equals(150));
            assert (it.next().getValue().equals(202));
            assert (it.next().getValue().equals(210));
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

        @Test
        public void balanceRemoveTest01() {
            AVLTree<Integer, Integer> dic = new AVLTree<>();
            dic.insert(14, 14);
            dic.insert(8, 8);
            dic.insert(21, 21);
            dic.insert(1, 1);
            dic.insert(16, 16);
            dic.insert(30, 30);
            dic.insert(19, 19);
            dic.insert(23, 23);

            dic.remove(8);

            Iterator<Entry<Integer, Integer>> it = dic.breadthIterator();

            assert (it.next().getValue().equals(21));
            assert (it.next().getValue().equals(14));
            assert (it.next().getValue().equals(30));
            assert (it.next().getValue().equals(1));
            assert (it.next().getValue().equals(16));
            assert (it.next().getValue().equals(23));
            assert (it.next().getValue().equals(19));
        }

        @Test
        public void balanceRemoveTest02() {
            AVLTree<Integer, Integer> dic = new AVLTree<>();
            dic.insert(20, 20);
            dic.insert(15, 15);
            dic.insert(25, 25);
            dic.insert(10, 10);
            dic.insert(17, 17);
            dic.insert(22, 22);
            dic.insert(30, 30);
            dic.insert(16, 16);
            dic.insert(23, 23);
            dic.insert(27, 27);

            dic.remove(25);
            Iterator<Entry<Integer, Integer>> it = dic.breadthIterator();

            assert (it.next().getValue().equals(20));
            assert (it.next().getValue().equals(15));
            assert (it.next().getValue().equals(27));
            assert (it.next().getValue().equals(10));
            assert (it.next().getValue().equals(17));
            assert (it.next().getValue().equals(22));
            assert (it.next().getValue().equals(30));
            assert (it.next().getValue().equals(16));
        }

        @Test
        public void balanceRemoveTest04() {
            AVLTree<Integer, Integer> dic = new AVLTree<>();
            dic.insert(20, 20);
            dic.insert(15, 15);
            dic.insert(25, 25);
            dic.insert(10, 10);
            dic.insert(17, 17);
            dic.insert(22, 22);
            dic.insert(30, 30);
            dic.insert(12, 12);

            dic.remove(17);

            Iterator<Entry<Integer, Integer>> it = dic.breadthIterator();

            assert (it.next().getValue().equals(20));
            assert (it.next().getValue().equals(12));
            assert (it.next().getValue().equals(25));
            assert (it.next().getValue().equals(10));
            assert (it.next().getValue().equals(15));
            assert (it.next().getValue().equals(22));
            assert (it.next().getValue().equals(30));
        }

        @Test
        public void balanceRemoveTest05() {
            AVLTree<Integer, Integer> dic = new AVLTree<>();
            dic.insert(20, 20);
            dic.insert(15, 15);
            dic.insert(25, 25);
            dic.insert(10, 10);
            dic.insert(17, 17);
            dic.insert(22, 22);
            dic.insert(30, 30);
            dic.insert(27, 27);

            dic.remove(22);

            Iterator<Entry<Integer, Integer>> it = dic.breadthIterator();

            assert (it.next().getValue().equals(20));
            assert (it.next().getValue().equals(15));
            assert (it.next().getValue().equals(27));
            assert (it.next().getValue().equals(10));
            assert (it.next().getValue().equals(17));
            assert (it.next().getValue().equals(25));
            assert (it.next().getValue().equals(30));
        }

        @Test
        public void balanceMultipleRemovesTest() {
            AVLTree<Integer, Integer> dic = new AVLTree<>();
            dic.insert(50, 50);
            dic.insert(40, 40);
            dic.insert(60, 60);
            dic.insert(30, 30);
            dic.insert(45, 45);
            dic.insert(55, 55);
            dic.insert(70, 70);
            dic.insert(35, 35);
            dic.insert(54, 54);
            dic.insert(56, 56);
            dic.insert(80, 80);

            dic.remove(45);
            dic.remove(56);
            dic.remove(80);

            Iterator<Entry<Integer, Integer>> it = dic.breadthIterator();

            assert (it.next().getValue().equals(50));
            assert (it.next().getValue().equals(35));
            assert (it.next().getValue().equals(60));
            assert (it.next().getValue().equals(30));
            assert (it.next().getValue().equals(40));
            assert (it.next().getValue().equals(55));
            assert (it.next().getValue().equals(70));
            assert (it.next().getValue().equals(54));

        }

    }

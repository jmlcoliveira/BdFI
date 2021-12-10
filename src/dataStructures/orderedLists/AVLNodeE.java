package dataStructures.orderedLists;

public class AVLNodeE<E extends Comparable<E>> extends BSTNodeE<E> {

    private char balance;

    public AVLNodeE(E element, char balance, AVLNodeE<E> left, AVLNodeE<E> right) {
        super(element, left, right);
        this.balance = balance;
    }

    public AVLNodeE(E element) {
        this(element, 'E', null, null);
    }

    public char getBalance() {
        return balance;
    }

    public void setBalance(char newBalance) {
        balance = newBalance;
    }
}

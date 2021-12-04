package dataStructures;

public class AVLNode<K extends Comparable<K>, V> extends BSTNode<K,V>{

    private char balance;

    public AVLNode(K key, V value, char balance, AVLNode<K,V> left, AVLNode<K,V> right) {
        super(key,value,left,right);
        this.balance = balance;
    }
    public AVLNode(K key, V value) {
        this(key, value, 'E', null, null);
    }
    public char getBalance() {
        return balance;
    }
    public void setBalance(char newBalance) {
        balance = newBalance;
    }
}

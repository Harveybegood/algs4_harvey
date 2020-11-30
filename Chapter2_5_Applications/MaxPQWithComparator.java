package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class MaxPQWithComparator<Key extends Comparable<Key>> {
    private int n;
    private Key[] pq;
    private Comparator<Key> comparator;
    public MaxPQWithComparator(int initCapacity, Comparator<Key> c){
        this.comparator = c;
        pq = (Key[]) new Comparable[initCapacity + 1];
    }
    private boolean less(int i, int j){
        if (comparator == null){
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        }
        else{
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }
    public boolean isEmpty(){return  n == 0;}
    public int size(){return n;}
    public void resize(int newSize){
        Key[] array = (Key[]) new Comparable[newSize];
        for (int i = 0; i < n; i++){
            array[i + 1] = pq[i];
        }
        pq = array;
    }
    public static class Transaction{
        private final String who;
        private final double amount;
        private final edu.princeton.cs.algs4.Date when;
        public Transaction(String who, double amount, edu.princeton.cs.algs4.Date when){
            this.who = who; this.amount = amount; this.when = when;
        }
        public static class WhoOrder implements Comparator<Transaction>{
            public int compare(Transaction v, Transaction w){
                return v.who.compareTo(w.who);
            }
        }
        public static class WhenOrder implements Comparator<Transaction>{
            public int compare(Transaction v, Transaction w){
                return v.when.compareTo(w.when);
            }
        }
        public static class HowMuchOrder implements Comparator<Transaction>{
            public int compare(Transaction v, Transaction w){
                if (v.amount < w.amount) return -1;
                if (v.amount > w.amount) return +1;
                return 0;
            }
        }
    }
    private void exchange(int i, int j){
        Key temp = pq[i]; pq[i] = pq[j]; pq[j] = temp;
    }
    public void insert(Key key){
        if (n == pq.length / 2){resize(2 * pq.length);}
        pq[++n] = key;
        swim(n);
    }
    public Key delMax(){
        if (isEmpty()){throw new NoSuchElementException("Priority queue underflow. ");}
        Key max = pq[1];
        exchange(1, n);
        n--;
        pq[n + 1] = null;
        if (n == pq.length / 4){resize(pq.length / 2);}
        sink(1);
        return max;
    }
    private void swim(int k){
        while (k > 1){
            if (less(k / 2, k)){
                exchange(k / 2, k);
            }
            k = k / 2;
        }
    }
    private void sink(int k){
        while (k * 2 <= n){
            int j = k * 2;
            if (j < n && less(j + 1, j)){
                j++;
            }
            if (!(less(k, j))){
                break;
            }
            exchange(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        MaxPQWithComparator maxPQWithComparator = new MaxPQWithComparator(1, new Transaction.HowMuchOrder());
        MaxPQWithComparator<edu.princeton.cs.algs4.Date> maxPQWithComparator1 = new
                MaxPQWithComparator(1, new Transaction.WhenOrder());
        MaxPQWithComparator<String> maxPQWithComparator2 = new
                MaxPQWithComparator(1, new Transaction.WhoOrder());
        //String who = "harvey";
        //Date date = new Date(4,3,2019);
        Transaction transaction1 = new Transaction("harvey", 3.01, new edu.princeton.cs.algs4.Date(4,4,2019));
        Transaction transaction2 = new Transaction("Michael", 5.01, new edu.princeton.cs.algs4.Date(6,4,2019));
        Transaction transaction3 = new Transaction("harvey", 1.01, new edu.princeton.cs.algs4.Date(1,4,2019));
        maxPQWithComparator.insert(new Transaction.HowMuchOrder().compare(transaction1, transaction2));
        maxPQWithComparator.insert(transaction2.amount);
        maxPQWithComparator.insert(transaction3.amount);
        maxPQWithComparator1.insert(transaction1.when);
        maxPQWithComparator1.insert(transaction2.when);
        maxPQWithComparator1.insert(transaction3.when);
        maxPQWithComparator2.insert(transaction1.who);
        maxPQWithComparator2.insert(transaction2.who);
        maxPQWithComparator2.insert(transaction3.who);
        maxPQWithComparator.delMax();
        maxPQWithComparator.delMax();
        maxPQWithComparator.delMax();
        StdOut.print(maxPQWithComparator + " ");
    }
}

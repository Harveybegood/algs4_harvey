package Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/*
*   Instrument BinarySearch(page 47) to use a Counter to count the total number of keys examined during all searches
*   and then print the total after all searches are complete. Hint: Create a Counter in main() and pass it as an
*   argument to rank();
*
* */
public class Ex09 {
    public class Counter{
        private String name;
        private int count;
        public Counter(String s){
            name = s;
            count = 0;
        }
        public void increment(){
            count++;
        }
        public int tally(){
            return count;
        }
        public String toString(){
            return count + " " + name;
        }
    }
    // binary search helper for recursive method
    public static int rank(int key, int[] array, Counter counter){
        return rank(0, array.length - 1, key, array, counter);
    }
    public static int rank(int low, int high, int key, int[] array, Counter counter){
        while (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (key < array[mid]){
            counter.increment();
            return rank(low, mid - 1, key, array, counter);
        }
        else if (key > array[mid]){
            counter.increment();
            return rank(mid + 1, high, key, array, counter);
        }
        else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        int key = 10;
        for (int i = 0; i < 100; i++){
            array[i] = StdRandom.uniform(1, 100);
        }
        Ex09 ex09 = new Ex09();
        Counter counter = ex09.new Counter("times");
        Arrays.sort(array);
        rank(key, array, counter);
        StdOut.println(counter);
    }
}

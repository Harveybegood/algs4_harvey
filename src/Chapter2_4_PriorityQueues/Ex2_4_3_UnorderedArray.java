package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Provide priority-queue implementations that support insert and remove the maximum, one for each of the following
*   underlying data structures: unordered array, ordered array, unordered linked list, and linked list. Give a table
*   of the worst-case bounds for each operation for each of your four implementations.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_4_3_UnorderedArray {
    public static class unOrderedArrayPQ<Item extends Comparable<Item>>{
        private int N;
        private Item unOrderedArray[];
        public unOrderedArrayPQ(){
            unOrderedArray = (Item[]) new Comparable[1];
        }
        public boolean isEmpty(){
            return N == 0;
        }
        public int size(){
            return N;
        }
        public void insert(Item item){
            if (N == unOrderedArray.length / 2){
                resize(2 * unOrderedArray.length);
            }
            unOrderedArray[N] = item;
            N++;
        }
        public Item remove(){
            if (isEmpty()){
                throw new RuntimeException("PQ is empty");
            }
            if (N > 0 && N < unOrderedArray.length / 4){
                resize(unOrderedArray.length / 2);
            }
            int max = 0;
            Item maximum;
            for (int j = 1; j < N; j++){
                if (unOrderedArray[j].compareTo(unOrderedArray[max]) > 0){
                    max = j;
                }
            }
            exch(max, N - 1);
            maximum = unOrderedArray[N - 1];
            unOrderedArray[N - 1] = null;
            N--;
            return maximum;
        }
        public void resize(int newSize){
            Item[] newArray = (Item[]) new Comparable[newSize];
            for (int i = 0; i < N; i++){
                newArray[i] = unOrderedArray[i];
            }
            unOrderedArray = newArray;
        }
        public void exch(int v, int w){
            Item t = unOrderedArray[v];
            unOrderedArray[v] = unOrderedArray[w];
            unOrderedArray[w] = t;
        }
    }

    public static void main(String[] args) {
        unOrderedArrayPQ<Integer> unOrderedArrayObject = new unOrderedArrayPQ<>();
        for (int i = 0; i < 10; i++){
            unOrderedArrayObject.insert(StdRandom.uniform(1, 11));
        }
        for (int i = 0; i < 10; i++){
            StdOut.printf("The %d round, delete max: %2d\n", i + 1, unOrderedArrayObject.remove());
        }
    }
}

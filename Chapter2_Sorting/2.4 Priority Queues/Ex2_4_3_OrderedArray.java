package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

@SuppressWarnings("unchecked")
public class Ex2_4_3_OrderedArray {
    public static class OrderedArrayPQ<Key extends Comparable<Key>>{
        private int size;
        private Key[] PQArray;
        public OrderedArrayPQ(){
            PQArray = (Key[]) new Comparable[2];
            size = 0;
        }
        public int size(){
            return size;
        }
        public boolean isEmpty(){
            return size == 0;
        }
        public void insert(Key arrayItem){
            if (size == PQArray.length / 2){
                resize(2 * PQArray.length);
            }
            PQArray[size] = arrayItem;
            size++;
            for (int i = size; i > 1; i--){
                if (size > 1 && PQArray[i - 1].compareTo(PQArray[i - 2]) < 0){
                    Key temp = PQArray[i - 1];
                    PQArray[i - 1] = PQArray[i - 2];
                    PQArray[i - 2] = temp;
                }
            }
        }
        public Key removeMax(){
            if (size <= 0){
                throw new RuntimeException("Queue is empty.");
            }
            if (size == PQArray.length / 4){
                resize(PQArray.length / 2);
            }
            Key max = PQArray[size - 1];
            PQArray[size - 1] = null;
            size--;
            return max;
        }
        public void resize(int newSize){
            Key[] newArray = (Key[]) new Comparable[newSize];
            for (int i = 0; i < size; i++){
                newArray[i] = PQArray[i];
            }
            PQArray = newArray;
        }
    }

    public static void main(String[] args) {
        OrderedArrayPQ orderedArrayPQ = new OrderedArrayPQ();
        for (int i = 0; i < 10; i++){
            orderedArrayPQ.insert(StdRandom.uniform(1, 11));
        }
        for (int i = 0; i < 10; i++){
            StdOut.printf("Round %1d, %2d\n", i + 1, orderedArrayPQ.removeMax());
        }
    }
}

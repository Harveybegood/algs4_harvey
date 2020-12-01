package Chapter1_3_Bags_Queues_Stacks;
/*
*   Delete Kth element. Implement a class that supports the following API:
*
*   First, develop an implementation that uses an array implementation, and then develop one that uses
*   a linked-list implementation.
*   Note: the algorithms and data structures that we introduce in CHAPTER 3 make it possible to develop
*   an implementation that can guarantee that both insert() and delete() take time proportional to
*   the logarithm of the number of items in the group - see Exercise 3.5.27
*
* */


import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

@SuppressWarnings("unchecked")
public class Ex38 {
    public class GeneralizedQueue<Item> implements Iterable<Item>{
        Item[] arrayItems = (Item[]) new Object[1];
        private int N;
        public GeneralizedQueue(){
            N = 0;
        }
        public boolean isEmpty(){return N == 0;}
        public int size(){return N;}
        public void insert(Item item){
            if (N == arrayItems.length){resize(2*arrayItems.length);}
            arrayItems[N] = item;
            N++;
        }
        public Item delete(){
            if (isEmpty()){throw new RuntimeException("Array underflow");}
            Item item = arrayItems[N];
            arrayItems[N] = null;
            N--;
            if (N > 0 && N > arrayItems.length/2){resize(arrayItems.length/4);}
            return item;
        }

        public void resize(int newSize){
            Item[] newArray = (Item[]) new Object[newSize];
            for (int i = 0; i < N; i++){
                newArray[i] = arrayItems[i];
            }
            arrayItems = newArray;
        }
        public Iterator<Item> iterator(){
            return new arrayIterator();
        }
        public class arrayIterator implements Iterator<Item>{
            //Item[] items = (Item[]) new Object[arrayItems.length];
            private int index = 0;
            public boolean hasNext(){return index < N;}
            public Item next(){
                Item item = arrayItems[index];
                index++;
                return item;
            }
        }
    }

    public static void main(String[] args) {
        Ex38 ex38 = new Ex38();
        GeneralizedQueue<Integer> generalizedQueue = ex38.new GeneralizedQueue<>();
        for (int i = 0; i < 3; i++){
            generalizedQueue.insert(i);
        }
        StdOut.println("items inserted in arrayItems: ");
        //StringBuilder list = new StringBuilder();

        StdOut.println(generalizedQueue.size());
        StringJoiner newQueue = new StringJoiner(" ");
        for (int number : generalizedQueue){
            newQueue.add(String.valueOf(number));
        }
        StdOut.print(newQueue);
    }
}

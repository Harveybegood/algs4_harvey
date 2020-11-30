package Chapter1_3_Bags_Queues_Stacks.bag;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.StringJoiner;

/*
*   Random Chapter1_3_Bags_Queues_Stacks.bag. A random Chapter1_3_Bags_Queues_Stacks.bag stores a collection of items and supports the following API:
*
*   Write a class RandomBag that implements this API. Note that this API is the same as for Bag, except for the adjective
*   random, which indicates that the iteration should provide the items in random order(all N! permutations equally
*   likely, for each iterator), Hint: Put the items in an array and randomize their order in the iterator's constructor.
*
* */
public class Ex1_3_34 {
    public class RandomBag<Item> implements Iterable<Item>{
        //@SuppressWarnings("Unchecking")
        private Item[] array = (Item[]) new Object[10];
        private int N = 0;
        public boolean isEmpty(){return N == 0;}
        public int size(){return N;}
        public void add(Item item){
            array[N++] = item;
        }
        @SuppressWarnings("Unchecking")
        public Iterator<Item> iterator(){
            return new randomBagIterator();
        }
        public class randomBagIterator implements Iterator<Item>{
            //@SuppressWarnings("Unchecking")
            //Item[] items = (Item[]) new Object[1];
            public boolean hasNext(){
                return N > 0;
            }
            public Item next(){
               return array[N--];
            }
        }
    }
    public static void main(String[] args) {
        Ex1_3_34 ex1_3_34 = new Ex1_3_34();
        RandomBag<Integer> randomBag = ex1_3_34.new RandomBag<>();
        randomBag.add(1);
        randomBag.add(1);
        randomBag.add(1);
        randomBag.add(1);
        randomBag.add(1);
        //StdOut.println(String.format());
        //StringBuilder stringBuilder = new StringBuilder();
        StringJoiner stringJoiner =new StringJoiner(" ");
        for (int s : randomBag){
            //StdOut.println(stringJoiner.);
            stringJoiner.add(String.valueOf(s));
            StdOut.println(" --  ");
        }
    }
}

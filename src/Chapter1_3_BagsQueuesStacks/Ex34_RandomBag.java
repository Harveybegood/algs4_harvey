package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/*
*   Random bag. A random bag stores a collection of items and supports the following API:
*
*   Write a class RandomBag that implement this API. Note that this API is the same as for Bag. except for the adjective random,
*   which indicates that the iteration should provide the items in random order(all N! permutation equally likely, for each
*   iterator). Hint: Put the items in an array and randomize their order in the iterator's constructor.
*
* */
@SuppressWarnings("unchecked")
public class Ex34_RandomBag<Item> implements Iterable<Item> {

    public int n;
    private Item[] items = (Item[]) new Object[1];
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}

    public void add(Item item){
        if (n == items.length){
            resizeItem(2 * items.length);
        }
        items[n] = item;
        n++;
    }
    public Iterator iterator(){
        return new randomBagIterator();
    }
    private class randomBagIterator implements Iterator<Item>{
        int index;
        Item[] itemCopy = (Item[]) new Object[n];
        public randomBagIterator(){
            int m = n;
            for(int i = 0; i < n; i++){
                int l = 0;
                int j = StdRandom.uniform(m);
                itemCopy[i] = items[j];
                for(int k = 0; k < m; k++){
                    if (k == j){
                        continue;
                    }
                    items[l] = items[k];
                    l++;
                }
                m--;
            }
        }

        public boolean hasNext(){
            return index < n;
        }
        public Item next(){
            Item item = itemCopy[index];
            index++;
            return item;
        }
    }
    public void resizeItem(int newSize){
        Item[] temp = (Item[]) new Object[newSize];
        for(int i = 0; i < n; i++){
            temp[i] = items[i];
        }
        items = temp;
    }

    public static void main(String[] args) {
        Ex34_RandomBag<String> randomBag = new Ex34_RandomBag<>();
        randomBag.add("a");
        randomBag.add("b");
        randomBag.add("c");
        randomBag.add("d");
        randomBag.add("e");
        for (String s : randomBag){
            StdOut.print(s + " ");
        }
    }
}

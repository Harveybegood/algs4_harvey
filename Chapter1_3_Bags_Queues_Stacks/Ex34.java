package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.StringJoiner;

/*
*   Random Chapter1_3_Bags_Queues_Stacks.bag. A random Chapter1_3_Bags_Queues_Stacks.bag stores a collection of items and supports the following API:
*       RandomBag()  ---> create an empty random Chapter1_3_Bags_Queues_Stacks.bag
*       boolean isEmpty() ---> is the Chapter1_3_Bags_Queues_Stacks.bag empty?
*       int size()      ---> number of items in the Chapter1_3_Bags_Queues_Stacks.bag
*       void add(Item item) ---> add an item
*
*   Write a class RandomBag that implements this API.  Note that this API is the same as for Bag, Except for
*   the adjective random, which indicates that the iteration should provide the items in random order(all N!
*   permutations equally likely, for each iterator). Hint: Put the items in an array and randomize their order
*   in the iterator's constructor.
*
* */
@SuppressWarnings("unchecked")
public class Ex34<Item> implements Iterable<Item> {
    // create a class RandomBag
    private Item[] array  = (Item[]) new Object[1];
    private int size = 0;

  /*  public Ex34(){
        array = (Item[]) new Object[1];
        size = 0;
    }*/
    public boolean isEmpty(){return size == 0;}
    public int size(){return size;}

    public void add(Item item){
        if (size() == array.length){resize(array.length*2);}
        array[size++] = item;
    }
    @SuppressWarnings("unchecked")
    public void resize(int newSize){
        Item[] newArray = (Item[]) new Object[newSize];

        for (int i = 0; i < array.length; i++){
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public Iterator<Item> iterator(){
        return new RandomBagIterator();
    }
    @SuppressWarnings("unchecked")
    private class RandomBagIterator implements Iterator<Item>{
        int index;
        Item[] arrayCopy;
        public RandomBagIterator(){
            index = 0;
            arrayCopy = (Item[]) new Object[size];
            for(int i = 0; i < size; i++){
                arrayCopy[i] = array[i];
            }
            sortArrayCopy();
        }
        public boolean hasNext(){return index < size();}
        public Item next(){
            Item item = arrayCopy[index];
            index++;
            return item;
        }
        private void sortArrayCopy(){
            for (int i = 0; i < size; i++){
                int randomIndex = StdRandom.uniform(0, size-1);
                Item temp = arrayCopy[i];
                arrayCopy[i] = arrayCopy[randomIndex];
                arrayCopy[randomIndex] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Ex34<Integer> randombag =  new Ex34<>();
        for (int i = 0; i < 3; i++){
            randombag.add(i);
        }
        StdOut.println("Random Chapter1_3_Bags_Queues_Stacks.bag items: ");
        StringJoiner randomBagItems = new StringJoiner(" - ");
        for (int number : randombag){
            randomBagItems.add(String.valueOf(number));
        }
        StdOut.println(randomBagItems.toString());
    }
}

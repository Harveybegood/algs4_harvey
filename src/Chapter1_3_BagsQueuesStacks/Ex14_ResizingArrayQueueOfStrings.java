package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/*
*   Develop a class ResizingArrayQueueOfString that implements the queue abstraction with a fixed-size array, and then
*   extend your implementation to use array resizing to remove the size restriction.
*
* */
@SuppressWarnings("unchecked")
public class Ex14_ResizingArrayQueueOfStrings<Item> {
    private Item[] array;
    private int n;
    private int i;
    public Ex14_ResizingArrayQueueOfStrings(int cap){
        array = (Item[]) new Object[cap];
    }
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public void enqueue(Item item){
        if (n == array.length / 2){
            resizingArray(array.length * 2);
        }
        array[n++] = item;
    }
    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("queue is empty");
        Item a = array[i];
        array[i] = null;
        i++;
        if (n == array.length / 4){
            resizingArray(array.length / 2);
        }
        return a;
    }
    public void resizingArray(int newSize){
        Item[] temp = (Item[]) new Object[newSize];
        for (int i = 0; i < n; i++){
            temp[i] = array[i];
        }
        array = temp;
    }

    public static void main(String[] args) {
        Ex14_ResizingArrayQueueOfStrings<String> resizingArrayQueueOfStrings = new Ex14_ResizingArrayQueueOfStrings<>(2);
        for (int i = 0; i < 10; i++){
            resizingArrayQueueOfStrings.enqueue(i + "String");
        }
        for (int i = 0; i < 10; i++){
            StdOut.println(resizingArrayQueueOfStrings.dequeue());
        }
    }
}

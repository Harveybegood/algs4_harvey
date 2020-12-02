package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

/*
*   Write a Queue client that takes a command-line argument k and prints the kth from the last string found on standard
*   input(assuming that standard input has k or more strings)
*
* */
@SuppressWarnings("unchecked")
public class Ex15_QueueClient<Item> {
    private Item[] array;
    private int n;
    private int i;
    public Ex15_QueueClient(int cap){
        array = (Item[]) new Object[cap];
    }
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public void enquue(Item item){
        array[n] = item;
        n++;
    }
    public Item dequeue(){
        Item item = array[i];
        i++;
        return item;
    }
    public Item KthDequeue(int k){
        return array[k];
    }

    public static void main(String[] args) {
        Ex15_QueueClient<String> queueClient = new Ex15_QueueClient<>(10);
        for (int i = 0; i < 10; i++){
            queueClient.enquue(i + " " +"String");
        }
        StdOut.println("Desire to print 6th item");
        StdOut.println("Expected 6 String: " + queueClient.KthDequeue(6));
    }
}

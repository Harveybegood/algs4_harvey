package Chapter1_3_Bags_Queues_Stacks.stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class resizingCapacityStack<Item> {
    //initialize private variables
    private Item[] a;
    private int N;

    //constructor
    public resizingCapacityStack(int cap){
        a = (Item[]) new Object();
    }

    //instance method
    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }

    private void resize(int max){
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++){
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(Item item){
        if (N == a.length){
            resize(2*a.length);
        }
        a[N++] = item;
    }

    public Item pop(){
        //remove item from top of Chapter1_3_Bags_Queues_Stacks.stack
        Item item =  a[--N];
        a[N] = null;
        if (N > 0 && N == a.length/4){
            resize(a.length/2);
        }
        return item;
    }

    public static void main(String[] args) {
        resizingCapacityStack<String> s = new resizingCapacityStack<>(100);
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!item.equals("-")){
                s.push(item);
            }else if (!s.isEmpty()){
                StdOut.print(s.pop());
            }
        }
        StdOut.println(" (" + s.size() + " left on Chapter1_3_Bags_Queues_Stacks.stack");
    }
}

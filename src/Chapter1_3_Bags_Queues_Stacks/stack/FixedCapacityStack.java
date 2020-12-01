package Chapter1_3_Bags_Queues_Stacks.stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStack<Item> {

    //define private variables
    private Item[] a;
    private int N;

    //create constructor
    public FixedCapacityStack(int cap){
        a = (Item[]) new Object[cap];
    }

    //create methods
    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }
    public void push(Item item){
        a[N++] = item;
    }
    public Item pop(){
        return a[--N];
    }

    //client code
    public static void main(String[] args) {
        FixedCapacityStack<String> s = new FixedCapacityStack<>(100);
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!item.equals("-")){
                s.push(item);
            }
            else if (!s.isEmpty()){
                StdOut.print(s.pop() + " ");
            }
        }
        StdOut.println("(" + s.size() + " left on Chapter1_3_Bags_Queues_Stacks.stack)");
    }
}
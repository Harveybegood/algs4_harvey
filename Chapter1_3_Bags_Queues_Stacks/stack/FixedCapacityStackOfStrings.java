package Chapter1_3_Bags_Queues_Stacks.stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStackOfStrings {
    private String[] a;
    private int N;
    public FixedCapacityStackOfStrings(int cap){
        a = new String[cap];
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return 0;
    }
    public void push(String item){
        a[N++] = item;
    }
    public String pop(){
        return a[--N];
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings s = new FixedCapacityStackOfStrings(100);
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

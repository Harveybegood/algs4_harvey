package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Add a method isFull() to FixedCapacityStackStrings
* */
public class ex1 {
    private int N;
    private String[] a;

    public ex1(int cap){
        a = new String[cap];
    }

    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }

    public boolean isFull(){
        return N == a.length;
    }

    public void push(String item){
        a[N++] = item;
    }
    public String pop(){
        return a[--N];
    }

    public static void main(String[] args) {
        ex1 S = new ex1(5);
        StdOut.println("Stack is full: " + S.isFull() + " | " + S.size() +", Expected error: False!");
        StdOut.println("Stack is empty: " + S.isEmpty() + " | " + S.size() + ", Expected: True");
        S.push("a");
        S.push("b");
        S.push("c");
        S.push("d");
        S.push("e");
        StdOut.println("Stack is full: " + S.isFull() + " | " + S.size() + ", Expected: True");
        StdOut.println("Please enter the desired number: ");
        int n = StdIn.readInt();
        for (int i = 0; i < n; i++){
            S.pop();
        }
        StdOut.println("The size of current Chapter1_3_Bags_Queues_Stacks.stack: " + S.size() + " !");
    }
}

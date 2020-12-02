package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Add a method isFull() to FixedCapacityStackOfStrings
*
* */
public class Ex01_IsFull {
    public class FixedCapacityStackOfStrings{
        private String[] a;
        private int N;
        public FixedCapacityStackOfStrings(int cap){
            a = new String[cap];
        }
        public boolean isEmpty(){
            return N == 0;
        }
        public int size(){
            return N;
        }
        public void push(String item){
            a[N++] = item;
        }
        public String pop(){
            return a[--N];
        }
        public boolean isFull(){
            return N == a.length;
        }
    }

    public static void main(String[] args) {
        Ex01_IsFull isFull = new Ex01_IsFull();
        FixedCapacityStackOfStrings stack = isFull.new FixedCapacityStackOfStrings(5);
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!stack.isFull()){
                stack.push(item);
            }
            if (!stack.isEmpty()){
                StdOut.print(stack.pop() + " ");
            }
        }
        StdOut.println(stack.size());
    }
}

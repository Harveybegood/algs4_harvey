package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Give the output printed by java Stack for the input
*       it was - the best - of times - - - it was - the - -
*
* */
public class ex2 {

    private String[] a;
    private int N;

    public ex2(int cap){
        a = new String[cap];
    }

    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        //return a.length;
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

    public static void main(String[] args) {
        ex2 s = new ex2(100);
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!item.equals("-")){
                s.push(item);
            }else if (!s.isEmpty()){
                StdOut.println("The output printed as: ");
                StdOut.print(s.pop() + " ");
            }
        }
        StdOut.println(" (" + s.size() + " left on Chapter1_3_Bags_Queues_Stacks.stack)");
    }
}

package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


/*
*   Copy a Chapter1_3_Bags_Queues_Stacks.stack. Create a new constructor for the linked-list implementation of Stack so that
*
*       Stack<Item> t = new Stack<Item>(s);
*
*    makes t a reference to a new and independent copy of the Chapter1_3_Bags_Queues_Stacks.stack s.
*
* */
@SuppressWarnings("unchecked")
public class Ex42<Item> extends Stack {
    private Ex42(Stack<Item> t){
        for (Item item : t){
            t.push(item);
        }
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        Ex42<Integer> copyS = new Ex42<>(s);

        copyS.push(4);

        StringBuilder m = new StringBuilder();
        for (int n : s){
            m.append(n);
            m.append(" ");
        }

        StdOut.println(m);

    }
}

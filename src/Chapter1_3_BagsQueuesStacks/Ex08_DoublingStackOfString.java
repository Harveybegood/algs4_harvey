package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


/*
*   Give the content and size of the array for DoublingStackOfString with the input
*       it was - the best - of times - - - it was - the - -
*
* */
public class Ex08_DoublingStackOfString {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (!s.equals("-")){
                stack.push(s);
            }
            else {
                stack.pop();
            }
        }
        while (!stack.isEmpty()){
            StdOut.print(stack.pop() + " ");
        }
        StdOut.println(stack.size());
    }
}

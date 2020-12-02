package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


/*
*   Write an iterable Stack client that has a static method copy() that takes a stack of strings as argument and returns
*   a copy of the stack. Note: This ability is a prime example of the value of having an iterator, because it allows
*    development of such functionality  without changing the basic of API.
*
* */
public class Ex12_IterableStack {
    public static Stack<String> copy(Stack<String> stack){
        Stack<String> temp = new Stack<>();
        for (String s : stack){
            temp.push(s);
        }
        Stack<String> temp1 = new Stack<>();
        for (String s : temp){
            temp1.push(s);
        }
        return temp1;
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("Third string");
        stack.push("Second string");
        stack.push("First string");
        Stack<String> stack1 = copy(stack);
        for (String s : stack1){
            StdOut.println(s);
        }
        /*StdOut.println(stack1.pop());
        StdOut.println(stack1.pop());
        StdOut.println(stack1.pop());*/
        StdOut.println();
        StdOut.println("Expected: " + "\nFirst string" + "\nSecond string" + "\nThird string");
    }
}

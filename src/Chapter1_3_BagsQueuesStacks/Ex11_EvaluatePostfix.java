package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdIn;

import java.util.Stack;

/*
*   Write a program EvaluatePostfix that takes a postfix expression from standard input, evaluate it, and prints the value.
*   (Piping the output of your program from the previous exercise to this program gives equivalent behavior to Evaluate.)
*
*                1 + 2) * 3 - 4) * 5 - 6)))
* */
public class Ex11_EvaluatePostfix {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (s.equals("+")){
                ops.push(s);
            }
            else if (s.equals("-")){
                ops.push(s);
            }
            else if (s.equals("*")){
                ops.push(s);
            }
            else if (s.equals(")")){
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+")) v = vals.pop() + v;
                else if (op.equals("-")) v = vals.pop() - v;
                else if (op.equals("*")) v = vals.pop() * v;
                vals.push(v);

            }
            else {
                vals.push(Double.parseDouble(s));
            }
        }
    }
}

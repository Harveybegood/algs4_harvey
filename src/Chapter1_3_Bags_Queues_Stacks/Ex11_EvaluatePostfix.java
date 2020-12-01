package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 *   Write a program EvaluatePostfix that takes a postfix expression from standard input, evaluates it,
 *   and prints the value.(Piping the output of your program from the previous exercise to this program
 *   gives equivalent behavior to Evaluate)
 *
 *      "( ( ( 6 5 - ) ( 4 3 - ) * ) ( 2 1 + ) * )"
* */
public class Ex11_EvaluatePostfix {
    public static void main(String[] args) {
        Stack<String> Ops = new Stack<>();
        Stack<Double> Vals = new Stack<>();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (s.equals("(")){}
            else if (s.equals("+")){
                Ops.push(s);
            }
            else if (s.equals("-")){
                Ops.push(s);
            }
            else if (s.equals("*")){
                Ops.push(s);
            }
            else if (s.equals(")")){
                String op = Ops.pop();
                double v = Vals.pop();
                if (op.equals("+")){
                    v = Vals.pop() + v;
                }
                else if (op.equals("-")){
                    v = Vals.pop() - v;
                }
                else if (op.equals("*")){
                    v = Vals.pop() * v;
                }
                Vals.push(v);
            }
            else {
                Vals.push(Double.valueOf(s));
            }
        }
        StdOut.println(Vals.pop() + " Expecting 3");
    }
}

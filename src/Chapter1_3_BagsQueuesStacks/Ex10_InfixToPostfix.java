package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

/*
*   Write a filter InfixToPostfix that converts an arithmetic expression from infix to postfix
*
* */
public class Ex10_InfixToPostfix {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<String> vals = new Stack<>();
        String source = "( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )";
        String[] s = source.split("\\s");
        for (String string : s){
            if (string.equals("("));
            else if (string.equals("+")){
                ops.push(string);
            }
            else if (string.equals("*")){
                ops.push(string);
            }
            else if (string.equals("-")){
                ops.push(string);
            }
            else if (string.equals(")")){
                String op = ops.pop();
                String v1 = vals.pop();
                String v2 = vals.pop();
                String subExpression = v2 + " " + op + " " + v1 + ")";
                vals.push(subExpression);
            }
            else {
                vals.push(string);
            }
        }
        StdOut.print(vals.pop());
    }
}

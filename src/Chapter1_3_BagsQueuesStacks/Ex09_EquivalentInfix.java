package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Write a program that takes from standard input an expression without left parentheses and prints the equivalent infix
*   parentheses inserted. For example, given the input:
*       1 + 2 ) * 3 - 4) * 5 - 6 ) ) )
*
*   your program should print
*       ((1 + 2) * ((3 -4) * (5 - 6)))
*
* */
public class Ex09_EquivalentInfix {
    public static void main(String[] args) {
        Stack<String> operands = new Stack<>();
        Stack<String> operators = new Stack<>();
        String string = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
        String[] values = string.split("\\s");
        for (String  value : values){
            if (value.equals("("));
            else if (value.equals("+")){
                operators.push(value);
            }
            else if (value.equals("-")){
                operators.push(value);
            }
            else if (value.equals("*")){
                operators.push(value);
            }
            else if (value.equals(")")){
                String operator = operators.pop();
                String val1 = operands.pop();
                String val2 = operands.pop();
                String subExpression = "( " + val2 + " " + operator + " " + val1 + " )";
                operands.push(subExpression);
            }
            else {
                operands.push(value);
            }
        }
        StdOut.print(operands);
    }
}

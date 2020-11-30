package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*
*   Write a program that takes from standard input an expression without left parentheses and prints
*   the equivalent infix expression with the parentheses inserted. For example, given the input:
*   1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
*   your program should print
*   ((1 + 2) * ((3 - 4) * (5 - 6)))
*
* */
public class Ex9 {
    private static String getInfixExpression(String input){
        Stack<String> operands = new Stack<>();     // store operands in this Chapter1_3_Bags_Queues_Stacks.stack
        Stack<String> operators = new Stack<>();    // store operators in this Chapter1_3_Bags_Queues_Stacks.stack

        String[] inputValues = input.split("\\s"); // split the input string with whitespace

        for (String value : inputValues){
            if (value.equals("(")){
                //do nothing
            }
            else if (value.equals("+") || value.equals("-") || value.equals("*")){
                operators.push(value);
            }
            else if (value.equals(")")){
                String operator = operators.pop();
                String value1 = operands.pop();
                String value2 = operands.pop();

                String subExpression = "( " + value1 + " " + operator + " " + value2 + " )";
                operands.push(subExpression);
            }
            else {
                operands.push(value);
            }

        }
        return operands.pop();
    }

    public static void main(String[] args) {
        //String input = args[0];
        StdOut.println(getInfixExpression("1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )"));
    }

}

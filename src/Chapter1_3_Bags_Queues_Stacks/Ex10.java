package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*
*   Write a filter InfixToPostfix that converts an arithmetic expression from infix to postfix
*
* */
public class Ex10 {

    public static String InfixToPostfix(String input){
        Stack<String> operators = new Stack<>();
        Stack<String> operands = new Stack<>();

        String[] inputString = input.split("\\s");
        for (String string : inputString){
            if (string.equals("(")){
                //do nothing;
            }
            else if (string.equals("+") || string.equals("-") || string.equals("*")){
                operators.push(string);
            }
            else if (string.equals(")")){
                String operator = operators.pop();
                String value1 = operands.pop();
                String value2 = operands.pop();

                String subexpression = "( " + value2 + " " + value1 + " " + operator + " )";
                operands.push(subexpression);
            }
            else {
                operands.push(string);
            }
        }
        return operands.pop();
    }

    public static void main(String[] args) {
        StdOut.println(InfixToPostfix("( ( ( 6 - 5 ) * ( 4 - 3 ) ) * ( 2 + 1 ) )"));
    }

}

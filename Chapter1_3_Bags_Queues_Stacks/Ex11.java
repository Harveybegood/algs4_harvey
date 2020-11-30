package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*
*   Write a program EvaluatePostfix that takes a postfix expression from standard input, evaluates it,
*   and prints the value.(Piping the output of your program from the previous exercise to this program
*   gives equivalent behavior to Evaluate)
*
* */
public class Ex11 {
    public static int EvaluatePostfix(String input){

        Stack<String> operators = new Stack<>();
        Stack<String> operands = new Stack<>();

        String[] inputString = input.split("\\s");

        for (String value : inputString){
            if (value.equals("(")){
                // do nothing
            }
            else if (value.equals("+") || value.equals("-") || value.equals("*")){
                operators.push(value);
            }
            else if (value.equals(")")){
                String operator = operators.pop();
                int value2 = Integer.parseInt(operands.pop());
                int value1 = Integer.parseInt(operands.pop());
                int result = 0;
                if (operator.equals("+")){
                    result = value1 + value2;
                }
                else if (operator.equals("-")){
                    result = value1 - value2;
                }
                else if(operator.equals("*")){
                    result = value1 * value2;
                }
                operands.push(String.valueOf(result));
            }
            else {
                operands.push(value);
            }
        }
        return Integer.parseInt(operands.pop());
    }

    public static void main(String[] args) {
        StdOut.println("Result: " + EvaluatePostfix("( ( ( 6 5 - ) ( 4 3 - ) * ) ( 2 1 + ) * )"));
    }
}

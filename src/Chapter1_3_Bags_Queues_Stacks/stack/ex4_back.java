package Chapter1_3_Bags_Queues_Stacks.stack;



/*
*   Write a Chapter1_3_Bags_Queues_Stacks.stack client parenthesiss that reads in a text stream from standard input and uses a Chapter1_3_Bags_Queues_Stacks.stack
*   to determine whether its parenthesiss are properly balanced. For example, your program should print
*   true for [()]{}{[()()]()} and false for [(]).
*
* */

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ex4_back {

   private boolean isBalanced(String input){
      char[] parentheses = input.toCharArray();
      Stack<Character> stack = new Stack<>();
      for (char parenthesis : parentheses){
          if (parenthesis == '(' || parenthesis == '[' || parenthesis == '{'){
              stack.push(parenthesis);
          }else {
              if (stack.isEmpty()){
                  return false;
              }
              char firstItem = stack.pop();
              if (parenthesis == ')' && firstItem != '(' || parenthesis == ']' && firstItem != '['
                      || parenthesis == '}' && firstItem != '{'){
                  return false;
              }
          }
      }
      return true;
   }

    public static void main(String[] args) {
        ex4_back s = new ex4_back();
        //String textStream = args[0];
        StdOut.print("Please enter text stream: ");
        String textStream = StdIn.readString();
        StdOut.println("Is balanced " + textStream + ": " + s.isBalanced(textStream));
        StdOut.println("Is balanced [()]{}{[()()]()}: " + s.isBalanced("[()]{}{[()()]()}"));
        StdOut.println("Is balanced [(]): " + s.isBalanced("[(])"));
    }
}

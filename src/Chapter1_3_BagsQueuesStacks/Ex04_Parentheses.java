package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

/*
*   Write a stack client Parentheses that read in a text stream from standard input and uses a stack to determine
*   whether its parentheses are properly balanced. For example, your program should print true for
*   [()]{}{[()()]()} and false for [(]).
*
* */
public class Ex04_Parentheses {
    public static void main(String[] args) {
        In in = new In();
        String s = in.readString().trim();
        StdOut.println(isBalanced(s));
    }
    private static final char Left_Paren = '(';
    private static final char Right_Paren = ')';
    private static final char Left_Brace = '{';
    private static final char Right_Brace = '}';
    private static final char Left_Bracket = '[';
    private static final char Right_Bracket = ']';
    private static boolean isBalanced(String s){
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == Left_Brace) stack.push(Left_Brace);
            if (s.charAt(i) == Left_Bracket) stack.push(Left_Bracket);
            if (s.charAt(i) == Left_Paren) stack.push(Left_Paren);
            if (s.charAt(i) == Right_Brace){
                if (stack.isEmpty()){return false;}
                if (stack.pop() != Left_Brace) return false;
                for (Character t : stack){
                    StdOut.print(t);
                }
                StdOut.println();
            }
            else if (s.charAt(i) == Right_Bracket){
                if (stack.isEmpty()) return false;
                if (stack.pop() != Left_Bracket) return false;
                for (Character t : stack){
                    StdOut.print(t);
                }
                StdOut.println();
            }
            else if (s.charAt(i) == Right_Paren){
                if (stack.isEmpty()) return false;
                if (stack.pop() != Left_Paren) return false;
                for (Character t : stack){
                    StdOut.print(t);
                }
                StdOut.println();
            }
        }
        return stack.isEmpty();
    }
}

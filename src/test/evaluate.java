package test;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            //String s = "( 1 + ( ( 2 + 3 ) * ( 4 + 5 ) ) )";
            if (s.equals("(")) ;
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals("sqrt")) ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                double val = vals.pop();
                if (op.equals("+")) val = vals.pop() + val;
                else if (op.equals("-")) val = vals.pop() - val;
                else if (op.equals("*")) val = vals.pop() * val;
                else if (op.equals("/")) val = vals.pop() / val;
                else if (op.equals("sqrt")) val = Math.sqrt(val);
                vals.push(val);
            } else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }
}

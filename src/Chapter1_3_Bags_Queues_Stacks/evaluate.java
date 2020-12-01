package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class evaluate<Item> {

    private Item[] ops;
    private Item[] vals;
    private int N;
    private evaluate(int N){
        ops = (Item [])  new Object[N];
        vals = (Item []) new Object[N];
    }
    public void push(Item item){
        ops[N++] = item;
        vals[N++] = item;
    }

    public Item pop(){

        return vals[--N];
    }

    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        while (!StdIn.isEmpty()){
            String s = StdIn.readAll();
            if (s.equals("("));
            else if (s.equals("+")){
                ops.push(s);
            }
            else if (s.equals("-")){
                ops.push(s);
            }
            else if (s.equals("*")){
                ops.push(s);
            }
            else if (s.equals("/")){
                ops.push(s);
            }
            else if (s.equals("sqrt")){
                ops.push(s);
            }
            else if (s.equals(")")){
                String op = ops.pop();
                Double v = vals.pop();
                if (op.equals("+")){
                    v = vals.pop() + v;
                }
                else if (op.equals("-")){
                    v = vals.pop() + v;
                }
                else if (op.equals("*")){
                    v = vals.pop() + v;
                }
                else if (op.equals("/")){
                    v = vals.pop() + v;
                }
                else if (op.equals("sqrt")){
                    v = Math.sqrt(v);
                }
                else {
                    vals.push(v);
                }
            }
            else {
                vals.push(Double.parseDouble(s));
            }
        }
        StdOut.println(vals.pop());
    }
}

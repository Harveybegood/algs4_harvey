package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


/*
*   Deque with three stacks. Implement a deque with three stacks so that each deque operation takes a constant
*   amortized number of Chapter1_3_Bags_Queues_Stacks.stack operations.
*
* */
public class Ex31<Item> {
    private Stack<Item> headStack;
    private Stack<Item> middleStack;
    private Stack<Item> tailStack;
    public Ex31(){
        headStack = new Stack<>();
        middleStack = new Stack<>();
        tailStack = new Stack<>();
    }
    public boolean isEmpty(){return headStack.isEmpty() && middleStack.isEmpty() && tailStack.isEmpty();}
    public int size(){return headStack.size() + middleStack.size() + tailStack.size();}

    public void pushLeft(Item item){
        headStack.push(item);
    }
    public Item popLeft(){
        if (isEmpty()) throw new RuntimeException("Steque underflow");
        Item item = null;
        if (!headStack.isEmpty()){
            item = headStack.pop();
        }else {
            int half = tailStack.size()/2;
            // Move half items tail Chapter1_3_Bags_Queues_Stacks.stack to middle Chapter1_3_Bags_Queues_Stacks.stack
            while (half-- > 0)middleStack.push(tailStack.pop());
            // Move the other half items from tail Chapter1_3_Bags_Queues_Stacks.stack to head Chapter1_3_Bags_Queues_Stacks.stack
            while (half-- > 0)headStack.push(tailStack.pop());
            // Return all items from middle Chapter1_3_Bags_Queues_Stacks.stack to tail Chapter1_3_Bags_Queues_Stacks.stack
            while(!middleStack.isEmpty())tailStack.push(middleStack.pop());
            item = headStack.pop();
        }
        return item;
    }
    public void pushRight(Item item){
        tailStack.push(item);
    }
    public Item popRight(){
        if (isEmpty()) throw new RuntimeException("Steque underflow");
        Item item = null;
        if (!tailStack.isEmpty()){
            item = tailStack.pop();
        }else {
            int half  = headStack.size() / 2;
            // Move half items from head Chapter1_3_Bags_Queues_Stacks.stack to middle Chapter1_3_Bags_Queues_Stacks.stack
            while (half-- > 0)middleStack.push(headStack.pop());
            // Move the other half items from head Chapter1_3_Bags_Queues_Stacks.stack to tail Chapter1_3_Bags_Queues_Stacks.stack
            while (half-- > 0)tailStack.push(headStack.pop());
            // Return all items from middle Chapter1_3_Bags_Queues_Stacks.stack back to head Chapter1_3_Bags_Queues_Stacks.stack
            while (!middleStack.isEmpty())headStack.push(middleStack.pop());
            item = tailStack.pop();
        }
        return item;
    }

 /*   @Override
    public Iterator<Item> iterator() {
        return new steIterator;
    }
    public class steIterator implements Iterator<Item>{
        private int N = headStack.size() + tailStack.size();
        public boolean hasNext(){
            return N > 0;
        }
        public Item next(){
            int[] items = new int[N];
            items =
        }
    }
*/
    public static void main(String[] args) {
        Ex31<Integer> ex31 = new Ex31<>();
        StdOut.println("the current size of object ex31: " + ex31.size());
        ex31.pushLeft(1);
        ex31.pushLeft(2);
        ex31.pushLeft(3);
        ex31.pushLeft(4);
        ex31.pushRight(1);
        ex31.pushRight(2);
        ex31.pushRight(3);
        ex31.pushRight(4);
       /* StringJoiner stringJoiner = new StringJoiner(" ");
        for (int number : ex31){
            stringJoiner.add(String.valueOf(number));
        }*/
       StdOut.println("Pop out from left Chapter1_3_Bags_Queues_Stacks.stack - " + ex31.popLeft());
       StdOut.println("Pop out from left Chapter1_3_Bags_Queues_Stacks.stack - " + ex31.popLeft());
       StdOut.println("Pop out from left Chapter1_3_Bags_Queues_Stacks.stack - " + ex31.popLeft());
       StdOut.println("Pop out from left Chapter1_3_Bags_Queues_Stacks.stack - " + ex31.popLeft());
       StdOut.println("Pop out from left Chapter1_3_Bags_Queues_Stacks.stack - " + ex31.popLeft());
       StdOut.println("Pop out from Right Chapter1_3_Bags_Queues_Stacks.stack - " + ex31.popRight());
       StdOut.println("Pop out from Right Chapter1_3_Bags_Queues_Stacks.stack - " + ex31.popRight());
       StdOut.println("Pop out from Right Chapter1_3_Bags_Queues_Stacks.stack - " + ex31.popRight());
       StdOut.println("Pop out from Right Chapter1_3_Bags_Queues_Stacks.stack - " + ex31.popRight());
       StdOut.println("Pop out from Right Chapter1_3_Bags_Queues_Stacks.stack - " + ex31.popRight());
       StdOut.println("The expected sequence of steque: 4 3 2 1 4 3 2 1");
    }
}

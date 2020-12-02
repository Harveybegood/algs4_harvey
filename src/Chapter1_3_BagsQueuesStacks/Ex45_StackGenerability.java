package Chapter1_3_BagsQueuesStacks;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Stack generability. Suppose that we have a sequence of intermixed push and pop operations as with our test stack client,
*   where the integers 0, 1, ..., N-1 in that order(push directives)are intermixed with N minus signs(pop directives). Devise
*   an algorithm that determines whether the intermixed sequence causes the stack to underflow. (You may use only an amount
*   of space independent of N - you cannot store the integers in a data structure.) Devise a linear-time algorithm that
*   determines whether a given permutation can be generated as output by our test client(depending on where the pop
*   directives occur).
*
*   Solution: The stack does not overflow unless there exists an integer k such that the first k pop operations occur before
*   the first k push operations.
*   If a given permutation can be generated, it is uniquely generated as follows: if the next integer in the output
*   permutation is in the top of the stack, pop it; otherwise, push it onto the stack.
*
* */
public class Ex45_StackGenerability<Item> {
    private class Node {
        Item item;
        Node next;
    }
    private Node first;
    private int n;
    public boolean isEmpty(){return first == null;}
    public int size(){return n;}
    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }
    public Item pop(){
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }
    public void stackUnderflow(int num, int numSign){
        if (numSign > num){
            StdOut.println("Stack underflow:");
        }
        else {
            StdOut.println("Do not cause stack to underflow");
        }
    }

    public static void main(String[] args) {
        Ex45_StackGenerability stackGenerability = new Ex45_StackGenerability();
        int num = 0;
        int numSign = 0;
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (s.equals("-")){
               numSign++;
            }
            else {
                num++;
            }
        }
        stackGenerability.stackUnderflow(num, numSign);
    }
}

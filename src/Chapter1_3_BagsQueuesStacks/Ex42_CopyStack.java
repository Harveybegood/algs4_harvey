package Chapter1_3_BagsQueuesStacks;


import edu.princeton.cs.algs4.StdOut;

/*
*   Copy a stack. Create a new constructor for the linked-list implementation of Stack so that
*
*       Stack<Item> t = new Stack<>(s);
*
*   makes t a reference to a new and independent copy of the stack s.
*
* */
public class Ex42_CopyStack<Item> {
    private class Node{
        Item item;
        Node next;
        public Node(Node x){
            this.item = x.item;
            this.next = x.next;
        }
        public Node(){}
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
        return  item;
    }
    public Ex42_CopyStack(Ex42_CopyStack<Item> stack){
        if (stack.first != null){
            first = new Node(stack.first);
            for (Node x = first; x.next != null; x = x.next){
                x.next = new Node(x.next);
            }
        }
    }
    public Ex42_CopyStack(){}
    public static void main(String[] args) {
        Ex42_CopyStack<String> copyStack = new Ex42_CopyStack<>();
        copyStack.push("a");
        copyStack.push("c");
        copyStack.push("e");
        Ex42_CopyStack<String> stack = new Ex42_CopyStack<>(copyStack);
        StdOut.print(stack.pop() + " ");
        StdOut.print(stack.pop() + " ");
        StdOut.print(stack.pop() + " ");
        stack.push("g");
        StdOut.println(stack.pop());
    }
}

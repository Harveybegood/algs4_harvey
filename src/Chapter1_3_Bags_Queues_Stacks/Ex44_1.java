package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

public class Ex44_1<Item> {
    private class Node{
        Item item;
        Node next;
    }
    private int sizeLeft;
    private int sizeRight;
    private Node firstLeftStack;
    private Node firstRightStack;
    private Ex44_1(){
        sizeLeft = 0;
        sizeRight = 0;
        firstLeftStack = null;
        firstRightStack = null;
    }
    private void insertOnLeftStack(Item item){
        Node oldFirstLeft = firstLeftStack;
        firstLeftStack = new Node();
        firstLeftStack.item = item;
        firstLeftStack.next = oldFirstLeft;
        sizeLeft++;
    }
    private void insertOnRightStack(Item item){
        Node oldFirstRight = firstRightStack;
        firstRightStack = new Node();
        firstRightStack.item = item;
        firstRightStack.next = oldFirstRight;
        sizeRight++;
    }
    public Item get(){
        if (sizeRight == 0){return null;}
        return firstRightStack.item;
    }
    public Item delete(){
        if (sizeRight == 0){return null;}
        Item item = firstRightStack.item;
        firstRightStack = firstLeftStack.next;
        sizeRight--;
        return item;
    }
    public void left(int k){
        int count = 0;
        while (count < k && sizeLeft > 0){
            Item item = firstLeftStack.item;
            insertOnRightStack(item);
            firstLeftStack = firstLeftStack.next;
            sizeLeft--;
            count++;
        }
    }
    public void right(int k){
        int count = 0;
        while (count < k && sizeRight > 0){
            Item item = firstRightStack.item;
            insertOnLeftStack(item);
            firstRightStack = firstRightStack.next;
            sizeRight--;
            count++;
        }
    }
    public int size(){return sizeLeft + sizeRight;}

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Left Stack: ");
        for (Node current = firstLeftStack; current.next != null; current = current.next){
            stringBuilder.append(current.item).append(" ");
        }
        stringBuilder.append("\nRight Stack: ");
        for (Node current = firstRightStack; current != null; current = current.next){
            stringBuilder.append(current.item).append(" ");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Ex44_1<Character> textEditorBuffer = new Ex44_1<>();
        StdOut.println("Test insert");
        textEditorBuffer.insertOnLeftStack('R');
        textEditorBuffer.insertOnLeftStack('e');
        textEditorBuffer.insertOnLeftStack('n');
        textEditorBuffer.insertOnLeftStack('e');
        StdOut.println(textEditorBuffer);
        StdOut.println("\nTest left");
        textEditorBuffer.left(3);
        StdOut.println(textEditorBuffer);
        StdOut.println("\nTest Right");
        textEditorBuffer.right(2);
        StdOut.println(textEditorBuffer);
        StdOut.println("\nTest get");
        StdOut.println(textEditorBuffer.get());
        StdOut.println(textEditorBuffer.get());
        StdOut.println("\nTest delete");
        StdOut.println(textEditorBuffer.delete());
        StdOut.println(textEditorBuffer);
    }
}

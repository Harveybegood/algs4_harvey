package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
*   Write a method remove() that takes a linked list and a string key as arguments and removes all of the nodes in the list
*   that have key as its item field.
*
* */
public class Ex26_Remove<Item> implements Iterable<Item> {
    public static void main(String[] args) {
        String string = "kg";
        Ex26_Remove<String> removeKey = new Ex26_Remove<>();
        removeKey.push("c");
        removeKey.push("d");
        removeKey.push("e");
        removeKey.push("g");
        removeKey.push("h");
        removeKey.push("y");
        StdOut.println(removeKey.size() + " Expected 6");
        StdOut.print("Link list before remove: ");
        for (String s : removeKey){
            StdOut.print(s + " ");
        }
        StdOut.println();
        StdOut.print("Link list after remove: ");
        removeKey.remove(removeKey, string);
        for (String s : removeKey){
            StdOut.print(s + " ");
        }
    }
    private class Node{
        private Node next;
        private Item item;
        public Node(Item item){
            this.item = item;
        }
    }
    private Node first;
    private int n;
    public boolean isEmpty(){
        return first == null;
    }
    public int size(){return n;}
    public void push(Item item){
        Node oldNode = first;
        first = new Node(item);
        first.item = item;
        first.next = oldNode;
        n++;
    }
    public void delete(String s){
        Node current;
        for(current = first; current != null; current = current.next){
            if (current.item.equals(s)){
                current = current.next;
                n--;
            }
        }
    }
    public void remove(Ex26_Remove<String> list, String s){
        String[] strings = s.split("");
        for(String string : list){
            for(int i = 0; i < s.length(); i++){
                if (string.equals(strings[i])){
                    list.delete(string);
                }
            }
        }
    }


    public Iterator<Item> iterator() {
        return new listIterator();
    }
    private class listIterator implements Iterator<Item>{
        Node node = first;
        Item item;
        public boolean hasNext(){
            return node != null;
        }
        public Item next(){
            item = node.item;
            node = node.next;
            return item;
        }
    }
}

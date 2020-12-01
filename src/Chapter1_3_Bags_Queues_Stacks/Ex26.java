package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;

import java.util.StringJoiner;

/*
*   Write a method remove() that takes a linked list and a string key as arguments and removes all of the nodes
*   in the list that have key as its item field.
*
* */
public class Ex26<Item> {

    private class Node{
        Item item;
        Node next;
    }
    private Node first;
    private int N;
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}

    private void remove(Node list, Item item){
        Node current;
        for (current = first; current.next != null; current = current.next){
            while(list.item.equals(item)){
                current.next = current.next.next;
                N--;
            }
        }
    }

    private Node createNode(Item item){
        Node node = new Node();
        node.item = item;
        return node;
    }

    private void AddNewNode(Item item){
        if (isEmpty()){
            first = new Node();
            first.item = item;
        }else {
            Node current;
            for (current = first; current.next != null; current = current.next){
                Node newNode = new Node();
                newNode.item = item;
                current.next = newNode;
            }
        }
        N++;
    }

    public static void main(String[] args) {
        Ex26<Integer> LinkedList = new Ex26<>();
      /*  LinkedList.AddNewNode(0);
        LinkedList.AddNewNode(1);
        LinkedList.AddNewNode(2);
        LinkedList.AddNewNode(3);
        LinkedList.AddNewNode(4);*/

        for (int i = 0; i < 5; i ++){LinkedList.AddNewNode(i);}
        StdOut.println("The length: " + LinkedList.size());

        //StdOut.println("The list of elements: " + LinkedList);
        StringJoiner nodesOfLinkedList = new StringJoiner(" ");
       /* for (int number : nodesOfLinkedList){
            StdOut.println(nodesOfLinkedList.add(String.valueOf(number)));
        }*/
       for (int i = 0 ; i < LinkedList.size(); i++){
           nodesOfLinkedList.add(String.valueOf(i));
       }
       StdOut.println(nodesOfLinkedList);
       Ex26<Integer>.Node creatednode = LinkedList.createNode(3);

       StdOut.println(creatednode.item);

       //LinkedList.remove(LinkedList, creatednode.item);
    }
}

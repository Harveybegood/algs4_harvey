package Chapter1_3_Bags_Queues_Stacks;


/*
*   Implement a nested class DoubleNode for building doubly-linked lists, where each DoubleNode contains a reference to the item
*   preceding it and the item following it in the list(null if there is no such item).
*   Then implement static methods,  for the following tasks: 
*   insert at the beginning, insert at the end, 
*   remove from the beginning, remove from the end,
*   insert before a given DoubleNode, insert after a given DoubleNode, 
*   and remove a given DoubleNode.
*
* */
@SuppressWarnings("unchecked")
public class Ex31_NestedDoubleNode {
    private static class DoubleNode<Item> {
        Item item;
        static DoubleNode prev;
        static DoubleNode next;
        public DoubleNode(Item item, DoubleNode prev, DoubleNode next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    private DoubleNode first;
    public int N;
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public static <Item> void insertAtBeginning(Item item){
        DoubleNode<Item> oldFirst = new Ex31_NestedDoubleNode().first;
        new Ex31_NestedDoubleNode().first = new DoubleNode(item, DoubleNode.prev, DoubleNode.next);

    }
    public static <Item> void insertAtEnd(Item item){

    }
    public static void removeFromBeginning(){

    }
    public static void removeFromEnd(){

    }
    public static <Item> void insertBeforeGivenNode(Item item, DoubleNode<Item> node){

    }
    public static <Item> void insertAfterGivenNode(Item item, DoubleNode<Item> node){

    }
    public static <Item> void removeGivenNode(DoubleNode<Item> node){

    }

}

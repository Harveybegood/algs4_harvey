package Chapter1_3_BagsQueuesStacks;
/*
*   Give a code fragment that removes the last node in a linked list whose first node is first.
*
* */
public class Ex19_DeleteLastNode {
    public class Node{
        Node next;
        int n;
        public Node(Node next){
            this.next = next;
        }
    }
    private Node first;
    public void deleteLastNode(){
        Node x = first;
        while (x != null){
            x = x.next;
            if (x.next == null){
                x = null;
            }
        }

        /*for (x = first; x != null; x = x.next){

        }
        x = null;*/
    }
}

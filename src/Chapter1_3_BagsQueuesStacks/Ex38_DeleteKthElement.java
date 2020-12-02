package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.StdOut;

/*
*   Delete kth element. Implement a class that supports the following API:
*
*   First, develop an implementation that uses an array implementation, and then develop one that uses a linked-list
*   implementation. Note: the algorithms and data structures that we introduce in CHAPTER 3 make it possible to develop
*   an implementation that can guarantee that both insert() and delete() take time proportional to the logarithm of the
*   number of items in the queue - see exercise 3.5.27
*
* */
@SuppressWarnings("unchecked")
public class Ex38_DeleteKthElement {
    // develop one that uses a linked-list implementation
    public static class GeneralizedQueueLinkedList<Item>{
        private class Node{
            Item item;
            Node next;
        }
        int n;
        Node first;
        Node last;
        public boolean isEmpty(){return first == null;}
        public int size(){return n;}
        public void insert(Item item){
            Node oldLast = last;
            last = new Node();
            last.item = item;
            if (isEmpty()){
                first = last;
            }
            else {
                oldLast.next = last;
            }
            n++;
        }
        // delete kth element
        public Item delete(int k){
            int count = 1;
            while (first != null){
                if (k == count){
                    Node temp = first;
                    first = first.next.next;
                    return temp.item;
                }
                count++;
                first = first.next;
            }
            return null;
        }
    }

    // develop one that uses an array implementation
    public static class GeneralizedQueueArray<Item>{
        int n;
        private Item[] items;
        public GeneralizedQueueArray(int cap){
            items = (Item[]) new Object[cap];
            n = 0;
        }
        public boolean isEmpty(){return n == 0;}
        public void resize(int newSize){
            Item[] temp = (Item[]) new Object[newSize];
            for (int i = 0; i < n; i++){
                temp[i] = items[i];
            }
            items = temp;
        }
        public void insert(Item item){
            if (n == items.length){resize(2 * items.length);}
            items[n] = item;
            n++;
        }
        public Item delete(int k){
            if (k < 0 || k > n || isEmpty()){
                throw new RuntimeException("Queue underflow");
            }
            Item item = items[k-1];
            moveToLeft(items, k);
            return item;
        }
        public void moveToLeft(Item[] items, int k){
            while (k < items.length){
                items[k - 1] = items[k];
                k++;
            }
        }
    }

    public static void main(String[] args) {
        GeneralizedQueueLinkedList<Integer> generalizedQueueLinkedList = new GeneralizedQueueLinkedList<>();
        generalizedQueueLinkedList.insert(0);
        generalizedQueueLinkedList.insert(1);
        generalizedQueueLinkedList.insert(2);
        generalizedQueueLinkedList.insert(3);
        generalizedQueueLinkedList.insert(4);
        StdOut.println("Delete the second element: " + generalizedQueueLinkedList.delete(2) + " expected 1");
        GeneralizedQueueArray<Integer> generalizedQueueArray = new GeneralizedQueueArray<>(2);
        generalizedQueueArray.insert(0);
        generalizedQueueArray.insert(1);
        generalizedQueueArray.insert(2);
        generalizedQueueArray.insert(3);
        generalizedQueueArray.insert(4);
        StdOut.println("Delete the second element: " + generalizedQueueArray.delete(2) + " expected 1");

    }
}

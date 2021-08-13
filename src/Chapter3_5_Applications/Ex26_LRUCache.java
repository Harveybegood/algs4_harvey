package Chapter3_5_Applications;

import Chapter1_3_BagsQueuesStacks.Ex31_DoublyLinkedList;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;

/*
*   LRU cache. Create a data structure that supports the following operations: access and remove. The access operation inserts the item onto
*   the data structure if it's not already present. The remove operation deletes and returns the item that was least recently accessed.
*
*   Hint: Maintain the items in order of access in a doubly linked list, along with pointers to the first and last nodes. Use a symbol table
*   with keys = items, values = location in linked list. When you access an element, delete it from the linked list and reinsert it at the
*   beginning. When you remove an element, delete it from the end and remove it from the symbol table.
*
* */
@SuppressWarnings("unchecked")
public class Ex26_LRUCache<Item> {
    private Ex31_DoublyLinkedList doublyLinkedList;

    HashMap<Item, Ex31_DoublyLinkedList.DoubleNode> st;
    public Ex26_LRUCache(){
        doublyLinkedList = new Ex31_DoublyLinkedList();
        st = new HashMap<>();
    }
    public void access(Item item){
        Ex31_DoublyLinkedList.DoubleNode itemInList = st.get(item);
        if (st.containsKey(item)){
            doublyLinkedList.insertAtTheBeginning(item, itemInList);
        }
        Ex31_DoublyLinkedList.DoubleNode newNode = doublyLinkedList.insertAtTheBeginningAndReturnNode(item, itemInList);
        st.put(item, newNode);
    }
    public Item remove(){
        Item tailOfDoublyLinkedList = (Item) doublyLinkedList.removeFromEndAndReturnItem();
        st.remove(tailOfDoublyLinkedList);
        return tailOfDoublyLinkedList;
    }

    public static void main(String[] args) {
        Ex26_LRUCache<String> lruCache = new Ex26_LRUCache<>();
        lruCache.access("A");
        lruCache.access("B");
        lruCache.access("C");
        for (String s : lruCache.st.keySet()){
            StdOut.println(s);
        }
        lruCache.remove();
        for (String s : lruCache.st.keySet()){
            StdOut.println(s);
        }
        lruCache.access("A");
    }
}


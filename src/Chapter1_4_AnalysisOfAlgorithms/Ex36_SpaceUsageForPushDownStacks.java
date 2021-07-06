package Chapter1_4AnalysisOfAlgorithms;
/*
*   Space usage for pushdown stacks. Justify the entries in the table below, which shows typical space usage for various pushdown stack
*   implementations. Use a static nested class for linked-list nodes to avoid the non-static nested class overhead.
*
* */
public class Ex36_SpaceUsageForPushDownStacks {
    /*

    *       data structure      item type           space usage for N int values (bytes)
    *
                                 int               : 16 for stack object + 8 for first Node + 4 int value + 4 padding, and then all multiply N
    *        linked list
    *                            Integer           : 16 for stack object + 8 for first Node + 8 references to inner Integer +
                                                     16 for Integer object + 4 int value + 4 padding, and then all multiply N
    *
                                 int
    *        resizing array
    *                            Integer
    **/

     public class Stack<Item>
    {
        private Node first; // top of stack (most recently added node)
        private int N;
        // number of items
        private class Node
        { // nested class to define nodes
            Item item;
            Node next;
        }
        public boolean isEmpty() {return first == null;}
            public int size(){return N; }

        public void push(Item item)
        { // Add item to top of stack.
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            N++;
        }
        public Item pop()
        { // Remove item from top of stack.
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        }

    // See page 155 for iterator() implementation.
    // See page 147 for test client main().
    }
}

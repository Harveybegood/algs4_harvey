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
                                 int in implementation            : 16 for Node object overhead + 8 for reference to next per Node(including one first Node) + 4 int value + 4 padding, all of them multiply N
                                                                    and Stack object implementation: 16 object overhead + 8 for reference to first Node + 4 int value + 4 padding
                                                                    So ~32N
    *        linked list
    *                            Integer in implementation        : 16 for Node object overhead + 8 for reference to next per Node(including one first node) + 8 for references to Integer +
                                                                    16 for Integer object overhead + 4 int value + 4 padding, and then all multiply N
    *
                                 int in implementation            : by default, 4 for int value, and then multiply N, equals 4N. leave out the object initialization and reference to array and int value for array size
                                                                    and then, the array can be 1/4 to 4/4 full. so ~4N to 16N
    *        resizing array
    *                            Integer in implementation        : by default, 24 for (16 for Integer object overhead, 4 for int value, 4 for padding,) 8 for reference to Integer,
                                                                    and, leave out 16 for stack object, 8 for reference to the array, 4 for array length and 4 for padding,
                                                                    and then array can be 1/4 to 4/4. So ~(24 + 8)N to ~(24 + 32)N. 
    **/

    public void resize(int newSize){
        Item[] items = (Item[]) new Object[newSize];
        for(int i = 0; i < n; i++){
            items[i] = 
        }
        
    }
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

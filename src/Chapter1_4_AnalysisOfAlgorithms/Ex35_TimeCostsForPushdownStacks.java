package Chapter1_4AnalysisOfAlgorithms;

import java.util.Iterator;

/*
*   Time costs for pushdown stacks. Justify the entries in the table below, which shows typical time costs for various pushdown
*   stack implementations, using a cost model that counts both data references(references to data pushed onto the stacks, either
*   an array reference or a reference to an object's instance variable) and objects created.
*
* */
public class Ex35_TimeCostsForPushdownStacks {

    /*                                                  cost to push N int values
    *       data structure      item type       data references         objects created
    *
    *                            int                 2N                      N
    *        linked list
    *                            Integer             3N                     2N
    *
    *                            int                 ~5N                    lgN
    *        resizing array
    *                            Integer             ~5N                    ~N
    *
    *           The time cost is O(1) for each pushdown stack operation       
    
    *        linked list for int:
    *                 Data references: N references for the enclosing Node class,
    *                                  N references for the next Node(including one first Node)
    *
    *                 object:          N nodes object created
    *
    *        linked list for Integer:
    *                 Data references: N references for the enclosing Node class,
    *                                  N references for the next Node(including one first Node)
    *                                  N references for inner class Integer
    *
    *                 object:          N Nodes object created
    *                                  N Integers object created
    *
    *
    *       resizing array for int:
    *                 Data references: by default, N for int value, and due to the resizing, it can be 1/4 to 4/4, hence 4N. meanwhile the stack has an array reference.
                                       And, it has to consider int type for the length of array, it is 4 bytes. the total is ~5N
    *
    *                 object: Initially, it create array with 1, then 2, then 4 ... 2^n. the total is lgN
    *
    *       resizing array for Integer:
    *                 Data references: ditto for int
    *
    *                 object: When the Item is Integer, except for time cost lgN, N for Integer objects created. hence the total is ~N
    *
    *
    * */

    public class ResizingArrayStack<Item> implements Iterable<Item>{
        private Item[] items = (Item[]) new Object[1];
        private int n;
        public boolean isEmpty(){return n == 0;}
        public int size(){return n;}
        private void resize(int max){
            Item[] temp = (Item[]) new Object[max];
            for (int i = 0; i < n; i++){
                temp[i] = items[i];
            }
            items = temp;
        }
        public void push(Item item){
            if (n == items.length){resize(2 * items.length);}
            items[n++] = item;
        }
        public Item pop(){
            Item item = items[--n];
            items[n] = null;
            if (n > 0 && n < items.length / 4){
                resize(items.length / 2);
            }
            return item;
        }
        public Iterator<Item> iterator(){
            return new ReverseArrayIterator();
        }
        private class ReverseArrayIterator implements Iterator<Item>{
            private int i = n;
            public boolean hasNext(){return i > 0;}
            public Item next(){
                return items[--i];
            }
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

    public static void main(String[] args) {

    }
}

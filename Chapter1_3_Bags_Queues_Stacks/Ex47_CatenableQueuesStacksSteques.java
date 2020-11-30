package Chapter1_3_Bags_Queues_Stacks;


/*
*   Catenable queues, stacks, or steques. Add an extra operation catenation that(destructively)concatenates two queues,
*   stacks, aor steques(see exercise3.1.32). Hint: Use a circular linked list, maintaining a pointer to the last time.
* 
*   steque: A stack-ended queue or steque is a data type that supports push, pop, and
    enqueue. Articulate an API for this ADT. Develop a linked-list-based implementation
* */
public class Ex47_CatenableQueuesStacksSteques {
    public class steque<Item>{
        private class Node{
           Node next;
           Node prev;
           Item item;
           //public Node(){}
        }
        private Node first, last;
        private int size;

        private steque(){
            first = null;
            last = null;
            size = 0;
        }

        private boolean isEmpty(){return size == 0;}
        private int size(){return size;}
        /* public Item pop(){}*/
        public void push(Item item){
           Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            size++;
        }
        public void enqueue(Item item){
           Node oldLast = last;
            last = new Node();
            last.item = item;
            last.prev = oldLast;
            size++;
        }
    }
    private class Queue<Item>{
        private class Node{
           Node next;
           Item item;
           Node prev;
        }
        private Node first, last;
        private int size;
        public boolean isEmpty(){return size == 0;}
        public int size(){return size;}
        private Queue(){
            first = null;
            last = null;
            size = 0;
        }
        public void enqueue(Item item){
           Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            size++;
        }
    }
    private class Stack<Item>{
        private class Node{
           Node next, prev;
            Item item;
        }
        private Node first,last;
        private int size;
        public boolean isEmpty(){return first == null;}
        public int size(){return size;}
        /* public Stack(){
             first = null;
             last = null;
             size = 0;
         }*/
        public void push(Item item){
           Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            size++;
        }
    }
    private void queueConcatenate(Queue q1, Queue q2){
        q1.first.next = q2.first.prev;
    }
    private void stackConcatenate(Stack s1, Stack s2){
        s1.first.next = s2.last.prev;
    }
    private void stequeConcatenate(steque st1, steque st2){
        st1.first.next = st2.last.prev;
    }
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        Ex47_CatenableQueuesStacksSteques ex47 = new Ex47_CatenableQueuesStacksSteques();
        Stack<Integer> stack1 = ex47.new Stack<>();
        Stack<Integer> stack2 = ex47.new Stack<>();
        for (int i = 0; i < 3; i++){
            stack1.push(i);
        }
        for (int i = 0; i < 3; i++){
            stack2.push(i);
        }
        ex47.stackConcatenate(stack1,stack2);

        Queue<Integer> queue1 = ex47.new Queue<>();
        Queue<Integer> queue2 = ex47.new Queue<>();
        for (int i = 0; i < 3; i++){
            queue1.enqueue(i);
        }
        for (int i = 0; i < 3; i++){
            queue2.enqueue(i);
        }
        ex47.queueConcatenate(queue1, queue2);

        steque<Integer> steque1 = ex47.new steque<>();
        steque<Integer> steque2 = ex47.new steque<>();

        for (int i = 0; i < 3; i++){
            steque1.push(i);
            steque1.enqueue(i);
        }
        for (int i = 0; i < 3; i++){
            steque2.push(i);
            steque2.enqueue(i);
        }

        ex47.stequeConcatenate(steque1, steque2);
    }
}

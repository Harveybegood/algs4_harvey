package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.StdOut;


/*
*   Develop a class ResizingArrayQueueOfStrings that implements the queue abstraction with a fixed-size array, and
*   then extend your implementation to use array resizing to remove the size restriction.
*
* */
public class Ex1_3_14 {
    public class ResizingArrayQueueOfStringWithFixedSizeArray{
        private int i = 0;
        private int size;
        private String[] queue;
        public ResizingArrayQueueOfStringWithFixedSizeArray(int n){
            queue = new String[n];
            size = 0;
        }
        public boolean isEmpty(){return size == 0;}
        public int size0fQueue(){return size;}
        public void enqueue(String s){
            if (size0fQueue() == queue.length){
                throw new RuntimeException("Queue is overflow. ");
            }
            queue[size] = s;
            size++;
        }
        public String dequeue(){
            if (isEmpty()){
                throw new RuntimeException("Queue is underflow. ");
            }
            size--;
            String s = queue[i];
            queue[i] = "";
            i++;
            return s;
        }
    }
    public class ResizingArrayQueueOfString{
        private int i = 0;
        private int size;
        private String[] queue;
        public ResizingArrayQueueOfString(){
            queue = new String[1];
        }
        public boolean isEmpty(){return size == 0;}
        public int sizeOfQueue(){return size;}
        public void resize(int newSize){
            String[] s = new String[newSize];
            for (int i = 0; i < size; i++){
                s[i] = queue[i];
            }
            queue = s;
        }
        public void enqueue(String s){
            if (sizeOfQueue() == queue.length / 2){
                resize(2 * queue.length);
            }
            queue[size] = s;
            size++;
        }
        public String dequeue(){
            if (isEmpty()){
                throw new RuntimeException("Queue is underflow. ");
            }
            if (sizeOfQueue() > 0 && sizeOfQueue() == queue.length / 4){
                resize(queue.length / 2);
            }
            size--;
            String s = queue[i];
            queue[i] = "";
            i++;
            return s;
        }
    }


    public static void main(String[] args) {
        Ex1_3_14 ex1_3_14 = new Ex1_3_14();
        ResizingArrayQueueOfStringWithFixedSizeArray fixedSizeArray = ex1_3_14.new
                ResizingArrayQueueOfStringWithFixedSizeArray(5);
       fixedSizeArray.enqueue("a");
       fixedSizeArray.enqueue("b");
       fixedSizeArray.enqueue("c");
       fixedSizeArray.enqueue("d");
       fixedSizeArray.enqueue("e");
       StdOut.println("Expected a: " + fixedSizeArray.dequeue() + " - Size remained: 4: " + fixedSizeArray.size0fQueue());
       StdOut.println("Expected b: " + fixedSizeArray.dequeue() + " - Size remained: 3: " + fixedSizeArray.size0fQueue());
       StdOut.println("Expected c: " + fixedSizeArray.dequeue() + " - Size remained: 2: " + fixedSizeArray.size0fQueue());

       ResizingArrayQueueOfString nonFixedSizeArray = ex1_3_14.new ResizingArrayQueueOfString();
       nonFixedSizeArray.enqueue("A");
       nonFixedSizeArray.enqueue("B");
       nonFixedSizeArray.enqueue("C");
       nonFixedSizeArray.enqueue("D");
       nonFixedSizeArray.enqueue("E");
       StdOut.println("Expected A: " + nonFixedSizeArray.dequeue() + " - Size remained: 4: " + nonFixedSizeArray.sizeOfQueue());
       StdOut.println("Expected B: " + nonFixedSizeArray.dequeue() + " - Size remained: 3: " + nonFixedSizeArray.sizeOfQueue());
       StdOut.println("Expected C: " + nonFixedSizeArray.dequeue() + " - Size remained: 2: " + nonFixedSizeArray.sizeOfQueue());
    }

}

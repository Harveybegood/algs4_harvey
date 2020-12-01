package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Resizing arrays versus linked lists. Run experiments to validate the hypothesis that resizing arrays are faster than
*   linked lists for stacks(see exercise 1.4.35 and exercise 1.4.36). Do so by developing a version of DoublingRatio
*   that computes the ratio of the running times of the two programs.
*
* */
public class Ex43 {

    @SuppressWarnings("unchecked")
    static class stackForArrays<Item>{
        private Item[] array = (Item[]) new Object[1];
        private int N;

        public boolean isEmpty(){return N == 0;}
        public int size(){return N;}
        private void resize(int newSize){
            Item[] temp = (Item[]) new Object[newSize];
            for (int i = 0; i < N; i++){
                temp[i] = array[i];
            }
            array = temp;
        }
        public void push(Item item){
            if (N > array.length){
                throw new RuntimeException("an array of Chapter1_3_Bags_Queues_Stacks.stack is overflow");
            }
            if (N == array.length){
                resize(2 * array.length);
            }
            array[N++] = item;
        }
        public Item pop(){
            if (isEmpty()) throw new RuntimeException("an array of Chapter1_3_Bags_Queues_Stacks.stack is underflow");
            Item item = array[--N];
            array[N] = null;
            if (N > 0 && N == array.length / 4) resize(array.length/2);
            return item;
        }
    }

    @SuppressWarnings("unchecked")
    static class stackForLinkedList<Item>{
        private class Node{
            Item item;
            Node next;
        }
        private Node first;
        //private Node last;
        private int N;
        public boolean isEmpty(){return first == null;}
        public int size(){return N;}
        public void push(Item item){
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            N++;
        }
        public Item pop(){
            if (isEmpty()) throw new RuntimeException("Stack Underflow");
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        }
    }

    static class DoublingRatio{
        stackForArrays<Integer> stackArray = new stackForArrays<>();
        stackForLinkedList<Integer> stackLinkedList = new stackForLinkedList<>();
        public double DoublingTest1(){
            Stopwatch timer1 = new Stopwatch();
            for (int i = 0; i < 10; i++){
                stackArray.push(1);
                //StdOut.println(String.format("%5d\n", stackArray));
                StdOut.println(stackArray.size());
                stackArray.pop();
            }
            return timer1.elapsedTime();
        }
        public double DoublingTest2(){
            Stopwatch timer2 = new Stopwatch();
            for (int i = 0; i < 10; i++){
                stackLinkedList.push(1);
                //StdOut.println(String.format("%5d\n", stackLinkedList));
                StdOut.println(stackLinkedList.size());
                stackLinkedList.pop();
            }
            return timer2.elapsedTime();
        }
    }

    public static void main(String[] args) {
        DoublingRatio doublingRatio = new DoublingRatio();
        for (int i = 0; i < 10; i++){
            StdOut.printf("%5.1f /%5.1f\n", doublingRatio.DoublingTest1(), doublingRatio.DoublingTest2());
        }
    }
}

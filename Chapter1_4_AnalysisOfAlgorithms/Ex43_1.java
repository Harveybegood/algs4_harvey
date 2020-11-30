package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.LinkedList;
import java.util.List;

/*
*   Resizing arrays versus linked lists. Run experiments to validate the hypothesis that resizing arrays are faster than linked
*   lists for stacks (see exercise1.4.35 and exercise 1.4.36). Do so by developing a version of DoublingRatio that computes
*   the ratio of the running times of the two programs.
*
* */
public class Ex43_1<Item> {
    private interface Stack<Item>{
        boolean isEmpty();
        int size();
        void push(Item item);
        Item pop();
    }
    private class Node{
        Item item;
        Node next;
    }
    private class StackWithLinkedList implements Stack<Item>{
        private Node first;
        private int size;
        public boolean isEmpty(){return first == null;}
        public int size(){return size;}
        public void push(Item item){
            first = new Node();
            Node oldFirst = first;
            first.item = item;
            first.next = oldFirst;
            size++;
        }
        public Item pop(){
            if (isEmpty()) throw new RuntimeException("Stack underflow");
            Item item = first.item;
            first = first.next;
            //first.item = null;
            size--;
            return item;
        }
    }
    @SuppressWarnings("unchecked")
    private class StackWithResizingArray implements Stack<Item>{
        private int N;
        private Item[] StackArray;
        public StackWithResizingArray(int initialSize){
            StackArray = (Item[]) new Object[initialSize];
            N = 0;
        }
        public void resizingArraySize(int newSize){
            Item[] newArray = (Item[]) new Object[newSize];
            for (int i = 0; i < N; i++){
                newArray[i] = StackArray[i];
            }
            StackArray = newArray;
        }
        public boolean isEmpty(){return N == 0;}
        public int size(){return N;}
        public void push(Item item){
            if (size() == StackArray.length / 2) resizingArraySize(2 * StackArray.length);
            StackArray[N++] = item;
        }

        public Item pop(){
            if (isEmpty()) throw new RuntimeException("Stack underflow");
            Item item = StackArray[N -1];
            StackArray[N - 1] = null;
            N--;
            if (N > 0 && N == StackArray.length / 4) resizingArraySize(StackArray.length / 2);
            return item;
        }
    }

    private final int INITIAL_NUMBER_OF_OPERATIONS = 524288;
    private final int FINAL_NUMBER_OF_OPERATIONS = 67108864;

    private List<Double> runExperiments(Stack<Integer> stack){
       /* final int INITIAL_NUMBER_OF_OPERATIONS = 524288;
        final int FINAL_NUMBER_OF_OPERATIONS = 67108864;*/
        List<Double> runningTimes = new LinkedList<>();
        for (int n = INITIAL_NUMBER_OF_OPERATIONS; n < FINAL_NUMBER_OF_OPERATIONS; n++){
            double runningTime = timeTrial(n, stack);
            runningTimes.add(runningTime);
        }
        return runningTimes;
    }
    private double timeTrial(int n, Stack<Integer> stack){
        int max = 1000000;
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++){
            numbers[i] = StdRandom.uniform(-max, max);
        }
        Stopwatch timer = new Stopwatch();
        for (int number : numbers) stack.push(number);
        while (!stack.isEmpty()) stack.pop();
        return timer.elapsedTime();
    }
    private void printResult(double[][] runningTimes){
        StdOut.printf("%13s %7s %6s %9s\n", "N operation", "Array", "List", "Ratio");
        int numberOfOperations = INITIAL_NUMBER_OF_OPERATIONS;
        for (int i = 0; i < runningTimes.length; i++){
            StdOut.printf("%13d %7.1f %6.1f", numberOfOperations, runningTimes[i][0], runningTimes[i][1]);
            StdOut.printf("%9.1f\n", runningTimes[i][0] / runningTimes[i][1]);
            numberOfOperations *= 2;
        }
    }

    public static void main(String[] args) {
        Ex43_1<Integer> ex43_1 = new Ex43_1<>();
        // Resizing array Chapter1_3_Bags_Queues_Stacks.stack
        Stack<Integer> resizingArrayStack = ex43_1.new StackWithResizingArray(10);
        List<Double> resizingArrayRunningTimes = ex43_1.runExperiments(resizingArrayStack);
        // Linked list Chapter1_3_Bags_Queues_Stacks.stack
        Stack<Integer> linkedListStack = ex43_1.new StackWithLinkedList();
        List<Double> linkedListRunningTimes = ex43_1.runExperiments(linkedListStack);
        double[][] runningTimes = new double[resizingArrayRunningTimes.size()][2];
        for (int i = 0; i < resizingArrayRunningTimes.size(); i++){
            runningTimes[i][0] = resizingArrayRunningTimes.get(i);
        }
        for (int i = 0; i < linkedListRunningTimes.size(); i++){
            runningTimes[i][1] = linkedListRunningTimes.get(i);
        }
        ex43_1.printResult(runningTimes);

    }
}

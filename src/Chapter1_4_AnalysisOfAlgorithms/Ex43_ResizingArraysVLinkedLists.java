package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Resizing arrays versus linked lists. Run experiments to validate the hypothesis that resizing arrays are faster than
*   linked lists for stacks(see ex1.4.35 and ex1.4.36). Do so by developing a version of DoublingRatio that computes the
*   ratio of the running times of the two programs.
*
* */
@SuppressWarnings("unchecked")
public class Ex43_ResizingArraysVLinkedLists {
    public static class ResizingArraysForStacks<Item>{
        Item[] items;
        int n;
        public ResizingArraysForStacks(){
            items = (Item[]) new Object[1];
        }
        public boolean isEmpty(){return n == 0;}
        public int size(){return n;}
        public void push(Item item){
            if (n == items.length / 2){
                resize(2 * items.length);
            }
            items[n++] = item;
        }
        public Item pop(){
            Item item = items[n];
            items[n] = null;
            n--;
            return item;
        }
        public void resize(int newSize){
            Item[] temp = (Item[]) new Object[newSize];
            for (int i = 0; i < n; i++){
                temp[i] = items[i];
            }
            items = temp;
        }
    }
    public static class LinkedListForStacks<Item>{
        public class Node{
            private Item item;
            private Node next;
            public Node(Item item, Node next){
                this.next = next;
                this.item = item;
            }
        }
        private Node first;
        private int n;
        public boolean isEmpty(){return first == null;}
        public int size(){return n;}
        public void push(Item item){
            Node oldFirst = first;
            first = new Node(item, first);
            first.next = oldFirst;
            n++;
        }
        public Item pop(){
            Item item = first.item;
            first = first.next;
            n--;
            return item;
        }
    }
    public enum ArraysVersusLinkedLists{
        ResizingArrays, LinkedLists
    }
    public double timeTrial(int N, String resizingArraysVLinkedLists){
        if (ArraysVersusLinkedLists.ResizingArrays.equals(ArraysVersusLinkedLists.valueOf(resizingArraysVLinkedLists))){
            int random = StdRandom.uniform(Integer.MAX_VALUE);
            ResizingArraysForStacks<Integer> resizingArraysForStacks = new ResizingArraysForStacks<>();
            Stopwatch timer = new Stopwatch();
            for (int i = 0; i < N; i++){
                resizingArraysForStacks.push(random);
            }
            return timer.elapsedTime();
        }
        if (ArraysVersusLinkedLists.LinkedLists.equals(ArraysVersusLinkedLists.valueOf(resizingArraysVLinkedLists))){
            int random = StdRandom.uniform(Integer.MAX_VALUE);
            LinkedListForStacks<Integer> linkedListForStacks = new LinkedListForStacks<>();
            Stopwatch timer = new Stopwatch();
            for (int i = 0; i < N; i++){
                linkedListForStacks.push(random);
            }
            return timer.elapsedTime();
        }
        return 1.0;
    }
    public void doublingRatio(String resizingArraysVLinkedLists){
        if (ArraysVersusLinkedLists.ResizingArrays.equals(ArraysVersusLinkedLists.valueOf(resizingArraysVLinkedLists))){
            double prev = timeTrial(125, resizingArraysVLinkedLists);
            for (int N = 250; N < 20000001; N += N){
                double time = timeTrial(N, resizingArraysVLinkedLists);
                StdOut.printf("%7d %5.1f % 5.1f\n", N, time, time / prev);
                prev = time;
            }
        }
        if (ArraysVersusLinkedLists.LinkedLists.equals(ArraysVersusLinkedLists.valueOf(resizingArraysVLinkedLists))){
            double prev = timeTrial(125, resizingArraysVLinkedLists);
            for (int N = 250; N < 20000001; N += N){
                double time = timeTrial(N, resizingArraysVLinkedLists);
                StdOut.printf("%7d %5.2f %5.2f\n", N, time, time / prev);
                prev = time;
            }
        }
    }
    public static void main(String[] args) {
        Ex43_ResizingArraysVLinkedLists resizingArraysVLinkedLists = new Ex43_ResizingArraysVLinkedLists();
        StdOut.println("Double ratio for resizing arrays");
        resizingArraysVLinkedLists.doublingRatio("ResizingArrays");
        StdOut.println("Double ratio for linked lists");
        resizingArraysVLinkedLists.doublingRatio("LinkedLists");
    }
}

/*
*           Double ratio for resizing arrays
                250   0.0  Infinity
                500   0.0   1.0
               1000   0.0   0.0
               2000   0.0   NaN
               4000   0.0   NaN
               8000   0.0  Infinity
              16000   0.0   3.0
              32000   0.0   2.0
              64000   0.0   1.3
             128000   0.0   1.6
             256000   0.0   1.8
             512000   0.1   4.3
            1024000   0.1   1.2
            2048000   0.3   2.2
            4096000   0.5   1.8
            8192000   0.8   1.7
            16384000   1.4   1.8
            Double ratio for linked lists
                250  0.00 Infinity
                500  0.00  0.00
               1000  0.00   NaN
               2000  0.00   NaN
               4000  0.00 Infinity
               8000  0.00  1.00
              16000  0.00  1.00
              32000  0.00  3.00
              64000  0.01  2.00
             128000  0.01  1.33
             256000  0.01  1.50
             512000  0.02  1.67
            1024000  0.25 12.30
            2048000  0.48  1.96
            4096000  1.10  2.28
            8192000  2.03  1.84
            16384000  5.47  2.70
*
*
* */

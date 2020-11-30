package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Autoboxing performance penalty. Run experiments to determine the performance penalty on your machine for using
*   autoboxing and auto-unboxing. Develop an implementation FixedCapacityStackOfInts and use a client such as
*   DoublingRatio to compare its performance with the generic FixedCapacityStack<Integer>, for a large number of
*   push() and pop() operations.
*
* */
public class Ex37{
    public class FixedCapacityStackOfInts{
        private int[] s;
        private int N;
        public FixedCapacityStackOfInts(int cap){
            s = new int[cap];
        }
        public boolean isEmpty(){return N == 0;}
        public int size(){return N;}
        public void push(int item){
            s[N++] = item;
        }
        public int pop(){
            return s[--N];
        }
    }
    @SuppressWarnings("unchecked")
    public class FixedCapacityStack<Integer>{
        private Integer[] s;
        private int N;
        public FixedCapacityStack(int cap){
            s = (Integer[]) new Object[cap];
        }
        public boolean isEmpty(){return N == 0;}
        public int size(){return N;}
        public void push(Integer item){
            s[N++] = item;
        }
        public Integer pop(){
            return s[--N];
        }
    }

  /*  public static double DoublingRatio(int N){

        Stopwatch timer1 = new Stopwatch();
        Ex37 ex37 = new Ex37();
        FixedCapacityStackOfInts FixedInts = ex37.new FixedCapacityStackOfInts(N);
        //FixedInts.push();
        for (int i = 100; i < N; i += i){
            FixedInts.push(i);
            FixedInts.pop();
        }
        double timeCostInts = timer1.elapsedTime();

        Stopwatch timer2 = new Stopwatch();
        FixedCapacityStack stackInteger = ex37.new FixedCapacityStack(N);

        for (int i = 100; i < N; i += i){
            stackInteger.push(i);
            stackInteger.pop();
        }
        double timeCostInteger = timer2.elapsedTime();
        return timeCostInts / timeCostInteger;
        //StdOut.printf("The performance penalty comparing to primitive and autoboxing: %5.1f \n" + timeCostInts / timeCostInteger);
    }*/

    public static void main(String[] args) {
        int N = 1000000;
        Stopwatch timer1 = new Stopwatch();
        Ex37 ex37 = new Ex37();
        FixedCapacityStackOfInts FixedInts = ex37.new FixedCapacityStackOfInts(N);
        //FixedInts.push();
        for (int i = 1; i < N; i++){
            FixedInts.push(i);
            FixedInts.pop();
        }
        double timeCostInts = timer1.elapsedTime();

        Stopwatch timer2 = new Stopwatch();
        FixedCapacityStack stackInteger = ex37.new FixedCapacityStack(N);

        for (int i = 1; i < N; i++){
            stackInteger.push(i);
            stackInteger.pop();
        }
        double timeCostInteger = timer2.elapsedTime();
        //return timeCostInts / timeCostInteger;
        for (int i = 0; i < 10; i++){

            StdOut.printf("performance penalty int/Integer %5.1f", +timeCostInts/timeCostInteger);

            StdOut.printf("Integer/ints %5.1f\n", + timeCostInteger/timeCostInts);
        }

    }
}

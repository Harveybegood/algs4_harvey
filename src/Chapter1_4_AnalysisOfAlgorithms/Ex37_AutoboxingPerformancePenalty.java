package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Autoboxing performance penalty. Run experiments to determine the performance penalty on your machine for using autoboxing and
*   auto-unboxing. Develop an implementation FixedCapacityStackOfInts and use a client such as DoublingRatio to compare its performance
*   with the generic FixedCapacityStack<Integer>, for a large number of push() and pop() operations.
*
* */
@SuppressWarnings("unchecked")
public class Ex37_AutoboxingPerformancePenalty {
    public static class FixedCapacityStackOfInts{
        private int[] a;
        private int n;
        public FixedCapacityStackOfInts(int cap){
            a = new int[cap];
        }
        public boolean isEmpty(){return n == 0;}
        public int size(){return n;}
        public void push(int item){
            a[n++] = item;
        }
        public int pop(){
            if (isEmpty()){throw new RuntimeException("Stack underflow");}
            return a[--n];
        }
    }
    public static class FixedCapacityStack<Item>{
        private Item[] a;
        private int n;
        public FixedCapacityStack(int cap){
            a = (Item[]) new Object[cap];
        }
        public boolean isEmpty(){return n == 0;}
        public int size(){return n;}
        public void push(Item item){
            a[n++] = item;
        }
        public Item pop(){
            if (isEmpty()){throw new RuntimeException("Stack underflow");}
            return a[--n];
        }
    }
    public double timeTrialForInt(int cap){
        Stopwatch stopwatch = new Stopwatch();
        FixedCapacityStackOfInts fixedCapacityStackOfInts = new FixedCapacityStackOfInts(cap);
        for (int i = 0; i < cap; i++){
            fixedCapacityStackOfInts.push(i);
            fixedCapacityStackOfInts.pop();
        }
        return stopwatch.elapsedTime();
    }
    public double timeTrialForInteger(int cap){
        Stopwatch stopwatch = new Stopwatch();
        FixedCapacityStack<Integer> fixedCapacityStack = new FixedCapacityStack<>(cap);
        for (int i = 0; i < cap; i++){
            fixedCapacityStack.push(i);
            fixedCapacityStack.pop();
        }
        return stopwatch.elapsedTime();
    }
    public void doublingRatioForInt(){
        double prev = timeTrialForInt(125);
        for (int N = 250; N < 262144001 ; N += N){
            double time = timeTrialForInteger(N);
            StdOut.printf("%10d %7.1f %5.1f\n", N, time, time / prev);
            prev = time;
        }
    }
    public void doublingRatioForInteger(){
        double prev = timeTrialForInteger(125);
        for (int N = 250; N < 262144001 ; N += N){
            double time = timeTrialForInteger(N);
            StdOut.printf("%10d %7.1f %5.1f\n", N, time, time / prev);
            prev = time;
        }
    }

    public static void main(String[] args) {
        Ex37_AutoboxingPerformancePenalty autoboxingPerformancePenalty = new Ex37_AutoboxingPerformancePenalty();
        StdOut.println("Performance for FixedCapacityStackOfInts");
        autoboxingPerformancePenalty.doublingRatioForInt();
        StdOut.println("Performance for FixedCapacityStackInteger");
        autoboxingPerformancePenalty.doublingRatioForInteger();
    }
}

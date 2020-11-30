package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/*
*   Binary search with only addition and subtraction. [Mihai Patrascu] Write a program that, given an array of N
*   distinct int values in ascending order, determines whether a given integer is in the array. You may use only
*   additions and subtractions and a constant amount of extra memory. The running time of your program should be
*   proportional to log N in the worst case.
*
*   Answer: Instead of searching based on powers of two(binary search), use Fibonacci numbers(which also grow
*   exponentially). Maintain the current search range to be the interval[i, i + F k] and keep F k and F k-1 in
*   two variables. At each step compute F k-2 via subtraction, check element i + F k-2, and update the current
*   range to either[i, i + F k-2] or [i + F k-2, i + F k-2 + F k-1].
*
* */
public class Ex22 {
    static class FibonacciSearch{
        // check if addition operation is overflow
        public static boolean isOverFlow(int x, int y){
            long r = x + y;
            return ((x^r) & (y^r)) < 0;
        }
        // compute the Kth of Fibonacci list
        public static int fibonacci(int k){
            int previous = 1, next = 1, kk =k;
            while (--k > 0){
                if (isOverFlow(next, previous))
                    StdOut.printf("k = %d previous = %d overflowing\n", kk, next, previous);
                next = next + previous;
                previous = next - previous;
            }
            return next;
        }
        public static final int[] fibonacci;
        static {
            fibonacci = new int[46];
            for (int i = 0; i < fibonacci.length; i++) fibonacci[i] = fibonacci(i);
        }
        // search ..
        public static int splitBetween(int low, int high){
            int fibonacciIndex = 0;
            while (fibonacci[fibonacciIndex] <= (high - low)) fibonacciIndex++;
            return fibonacci[--fibonacciIndex] + low - 1;
        }
        public static int rank(int key, int[] array){
            int low = 0, high = array.length - 1, split = 0;
            while (low < high){
                split = splitBetween(low,high);
                if (array[split] < key) low = split + 1;
                else if (array[split] > key) high = split;
                else return split;
            }
            return -1;
        }
        //generate sorted random array
        public static int[] sourceArray(int N){
            int[] array = new int[N];
            for (int i = 0; i < N; i++){
                array[i] = StdRandom.uniform(0,100);
            }
            Arrays.sort(array);
            return array;
        }
        // design the desired formatted array
        public static void printArray(int[] array){
            for (int i = 0; i < array.length; i++){
                if ((i + 1)%10 == 0)
                    StdOut.printf("%2d\n", array[i]);
                else
                    StdOut.printf("%2d", array[i]);
            }
            StdOut.println();
        }

        public static void main(String[] args) {
            int[] array = sourceArray(10);
            printArray(array);
            StdOut.println("The result using Fibonacci: " + FibonacciSearch.rank(4, array));
        }
    }
}

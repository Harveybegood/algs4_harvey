package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*  Floyd's method. Implement a version of heapsort based on Floyd's sink-to-the-bottom-and-swim idea, as described in the
*  text. Count the number of compares used by your program and the number of compares used by standard implementation, for
*  randomly ordered distinct keys with N = 10^3, 10^6, and 10^9.
*
*   https://alg4.ikesnowy.com/2-4-40/
*
* */
@SuppressWarnings("unchecked")
public class Ex40_FloydMethod {
    private static int countOfCompares;
    public static int heapsortStandard(Comparable[] array){
        countOfCompares = 0;
        int n = array.length - 1;
        for (int k = n/2; k >= 1; k--){
            sink(array, k, n);
        }
        while (n > 1){
            exchange(array, 1, n--);
            sink(array, 1, n);
        }
        return countOfCompares;
    }
    public static void sink(Comparable[] array, int k, int N){
        while (k * 2 <= N){
            int j = 2 * k;
            if (j < N && less(array, j, j + 1)){
                j++;
            }
            if (!(less(array, k, j))){
                break;
            }
            exchange(array, k, j);
            k = j;
        }
    }
    public static void exchange(Comparable[] array, int k, int N){
        Comparable temp = array[k];
        array[k] = array[N];
        array[N] = temp;
    }
    public static boolean less(Comparable[] array ,int v, int w){
        countOfCompares++;
        return array[v].compareTo(array[w]) < 0;
    }
    public static int heapSortFloyd(Comparable[] array){
        countOfCompares = 0;
        int n = array.length - 1;
        for (int k = n / 2; k >= 1; k--){
            sinkFloyd(array, k, n);
        }
        while (n > 1){
            exchange(array, 1, n--);
            sinkFloyd(array, 1, n);
        }
        return countOfCompares;
    }
    public static void sinkFloyd(Comparable[] array, int k, int N){
        // key idea: Avoid to check for whether the reinserted element is reached to its position. of which it means we
        // can save the compares bound to the line code of "if(!less(array, k, j))". instead that, we promote the larger
        // of the two children until the bottom is reached. then use swim() to move the larger back to proper position.
        while (k * 2 <= N){
            int j = k * 2;
            if (j < N && less(array, j, j+1)){
                j++;
            }
            exchange(array, k, j);
            k = j;
        }
        swim(array, k);
    }
    public static void swim(Comparable[] array, int k){
        while (k > 1){
            if (less(array, k / 2, k)){
                exchange(array, k, k / 2);
            }
            k = k / 2;
        }
    }

    public static void main(String[] args) {
        StdOut.println("Random Data   CountOfStandard   CountOfFloyd   Ratio");
        //Ex40_FloydMethod floydMethod = new Ex40_FloydMethod();
        Comparable[] array;
        for (int n = 3; n <= 9; n += 3){
            int N = (int) Math.pow(10, n);
            array = new Comparable[N + 1];
            for (int i = 1; i <= N; i++){
                array[i] = StdRandom.uniform(1, N);
            }
            double Ratio = heapsortStandard(array) / heapSortFloyd(array);
            StdOut.printf("%5d %15d %15d %12.1f\n", N, heapsortStandard(array), heapSortFloyd(array), Ratio);
        }
    }
}

package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/*
*   Modify binary search so that it always returns the element with the smallest index that matches
*   the search element(and still guarantees the logarithmic running time)
*
* */
public class Ex10_1 {
    public static int modifiedBinarySearch(int key, int[] array){
        int lo = 0, hi = array.length - 1, mid;
        while (lo < hi){
            mid = (int)Math.ceil((lo + hi)/2.0);
            if (array[mid] > key) hi = mid - 1;
            else lo = mid;
        }
        if (array[hi] == key) return hi;
        return ++hi == array.length || array[hi] != key ? -1 : hi;
    }
    public static int[] sourceArray(int N){
        int[] array = new int[N];
        for (int i = 0; i < N; i++){
            array[i] = StdRandom.uniform(0, 10);
        }
        Arrays.sort(array);
        return array;
    }
    public static void printArray(int[] array){
        for (int i = 0; i < array.length;i++){
            if ((i + 1) % 10 == 0) StdOut.print(array[i] + "\n");
            else StdOut.print(array[i] + " ");
        }
        StdOut.println();
    }
    public static void test(int key, int N){
        int[] array = sourceArray(N);
        printArray(array);
        StdOut.println("Minimum index of: " + key + " is " + modifiedBinarySearch(key,array));
    }

    public static void main(String[] args) {
        Ex10_1.test(3,100);
    }
}

package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
*   Write a static method histogram() that takes an array a[] of int values and an integer M as arguments and
*   returns an array of length M whose ith entry is the number of times the integer i appeared in the argument
*   array. If the values in a[] are all between 0 and M - 1, the sum of the values in the returned array should
*   be equal to a.length.
*
* */
public class Ex15 {
    public static void main(String[] args) {
        int[] a = {11,  11,  13,  4,  5,  6,  7,  8,  9,  5,  1,  17,  3,  2,  1,  7,  7,  8,  8};
        int M = 19;
        StdOut.println();
        //histogram(a, M);
        //StdOut.println(a.length);
        StdOut.print(Arrays.toString(histogram(a, M)));
    }

    private static int[] histogram(int[] a, int M){
        int[] array = new int[M];
        insertion(a);
        StdOut.print(Arrays.toString(a));
        StdOut.println();
        for (int i = 0; i < M; i++){
            int c = 0;
            while (i + 1 < a.length && a[i] == a[i + 1]){
                c++;
                i++;
            }
            array[i] = c + 1;
        }
        //insertion(array);
        return array;
    }
    private static void insertion(int[] a){
        int n = a.length;
        for (int i = 1; i < n; i++){
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--){
                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        }
    }
}

package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/*
*   Certification. Write a check() method that calls sort() for a given array and returns true if sort() puts the array
*   in order and leaves the same set of objects in the array as were there initially, false otherwise. Do not assume
*   that sort() is restricted to move data only with exch(). You may use Arrays.sort() and assume that it is correct.
*
* */
@SuppressWarnings("unchecked")
public class Ex16_Certification {
    static int n = 100;
    static Comparable[] array = new Comparable[n];
    public static void main(String[] args) {
        array = generateArray(n);
        Arrays.sort(array);
        StdOut.println(check(array));
    }

    // leave out the check of "if the same set of objects"
    public static boolean check(Comparable[] a){
        for (int i = 1; i < a.length; i++){
            if (a[i].compareTo(a[i-1]) < 0) return false;
        }
        return true;
    }
    public static Comparable[] generateArray(int n){
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(n);
        }
        return array;
    }
}

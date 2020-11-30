package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/*
*   Equal keys. Add to BinarySearch a static method rank() that takes a key and a sorted array of int values (some of
*   which may be equal) as arguments and returns the number of elements that are smaller than the key and a similar
*   method count() that returns the number of elements equal to the key. Note: If i and j are the values returned by
*   rank(key, a) and count(key, a) respectively, then a[i..i+j-1] are the values in the array that are equal to key.
*
* */
public class Ex29 {
    public static void main(String[] args) {
        //int mid = 0;
        //Ex29 ex29 = new Ex29();
        int[] array = new int[30];
        int key = 8;
        for (int i = 0; i < 30; i++){
            array[i] = StdRandom.uniform(2,12);
        }
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++){
            StdOut.printf(array[i] + " ");
        }
        StdOut.println();
        int mid = count(key, array);
        int mid_rank = rank(key, array);
        if (mid_rank != -1){
            StdOut.println("Smaller than key: " + mid_rank);
        }
        int equal = 0;
        if (mid != -1){
            while (mid <= array.length && array[mid] == key){
                equal++;
                mid++;
            }
            while (array[--mid] == key && mid >= 0){
                equal++;
                //--mid;
            }
            StdOut.println("Equal key: " + equal);
        }
    }
    /* int mid = 0;*/
    public static int rank(int key, int[] array){
        return rank(0, array.length, key, array);
    }
    // return the number of elements that are smaller than the key
    public static int rank(int lo, int hi, int key, int[] array){
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < array[mid]) return rank(lo, mid - 1, key, array);
        else if (key > array[mid]) return rank(mid + 1, hi, key, array);
        else  return mid;
    }
    // return the number of elements that are equal to the key
    public static int count(int key, int[] array){
        return count(0, array.length - 1, key, array);
    }
    public static int count(int lo, int hi, int key, int[] array){
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < array[mid]) return count(lo, mid - 1, key, array);
        else if (key > array[mid]) return count(mid + 1, hi, key, array);
        //mid++;
        else return mid;
    }
}


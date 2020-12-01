package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/*
*   Binary search versus brute-force search. Write a program BruteForceSearch that uses the brute-force search
*   method given on page 48 and compare its running time on your computer with that of BinarySearch for largeW.txt
*   and largeT.txt.
*
* */
public class Ex38 {
    public static void main(String[] args) {
        int[] array = In.readInts(args[0]);
        StdOut.println(runningTime(array, "binary"));
        StdOut.println(runningTime(array, "bruteForce"));
    }
    public static void bruteForceSearch(int[] array){
        Arrays.sort(array);
        while (!StdIn.isEmpty()){
            int key = StdIn.readInt();
            if (bruteForceRank(array, key) == -1){
                //StdOut.println(key);
            }
        }
    }
    public static void binarySearch(int[] array){
        Arrays.sort(array);
        while (!StdIn.isEmpty()){
            int key = StdIn.readInt();
            if (binarySearchRank(array, key) == -1){
                //StdOut.println(key);
            }
        }
    }
    public static int bruteForceRank(int[] array, int key){
        for (int i = 0; i < array.length; i++){
            if (array[i] == key){
                return i;
            }
        }
        return -1;
    }
    public static int binarySearchRank(int[] array, int key){
        // recursive method
       return binarySearchRank(0, array.length - 1, array, key);
    }
    public static int binarySearchRank(int low, int high, int[] array, int key){
        int mid = low + (high - low) / 2;
        while (low < high){
            if (key < array[mid]){
                return binarySearchRank(low, mid - 1, array, key);
            }
            else if (key > array[mid]){
                return binarySearchRank(mid + 1, high, array, key);
            }
            else {
                return mid;
            }
        }
        return -1;
    }
    public static double runningTime(int[] array, String s){
        if (s.equals("bruteForce")){
            Stopwatch time1 = new Stopwatch();
            bruteForceSearch(array);
            return time1.elapsedTime();
        }
        if (s.equals("binary")){
            Stopwatch time2 = new Stopwatch();
            binarySearch(array);
            return time2.elapsedTime();
        }
        return 0;
    }
}

package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
*   the client reads integers from the file names on the command line,
*
* */
public class BinarySearch {
    public static int rank(int key, int[] a){
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi){
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        /*int[] whitelist = new int[100];
        while (!StdIn.isEmpty()){
            whitelist = StdIn.readAllInts();
        }*/
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()){
            int key = StdIn.readInt();
            if (rank(key, whitelist) < 0){
                StdOut.print(key + " ");
            }
        }
        StdOut.println();
    }
}

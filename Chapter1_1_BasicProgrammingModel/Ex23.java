package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
*   Add to the BinarySearch test client the ability to respond to a second argument:
*       + to print numbers from standard input that are not in the whitelist,
*       - to print numbers that are in the whitelist.
*
* */
public class Ex23 {
    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);
        //int[] whitelist = StdIn.readAllInts();
       /* int[] whitelist = new int[args.length];
        for (int i = 0; i < args.length; i++){
            whitelist[i] = Integer.parseInt(args[i]);
        }*/
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()){
            int key = StdIn.readInt();
            if (recRank(key, whitelist) == -1){
                StdOut.println(key + " -");
            }
            else {
                StdOut.println(recRank(key, whitelist) + " +");
            }
        }
    }

    private static int recRank(int key, int[] array){
        return recRank(0, array.length - 1, key, array);
    }
    private static int recRank(int lo, int high, int key, int[] array){
        if (lo > high) return -1;
        int mid = lo + (high - lo) / 2;
        if (key < array[mid])
            return recRank(lo, mid - 1, key, array);
        else if (key > array[mid])
            return recRank(mid + 1, high, key, array);
        else
            return mid;
    }
}

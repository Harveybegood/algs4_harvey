package test;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
*   generates a set of ints
*   search keys contained in that set
*
*   API - public class staticSetOfInts
*
*   : int[] array;
*   : input key which desired to be searched within int[] array
*
*   a instantial stance to search - rank()
*   a instantial stance to check  - contain()
* */
public class staticSetOfInts {
    private int[] a;
    public staticSetOfInts(int[] w){
        a = new int[w.length];
        for (int i = 0; i < w.length - 1; i++){
            a[i] = w[i];
        }
        Arrays.sort(a);
    }

    public int rank(int key){
        int low = 0;
        int high = a.length - 1;
        while (low < high){
            int middle = low + (high - low) / 2;
            if (a[middle] < key) low = middle + 1;
            else if (a[middle] > key) high = middle - 1;
            else return middle;
        }
        return -1;
    }

    public boolean contain(int key){
        return rank(key) != -1;
    }

    public static void main(String[] args) {
        int[] w = In.readInts(args[0]);
        staticSetOfInts newSet = new staticSetOfInts(w);
        while (!StdIn.isEmpty()){
            int key = StdIn.readInt();
            if (!newSet.contain(key))
                StdOut.println(key);
        }
    }
}

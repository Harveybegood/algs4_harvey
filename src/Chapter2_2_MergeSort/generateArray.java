package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdRandom;

public class generateArray {
    public static Comparable[] generateArray(int n){
        Comparable[] arrayElements = new Comparable[n];
        for (int i = 0; i < n; i++){
            arrayElements[i] = StdRandom.uniform(1,100);
        }
        return arrayElements;
    }
}

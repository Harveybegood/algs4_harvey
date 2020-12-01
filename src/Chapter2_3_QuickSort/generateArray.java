package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdRandom;

public class generateArray {
    public static Comparable[] generateNewArray(int n){
        Comparable[] newArray = new Comparable[n];
        for (int i = 0; i < n; i++){
            newArray[i] = StdRandom.uniform(1, 100);
        }
        return newArray;
    }
}

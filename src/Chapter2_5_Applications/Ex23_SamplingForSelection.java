package Chapter2_5_Applications;

import Tool.QuickSort;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Sampling for selection. Investigate the idea of using sampling to improve selection.
*   Hint: Using the median may not always be helpful.
*
* */
public class Ex23_SamplingForSelection {
    // review the selection in text
    public static Comparable select(Comparable[] a, int k){
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo){
            int j = QuickSort.partition(a, lo, hi);
            if (j > k) hi = j - 1;
            else if (j < k) lo = j + 1;
            else return a[k];
        }
        return a[k];
    }
}

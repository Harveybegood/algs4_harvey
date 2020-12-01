package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Show that the number of compares used by mergesort is monotonically increasing(C(N + 1) > C{N} for all N > 0)
*
* */
@SuppressWarnings("unchecked")
public class Ex2_2_7 {
    private static int accessArrayTime = 0;
    //private static int totalAccessTimes = 0;
    //private static Comparable[] array;
    private static Comparable[] aux;
    private static boolean less(Comparable v, Comparable w){
        accessArrayTime += 2;
        return v.compareTo(w) < 0;
    }
    private static int merge(Comparable[] a, int lo, int mid, int hi){
        accessArrayTime = 0;
        int totalAccessTimes = 0;
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++){
            accessArrayTime += 2;
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++){
            if (i > mid)
            {   accessArrayTime += 2;
                a[k] = aux[j++];
            }
            else if (j > hi)
            {   accessArrayTime += 2;
                a[k] = aux[i++];
            }
            else if (less(aux[j], aux[i]))
            {
                accessArrayTime += 2;
                a[k] = aux[j++];
            }
            else {
                accessArrayTime += 2;
                a[k] = aux[i++];
            }
        }
        totalAccessTimes += accessArrayTime;
        return totalAccessTimes;
    }
    private static int sort(Comparable[] a){
        aux = new Comparable[a.length];
        return sort(a, 0, a.length - 1);
    }
    private static int sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return 0;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        return merge(a, lo, mid, hi);
    }

    private static Comparable[] generateArray(int N){
        Comparable[] array = new Comparable[N];
        for (int i = 0; i < N; i++){
            array[i] = StdRandom.uniform(1, 100);
        }
        return array;
    }
    public static void main(String[] args) {
        StdOut.println("Length of comparing array  the number of compares");
        for (int i = 100; i < 2100; i += 100){
            StdOut.printf("%10d %25d\n", i, sort(generateArray(i)));
        }
    }
}

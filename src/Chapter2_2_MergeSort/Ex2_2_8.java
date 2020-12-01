package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Suppose that Algorithm 2.4 is modified to skip the call on merge() whenever a[mid] <= a[mid+1]. Prove that the
*   number of compares used to mergesort a sorted array is linear.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_2_8 {
    private static int arrayAccessTime = 0;
    private static Comparable[] aux;
    private static boolean less(Comparable v, Comparable w){
        arrayAccessTime += 2;
        return v.compareTo(w) <= 0;
    }
    private static int merge(Comparable[] a, int lo, int mid, int hi){
        arrayAccessTime = 0;
        int totalAccessTimes = 0;
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++){
            aux[k] = a[k];
            arrayAccessTime += 2;
        }
        for (int k = lo; k <= hi; k++){
            if (i > mid){
                arrayAccessTime += 2;
                a[k] = aux[j++];
            }
            else if (j > hi){
                arrayAccessTime += 2;
                a[k] = aux[i++];
            }
            else if (less(aux[j], aux[i])){
                arrayAccessTime += 2;
                a[k] = aux[j++];
            }
            else{
                arrayAccessTime += 2;
                a[k] = aux[i++];
            }
        }
        totalAccessTimes += arrayAccessTime;
        return totalAccessTimes;
    }
    private static int sort(Comparable[] a){
        aux = new Comparable[a.length];
        return sort(a, 0, a.length - 1);
    }
    private static int sort(Comparable[] a, int lo, int hi){
        if (hi <= lo)return 0;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        if (a[mid].compareTo(a[mid + 1]) <= 0){
            return 0;
        }
        return merge(a, lo, mid, hi);
    }
    private static Comparable[] array(int N){
        Comparable[] arrayPerN = new Comparable[N];
        for (int i = 0; i < N; i++){
            arrayPerN[i] = StdRandom.uniform(1, 100);
        }
        return arrayPerN;
    }

    public static void main(String[] args) {
        StdOut.println("Length N - number of Compares - linear");
        for (int i = 1; i < 256; i++){
            double n = (double) sort(array(i));
            StdOut.printf("%5d %15.1f %15.2f\n", i, n, n / i);
        }
    }
}

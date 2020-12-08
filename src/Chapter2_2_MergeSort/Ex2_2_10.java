package Chapter2_2_MergeSort;


import Chapter2_1_ElementarySorts.Ex2_1_1;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Faster merge. Implement a version of merge() that copies the second half of a[] to aux[] in decreasing order and then
*   does the merge back to a[]. This change allows you to remove the code to Test that each of the halves has been
*   exhausted from the inner loop. Note: The resulting sort is not stable(see page 341).
*
* */
public class Ex2_2_10 {
    //private static Comparable[] aux;
    @SuppressWarnings("unchecked")
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++){
            if (aux[j].compareTo(aux[i]) < 0)
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }
    private static void sort(Comparable[] a, Comparable[] aux){
        sort(a, aux, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if (hi <= lo)return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid , hi);
    }
    private static Comparable[] generateArray(int n){
        Comparable[] arrayElement = new Comparable[n];
        for (int i = 0; i < n; i++){
            arrayElement[i] = StdRandom.uniform(1, 100);
        }
        for (int j = n / 2; j < n; j++){
            Ex2_1_1.selection(arrayElement);
        }
        return arrayElement;
    }
    public static void main(String[] args) {
        int n = 50;
        Comparable[] aux = new Comparable[n];
        Comparable[] newArray = generateArray(n);
        sort(newArray, aux);
        for (int i = 0; i < n; i++){
            StdOut.println(newArray[i]);
        }
        StdOut.println("\n" + newArray.length);
    }
}

package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;


/*
*   Improvement. Run empirical studies to evaluate the effectiveness of each of the three improvements to mergesort
*   that are described in the text(see exercise 2.2.11). Also, compare the performance of the merge implementation
*   given in the text with the merge described in exercise 2.2.10. In particular, empirically determine the best value
*   of the parameter that decides when to switch to insertion sort for small subarrays.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_2_23 {
    private static final int cutOff = 15;
    // cutoff for small subarrays,
    private static Comparable[] aux;
    private static void cutOffSmallSubArray(Comparable[] a){
        aux = new Comparable[a.length];
        cutOffSmallSubArray(a, 0, a.length - 1);
    }
    private static void cutOffSmallSubArray(Comparable[] a, int low, int high){
        if (high - low < cutOff){
            insertion(a);
            return;
        }
        if (high <= low)return;
        int mid = low + (high - low) / 2;
        cutOffSmallSubArray(a, low, mid);
        cutOffSmallSubArray(a, mid + 1, high);
        cutOffMerge(a, low, mid, high);
    }
    private static void cutOffMerge(Comparable[] a, int low, int mid, int high){
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++){
            aux[k] = a[k];
        }
        for (int k = low; k <= high; k++){
            if (i > mid)
                a[k] = aux[j++];
            else if (j > high)
                a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0)
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }
    private static void insertion(Comparable[] a){
        for (int i = 1; i < a.length; i++){
            for (int j = i; j > 0 && a[j].compareTo(a[j-1]) < 0; j--){
                Comparable temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
            }
        }
    }
    // Test whether the array is already in order
    private static void alreadyInOrder(Comparable[] a){
        aux = new Comparable[a.length];
        alreadyInOrder(a, 0, a.length - 1);
    }
    private static void alreadyInOrder(Comparable[] a, int low, int high){
        if (high <= low)return;
        int mid = low + (high - low) / 2;
        alreadyInOrder(a, low, mid);
        alreadyInOrder(a, mid + 1, high);
        if (a[mid].compareTo(a[mid+1]) > 0){
            alreadyInOrderMerge(a, low, mid, high);
        }
    }
    private static void alreadyInOrderMerge(Comparable[] a, int low, int mid, int high){
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++){
            aux[k] = a[k];
        }
        for (int k = low; k <= high; k++){
            if (i > mid)
                a[k] = aux[j++];
            else if (j > high)
                a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0)
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }
    // avoid the copy by switching arguments in the recursive code
    private static void aVoidTheCopy(Comparable[] a){
        //aux = new Comparable[a.length];
        aux = a.clone();
        aVoidTheCopy(aux, a,0, a.length - 1);
    }
    private static void aVoidTheCopy(Comparable[] a, Comparable[] aux, int low, int high){
        if (high <= low) return;
        int mid = low + (high - low) / 2;
        aVoidTheCopy(aux, a, low, mid);
        aVoidTheCopy(aux, a, mid + 1, high);
        aVoidTheCopyMerge(a, aux, low, mid, high);
    }
    private static void aVoidTheCopyMerge(Comparable[] a, Comparable[] aux, int low, int mid, int high){
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++){
            if (i > mid)
                a[k] = aux[j++];
            else if (j > high)
                a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0)
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }
    // faster merge, copy the second half of a[] to aux[] in decreasing order and then does the merge back to a[]
    private static void fasterMerge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
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
    private static void fasterMergeSort(Comparable[] a, Comparable[] aux){
        fasterMergeSort(a, aux, 0, a.length - 1);
    }
    private static void fasterMergeSort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if (hi <= lo)return;
        int mid = lo + (hi - lo) / 2;
        fasterMergeSort(a, aux, lo, mid);
        fasterMergeSort(a, aux, mid + 1, hi);
        System.arraycopy(a, lo + (hi
         - lo) / 2 + 1, aux, lo, (hi - lo) / 2 - 1);
        fasterMerge(a, aux, lo, mid , hi);
    }
/*    private static Comparable[] generateArray(int n){
        Comparable[] arrayElement = new Comparable[n];
        for (int i = 0; i < n; i++){
            arrayElement[i] = StdRandom.uniform(1, 100);
        }
        for (int j = n / 2; j < n; j++){
            Ex2_1_1.selection(arrayElement);
        }
        return arrayElement;
    }*/
    private static Comparable[] generateAllArrays(int n){
        Comparable[] arrayElement = new Comparable[n];
        for (int i = 0; i < n; i++){
            arrayElement[i] = StdRandom.uniform(1, 100);
        }
        return arrayElement;
    }

    public static void main(String[] args) {
        StdOut.println("Cutoff for small subarrays");
        Comparable[] experimentArray1 = generateAllArrays(100000);
        Stopwatch timer1 = new Stopwatch();
        cutOffSmallSubArray(experimentArray1);
        double time1 = timer1.elapsedTime();
        StdOut.println(time1);
        StdOut.println("Test whether the array is already in order");
        Comparable[] experimentArray2 = generateAllArrays(100000);
        Stopwatch timer2 = new Stopwatch();
        alreadyInOrder(experimentArray2);
        double time2 = timer2.elapsedTime();
        StdOut.println(time2);
        StdOut.println("avoid the copy by switching arguments in the recursive code");
        Comparable[] experimentArray3 = generateAllArrays(100000);
        Stopwatch timer3 = new Stopwatch();
        aVoidTheCopy(experimentArray3);
        double time3 = timer3.elapsedTime();
        StdOut.println(time3);
        StdOut.println("faster merge");
        Comparable[] experimentArray4 = generateAllArrays(100000);
        Stopwatch timer4 = new Stopwatch();
        fasterMergeSort(experimentArray4, experimentArray4);
        double time4 = timer4.elapsedTime();
        StdOut.println(time4);
    }
}

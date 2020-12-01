package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Indirect sort. Develop and implement a version of mergesort that does not rearrange the array, but returns an int[]
*   array perm such that perm[i] is the index of the ith smallest entry in the array.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_2_20 {
    private static Comparable[] perm;
    private static void sort(Comparable[] a, int low, int high){
        if (high <= low) return;
        int mid = low + (high - low) / 2;
        sort(a, low, mid);
        sort(a, mid+1, high);
        merge(a, low, mid, high);
    }
    private static void merge(Comparable[] a, int low, int mid, int high){
        perm =  new Comparable[a.length];
        int leftIndex = low;
        int rightIndex = mid + 1;
        int i;
        while (leftIndex <= mid && rightIndex <= high){
            if (a[leftIndex].compareTo(a[rightIndex]) < 0){
                i = leftIndex;
                perm[i] = leftIndex;
                leftIndex++;
            }else {
                i = rightIndex;
                perm[i] = rightIndex;
                rightIndex++;
            }
        }
        while (leftIndex <= mid){
            i = leftIndex;
            perm[i] = leftIndex;
            leftIndex++;
        }
        while (rightIndex <= high){
            i =  rightIndex;
            perm[i] = rightIndex;
            rightIndex++;
        }
        for (Comparable n : perm)
            StdOut.print(String.valueOf(n) + " ");
    }
    private static Comparable[] generateArray(int n){
        Comparable[] newArray = new Comparable[n];
        for (int i = 0; i < n; i++){
            newArray[i] = StdRandom.uniform(1, 100);
        }
        return newArray;
    }

    public static void main(String[] args) {
        Comparable[] array = generateArray(10);
        for (int i = 0; i < 10; i++){
            StdOut.print(array[i] + " ");
        }
        StdOut.println();
        sort(array, 0, 9);
        /*for (int i = 0; i < 10; i++){
            StdOut.print(perm[i] + " ");
        }*/
    }
}

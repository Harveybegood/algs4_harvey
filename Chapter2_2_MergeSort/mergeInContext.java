package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("unchecked")
public class mergeInContext {
    public static void merge(Comparable[] a, int lo, int mid, int hi){
        int i = lo, j = mid + 1;
        Comparable[] aux = new Comparable[hi];
        for (int k = lo; k < hi; k++){
            aux[k] = a[k];
        }
        for (int k = lo; k < hi; k++){

            if (i > mid){ // Left half exhausted

                a[k] = aux[j++]; // take from the right

            }else if (j > hi){   // Right half exhausted

                a[k] = aux[i++]; // take from the left

            }else if (aux[j].compareTo(aux[i]) < 0){ // current key on right less than current key on left

                a[k] = aux[j++]; // take from the right

            }else {

                a[k] = aux[i++]; // current key on right greater than or equal to current key on left, take from the left

            }
        }
    }

    public static void main(String[] args) {
        Comparable[] a = {"E", "E", "G", "M", "R", "A", "C", "E", "R", "T"};
        StdOut.println();
        for (int i = 0; i <= a.length - 1; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
        StdOut.println();
        merge(a, 0, 4, 10);
        for (int i = 0; i <= a.length - 1; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
}

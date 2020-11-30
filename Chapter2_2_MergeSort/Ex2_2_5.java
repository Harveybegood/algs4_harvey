package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;


/*
*   Give the sequence of subarray sizes in the merges performed by both the top-down and the bottom-up mergesort
*   algorithms, for N = 39.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_2_5 {
    //private static Comparable[] aux;
    public static class topDownMerge{
        private static Comparable[] aux;
        private static void sort(Comparable[] a){
            int N = a.length;
            aux = new Comparable[N];
            sort(a, 0, N - 1);
        }
        private static void sort(Comparable[] a, int lo, int hi){
            if (hi <= lo) return;
            int mid = lo + (hi - lo) / 2;
            sort(a, lo, mid);
            sort(a, mid + 1, hi);
            merge(a, lo, mid, hi);
        }
        private static void merge(Comparable[] a, int lo, int mid, int hi){
            StdOut.println();
            StdOut.printf("Left size: %d | ", mid - lo + 1);
            StdOut.printf("Right size: %d \n", hi - mid);
           /* for (int m = 0; m <= hi; m++){
                if (m <= mid)
                    StdOut.print(a[m] + " ");
                else
                    StdOut.print(a[m] + " ");
            }*/
            StdOut.print("Left sequence: ");
            for (int m = lo; m <= mid; m++){
                StdOut.print(a[m] + " ");
            }
            StdOut.print("  Right sequence: ");
            for (int m = mid + 1; m <= hi; m++){
                StdOut.print(a[m] + " ");
            }
            StdOut.println();
            int i = lo, j = mid + 1;
            for (int k = 0; k <= hi; k++){
                aux[k] = a[k];
            }
            for (int k = lo; k <= hi; k++){
                if (i > mid)
                    a[k] = aux[j++];
                else if (j > hi)
                    a[k] = aux[i++];
                else if (aux[j].compareTo(aux[i]) < 0)
                    a[k] = aux[j++];
                else
                    a[k] = aux[i++];
            }
        }
    }
    public static class bottomUpMerge{
        private static Comparable[] aux;
        private static void sort(Comparable[] a){
            int N = a.length;
            aux = new Comparable[N];
            for (int size = 1; size < N; size *= 2){
                for (int index = 0; index < N - size; index += 2 * size){
                    merge(a, index, index + size - 1, Math.min(index + size + size - 1, N - 1));
                }
            }
        }
        private static void merge(Comparable[] a, int lo, int mid, int hi){
            StdOut.println();
            StdOut.printf("Left size: %d | ", mid - lo + 1);
            StdOut.printf("Right size: %d \n", hi - mid);
           /* for (int m = 0; m <= hi; m++){
                if (m <= mid)
                    StdOut.print(a[m] + " ");
                else
                    StdOut.print(a[m] + " ");
            }*/
            StdOut.print("Left sequence: ");
            for (int m = lo; m <= mid; m++ ){
                StdOut.print(a[m] + " ");
            }
            StdOut.print("  Right sequence: ");
            for (int m = mid + 1; m <= hi; m++){
                StdOut.print(a[m] + " ");
            }
            StdOut.println();
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++){
                aux[k] = a[k];
            }
            for (int k = lo; k <= hi; k++){
                if (i > mid)
                    a[k] = aux[j++];
                else if (j > hi)
                    a[k] = aux[i++];
                else if (aux[j].compareTo(aux[i]) < 0)
                    a[k] = aux[j++];
                else
                    a[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        Comparable[] a = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E",
                "A", "E", "Q", "S", "U", "Y", "E", "I", "N", "O", "S", "T", "E", "A", "S", "Y", "Q", "U",
                "E", "S", "T", "I", "O"};
        //StdOut.println(a.length);
        StdOut.println("top down merge");
        topDownMerge.sort(a);
        StdOut.println();
        for (int i = 0; i < 38; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
        StdOut.println("\nbottom up merge");
        Comparable[] b = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E",
                "A", "E", "Q", "S", "U", "Y", "E", "I", "N", "O", "S", "T", "E", "A", "S", "Y", "Q", "U",
                "E", "S", "T", "I", "O"};
        bottomUpMerge.sort(b);
        for (int i = 0; i < 38; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
}

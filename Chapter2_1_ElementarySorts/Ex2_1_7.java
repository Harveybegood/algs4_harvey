package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Which method runs faster for an array in reverse order, selection sort or insertion sort?
*
* */
public class Ex2_1_7 {
    @SuppressWarnings("unchecked")
    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    public static void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void selection(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++){
            int min = i;
            for (int j = i + 1; j < N; j++){
                if (less(a[j], a[j-1]))
                    min = j;
            }
            exch(a, i, min);
        }
    }
    public static void insertion(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--){
                exch(a, j, j-1);
            }
        }
    }

    /*
    *   To selection sort, firstly,
    *   the second item compares to its previous item until the last item, then the number of compares is N;
    *   the third item compares to its previous item until the last item, then the number of compares is N-1;
    *   .....
    *   .....
    *   the last item compares to its own, then the number of compares is 1;
    *
    *   we come to total number of compares that are N^/2
    *
    *   For each round compares, its exchange is 1, to total is N
    *
    *   To insertion sort,
    *   The second item compares to the previous item, the number of compare is 1; meanwhile, 1 for exchange operation
    *   J   k   I   H   G   F   E   D   C   B   A
    *   The third item compares to its previous item, the number of compares are 2; meanwhile, 2 for exchange operation
    *   I   J   K   H   G   F   E   D   C   B   A
    *   and so on
    *   H   I   J   K   G   F   E   D   C   B   A, the number of compares are 3, 3 for exchange operations.
    *   G   H   I   J   K   F   E   D   C   B   A, 4 for comparision, 4 for exchange operations.
    *   F   G   H   I   J   K   E   D   C   B   A, 5 for comparision, 5 for exchange operations.
    *   E   F   G   H   I   J   K   D   C   B   A, 6 for comparision, 6 for exchange operation.
    *   D   E   F   G   H   I   J   K   C   B   A, 7 for comparision, 7 for exchange operation.
    *   C   D   E   F   G   H   I   J   K   B   A, 8 for comparision, 8 for exchange operations.
    *   B   C   D   E   F   G   H   I   J   K   A, 9 for comparision, 9 for exchange operations.
    *   A   B   C   D   E   F   G   H   I   J   K, 10 for comparison, 10 for exchange operations.
    *
    *   N^2 / 2 + N^2 / 2 = N^2;
    *
    *
    * */

    public static void main(String[] args) {
        String[] a= {"K", "J", "I", "H", "G", "F", "E", "D", "C", "B", "A" };
        Stopwatch timer1 = new Stopwatch();
        selection(a);
        double t1 = timer1.elapsedTime();
        Stopwatch timer2 = new Stopwatch();
        insertion(a);
        double t2 = timer2.elapsedTime();
        StdOut.println(t1 + " - " + t2);
    }
}

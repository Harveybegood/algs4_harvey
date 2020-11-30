package Chapter2_1_ElementarySorts;
/*
*   Which method runs faster for an array with all keys identical, selection or insertion sort?
*
* */
public class Ex2_1_6 {
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
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
        }
    }

    /*
    *   because of all keys identical,
     *  To selection sort, the number of compares is ~(N^2) / 2, and the number of exchange is N.
     *
     *  To insertion sort, the number of compare is N, and the number of exchange is 0
     *
     *  So, we come to conclusion that insertion is faster than selection sort.
    *
    * */

    public static void insertion(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j > 0 || less(a[j], a[j-1]); j--){
                exch(a, j, j-1);
            }
        }
    }
    /*
    *
    *
    * */
}

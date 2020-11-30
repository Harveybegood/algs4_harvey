package Chapter2_1_ElementarySorts;
/*
*   For each of the two conditions in the inner for loop in insertion sort(Algorithm2.2), describe an array of N items
*   where that condition is always false when the loop terminate.
*
* */
public class Ex2_1_5 {
    public static void insertion(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j > 0 || less(a[j], a[j-1]); j--){
                exch(a, j, j-1);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    public static void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /*
    *   all sorted sequence or all equal key sequence
    *
    *
    * */
}

package Chapter2_1_ElementarySorts;
/*
*   Give an example of an array of N items that maximize the number of times the Test a[j] < a[min] fails (and,
*   therefore, min gets updated) during the operation of selection sort(Algorithm2.1).
*
* */
public class Ex2_1_3 {

    public static void selection(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++){
            int min = i;
            for (int j = i + 1; i < N; i++){
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
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
    *
    *
    *   all equal keys or an array of N items that is increasing sequence.
    *
    *
    *
    * */
}

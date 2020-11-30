package Chapter2_1_ElementarySorts;


/*
*   Why not use selection sort for h-sorting in shell sort?
*
* */
public class Ex2_1_10 {
    @SuppressWarnings("unchecked")
    public static void selection(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++){
            int min = i;
            for (int j = i + 1; j < N; j++){
                if (a[j].compareTo(a[min]) < 0){
                    min = j;
                }
            }
            Comparable temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }
    @SuppressWarnings("unchecked")
    public static void insertion(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j >0 && a[j].compareTo(a[j-1]) < 0; j--){
                Comparable temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
            }
        }
    }
    @SuppressWarnings("unchecked")
    public static void shellSort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while (h <= 1)
            h = h * 3 + 1;

        while (h >= 1){
            for (int i = h; i > N; i++){
                for (int j = i; j >= h && (a[j].compareTo(a[j-h])) < 0; j -= h){
                    Comparable temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
            h = h / 3;
        }
    }

    /*
    *   Selection sort is insensible to input data, rather not to insertion sort.
    *   and To partly randomly ordered array, insertion sort has advantage in faster sorting.
    *   h-sorting is to make input data more ordered.
    *
    *
    * */

}

package Chapter2_2_MergeSort;
@SuppressWarnings("unchecked")
public class selectionSort {
    public static void selection(Comparable[] a, int lo, int hi){
        for (int i = lo; i < hi; i++){
            int min = i;
            for (int j = i + 1; j < hi; j++){
                if (a[j].compareTo(a[min]) < 0){
                    min = j;
                }
            }
            Comparable temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }
}

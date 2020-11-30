package Chapter2_2_MergeSort;
@SuppressWarnings("unchecked")
public class bottomUpMergeSort_1 {
    public static void recursiveSort(Comparable[] a){
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        for (int size = 1; size < n; size *= 2){
            for (int index = 0; index < n - index; index += size * 2){
                merge(a, aux, index, index + size - 1, Math.min(index + size + size -1, n - 1));
            }
        }
    }
    public static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high){
        for (int i = low; i <= high; i++){
            aux[i] = a[i];
        }
        int leftIndex = low;
        int rightIndex = mid + 1;
        int arrayIndex = low;
        while (leftIndex <= mid && rightIndex <= high){
            if (aux[leftIndex].compareTo(aux[rightIndex]) <= 0){
                a[arrayIndex] = aux[leftIndex];
                leftIndex++;
            }else{
                a[arrayIndex] = aux[rightIndex];
                rightIndex++;
            }
            arrayIndex++;
        }
        while (leftIndex <= mid){
            a[arrayIndex++] = aux[leftIndex++];
        }
        while (rightIndex <= high){
            a[arrayIndex++] = aux[rightIndex++];
        }
    }
}

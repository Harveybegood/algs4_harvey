package Chapter2_5_Applications;
/*
*   One way to describe the result of a sorting algorithm is to specify a permutation p[] of the numbers 0 to a.length-1,
*   such that p[i] specifies where the key originally in a[i] ends up. Give the permutation that describe the results of
*   insertion sort, selection sort, shellsort, mergesort, quicksort, and heapsort for an array of seven equal keys.
*
* */
@SuppressWarnings("unchecked")

/*
*   Observation:
*
*           One key to all types of sort that we have learned is there is a item to compare another one, then if an
*           operation of exchange executed with the two items compared varies.
*           Lets see their difference of each type of sort.
*
*
*
* */


public class Ex11_permutation {
    public static void main(String[] args) {
        String[] s = new String[7];
        for (int i = 0; i < s.length; i++){
            s[i] = "a";
        }

    }
    public boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    public void exchange(Comparable v, Comparable w){
        Comparable temp = v; v = w; w = temp;
    }
    // Idea: To find the smallest item, if so, which will be exchange with the first item.
    //
    public int[] selectionSort(String[] s){
        // obviously,
        int[] p = new int[7];
        int n = s.length;
        for (int i = 0; i < n; i++){
            int m = i;
            for (int j = i + 1; j < n; j++){
                if (less(s[j], s[m])){
                    m = j;
                }
            }
            exchange(s[i], s[m]);
            p[i] = m;
        }
        return p;
    }
    // Idea: To find the smallest item in each loop and then each of items met will be exchanged
    // all same elements with same value, obviously, there is no exchange operation occurred.
    public void insertionSort(String[] s){
        // exchange operation never occurs.
        int n = s.length;
        //int[] p = new int[7];
        for (int i = 1; i < n; i++){
            for (int j = i; j > 0 && less(s[j], s[j - 1]); j--){
                exchange(s[j], s[j - 1]);
                //p[i - 1] = j;
            }
        }
        //return p;
    }
    // Idea: To avoid the smallest item stationed at the last position, which will cause the number of both compare
    // and swap of linear order. In other words, the smallest item stationed at last position just compare each item
    // spaced in associative with the value of h.
    // Same as insertion sort, there is no exchange operation occurred.
    public void shellSort(String[] s){
        // exchange operation never occurs
        int n = s.length;
        int h = 1;
        while (h < n / 3) h = h * 3 + 1;
        while (h >= 1){
            for (int i = h; i < n; i++){
                for (int j = i; j >= h && less(s[j], s[j - h]); j -= h){
                    exchange(s[j], s[j - h]);
                }
            }
            h = h / 3;
        }
    }
    // Idea: initially split the problem to simplest sub-problem by recursive. then compare each of sub-problems to
    // come up with a smaller sequence lists, in a recursive way, finally come to a complete ordered list.
    public void mergeSort(String[] s, int low, int high){

        if (high <= low){
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(s, low, mid);
        mergeSort(s, mid + 1, high);
        merge(s, low, mid, high);
    }
    String[] aux;
    public void merge(String[] s, int low, int mid, int high){
        // the order of original a[i] will end up with different order, since
        aux = new String[s.length];
        for (int i = 0; i <= high; i++){
            aux[i] = s[i];
        }
        int left = low, right = mid + 1;
        for (int i = 0; i <= high; i++){
            if (left > mid){
                s[i] = aux[right++];
            }
            else if (right > high){
                s[i] = aux[left++];
            }
            else if (less(aux[left], aux[right])){
                s[i] = aux[left++];
            }
            else {
                s[i] = aux[right++];
            }
        }
    }
    // Idea: to find a partitioning item which will be taken as a sentry and then scan from left and from right to compare
    // each of them with sentry, if reach to that left >= right, stop the scan and return a index from right scan.
    public void quickSort(String[] s, int low, int high){
        while (high <= low){
            break;
        }
        int j = partition(s, low, high);
        quickSort(s, low, j - 1);
        quickSort(s, j + 1, high);
    }
    public int partition(String[] s, int low, int high){
        String v = s[0];
        int i = low, j = high + 1;
        while (true){
            while (less(s[++i], v)){
                if (i >= j){
                    break;
                }
            }
            while (less(v, s[--j])){
                if (j <= i){
                    break;
                }
            }
            if (i >= j){
                break;
            }
            // obviously, the original order will be not kept
            exchange(s[i], s[j]);
        }
        exchange(s[0], s[j]);
        return j;
    }
    // Idea: There are two types of heap which are Max heap and Min heap. Literally Max heap is the higher levels of items
    // greater than lower levels of items, whereas higher levels of items is less than lower level of items for Min heap.
    // We have to construct a heap in order to implement the way of heap sort.
    public void heapSort(String[] s){
        int n = s.length;
        for (int i = n / 2; i >= 1; i--){
            sink(i, s);
        }
        while (n > 1){
            exchange(s[1], n--);
            sink(n, s);
        }
    }
    private void swim(int k, String[] s){
        while (k > 1){
            if (less(s[k/2], s[k])){
                exchange(s[k], s[2 * k]);
                k = k / 2;
            }
        }
    }
    private void sink(int k, String[] s){
        int n = s.length;
        while (2 * k <= n){
            int j = 2 * k;
            if (j < n && less(s[j], s[j + 1])){
                j++;
            }
            if (!less(s[k], s[j])){
                break;
            }
            // the original order will not be kept.
            exchange(s[k], s[j]);
            k = j;
        }
    }
}

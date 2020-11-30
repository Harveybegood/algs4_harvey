package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Fast 3-way partitioning. (J.Bentley and D.McIlroy) Implement an entropy optimal sort based on keeping item's with
*   equal keys at both the left and right ends of the subarray. Maintain indices p and q such that a[lo..p-1] and
*   a[q+1..hi] are all equal to a[lo], an index i such that a[p..i-1] are all less than a[lo],  and an index j such that
*   a[j+1..q] are all greater than a[lo]. Add to the inner partitioning loop code to swap a[i] with a[p](and increment p)
*   if it is equal to v and to swap a[j] with a[q](and decrement q) if it is equal to v before the usual comparisons of
*   a[i] and a[j] with v. After the partitioning loop has terminated, add code to swap the items with equal keys into
*   position. Note: This code complements the code given in the text, in the sense that it does extra swaps for keys
*   equal to the partitioning item's key, while the code in the text does extra swaps for keys that are not equal to the
*   partitioning item's key.
*
* */
public class Ex2_3_22 {
/*    @SuppressWarnings("unchecked")
    private static int quick(Comparable[] array, int low, int high){
        int i = low, j = high + 1;
        Comparable v = array[low];
        while (true){
            while (array[++i].compareTo(v) < 0){
                if (i == high)
                    break;
            }
            while (array[--j].compareTo(v) > 0){
                if (j == low)
                    break;
            }
            if (i >= j)
                break;
            Comparable temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        Comparable temp = array[low];
        array[low] = array[j];
        array[j] = temp;
        return j;
    }
    private static void sort(Comparable[] array){
        StdRandom.shuffle(array);
        sort(array, 0, array.length - 1);
    }
    private static void sort(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        int j = quick(array, low, high);
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }
    @SuppressWarnings("unchecked")
    private static void quickwith3way(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        int lt = low, i = low + 1, gt = high;
        Comparable v = array[low];
        while (i <= gt){
            int cmp = array[i].compareTo(v);
            if (cmp < 0){
                Comparable temp = array[lt];
                array[lt] = array[i];
                array[i] = temp;
                i++;
                lt++;
            }
            else if (cmp > 0){
                Comparable temp = array[gt];
                array[gt] = array[i];
                array[i] = temp;
                gt--;
            }
            else {
                i++;
            }
        }
        sort(array, low, lt - 1);
        sort(array, gt + 1, high);
    }*/
    @SuppressWarnings("unchecked")
    private static void fast3WayPartition(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        // p <[lo, p - 1], q <[q + 1, hi], i <[p, i - 1], j <[j + 1, q]
        int p = low + 1, q = high, i = low + 1, j = high;
        Comparable v = array[low];
        while (i <= j){
            int cmp1 = array[i].compareTo(v);
            int cmp2 = array[j].compareTo(v);
            if (cmp1 == 0){
                Comparable temp = array[p];
                array[p] = array[i];
                array[i] = temp;
                ++p;

                //i++;
            }
            if (cmp2 == 0){
                Comparable temp = array[q];
                array[q] = array[j];
                array[j] = temp;
                q--;
                //j--;
            }
            i++;
            j--;
            StdOut.println("i -> " + i);
            StdOut.println("j -> " + j);
            StdOut.println();

        }
        StdOut.println(p);

        for (int m = p + 1; m <= q; m++){
            for (int n = m; n > p && array[n + 1].compareTo(array[n]) < 0 && n < q; n--){
                Comparable temp = array[n];
                array[n] = array[n + 1];
                array[n + 1] = temp;
            }
        }

        /* exchange all items smaller than 7 to lower indices and bigger than 7 to higher indices respectively */


      /*  while (p <= q){
            while (array[++p].compareTo(v) < 0){

            }
            while (array[--q].compareTo(v) > 0){

            }
            Comparable temp = array[p];
            array[p] = array[q];
            array[q] = temp;
        }*/
    }
    private static Comparable[] generateArray(int n){
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(1, 100);
        }
        return array;
    }

    public static void main(String[] args) {
        Comparable[] array = {7, 7, 4, 9 ,7, 6, 8, 7, 7, 3, 9, 7, 5, 8};
        //StdRandom.shuffle(array);
        for (Comparable x : array){
            StdOut.print(x + " ");
        }
        StdOut.println();
        fast3WayPartition(array, 0, array.length - 1);;
        for (Comparable x : array){
            StdOut.print(x + " ");
        }
    }
}

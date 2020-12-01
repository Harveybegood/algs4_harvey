package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdRandom;
@SuppressWarnings("unchecked")
public class quickSortExample {
    private static int partition(Comparable[] array, int low, int high){
        int i = low, j = high + 1;
        Comparable v = array[low];
        while (true){
            while (array[++i].compareTo(v) < 0)
                if (i == high)
                    break;
            while (array[--j].compareTo(v) > 0)
                if (j == low)
                    break;
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
    public static void sort(Comparable[] array){
        StdRandom.shuffle(array);
        sort(array, 0, array.length - 1);
    }
    public static void sort(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        int j = partition(array, low, high);
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }

/*    public static void main(String[] args) {
        Comparable[] array = {"Q", "U", "I", "C", "K", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(array);
        for (Comparable x : array){
            StdOut.print(x + " ");
        }
        StdOut.println();
    }*/
}

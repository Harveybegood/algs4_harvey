package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("unchecked")
public class quick3Way {
    private static void sortQuick3Way(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        int lt = low, i = low + 1, gt = high;
        Comparable v = array[low];
        while (i <= gt){
            if (array[i].compareTo(v) < 0){
                Comparable temp = array[i];
                array[i] = array[lt];
                array[lt] = temp;
                i++;
                lt++;
            }else if (array[i].compareTo(v) > 0){
                Comparable temp = array[i];
                array[i] =  array[gt];
                array[gt] = temp;
                gt--;
            }
            else
                i++;
        }
        sortQuick3Way(array, low, lt - 1);
        sortQuick3Way(array, gt + 1, high);
    }

    public static void main(String[] args) {
        Comparable[] array = {"Q", "U", "I", "C", "K", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sortQuick3Way(array, 0, array.length - 1);
        for (Comparable x : array){
            StdOut.print(x + " ");
        }
        StdOut.println();
    }
}

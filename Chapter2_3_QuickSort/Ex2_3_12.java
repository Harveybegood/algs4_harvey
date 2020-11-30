package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.StringJoiner;

/*
*   Show, in the style of the trace given with the code, how the entropy-optimal sort first partitionS the array
*
*   lt  i   gt          B   A   B   A   B   A   B   A   C   A   D   A   B   R   A
*
*
* */
@SuppressWarnings("unchecked")
public class Ex2_3_12 {
    private static Comparable[] generateArray(int n){
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(1, 100);
        }
        return array;
    }
    private static void exch(Comparable[] array, int i, int j){
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private static void quick3Way(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        int lt = low, i = low + 1, gt = high;
        Comparable v = array[low];
        while ( i <= gt){
            int cmp = array[i].compareTo(v);
            if (cmp < 0)
                exch(array, lt++, i++);
            else if (cmp > 0)
                exch(array, i, gt--);
            else
                i++;
        }
        quick3Way(array, low, lt - 1);
        quick3Way(array, gt + 1, high);
    }

    public static void main(String[] args) {
        //Comparable[] array = generateArray(10);
        Comparable[] array = {"R", "B", "W", "W", "R", "W", "B", "R", "R", "W", "B", "R"};
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (Comparable x : array){
            stringJoiner.add(String.valueOf(x));
        }
        StdOut.print(stringJoiner + "\n");
        quick3Way(array, 0, array.length - 1);
        StringJoiner stringJoiner1 = new StringJoiner(" ");
        for (Comparable x : array){
            stringJoiner1.add(String.valueOf(x));
        }
        StdOut.print(stringJoiner1);
    }
}

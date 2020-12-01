package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;

/*
*   Show, in the style of the trace given with partition(), how that method partitions the array
*       E   A   S   Y   Q   U   E   S   T   I   O   N
*
* */
public class Ex2_3_1_partitioning_trace {

    /*                                  0   1   2   3   4   5   6   7   8   9   10  11
    *        initial                    E   A   S   Y   Q   U   E   S   T   I   O   N
    *                   i   v   j
    *   partitioning    1   E   6               S               E
    *       exchange    2   E   6       E   A   E   Y   Q   U   S   S   T   I   O   N
    *   partitioning    3   E   2               E   Y
    *   partitioning    1   E   1       E   A
    *       exchange    1   E   1       A   E   E   Y   Q   U   S   S   T   I   O   N
    *
    *
    * */

    @SuppressWarnings("unchecked")
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
    private static void sort(Comparable[] array){
        //StdRandom.shuffle(array);
        sort(array, 0, array.length - 1);
    }
    private static void sort(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        int j = partition(array, low, high);
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }

    public static void main(String[] args) {
        Comparable[] array = {"E",   "A",   "S",   "Y",   "Q",   "U",   "E",   "S",   "T",   "I",   "O",   "N"};
        StdOut.printf("%3s %1s %4s", "low", "j", "high:");
        StdOut.printf("%3d %3d %3d %3d %3d %3d %3d %3d %3d% 3d %3d %3d\n", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        StdOut.printf("%8s %4s %3s %3s %3s %3s %3s %3s %3s %3s %3s %3s %3s\n",
               "initial:", "E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N");
        sort(array);
        for (Comparable x : array){
            StdOut.print(x + " ");
        }
        StdOut.println();
    }
}

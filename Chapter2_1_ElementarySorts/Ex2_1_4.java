package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;

/*
*   Show, in the style of the example trace with Algorithm2.2, how insertion sort sorts the array
*
*           E   A   S   Y   Q   U   E   S   T   I   O   N
*
*           i   j   0   1   2   3   4   5   6   7   8   9   10  11
*                   E   A   S   Y   Q   U   E   S   T   I   O   N
*           1   0   A   E   S   Y   Q   U   E   S   T   I   O   N
*           2   2   A   E   S   Y   Q   U   E   S   T   I   O   N
*           3   3   A   E   S   Y   Q   U   E   S   T   I   O   N
*           4   2   A   E   Q   S   Y   U   E   S   T   I   O   N
*           5   4   A   E   Q   S   U   Y   E   S   T   I   O   N
*           6   2   A   E   E   Q   S   U   Y   S   T   I   O   N
*           7   5   A   E   E   Q   S   S   U   Y   T   I   O   N
*           8   6   A   E   E   Q   S   S   T   U   Y   I   O   N
*           9   3   A   E   E   I   Q   S   S   T   U   Y   O   N
*           10  4   A   E   E   I   O   Q   S   S   T   U   Y   N
*           11  4   A   E   E   I   N   O   Q   S   S   T   U   Y
*
*
* */
public class Ex2_1_4 {

    public static void insertion(Comparable[] a){
        int N =  a.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j > 0 || less(a[j], a[j-1]); j--){
                exch(a, j, j-1);
            }
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
    public static boolean isSorted(Comparable[] a){
        for (int i = 1; i < a.length; i++){
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }
    public static void show(Comparable[] a){
        for (int i = 0; i< a.length; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {

    }
}

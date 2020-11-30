package Chapter2_1_ElementarySorts;


import edu.princeton.cs.algs4.StdOut;

/*
*   Show, in the style of the example trace with Algorithm 2.1, how selection sort sorts the array
*
*           E A S Y Q U E S T I O N
*                           a[]
*           i  min  0   1   2   3   4   5   6   7   8   9   10  11
*                   E   A   S   Y   Q   U   E   S   T   I   O   N
*           0   1   A   E   S   Y   Q   U   E   S   T   I   O   N
*           1   1   A   E   S   Y   Q   U   E   S   T   I   O   N
*           2   6   A   E   E   Y   Q   U   S   S   T   I   O   N
*           3   9   A   E   E   I   Q   U   S   S   T   Y   O   N
*           4   11  A   E   E   I   N   U   S   S   T   Y   O   Q
*           5   10  A   E   E   I   N   O   S   S   T   Y   U   Q
*           6   11  A   E   E   I   N   O   Q   S   T   Y   U   S
*           7   7   A   E   E   I   N   O   Q   S   T   Y   U   S
*           8   11  A   E   E   I   N   O   Q   S   S   Y   U   T
*           9   11  A   E   E   I   N   O   Q   S   S   T   U   Y
*          10   10  A   E   E   I   N   O   Q   S   S   T   U   Y
*          11   11  A   E   E   I   N   O   Q   S   S   T   U   Y
*
*
*
*
*
*
*
* */
public class Ex2_1_1 {
    public static void selection(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++){
            int min = i;
            for (int j = i + 1; j < N; j++){
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
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
        for (int i = 0; i < a.length; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        String[] a ={"E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N"};
        selection(a);
        isSorted(a);
        show(a);
    }
}

package Chapter2_1_ElementarySorts;


import edu.princeton.cs.algs4.StdOut;

/*
*   Show, in the style of the example trace with Algorithm2.3, how shellsort sorts the array
*
*       E   A   S   Y   S   H   E   L   L   S   O   R   T   Q   U   E   S   T   I   O   N
*
*       INPUT   0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20
*               E   A   S   Y   S   H   E   L   L   S   O   R   T   Q   U   E   S   T   I   O   N
*      13-sort  E   A   S   Y   S   H   E   L   L   S   O   R   T   Q   U   E   S   T   I   O   N   a[13] <-> a[0]
*               E   A   S   Y   S   H   E   L   L   S   O   R   T   Q   U   E   S   T   I   O   N   a[14] <-> a[1]
*               E   A   E   Y   S   H   E   L   L   S   O   R   T   Q   U   S   S   T   I   O   N   a[15] <-> a[2] 1
*               E   A   E   S   S   H   E   L   L   S   O   R   T   Q   U   S   Y   T   I   O   N   a[16] <-> a[3] 1
*               E   A   E   S   S   H   E   L   L   S   O   R   T   Q   U   S   Y   T   I   O   N   a[17] <-> a[4]
*               E   A   E   S   S   H   E   L   L   S   O   R   T   Q   U   S   Y   T   I   O   N   a[18] <-> a[5]
*               E   A   E   S   S   H   E   L   L   S   O   R   T   Q   U   S   Y   T   I   O   N   a[19] <-> a[6]
*               E   A   E   S   S   H   E   L   L   S   O   R   T   Q   U   S   Y   T   I   O   N   a[20] <-> a[7]
*
*      4-sort   E   A   E   S   S   H   E   L   L   S   O   R   T   Q   U   S   Y   T   I   O   N   a[4] <-> a[0]
*               E   A   E   S   S   H   E   L   L   S   O   R   T   Q   U   S   Y   T   I   O   N   a[5] <-> a[1]
*               E   A   E   S   S   H   E   L   L   S   O   R   T   Q   U   S   Y   T   I   O   N   a[6] <-> a[2]
*               E   A   E   L   S   H   E   S   L   S   O   R   T   Q   U   S   Y   T   I   O   N   a[7] <-> a[3] 1
*               E   A   E   L   L   H   E   S   S   S   O   R   T   Q   U   S   Y   T   I   O   N   a[8] <-> a[4] 1
*               E   A   E   L   L   H   E   S   S   S   O   R   T   Q   U   S   Y   T   I   O   N   a[4] <-> a[0]
*               E   A   E   L   L   H   E   S   S   S   O   R   T   Q   U   S   Y   T   I   O   N   a[9] <-> a[5]
*               E   A   E   L   L   H   E   S   S   S   O   R   T   Q   U   S   Y   T   I   O   N   a[10] <-> a[6]
*               E   A   E   L   L   H   E   R   S   S   O   S   T   Q   U   S   Y   T   I   O   N   a[11] <-> a[7] 1
*               E   A   E   L   L   H   E   R   S   S   O   S   T   Q   U   S   Y   T   I   O   N   a[7] <-> a[3]
*               E   A   E   L   L   H   E   R   S   S   O   S   T   Q   U   S   Y   T   I   O   N   a[12] <-> a[8]
 *              E   A   E   L   L   H   E   R   S   Q   O   S   T   S   U   S   Y   T   I   O   N   a[13] <-> a[9] 1
*               E   A   E   L   L   H   E   R   S   Q   O   S   T   S   U   S   Y   T   I   O   N   a[9] <-> a[5]
*               E   A   E   L   L   H   E   R   S   Q   O   S   T   S   U   S   Y   T   I   O   N   a[14] <-> a[10]
*               E   A   E   L   L   H   E   R   S   Q   O   S   T   S   U   S   Y   T   I   O   N   a[15] <-> a[11]
*               E   A   E   L   L   H   E   R   S   Q   O   S   T   S   U   S   Y   T   I   O   N   a[16] <-> a[12]
*               E   A   E   L   L   H   E   R   S   Q   O   S   T   S   U   S   Y   T   I   O   N   a[17] <-> a[13]
*               E   A   E   L   L   H   E   R   S   Q   O   S   T   S   I   S   Y   T   U   O   N   a[18] <-> a[14] 1
*               E   A   E   L   L   H   E   R   S   Q   I   S   T   S   O   S   Y   T   U   O   N   a[14] <-> a[10] 1
*               E   A   E   L   L   H   E   R   S   Q   I   S   T   S   O   S   Y   T   U   O   N   a[10] <-> a[6]
*               E   A   E   L   L   H   E   R   S   Q   I   S   T   S   O   S   Y   T   U   O   N   a[19] <-> a[15]
*               E   A   E   L   L   H   E   R   S   Q   I   S   T   S   O   S   N   T   U   O   Y   a[20] <-> a[16] 1
*               E   A   E   L   L   H   E   R   S   Q   I   S   N   S   O   S   T   T   U   O   Y   a[16] <-> a[12] 1
*               E   A   E   L   L   H   E   R   N   Q   I   S   S   S   O   S   T   T   U   O   Y   a[12] <-> a[8] 1
*               E   A   E   L   L   H   E   R   N   Q   I   S   S   S   O   S   T   T   U   O   Y   a[8] <-> a[4] 1
*
*               0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20
*      1-sort   E   A   E   L   L   H   E   R   N   Q   I   S   S   S   O   S   T   T   U   O   Y   a[1] <-> a[0] 1
*               A   E   E   L   L   H   E   R   N   Q   I   S   S   S   O   S   T   T   U   O   Y   a[2] <-> a[1]
*               A   E   E   L   L   H   E   R   N   Q   I   S   S   S   O   S   T   T   U   O   Y   a[3] <-> a[2]
*               A   E   E   L   L   H   E   R   N   Q   I   S   S   S   O   S   T   T   U   O   Y   a[4] <-> a[3]
*               A   E   E   L   H   L   E   R   N   Q   I   S   S   S   O   S   T   T   U   O   Y   a[5] <-> a[4] 1
*               A   E   E   H   L   L   E   R   N   Q   I   S   S   S   O   S   T   T   U   O   Y   a[4] <-> a[3] 1
*               A   E   E   H   L   L   E   R   N   Q   I   S   S   S   O   S   T   T   U   O   Y   a[3] <-> a[2]
*               A   E   E   H   L   E   L   R   N   Q   I   S   S   S   O   S   T   T   U   O   Y   a[6] <-> a[5] 1
*               A   E   E   H   E   L   L   R   N   Q   I   S   S   S   O   S   T   T   U   O   Y   a[5] <-> a[4] 1
 *              A   E   E   E   H   L   L   R   N   Q   I   S   S   S   O   S   T   T   U   O   Y   a[4] <-> a[3] 1
 *              A   E   E   E   H   L   L   R   N   Q   I   S   S   S   O   S   T   T   U   O   Y   a[3] <-> a[2] 1
 *              A   E   E   E   H   L   L   R   N   Q   I   S   S   S   O   S   T   T   U   O   Y   a[7] <-> a[6]
 *              A   E   E   E   H   L   L   N   R   Q   I   S   S   S   O   S   T   T   U   O   Y   a[8] <-> a[7] 1
 *              A   E   E   E   H   L   L   N   R   Q   I   S   S   S   O   S   T   T   U   O   Y   a[7] <-> a[6]
 *              A   E   E   E   H   L   L   N   Q   R   I   S   S   S   O   S   T   T   U   O   Y   a[9] <-> a[8] 1
 *              A   E   E   E   H   L   L   N   Q   R   I   S   S   S   O   S   T   T   U   O   Y   a[8] <-> a[7]
 *              A   E   E   E   H   L   L   N   Q   I   R   S   S   S   O   S   T   T   U   O   Y   a[10] <-> a[9] 1
 *              A   E   E   E   H   L   L   N   I   Q   R   S   S   S   O   S   T   T   U   O   Y   a[9] <-> a[8] 1
 *              A   E   E   E   H   L   L   I   N   Q   R   S   S   S   O   S   T   T   U   O   Y   a[8] <-> a[7] 1
 *              A   E   E   E   H   L   I   L   N   Q   R   S   S   S   O   S   T   T   U   O   Y   a[7] <-> a[6] 1
 *              A   E   E   E   H   I   L   L   N   Q   R   S   S   S   O   S   T   T   U   O   Y   a[6] <-> a[5] 1
 *              A   E   E   E   H   I   L   L   N   Q   R   S   S   S   O   S   T   T   U   O   Y   a[5] <-> a[4]
 *              A   E   E   E   H   I   L   L   N   Q   R   S   S   S   O   S   T   T   U   O   Y   a[11] <-> a[10]
 *              A   E   E   E   H   I   L   L   N   Q   R   S   S   S   O   S   T   T   U   O   Y   a[12] <-> a[11]
 *              A   E   E   E   H   I   L   L   N   Q   R   S   S   S   O   S   T   T   U   O   Y   a[13] <-> a[12]
 *              A   E   E   E   H   I   L   L   N   Q   R   S   S   O   S   S   T   T   U   O   Y   a[14] <-> a[13] 1
 *              A   E   E   E   H   I   L   L   N   Q   R   S   O   S   S   S   T   T   U   O   Y   a[13] <-> a[12] 1
 *              A   E   E   E   H   I   L   L   N   Q   R   O   S   S   S   S   T   T   U   O   Y   a[12] <-> a[11] 1
 *              A   E   E   E   H   I   L   L   N   Q   O   R   S   S   S   S   T   T   U   O   Y   a[11] <-> a[10] 1
 *              A   E   E   E   H   I   L   L   N   O   Q   R   S   S   S   S   T   T   U   O   Y   a[10] <-> a[9] 1
 *              A   E   E   E   H   I   L   L   N   O   Q   R   S   S   S   S   T   T   U   O   Y   a[9] <-> a[8]
 *              A   E   E   E   H   I   L   L   N   O   Q   R   S   S   S   S   T   T   U   O   Y   a[15] <-> a[14]
 *              A   E   E   E   H   I   L   L   N   O   Q   R   S   S   S   S   T   T   U   O   Y   a[16] <-> a[15]
 *              A   E   E   E   H   I   L   L   N   O   Q   R   S   S   S   S   T   T   U   O   Y   a[17] <-> a[16]
 *              A   E   E   E   H   I   L   L   N   O   Q   R   S   S   S   S   T   T   U   O   Y   a[18] <-> a[17]
 *              A   E   E   E   H   I   L   L   N   O   Q   R   S   S   S   S   T   T   O   U   Y   a[19] <-> a[18] 1
 *              A   E   E   E   H   I   L   L   N   O   Q   R   S   S   S   S   T   O   T   U   Y   a[18] <-> a[17] 1
 *              A   E   E   E   H   I   L   L   N   O   Q   R   S   S   S   S   O   T   T   U   Y   a[17] <-> a[16] 1
 *              A   E   E   E   H   I   L   L   N   O   Q   R   S   S   S   O   S   T   T   U   Y   a[16] <-> a[15] 1
 *              A   E   E   E   H   I   L   L   N   O   Q   R   S   S   S   O   S   T   T   U   Y   a[16] <-> a[15] 1
 *              A   E   E   E   H   I   L   L   N   O   Q   R   S   S   O   S   S   T   T   U   Y   a[15] <-> a[14] 1
 *              A   E   E   E   H   I   L   L   N   O   Q   R   S   O   S   S   S   T   T   U   Y   a[14] <-> a[13] 1
 *              A   E   E   E   H   I   L   L   N   O   Q   R   O   S   S   S   S   T   T   U   Y   a[13] <-> a[12] 1
 *              A   E   E   E   H   I   L   L   N   O   Q   O   R   S   S   S   S   T   T   U   Y   a[12] <-> a[11] 1
 *              A   E   E   E   H   I   L   L   N   O   O   Q   R   S   S   S   S   T   T   U   Y   a[11] <-> a[10] 1
 *              A   E   E   E   H   I   L   L   N   O   O   Q   R   S   S   S   S   T   T   U   Y   a[20] <-> a[19] 1
 *
  *
  * */
public class Ex2_1_9 {
    @SuppressWarnings("unchecked")
    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    public static void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void shellSort(Comparable[] a){
        int N = a.length; // N = 21
        int h = 1;
        while (h <= N/3) h = h * 3 + 1; // h = 4, h < 7, h = 13
        while (h >= 1){
            for (int i = h; i < N; i++){
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h){
                    exch(a, j, j - h);
                }
            }
            h = h/3;
        }
    }
    public static void show(Comparable[] a){
        for (int i = 0; i < a.length; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        String[] a = {"E", "A", "S", "Y", "S", "H", "E", "L", "L", "S", "O", "R", "T", "Q", "U", "E", "S", "T", "I",
                "O", "N"};
        shellSort(a);
        show(a);
    }
}

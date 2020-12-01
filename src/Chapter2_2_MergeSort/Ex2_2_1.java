package Chapter2_2_MergeSort;

/*
*   Give a trace, in the style of the trace given at the beginning of this section, showing how the keys
*       A E Q S U Y E I N O S T
*   are merged with the abstract in-place merge() method.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_2_1 {
    /*
    *         0 1 2 3 4 5 6 7 8 9 10 11                 0 1 2 3 4 5 6 7 8 9 10 11
    *a[]      A E Q S U Y E I N O S  T
    *aux[]    A E Q S U Y E I N O S  T                  A E Q S U Y E I N O S  T
    *                                      i        j
    *         A                            0        6   A           E
    *         A E                          0        7   A             I
    *         A E E                        1        7     E           I
    *         A E E I                      2        7       Q         I
    *         A E E I N                    2        8       Q           N
    *         A E E I N O                  2        9       Q             O
    *         A E E I N O Q                2        10      Q               S
    *         A E E I N O Q S              3        10        S             S
    *         A E E I N O Q S S            4        10                      S
    *         E A E I N O Q S S T          4        11                          T
    *         A E E I N O Q S S T U        5        11          U
    *         A E E I N O Q S S T U Y      5        11            Y
    *
    *
    *
    *
    * */
    //@SuppressWarnings("unchecked")
   /* private static Comparable[] aux;
    private static void merge(Comparable[] a, int lo, int mid, int hi){
        aux = new Comparable[hi + 1];
        //Comparable[] aux = new Comparable[hi];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++){
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0)
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        Comparable[] a = {"A", "E", "Q", "S", "U", "Y", "E", "I", "N", "O", "S", "T"};
        merge(a, 0, a.length / 2 - 1, a.length - 1);
        for (int i = 0; i <= a.length - 1; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();

    }*/
}

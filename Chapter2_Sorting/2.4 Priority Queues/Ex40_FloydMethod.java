package Chapter2_4_PriorityQueues;
/*
*  Floyd's method. Implement a version of heapsort based on Floyd's sink-to-the-bottom-and-swim idea, as described in the
*  text. Count the number of compares used by your program and the number of compares used by standard implementation, for
*  randomly ordered distinct keys with N = 10^3, 10^6, and 10^9
*
* */
@SuppressWarnings("unchecked")
public class Ex40_FloydMethod {
    private static int standardCount;
    private static int floydCount;
    public static void heapsortStandard(Comparable[] array){
        int n = array.length;
        for (int k = n/2; k >= 1; k--){
            sink(array, k, n);
        }
        while (n > 1){
            exchange(array, 1, n--);
            sink(array, 1, n);
        }
    }
    public static void sink(Comparable[] array, int k, int N){
        while (k * 2 <= N){
            int j = 2 * k;
            if (j < N && less(array, j, j + 1)){
                j++;
            }
            if (!(less(array, k, j))){
                break;
            }
            exchange(array, k, j);
            k = j;
        }
    }
    public static void exchange(Comparable[] array, int k, int N){
        Comparable temp = array[k];
        array[k] = array[N];
        array[N] = temp;
    }
    public static boolean less(Comparable[] array ,int v, int w){
        standardCount++;
        return array[v].compareTo(array[w]) < 0;
    }
}

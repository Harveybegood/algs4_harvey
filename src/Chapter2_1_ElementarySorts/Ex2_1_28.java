package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Equal keys. Formulate and validate hypothesis about the running time of insertion sort and selection sort for arrays
*   that contain just two key values, assuming that the values are equally likely to occur.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_1_28 {
    public static void insertionSOrt(Comparable[] array){
        int N = array.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j > 0 && array[j].compareTo(array[j-1]) < 0; j--){
                Comparable temp = array[j];
                array[j] = array[j-1];
                array[j-1] = temp;
            }
        }
    }
    public static void selectionSort(Comparable[] array){
        int N = array.length;
        for (int i = 0; i < N; i++){
            int min = i;
            for (int j = i + 1; j < N; j++){
                if (array[j].compareTo(array[min]) < 0){
                    min = j;
                }
            }
            Comparable temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }
    public static double time(Comparable[] array, String alg){
        Stopwatch timer = new Stopwatch();
        if (alg.equals("insertionSORT")){
            insertionSOrt(array);
        }else if (alg.equals("selectionSort")){
            selectionSort(array);
        }
        return timer.elapsedTime();
    }
    public static double timeRandomInput(String alg, int N, int T){
        double total = 0;
        //Double[] array = new Double[N];
        Integer[] array = new Integer[N];
        for (int experiment = 0; experiment < T; experiment++){
            for (int i = 0; i < N; i++){
                array[i] = StdRandom.uniform(1,3);
                StdOut.println(array[i]);
            }
            total += time(array, alg);
        }
        return total;
    }

    public static void main(String[] args) {
        int N = 1000;
        int T = 10;
        double T1 = timeRandomInput("insertionSort", N, T);
        double T2 = timeRandomInput("selectionSort", N, T);
        StdOut.printf("For %d experiment to sort random ordered array of length %d: \n", T, N);
        StdOut.printf("insertion sort is %.1f time faster than selection sort", T1 / T2);
    }
}

package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Inversion. Develop and implement a linearithmic algorithm for computing the number of inversions in a given array
*   (the number of exchanges that would be performed by insertion sort for that array - see SECTION 2.1).
*   This quantity is related to the Kendall tau distance, see SECTION 2.5.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_2_19_Inversions {
    private static int numberOfExchange = 0;
    public static int insertion(Comparable[] a){
        int n =  a.length;
        for (int i = 1; i < n; i++){
            for (int j = i; j > 0 && a[j].compareTo(a[j-1]) < 0; j--){
                numberOfExchange++;
                StdOut.printf("%d exchanges %d, Times:  %d\n", a[j], a[j-1], numberOfExchange);
                Comparable temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
            }
            StdOut.printf("%d round\n", i);
        }

        return numberOfExchange;
    }
    public static Comparable[] generateArray(int n){
        Comparable[] newArray = new Comparable[n];
        for (int i = 0; i < n; i++){
            newArray[i] = StdRandom.uniform(1, 100);
        }
        return newArray;
    }
    public static void main(String[] args) {
        Comparable[] experimentArray = generateArray(10);
        for (int i = 0; i < 10; i++){
            StdOut.print(experimentArray[i] + " ");
        }
        StdOut.println();
        int n = insertion(experimentArray);
        StdOut.println(n);
    }
}

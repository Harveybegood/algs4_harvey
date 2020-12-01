package Chapter2_1_ElementarySorts;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Insertion sort without exchange. Develop an implementation of insertion sort that moves larger elements to the right
*   one position with one array access per entry, rather than using exch(). Use SortCompare to evaluate the effectiveness
*   of doing so.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_1_25 {
    //private static double total;
    public enum InsertionType{
        Insertion_Type_Default, Insertion_Type_Without_EXCH
    }
    public static void insertionSortWithoutExch(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N; i++){
            Comparable aux = a[i];
            int j;
            for (j = i; j > 0 && aux.compareTo(a[j-1]) < 0; j--){
                a[j] = a[j-1];
            }
            a[j] = aux;
        }
    }
    public static void insertionSortWithDefault(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j > 0 && a[j].compareTo(a[j-1]) < 0; j--){
                Comparable temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
            }
        }
    }
    public static double time(Comparable[] a, InsertionType insertionType){
        Stopwatch timer = new Stopwatch();
        if (insertionType == InsertionType.Insertion_Type_Default){
            insertionSortWithDefault(a);
            //return timer.elapsedTime();
        }else if (insertionType == InsertionType.Insertion_Type_Without_EXCH){
            insertionSortWithoutExch(a);
            //return timer.elapsedTime();
        }
        return timer.elapsedTime();
    }
    public static double timeRandomInput(InsertionType insertionType, int arrayLength){
        double total = 0.0;
        int numberOfExperiment = 10;
        Comparable[] a = new Comparable[arrayLength];
        for (int experiment = 0; experiment < numberOfExperiment; experiment++){
            for (int i = 0; i < arrayLength; i++){
                a[i] = StdRandom.uniform();
            }
            total += time(a, insertionType);
        }
        return total;
    }
    public static void sortCompare(){
        int arrayLength = 10000;
        double time1 = timeRandomInput(InsertionType.Insertion_Type_Default, arrayLength);
        double time2 = timeRandomInput(InsertionType.Insertion_Type_Without_EXCH, arrayLength);
        StdOut.printf("With Exchange %.1f - %.1f faster than without exchange", time1,  time2);
    }

    public static void main(String[] args) {
        sortCompare();
    }
}

package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;


/*
*   Doubling Test. Write a client that perform a doubling Test for sort algorithms. Start at N equal to 1000, and print
*   N, the predicted number of seconds, the actual number of seconds, and the ratio as N doubles. Use your program to
*   validate that insertion sort and selection sort are quadratic for random inputs, and formulate and Test a hypothesis
*   for shellsort.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_1_31 {
    public enum SortType{
        SelectionSortType, InsertionSortType
    }
    public static void selectionSort(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++){
            int min = i;
            for (int j = i + 1; j < N; j++){
                if (a[j].compareTo(a[min]) < 0){
                    min = j;
                }
            }
            Comparable temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }
    public static void insertionSort(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j > 0 && a[j].compareTo(a[j-1]) < 0; j--){
                Comparable temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
            }
        }
    }
    public static double time(Comparable[] a, SortType sortType){
        Stopwatch timer = new Stopwatch();
        if (sortType == SortType.SelectionSortType){
            selectionSort(a);
        }else {
            insertionSort(a);
        }
        return timer.elapsedTime();
    }
    public static double timeRandomInput(int numberOfTrials, int arrayLength, SortType sortType){
        double total = 0.0;
        Comparable[] array = new Comparable[arrayLength];
        for (int experiments = 0; experiments < numberOfTrials; experiments++){
            for (int i = 0; i < arrayLength; i++){
                array[i] = StdRandom.uniform();
            }
            total += time(array, sortType);
        }
        return total;
    }
    public static void doublingTest(SortType sortType){
        int numberOfTrials = 10;
        int arrayLength = 500;
        double prevTimeSelection = timeRandomInput(numberOfTrials, arrayLength, SortType.SelectionSortType);
        double prevTimeInsertion = timeRandomInput(numberOfTrials, arrayLength, SortType.InsertionSortType);
        for (int N = 1000; N < 100000; N += N){
            double currentTimeSelection = timeRandomInput(numberOfTrials, N, SortType.SelectionSortType);
            double currentTimeInsertion = timeRandomInput(numberOfTrials, N, SortType.InsertionSortType);
            if (sortType == SortType.SelectionSortType){
                StdOut.printf("Random Input: %d, Cost Time: %.1f, ", N, currentTimeSelection);
                StdOut.printf("Doubling Ratio: %.2f\n", currentTimeSelection / prevTimeSelection);
                StdOut.println();
            }else {
                StdOut.printf("Random Input: %d, Cost Time: %.1f, ", N, currentTimeInsertion);
                StdOut.printf("Doubling Ratio: %.2f\n", currentTimeInsertion / prevTimeInsertion);
                StdOut.println();
            }
            prevTimeSelection = currentTimeSelection;
            prevTimeInsertion = currentTimeInsertion;
        }
    }

    public static void main(String[] args) {
        doublingTest(SortType.SelectionSortType);
        doublingTest(SortType.InsertionSortType);
    }
}


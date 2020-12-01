package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
*   Partially sorted. Write a client that generates partially sorted arrays, including the following:
*       95 percent sorted, last percent random values.
*       All entries within 10 positions of their final place in the array
*       Sorted except for 5 percent of the entries randomly dispersed throughout the array.
*   Develop and test hypotheses about the effect of such input on the performance of the algorithms in this section
*
* */
@SuppressWarnings("unchecked")
public class Ex2_1_37 {
    public enum SortType{
        SELECTION, INSERTION, SHELL
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
    public static void shellSort(Comparable[] a){
        int N = a.length;
        int increment = 1;
        while (increment < N / 3){increment = increment * 3 + 1;}
        while (increment >= 1){
            for (int i = increment; i < N; i++){
                for (int j = i; j >= increment && a[j].compareTo(a[j - increment]) < 0; j -= increment){
                    Comparable temp = a[j];
                    a[j] = a[j - increment];
                    a[j - increment] = temp;
                }
            }
            increment = increment / 3;
        }
    }
    public static double time(Comparable[] a, SortType sortType){
        Stopwatch timer = new Stopwatch();
        switch (sortType){
            case INSERTION:
                insertionSort(a);
                break;
            case SHELL:
                shellSort(a);
                break;
            case SELECTION:
                selectionSort(a);
                break;
        }
        return timer.elapsedTime();
    }
    public static Comparable[] generate95percentSortedExceptedEndingArray(int arrayLength){
        Comparable[] array = new Comparable[arrayLength];
        int last5PercentStartIndex = (int)(arrayLength * 0.95);
        for (int i = 0; i < last5PercentStartIndex; i++){
            array[i] = i;
        }
        for (int i = last5PercentStartIndex; i < arrayLength; i++){
            array[i] = StdRandom.uniform(Integer.MAX_VALUE);
        }
        return array;
    }
    public static Comparable[] generateElements10PositionFinalPositionArray(int arrayLength){
        Comparable[] array = new Comparable[arrayLength];
        Set<Integer> generatedValues = new HashSet<>();
        for (int i = 0; i < arrayLength; i++){
            int randomValue = StdRandom.uniform(i - 10, i + 10 + 1);
            while (generatedValues.contains(randomValue)){
                randomValue = StdRandom.uniform(i - 10, i + 10 + 1);
            }
            generatedValues.add(randomValue);
            array[i] = randomValue;
        }
        return array;
    }
    public static Comparable[] generate95PercentSortedArray(int arrayLength){
        Comparable[] array = new Comparable[arrayLength];
        int fivePercentLength = (int)(arrayLength*0.05);
        for (int i = 0; i < arrayLength; i++){array[i] = i;}
        for (int i = 0; i <= fivePercentLength / 2; i++){
            int randomIndex1 = StdRandom.uniform(0, arrayLength);
            int randomIndex2 = StdRandom.uniform(0, arrayLength);
            Comparable temp = array[randomIndex1];
            array[randomIndex1] = array[randomIndex2];
            array[randomIndex2] = temp;
        }
        return array;
    }
    public static void printExperiment(SortType sortType, int arrayLength, double time){
        StdOut.printf("%15s %17d %8.2f\n", sortType, arrayLength, time);
    }
    public static void runExperiments(Comparable[] array){
        Comparable[] arrayCopy1 = Arrays.copyOf(array, array.length);
        Comparable[] arrayCopy2 = Arrays.copyOf(array, array.length);
        double timeSelection = time(array, SortType.SELECTION);
        printExperiment(SortType.SELECTION, array.length, timeSelection);
        double timeInsertion = time(array, SortType.INSERTION);
        printExperiment(SortType.INSERTION, arrayCopy1.length, timeInsertion);
        double timeShell = time(array, SortType.SHELL);
        printExperiment(SortType.SHELL, arrayCopy2.length, timeShell);
    }
    private static void runAllException(int initialArrayLength, int experiments){
        int arrayLength = initialArrayLength;
        StdOut.printf("%15s %17s %8s\n", "Sort Type", "Array Length", "Time");
        StdOut.println("95% sorted and last 5 Percent random");
        for (int experiment = 0; experiment < experiments; experiment++){
            Comparable[] array = generate95percentSortedExceptedEndingArray(arrayLength);
            runExperiments(array);
            arrayLength *= 2;
        }
        StdOut.println();
        StdOut.println();

        arrayLength = initialArrayLength;
        StdOut.println("All entries within 10 position of their final place");
        for (int experiment = 0; experiment < experiments; experiment++){
            Comparable[] array = generateElements10PositionFinalPositionArray(arrayLength);
            runExperiments(array);
            arrayLength *= 2;
        }
        StdOut.println();
        StdOut.println();
        arrayLength = initialArrayLength;
        StdOut.println("Sorted except for %% of entries randomly dispersed");
        for (int experiment = 0; experiment < experiments; experiment++){
            Comparable[] array = generate95PercentSortedArray(arrayLength);
            runExperiments(array);
            arrayLength *= 2;
        }
    }

    public static void main(String[] args) {
        int initialArrayLength = 1000;
        int experiments = 10;
        runAllException(initialArrayLength, experiments);
    }
}

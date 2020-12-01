package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Shellsort is subquadratic. Use SortCompare to compare shellsort with insertion sort and selection sort on your computer
*   Use array sizes that are increasing powers of 2, starting at 128.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_1_27 {
    public enum SortType{
        ShellSort, InsertionSort, SelectionSort
    }

    public static void shellSort(Comparable[] array){
        int N = array.length;
        int h = 1;
        while (h < N / 3){
            h = h * 3 + 1;
        }
        while (h >= 1){
            for (int i = h; i < N; i++){
                //StdOut.print("++++++++++++++++++++++++++++++++++\n");
                //assert array != ;
               /* if (array != null){
                    StdOut.printf("--------------------- %d %f\n", array.length, array[100]);
                }else {
                    StdOut.print("Array is null referenced.\n");
                }*/
                for (int j = i; j >= h && array[j].compareTo(array[j-h]) < 0; j -= h){

                    //StdOut.print("++++++++++++++++++++++++++++++++++\n");
                    Comparable temp = array[j];
                    array[j] = array[j-h];
                    array[j-h] = temp;
                }
            }
            h = h / 3;
        }
    }
    public static void insertionSort(Comparable[] array){
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
            for (int j = i + 1; j > N; j++){
                if (array[j].compareTo(array[min]) < 0){
                    min = j;
                }
            }
            Comparable temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }
    public static double time(SortType sortType, Comparable[] array){
        Stopwatch timer = new Stopwatch();
        if (sortType == SortType.ShellSort){
            shellSort(array);
        }else if (sortType == SortType.InsertionSort){
            insertionSort(array);
        }else if (sortType == SortType.SelectionSort){
            selectionSort(array);
        }
        return timer.elapsedTime();
    }
    public static double timeRandomInput(SortType sortType, int arrayLength, int numberOfExperiment){
        double total = 0.0;
        int length = arrayLength;

        for (int experiment = 0; experiment < numberOfExperiment; experiment++){
            Comparable[] array = new Comparable[length];
            for (int i = 0; i < length; i++){
                array[i] = StdRandom.uniform();
            }
            //StdOut.println(array[100] + " null? ");
            total += time(sortType, array);
            length *= 2;
            //return total;
        }
        return total;
    }
    public static void sortCompare(){
        int arrayLength = 128;
        int numberOfExperiment = 10;
        double timeOfShellSort = timeRandomInput(SortType.ShellSort, arrayLength, numberOfExperiment);
        double timeOfInsertionSort = timeRandomInput(SortType.InsertionSort, arrayLength, numberOfExperiment);
        double timeOfSelectionSort = timeRandomInput(SortType.SelectionSort, arrayLength, numberOfExperiment);
        StdOut.printf("For %d random input, the different effectiveness sortCompared as:\n", arrayLength);
        StdOut.printf("Shell Vs. Insertion: %.1f\nInsertion Vs. Selection: %.1f\nShell Vs. Selection: %.1f\n",
                timeOfShellSort / timeOfInsertionSort, timeOfInsertionSort / timeOfSelectionSort, timeOfShellSort / timeOfSelectionSort);
    }

    public static void main(String[] args) {
        sortCompare();
    }
}

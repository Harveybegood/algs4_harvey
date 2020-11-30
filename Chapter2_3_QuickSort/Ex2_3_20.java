package Chapter2_3_QuickSort;


import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Map;

/*
*   Nonrecursive quicksort. Implement a nonrecursive version of quicksort based on a main loop where a subarray is popped
*   from a Chapter1_3_Bags_Queues_Stacks.stack to be partitioned, and the resulting subarrays are pushed onto the Chapter1_3_Bags_Queues_Stacks.stack. Note: Push the larger of the
*   subarrays onto the Chapter1_3_Bags_Queues_Stacks.stack first, which guarantees that the Chapter1_3_Bags_Queues_Stacks.stack will have at most lgN entries.
*
* */
public class Ex2_3_20 {
    @SuppressWarnings("unchecked")
    private static int partition(Comparable[] array, int low, int high){
        int i = low, j = high + 1;
        Comparable pivot = array[low];
        while (true){
            while (array[++i].compareTo(pivot) < 0){
                if (i == high)
                    break;
            }
            while (array[--j].compareTo(pivot) > 0){
                if (j == low)
                    break;
            }
            if (i >= j)
                break;
            Comparable temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        Comparable temp = array[low];
        array[low] = array[j];
        array[j] = temp;
        return j;
    }
   private static void printResults(int arraySize, double defaultQuickSortRunningTime, double nonRecursiveQuickSort){
       StdOut.printf("%10d %25.1f %24.1f\n", arraySize, defaultQuickSortRunningTime, nonRecursiveQuickSort);
   }
   private static class QuickSortRange{
        int low, high;
        QuickSortRange(int low, int high){
            this.low = low;
            this.high = high;
        }
   }
   private static void doExperiment(int numberOfExperiments, int initialArraySize, Map<Integer, Comparable[]> allInputArrays){
        StdOut.printf("%13s %23s %22s\n", "Array Size | ", "QuickSort Running Time |", "Nonrecursive QuickSort");
        int arraySize = initialArraySize;
        for (int i = 0; i < numberOfExperiments; i++){
            Comparable[] originalArray = allInputArrays.get(i);
            Comparable[] arrayCopy1 = new Comparable[originalArray.length];
            System.arraycopy(originalArray, 0, arrayCopy1, 0, originalArray.length);
            // default QuickSort
            Stopwatch quickSortTimer = new Stopwatch();
            quickSort(originalArray);
            double quickSortRunningTime = quickSortTimer.elapsedTime();
            Stopwatch nonRecursiveQuickSortTimer = new Stopwatch();
            nonRecursiveQuickSort(arrayCopy1);
            double nonRecursiveQuickSortRunningTime = nonRecursiveQuickSortTimer.elapsedTime();
            printResults(arraySize, quickSortRunningTime, nonRecursiveQuickSortRunningTime);
            arraySize *= 2;
        }
   }
   private static void nonRecursiveQuickSort(Comparable[] array){
        StdRandom.shuffle(array);
        quickSort(array, 0, array.length - 1);
   }
   private static void quickSort(Comparable[] array){
        StdRandom.shuffle(array);
        quickSort(array, 0, array.length - 1);
   }
   private static void quickSort(Comparable[] array, int low, int high){
        Stack<QuickSortRange> stack = new Stack<>();
        QuickSortRange quickSortRange = new QuickSortRange(low, high);
        stack.push(quickSortRange);
        while (stack.size() > 0){
            QuickSortRange currentQuickSortRange = stack.pop();
            int partition = partition(array, currentQuickSortRange.low, currentQuickSortRange.high);
            QuickSortRange leftQuickSortRange = new QuickSortRange(currentQuickSortRange.low, partition - 1);
            QuickSortRange rightQuickSortRange = new QuickSortRange(partition + 1, currentQuickSortRange.high);
            int leftSubArraySize = partition - currentQuickSortRange.low;
            int rightSubArraySize = currentQuickSortRange.high - partition + 2;
            if (leftSubArraySize > rightSubArraySize){
                if (leftSubArraySize > 1 && leftQuickSortRange.low < leftQuickSortRange.high){
                    stack.push(leftQuickSortRange);
                }
                if (rightSubArraySize > 1 && rightQuickSortRange.low < rightQuickSortRange.high){
                    stack.push(rightQuickSortRange);
                }
            }else {
                if (rightSubArraySize > 1 && rightQuickSortRange.low < rightQuickSortRange.high){
                    stack.push(rightQuickSortRange);
                }
                if (leftSubArraySize > 1 && leftQuickSortRange.low < leftQuickSortRange.high){
                    stack.push(leftQuickSortRange);
                }
            }
        }
   }

    public static void main(String[] args) {
        int numberOfExperiments = 20;
        int initialArraySize = 1000;

    }
}

package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/*
*   Recursion depth. Run empirical studies to determine the average recursive depth used by quick sort with cutoff for
*   arrays of size M, when sorting arrays of N distinct elements, for M = 10, 20, and 50 and N = 10^3, 10^4, 10^5
*   and 10^6
* */
@SuppressWarnings("unchecked")
public class Ex2_3_28 {
    // how to generate arrays of N distinct elements
    private static int depthOfRecursive = 0;
    private static int totalOfRecursive = 0;
    private static int[] generateArrays(int n){
        int[] arrayA = new int[n];
        int[] arrayB = new int[n];
        for (int i = 0; i < arrayA.length; i++){
            arrayA[i] = StdRandom.uniform(0, n);
            if (i == 0){
                System.arraycopy(arrayA, 0, arrayB, 0, 1);
            }else {
                int j = i - 1;
                while (j >= 0){
                    if (arrayA[i] != arrayA[j]){
                        j--;
                        while (j < 0)
                            break;
                    }else {
                        i--;
                        break;
                    }
                }
                System.arraycopy(arrayA, i, arrayB, i, 1);
            }
        }
        return arrayB;
    }

    private static int quickWithCutOff(int[] array, int low, int high){
        //depthOfRecursive++;
        int i = low, j = high + 1;
        int pivot = array[low];
        while (true){
            while (array[++i] < pivot){
                if (i >= high)
                    break;
            }
            while (array[--j] > pivot){
                if (j <= low)
                    break;
            }
            if (i >= j)
                break;
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        int temp = array[low];
        array[low] = array[j];
        array[j] = temp;
        return j;
    }
    private static void sort(int[] array, int cutoffSize){
        StdRandom.shuffle(array);
        depthOfRecursive =  sort(array, 0, array.length - 1, cutoffSize, 0);
    }
    private static int sort(int[] array, int low, int high, int cutoffSize, int depthOfRecursive){
        //totalOfRecursive += depthOfRecursive;
        if (high <= low)
            return depthOfRecursive;
        int sizeOfSubArray = high - low + 1;
        if (sizeOfSubArray < cutoffSize){
            insertion(array, low, high);
            return depthOfRecursive;
        }
        int pivot = quickWithCutOff(array, low, high);
        //int newDepthOfRecursive = depthOfRecursive + 1;
        int left = sort(array, low, pivot - 1, cutoffSize, depthOfRecursive+1);
        int right = sort(array, pivot + 1, high, cutoffSize, depthOfRecursive+1);
        return left > right ? left : right;
    }
    private static void insertion(int[] array, int low, int high){
        for (int i = low + 1; i <= high; i++){
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--){
                int temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
        }
    }
    private static void doExperiment(int[] array){
        int[] M = {10, 20, 50};
        for (int i = 0; i < 3; i++ ){
            for (int experiment = 0; experiment < 10; experiment++){
                sort(array, M[i]);
                totalOfRecursive += depthOfRecursive;
            }
            int averageRecursiveDepth = totalOfRecursive / 10;
            printResults(array.length,  M[i], averageRecursiveDepth);
            depthOfRecursive = 0;
            totalOfRecursive = 0;
        }
    }
    private static void printResults(int sizeOfDistinctElement, int sizeOfCutoff, int averageRecursiveDepth){
        StdOut.printf("%21d %12d %21d\n", sizeOfDistinctElement, sizeOfCutoff, averageRecursiveDepth);
    }
    public static void main(String[] args) {
        int[] ratio = {3, 4, 5, 6};
        StdOut.printf("%21s %12s %21s\n", "sizeOfDistinctElement", "sizeOfCutoff", "averageRecursiveDepth");
        for (int n = 0; n < 4; n++){
            int[] array = generateArrays((int) Math.pow(10, ratio[n]));
            doExperiment(array);
        }
    }
}

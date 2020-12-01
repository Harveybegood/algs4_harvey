package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;

/*
*   Best case. Write a program that produces a best-case array(with no duplicates) for sort() in algorithm2.5:
*   an array of N items with distinct keys having the property that every partition will produce subarrays that differ
*   in size at most 1(the same subarray sizes that would happen for an array of N equal keys).(For the purpose of this
*   exercise, ignore the initial shuffles.)
*
*
* */
public class Ex2_3_16 {
    static int middle;
    private static int[] bestCaseArray(int arraySize){
        int[] bestCaseArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++){
            bestCaseArray[i] = i;
        }
        bestCaseArray(bestCaseArray, 0, arraySize - 1);
        return bestCaseArray;
    }
    private static void bestCaseArray(int[] array, int low, int high){
        if (high <= low)
            return;
        middle = low + (high - low) / 2;
        bestCaseArray(array, low, middle - 1);
        bestCaseArray(array, middle + 1, high);
        int temp = array[low];
        array[low] = array[middle];
        array[middle] = temp;
    }

    public static void main(String[] args) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StdOut.println(alphabet.length());
        int[] array = bestCaseArray(52);
        for (int i = 0; i < 52; i++){
            StdOut.print(alphabet.charAt(array[i]) + " - " + middle + "\n");
        }
    }
}

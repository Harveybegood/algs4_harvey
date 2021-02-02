package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

/*
*   Bitonic search. An array is bitonic if it is compromised of an increasing sequence of integers followed immediately
*   by a decreasing sequence of integers. Write a program that, given a bitonic array of N distinct int values, determine
*   whether a given integer is in the array. Your program should use ~3lgN compares in the worst case.
*
* */
public class Ex20_BitonicSearch {

    // determine whether a given integer is in the array
    public static boolean IsAnIntegerInBitonic(int[] array, int number){
        int max = array[getTheIndexOfMax(array)];
        if (number == max || number == array[0] || number == array[array.length - 1]){
            //StdOut.println(true);
            return true;
        }
        //StdOut.println(max);
        if (number > array[0] && number< max){
            int lo = 0;
            int hi = getTheIndexOfMax(array) - 1;
            while (lo <= hi){
                int mid = lo + (hi - lo) / 2;
                if (array[mid] > number){
                    hi = mid - 1;
                }
                else if (array[mid] < number) {
                    lo = mid + 1;
                }
                else {
                    return true;
                }
            }
        }
        if (number > array[array.length - 1] && number < max){
            int lo = getTheIndexOfMax(array);
            int hi = array.length - 1;
            while (lo <= hi){
                int mid = lo + (hi - lo) / 2;
                if (array[mid] > number){
                    hi = mid - 1;
                }
                else if (array[mid] < number) {
                    lo = mid + 1;
                }
                else {
                    return true;
                }
            }
        }
        return false;
    }
    // FInd the max value and then get its index
    public static int getTheIndexOfMax(int[] array){
        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi){
            if (lo == hi){
                return hi;
            }
            int mid = lo + (hi - lo)/2;
            if (array[mid] < array[mid + 1]){
                lo = mid + 1;
            }
            else if (array[mid] > array[mid + 1]){
                hi = mid;
            }
            else {
                return mid;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] bitonicArray = {1, 3, 5, 7, 6, 4, 2, -1, -9};
        int number = 18;
        int number1 = 5;
        int number2 = -9;
        StdOut.println(bitonicArray[getTheIndexOfMax(bitonicArray)]);
        StdOut.println(IsAnIntegerInBitonic(bitonicArray, number));
        StdOut.println(IsAnIntegerInBitonic(bitonicArray, number1));
        StdOut.println(IsAnIntegerInBitonic(bitonicArray, number2));
    }
}

package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Binary search with only addition and subtraction. [Mihai Patrascu] Write a program that, given an array of N distinct
*   int values in ascending order, determines whether a given integer is in the array. You may use only additions and
*   subtractions and a constant amount of extra memory. The running time of your program should be proportional to logN
*   in the worst case.
*
*   Answer: Instead of searching based on powers of two(binary search), use Fibonacci numbers(which also grow exponentially).
*   Maintain the current search range to be the interval[i, i+Fk] and keep Fk and Fk-1 in two variables. At each step compute
*   Fk-2 via subtraction, check element i+Fk-2, and update the current range to either[i, i+Fk-2] or [i+Fk-2, i+Fk-2 + Fk-1].
*
* */
public class Ex22_BinarySearchWithOnlyAdditionSubtraction {
    // Fibonacci number: 1 1 2 3 5 8 13 21 34 55 89 144 233 ...
    //private final static int[] FIB = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233};

    // generate an array
   /* public static int[] ascendingOrderedArray(int n){
        int[] array = new int[n];
        array[0] = 0;
        for (int i = 1; i < n; i++){
            array[i] += array[i - 1] + StdRandom.uniform(1, 35);
        }
        return array;
    }*/
    // To determine if a given number is in the array
    public static boolean binarySearchWithAdditionSubtraction(int[] array, int number){
        // Compute Fk according to the length of array
        int Fk = 1;
        int Fk_1 = 1;
        int Fk_2 = 0;
        while (Fk < array.length - 1){
            Fk = Fk + Fk_1;
            Fk_1 = Fk_1 + Fk_2;
            Fk_2 = Fk - Fk_1;
        }
        int low = 0;
        int high = low + Fk;
        while (low <= high){
            // compute the Fk_2
            //int i = low + Fk_2 > array.length - 1 ? array.length - 1 : low + Fk_2;
            int i = Math.min(array.length - 1, low + Fk_2);
            if (i < 0){
                i = 0;
            }
            if (i > array.length){
                i = array.length - 1;
            }
            if (number > array[i]){
                low = low + Fk_2;
            }
            else if (number < array[i]){
                //low = low + Fk_1;
                high = Fk - Fk_1;
            }
            else {
                return true;
            }
            Fk = Fk_1;
            Fk_1 = Fk_2;
            Fk_2 = Fk - Fk_1;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 5, 6, 7, 9, 11};
        StdOut.println(binarySearchWithAdditionSubtraction(array, 9));
        StdOut.println(binarySearchWithAdditionSubtraction(array, 10));
        StdOut.println(binarySearchWithAdditionSubtraction(array, 1));
    }
}

package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

/*
*   Bitonic search. An array is bitonic if it is comprised of an increasing sequence of integers followed immediately
 *  by a decreasing sequence of integers. Write a program that, given a bitonic array of N distinct int values,
 *  determines whether a given integer is in the array. Your program should use ~3lgN compares in the worst case.
*
* */
public class Ex20 {
    public static int bitonic(int[] array, int value){
        if (array == null || array.length == 0) return -1;
        return bitonic(array,value,0,array.length-1);
    }
    private static int bitonic(int[] array, int value, int low, int high){
        if (low > high) return -1;
        int middle = low + (high - low) / 2;
        // case1: element in the middle is the value searched
        if (array[middle] == value) return middle;
        else if (array[middle] < value && ((middle > 0 && array[middle-1] > array[middle]) ||
                (middle < array.length-1 && array[middle] > array[middle+1]))){
            // case2: middle element is smaller than value searched, and max value is on the left
            return bitonic(array,value,low,middle-1);
        }else if (array[middle] < value && ((middle > 0 && array[middle-1] < array[middle]) ||
                (middle < array.length-1 && array[middle] < array[middle+1]))){
            // case3: middle element is smaller than value searched, and max value is on the right
            return bitonic(array,value,middle+1,high);
        }else {
            // case4: middle element is bigger than the value searched,
            // we do an ascending binary search on the left half and a descending binary search on the right half
            int leftHalfSearch = ascendingBinarySearch(array,value,low,middle);
            if (leftHalfSearch != -1) return leftHalfSearch;
            int rightHalfSearch = descendingBinarySearch(array,value,middle+1,high);
            if (rightHalfSearch != -1) return rightHalfSearch;
        }
        return -1;
    }
    private static int ascendingBinarySearch(int[] array, int value, int low, int high){
        while (low <= high){
            int middle = low + (high - low) /2;
            if (array[middle] < value) low = middle + 1;
            else if (array[middle] > value) high = middle - 1;
            else return middle;
        }
        return -1;
    }
    private static int descendingBinarySearch(int[] array, int value, int low, int high){
        while (low <= high){
            int middle = low + (high - low) / 2;
            if (array[middle] > value) low = middle + 1;
            else if (array[middle] < value) high = middle - 1;
            else return middle;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4, -1, -2, -3};
        int[] array2 = {1, 5, 4, 3, 2, 0};
        int[] array3 = {2, 4, 8, 16, 32, 1};
        int[] array4 = {2, 4, 8, 16, 32};
        int[] array5 = {2, 1};
        int[] array6 = {9};
        int indexOfElement1 = bitonic(array1,-1);
        int indexOfElement2 = bitonic(array2,5);
        int indexOfElement3 = bitonic(array3,2);
        int indexOfElement4 = bitonic(array3,99);
        int indexOfElement5 = bitonic(array4,32);
        int indexOfElement6 = bitonic(array5,1);
        int indexOfElement7 = bitonic(array6,9);
        StdOut.println("Index of element: " + indexOfElement1 + " -> " + indexOfElement1);
        StdOut.println("Index of element: " + indexOfElement2 + " -> " + indexOfElement2);
        StdOut.println("Index of element: " + indexOfElement3 + " -> " + indexOfElement3);
        StdOut.println("Index of element: " + indexOfElement4 + " -> " + indexOfElement4);
        StdOut.println("Index of element: " + indexOfElement5 + " -> " + indexOfElement5);
        StdOut.println("Index of element: " + indexOfElement6 + " -> " + indexOfElement6);
        StdOut.println("Index of element: " + indexOfElement7 + " -> " + indexOfElement7);
    }
}

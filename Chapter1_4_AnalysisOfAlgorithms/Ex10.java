package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

/*
*   Modify binary search so that it always returns the element with the smallest index that matches the search
*   element (and still guarantees logarithmic running time).
*
* */
public class Ex10 {
        static int binarySearch(int[] array, int element, int low, int high){
        if (low > high) return -1;
        int middle = low + (high - low)/2;
        if (array[middle] < element){
            return binarySearch(array, element, middle + 1, high);
        }else if (array[middle] > element){
            return binarySearch(array, element, low, middle - 1);
        }else {
            int possibleSmallestIndex = binarySearch(array,element,0, middle - 1);
            if (possibleSmallestIndex == -1){
                return middle;
            }else {
                return possibleSmallestIndex;
            }
        }
    }

    public static void main(String[] args) {
        int[] array1 = {3,4,4,5,6,10,15,20,20,20,21};
        int elementToSearch1 = 4;
        int elementToSearch2 = 20;
        int elementToSearch3 = -5;
        StdOut.println("Binary search: "+binarySearch(array1, elementToSearch1,0, array1.length)+" Expected: 1");
        StdOut.println("Binary search: "+binarySearch(array1, elementToSearch2,0, array1.length)+" Expected: 7");
        StdOut.println("Binary search: "+binarySearch(array1, elementToSearch3,0, array1.length)+" Expected: -1");
        int[] array2 = {4,4,4,4,4,4,15,20,20,20,20,21};
        int elementToSearch = 4;
        StdOut.println("Binary search: "+binarySearch(array2,elementToSearch,0,array2.length) + " Expected: 0");
    }
}

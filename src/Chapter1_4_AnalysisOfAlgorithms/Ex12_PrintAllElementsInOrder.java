package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
*   Write a program that, given two sorted arrays of N int values, prints all elements that appear in both arrays, in
*   sorted order. The running time of your program should be proportional to N in the worst case.
*
*   https://github.com/reneargento/algorithms-sedgewick-wayne/blob/master/src/chapter1/section4/Exercise12.java
*
* */
public class Ex12_PrintAllElementsInOrder {
    // given two sorted array of N int values by constructor
    private int[] a, b;
    public Ex12_PrintAllElementsInOrder(int[] array1, int[] array2){
        a = new int[array1.length];
        b = new int[array2.length];
        for (int i = 0; i < array1.length; i++){
            a[i] = array1[i];
        }
        Arrays.sort(a);
        for (int i = 0; i < array2.length; i++){
            b[i] = array2[i];
        }
        Arrays.sort(b);
    }
    public void equalSortedArray(){
        int array1Index = 0;
        int array2Index = 0;
        //int[] sortedarray;
        while (array1Index < a.length && array2Index < b.length){
            if (a[array1Index] < b[array2Index]){
                array1Index++;
            }
            else if (a[array1Index] > b[array2Index]){
                array2Index++;
            }
            else {
                StdOut.print(a[array1Index] + " ");
                array1Index++;
                array2Index++;
            }
        }
    }

    public static void main(String[] args) {
        int[] array1 = StdIn.readAllInts();
        int[] array2 = StdIn.readAllInts();
        Ex12_PrintAllElementsInOrder printAllElementsInOrder = new Ex12_PrintAllElementsInOrder(array1, array2);
        printAllElementsInOrder.equalSortedArray();
    }
}

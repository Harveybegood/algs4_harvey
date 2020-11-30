package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
*   Binary search on distinct values. Develop an implementation of binary search for StaticSETofInts(see page 98)
*   where the running time of contains() is guaranteed to be ~lg R, where R is the number of different integers in
*   the array given as argument to the constructor
*
* */
public class Ex21 {
    private int[] array;
    public Ex21(int[] keys){
        Map<Integer, Boolean> elementsMap = new HashMap<>();
        for (int i = 0; i < keys.length; i++){
            elementsMap.put(keys[i], true);
        }
        array = new int[elementsMap.size()];
        int arrayIndex = 0;
        for (int key : elementsMap.keySet()){
            array[arrayIndex] = key;
            arrayIndex++;
        }
        Arrays.sort(array);
    }
    public boolean contains(int key){
        return rank(key) != -1;
    }
    private int rank(int key){
        int low = 0, high = array.length - 1;
        while (low < high){
            int middle =  low + (high - low) / 2;
            if (key < array[middle]) high = middle - 1;
            else if (key > array[middle]) low = middle + 1;
            else return middle;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {99,8,8,1,2,4,1,-3};
        Ex21 ex21 = new Ex21(array);
        boolean containElement1 = ex21.contains(4);
        boolean containElement2 = ex21.contains(-3);
        boolean containElement3 = ex21.contains(99);
        boolean containElement4 = ex21.contains(0);
        StdOut.println("Index of element: " + containElement1);
        StdOut.println("Index of element: " + containElement2);
        StdOut.println("Index of element: " + containElement3);
        StdOut.println("Index of element: " + containElement4);
    }
}

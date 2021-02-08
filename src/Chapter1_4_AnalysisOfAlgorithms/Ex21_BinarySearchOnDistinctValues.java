package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
*   Binary search on distinct values. Develop an implementation of binary search for StaticSETofInts(see page 98)where
*   the running time of contains() is guaranteed to be ~lgR, where R is the number of different integers in the array
*   Given as argument to the constructor.
*
* */
public class Ex21_BinarySearchOnDistinctValues {
    int[] a;
    public Ex21_BinarySearchOnDistinctValues(int[] keys){
        //
        // Map<Integer, Boolean> map = new HashMap<>();
        a = new int[keys.length];
        a[0] = keys[0];
        int index = 1;
        // the length of array cannot be modified once it sets. we have to write another program to adjust its length
        // by removing zero value from array along with it length
        for (int i = 1; i < keys.length; i++){
            int j = i;
            while (j > 0){
                if (keys[j - 1] == keys[i]){
                    break;
                }
                {
                    j--;
                }
                if (j == 0){
                    a[index] = keys[i];
                    index++;
                }
            }
        }
        Arrays.sort(a);
    }
    public boolean contains(int key){
        return rank(key) != -1;
    }
    public int rank(int key){
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if (key < a[mid]){
                hi = mid - 1;
            }
            else if (key > a[mid]){
                lo = mid + 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {10, 8, 6, 6, 4, -9, -5, 7, -5, 6};
        Ex21_BinarySearchOnDistinctValues binarySearchOnDistinctValues = new Ex21_BinarySearchOnDistinctValues(array);
        for (int number : binarySearchOnDistinctValues.a){
            StdOut.print(number + " ");
        }
        StdOut.println(binarySearchOnDistinctValues.contains(8) + " Expecting true");
        StdOut.println(binarySearchOnDistinctValues.contains(-8) + " Expecting false");
    }
}

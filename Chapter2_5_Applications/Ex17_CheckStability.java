package Chapter2_5_Applications;

import Tool.QuickSort;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
*   Check stability. Extend your check() method from Exercise 2.1.16 to call sort() for a given array and return true
*   if sort() sorts the array in order in a stable manner, false otherwise. Do not assume that sort() is restricted
*   to move data only with exch().
*
* */
@SuppressWarnings("unchecked")
public class Ex17_CheckStability {
    public static void main(String[] args) {
        int n = 20;
        StdOut.println(new Ex17_CheckStability().check(generateArray(n), true));
        StdOut.println(new Ex17_CheckStability().check(generateArray(n), false));
    }
    // create a new class with which helps create objects containing both key value and index.
    private class Wrapper implements Comparable<Wrapper>{
        private Comparable keyValue;
        int originalIndex;
        public Wrapper(Comparable value, int index){
            this.keyValue = value;
            this.originalIndex = index;
        }

        public int compareTo(Wrapper that){
            return this.keyValue.compareTo(that.keyValue);
        }
    }

    public static Comparable[] generateArray(int n){
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(-10, 50);
        }
        return array;
    }

    private boolean check(Comparable[] array, boolean stable){
        // use above the class of wrapper to create any required objects as below.
        Wrapper[] wrappedKeys = new Wrapper[array.length];
        int wrappedKeysIndex = 0;
        for (int i = 0; i < array.length; i++){
            Wrapper wrapper = new Wrapper(array[i], i);
            wrappedKeys[wrappedKeysIndex++] = wrapper;
        }

        // insert all above related values to map.
        Map<Comparable, Integer> valuesMap = new HashMap<>();
        for (Comparable value : array){
            int count = 0;
            if (valuesMap.containsKey(value)){
                count = valuesMap.get(value);
            }
            count++;
            valuesMap.put(value, count);
        }
        if (stable){
            Arrays.sort(array);
            Arrays.sort(wrappedKeys);
        }
        else {
            QuickSort.sort(array);
            QuickSort.sort(wrappedKeys);
        }
        // check if array is sorted
        for (int i = 1; i < array.length; i++){
            if (array[i].compareTo(array[i-1]) < 0)
                return false;
        }
        // check if the initial set of objects is still in the array.
        for (Comparable value : array){
            if (valuesMap.containsKey(value)){
                int count = valuesMap.get(value);
                count--;
                if (count == 0)
                    valuesMap.remove(value);
                else
                    valuesMap.put(value, count);
            }
            else
                return false;
        }
        if (valuesMap.size() > 0)
            return false;

        // check if the elements were sorted in a stable manner
        for (int i = 1; i < wrappedKeys.length - 1; i++){
            if (wrappedKeys[i].keyValue.compareTo(wrappedKeys[i-1].keyValue) == 0 && wrappedKeys[i].originalIndex < wrappedKeys[i-1].originalIndex){
                return false;
            }
        }
        return true;
     }
}

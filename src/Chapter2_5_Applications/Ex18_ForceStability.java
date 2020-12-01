package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
*   Force stability. Write a wrapper method that makes any sort stable by creating a new key type that allows you to
*   append each key's index to the key, call sort(), then restore the original key after the sort.
*
* */
@SuppressWarnings("unchecked")
public class Ex18_ForceStability {
    public class Wrapper implements Comparable<Wrapper>{
        private Comparable keyValue;
        private int originalIndex;
        public Wrapper(Comparable keyvalue, int originalIndex){
            this.keyValue = keyvalue;
            this.originalIndex = originalIndex;
        }
        public int compareTo(Wrapper that){
            int compare = this.keyValue.compareTo(that.keyValue);
            // different key value
            if (compare != 0){
                return compare;
            }
            // equal key value
            if (this.originalIndex < that.originalIndex){
                return -1;
            }
            else if (this.originalIndex > that.originalIndex){
                return 1;
            }
            else
                return 0;
        }
    }
    private void sortInStableWay(Comparable[] array){
        Wrapper[] wrappersKeys = new Wrapper[array.length];
        int wrappedKeysIndex = 0;
        for (int i = 0; i < array.length; i++){
            Wrapper wrapper = new Wrapper(array[i], i);
            wrappersKeys[wrappedKeysIndex++] = wrapper;
        }
        Arrays.sort(wrappersKeys);
        for (int i = 0; i < array.length; i++){
            array[i] = wrappersKeys[i].keyValue;
        }
    }

    public static void main(String[] args) {
        Comparable[] array = {2, 20, -1, -30, 30, 5, 6, 8, -99, -3, 0, 4, 4, 4};
        String originalArray = Arrays.toString(array);
        //StdOut.println(array);
        StdOut.println("Original array: " + originalArray);
        new Ex18_ForceStability().sortInStableWay(array);
        String sortedArray = Arrays.toString(array);
        //StdOut.println(array);
        StdOut.println("Sorted array:" + sortedArray);
    }
}

package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   About how many compares will quick.sort() make when sorting an array of N items that are all equal?
*
* */
public class Ex2_3_8NitemsEqual {
    // hypothesis: NlnN
    private static int countOfCompares;
    private static Comparable[] generateArrayWithSameItems(int n){
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(1, 2);
        }
        return array;
    }
    private static Comparable[] generateArrayWithtwoDistinctItems(int n){
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(1, 3);
        }
        return array;
    }
    private static Comparable[] generateArrayWithThreeDistinctItems(int n){
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(1, 4);
        }
        return array;
    }
    @SuppressWarnings("unchecked")

    private static int partition(Comparable[] array, int low, int high){
        int i = low, j = high + 1;
        Comparable v = array[low];
        while (true){
            countOfCompares++;
            while (array[++i].compareTo(v) < 0){
                countOfCompares++;
                if (i == high)
                    break;
            }
            countOfCompares++;
            while (array[--j].compareTo(v) > 0){
                countOfCompares++;
                if (j == low)
                    break;
            }
            countOfCompares++;
            if (i >= j)
                break;
            Comparable temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        Comparable temp = array[low];
        array[low] = array[j];
        array[j] = temp;
        return j;
    }
    private static void sort(Comparable[] array){
        StdRandom.shuffle(array);
        sort(array, 0, array.length - 1);
    }
    private static void sort(Comparable[] array, int low, int high){
        if (high <= low)
            return;
        int j =  partition(array, low, high);
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }

    public static void main(String[] args) {
        Comparable[] array1 = generateArrayWithSameItems(100);
        Comparable[] array2 = generateArrayWithtwoDistinctItems(100);
        Comparable[] array3 = generateArrayWithThreeDistinctItems(100);
        sort(array1);
        StdOut.println(countOfCompares + " : " + 2 * 100 * Math.log(100));
        countOfCompares = 0;
        sort(array2);
        StdOut.println(countOfCompares + " : " + 2 * 100 * Math.log(100));
        countOfCompares = 0;
        sort(array3);
        StdOut.println(countOfCompares + " : " + 2 * 100 * Math.log(100));
    }
}

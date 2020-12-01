package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdRandom;

/*
*   What is the maximum number of times during the execution of Quick.sort() that the largest item can be exchanged,
*   for an array of length N?
*
*           N - 1
* */
public class Ex2_3_3_maximumNumberOfTimes {
    private static Comparable[] generateNewArray(int N){
        Comparable[] newArray = new Comparable[N];
        for (int i = 0; i < N; i++){
            newArray[i] = StdRandom.uniform(1, 100);
        }
        return newArray;
    }
    @SuppressWarnings("unchecked")
    private static int partition(Comparable[] array, int low, int high){
        int i = low, j = high + 1;
        Comparable v = array[low];
        while (true){
            while (array[++i].compareTo(v) < 0){
                if (i == high)
                    break;
            }
            while (array[--j].compareTo(v) > 0){
                if (j == low)
                    break;
            }
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
        int j = partition(array, low, high);
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }

    public static void main(String[] args) {
        Comparable[] newArray = generateNewArray(1000000);
        sort(newArray);
    }
}

package Chapter2_3_QuickSort;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/*
*   Write a program to compute the exact value of CN, and compare the exact value with the approximation 2NlnN,
*   for N = 100, 1000, and 10,000.
*
* */
public class Ex2_3_6 {
    private static int countOfCompare;
    //private static int total;
    private static Comparable[] generateRandomValue(int n){
        Comparable[] array = new Comparable[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(1,100);
        }
        return array;
    }
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w){
        countOfCompare++;
        return v.compareTo(w) < 0;
    }

    private static int partition(Comparable[] array, int low, int high){
        int i = low, j = high + 1;
        Comparable v = array[low];
        while (true){
            while (less(array[++i], v)){
                if (i == high)
                    break;
            }
            while (less(v, array[--j])){
                if (j == low)
                    break;
            }
            countOfCompare++;
            if (i >= j){
                break;
            }
            Comparable temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        Comparable temp = array[low];
        array[low] = array[j];
        array[j] = temp;
        //total += countOfCompare;
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
        //total += countOfCompare;
    }

    public static void main(String[] args) {
        Comparable[] randomArray1 = generateRandomValue(100);
        Comparable[] randomArray4 = generateRandomValue(100);
        Comparable[] randomArray2 = generateRandomValue(1000);
        Comparable[] randomArray3 = generateRandomValue(10000);
        sort(randomArray1);
        StdOut.println(countOfCompare + " : " + 2*100*Math.log(100));
        countOfCompare = 0;
        sort(randomArray4);
        StdOut.println(countOfCompare + " : " + 2*100*Math.log(100));
        countOfCompare = 0;
        sort(randomArray2);
        StdOut.println(countOfCompare + " : " + 2*1000*Math.log(1000));
        countOfCompare = 0;
        sort(randomArray3);
        StdOut.println(countOfCompare + " : " + 2*10000*Math.log(10000));
    }
}

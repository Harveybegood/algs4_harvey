package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/*
*   Faster 3-sum. As a warmup, develop an implementation TwoSumFaster that uses a linear algorithm to count the pairs that
*   sum to zero after the array is sorted(instead of the binary-search-based linearithmic algorithm). Then apply a similar
*   idea to develop a quadratic algorithm for the 3-sum problem.
*
*   Let's say that we have these numbers:  -6, -5, -3, 1, 2, 3
*
*                                          lo                hi
*                                          -6, -5, -3, 1, 2, 3
*                          (-6 + 3) < 0->
                                                lo            hi
*                          (-5 + 3) < 0->  -6, -5, -3, 1, 2, 3
*                                                   lo       hi
*                          (-3 + 3) = 0->  -6, -5, -3, 1, 2, 3    cnt = 1;
*
*                          (1 + 3) > 0  and  lo > 0 return;
* */
public class Ex15_Faster3Sum {
    public static int rank(int key, int[] array){
        int lo = 0;
        int hi = array.length;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if (key < array[mid]){hi = mid - 1;}
            else if (key > array[mid]){lo = mid + 1;}
            else {return mid;}
        }
        return -1;
    }
    public static int count(int[] array){
        int cnt = 0;
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++){
            if (rank(-array[i], array) > i){
                cnt++;
            }
        }
        return cnt;
    }

    // TwoSumFaster for taking the cost of the order of growth of linear,

    public static int TwoSumFaster(int[] array){
        int cnt = 0;
        int lo = 0;
        int hi = array.length - 1;
        Arrays.sort(array);
        while (lo < hi){
            if ((array[lo] + array[hi]) > 0){
                hi--;
            }
            else if ((array[lo] + array[hi]) < 0){
                lo++;
            }
            else {
                cnt++;
                lo++;
                hi--;
            }
            if (array[lo] > 0 &&  array[hi]> 0){
                break;
            }
            if ((array[lo] < 0) && (array[hi] < 0)){
                break;
            }
        }
        return cnt;
    }

    //  ThreeSumFaster
    public static int ThreeSumFaster(int[] array){
        int lo = 0;
        int hi = array.length - 1;
        int cnt = 0;
        Arrays.sort(array);
        for (int i = lo; i <= hi; i++){
            while (lo < hi){
                if ((array[i] + array[lo] + array[hi]) > 0){
                    hi--;
                }
                else if ((array[i] + array[lo] + array[hi]) < 0){
                    lo++;
                }
                else {
                    lo++;
                    hi--;
                    cnt++;
                }
                if (array[i] > 0 && array[lo] > 0 && array[hi] > 0){
                    break;
                }
                if (array[i] < 0 && array[lo] < 0 && array[hi] < 0){
                    break;
                }
            }
        }
        return cnt;
    }
    // write a program which will generates a random set of integers
    public static int[] arrayOfInteger(int n){
        int[] array = new int[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(-100, 100);
        }
        return array;
    }
    public static void main(String[] args) {
        int[] array = arrayOfInteger(1000);
        int count = ThreeSumFaster(array);
        StdOut.println("Number of three numbers that sum to zero: " + count);
    }
}








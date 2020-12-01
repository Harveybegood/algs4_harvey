package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

/*
*   Faster 3-sum. As a warmup, develop an implementation TwoSumFaster that uses a linear algorithm to count
*   the pairs that sum to zero after the array is sorted(instead of the binary-search-based linearithmic algorithm).
*   Then apply a similar idea to develop a quadratic algorithm for the 3-sum problem.
*
* */
public class Ex15 {
    public static int TwoSumFasterCount(int[] array){
        int end = array.length - 1;
        int start = 0;
        int cnt = 0;
       if ((array[start] > 0 && array[end] > 0) || (array[start] < 0 && array[end] < 0)){
           return 0;
       }
       while (start < end){
           if (array[start] + array[end] > 0) end--;
           else if (array[start] + array[end] < 0) start++;
           else {
               StdOut.println(array[start] + " " + array[end]);
               cnt++;
               start++;
               end--;
           }
       }
       return cnt;
    }
    public static int ThreeSumFasterCount(int[] array){
        int start = 0;
        int end = array.length - 1;
        int cnt = 0;
        if ((array[start] > 0 && array[end] > 0) || (array[start] < 0 && array[end] < 0)){
            return 0;
        }
        for (int i = 0; i < array.length; i++){
            start = i + 1;
            end = array.length - 1;
            while (start < end){
                if (array[i] + array[start] + array[end] > 0) end--;
                else if (array[i] + array[start] + array[end] < 0) start++;
                else {
                    StdOut.println(array[i] + " " + array[start] + " " + array[end]);
                    cnt++;
                    start++;
                    end--;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Ex15 ex15 = new Ex15();
        int[] array1 = {-3, -2, 2, 3, 5,88};
        int[] array2 = {-10, -10, -10, 10, 20};
        StdOut.println(ex15.TwoSumFasterCount(array1));
        StdOut.println(ex15.ThreeSumFasterCount(array2));
    }
}

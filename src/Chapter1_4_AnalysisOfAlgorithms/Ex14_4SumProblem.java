package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
*   4-sum. Develop an algorithm for the 4-sum problem.
*
* */
public class Ex14_4SumProblem {
    public static int rank(int key, int[] a){
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
    public static int count(int[] a){
        Arrays.sort(a);
        int cnt = 0;
        for (int i = 0; i < a.length; i++){
            for (int j = i + 1; j < a.length; j++){
                for (int k = j + 1; k < a.length; k++){
                    if (rank(-a[i]-a[j]-a[k], a) > k){
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));
    }
}

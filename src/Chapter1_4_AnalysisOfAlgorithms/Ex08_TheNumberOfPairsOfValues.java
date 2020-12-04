package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/*
*   Write a program to determine the number of pairs of values in an input file that are equal. If your first try is
*   quadratic, think again and use Arrays.sort() to develop a linearithmic solution.
*
* */
public class Ex08_TheNumberOfPairsOfValues {
    public int pairsOfValues(int[] a){
        Arrays.sort(a);
        int cnt = 0;
        int hi = a.length;
        for (int i = 0; i < hi; i++){
            int lo = i;
            while (hi >= lo){
                int mid = lo + (hi - lo)/2;
                if (a[i] < a[mid]){
                    hi = mid - 1;
                }
                else if (a[i] > a[mid]){
                    lo = mid + 1;
                }
                else {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }
    public int[] generateIntArray(int n){
        int[] a = new int[n];
        for (int i = 0; i < n; i++){
            a[i] = StdRandom.uniform(-100, 100);
        }
        return a;
    }

    public static void main(String[] args) {
        Ex08_TheNumberOfPairsOfValues pairsOfValues = new Ex08_TheNumberOfPairsOfValues();
        int[] a = pairsOfValues.generateIntArray(10000);
        StdOut.print(pairsOfValues.pairsOfValues(a));
    }
}

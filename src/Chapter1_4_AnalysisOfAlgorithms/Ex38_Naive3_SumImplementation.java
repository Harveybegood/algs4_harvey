package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Naive 3-sum implementation. Run experiments to evaluate the following implementation of the inner loop of ThreeSum:
*
*   Do so by developing a version of DoublingTest that computes the ratio of the running times of this program and ThreeSum.
*
* */
public class Ex38_Naive3_SumImplementation {
    private int N;
    public void naive3_Sum(int N){
        int[] array = generateArray(N);
        int cnt = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                for (int k = 0; k < N; k++){
                    if (i < j && j < k){
                        if (array[i] + array[j] + array[k] == 0){
                            cnt++;
                        }
                    }
                }
            }
        }
        //return cnt;
    }
    public void threeSum(int N){
        int[] array = generateArray(N);
        int cnt = 0;
        for (int i = 0; i < N; i++){
            for (int j = i + 1; j < N; j++){
                for (int k = j + 1; k < N; k++){
                    if (array[i] + array[j] + array[k] == 0){
                        cnt++;
                    }
                }
            }
        }
        //return cnt;
    }
    enum differentSum{
        naive3Sum, threeSum
    }
    public double timeTrial(int N, String s){
        if (differentSum.valueOf(s).equals(differentSum.naive3Sum)){
            Stopwatch timer1 = new Stopwatch();
            naive3_Sum(N);
            return timer1.elapsedTime();
        }
        else {
            Stopwatch timer2 = new Stopwatch();
            threeSum(N);
            return timer2.elapsedTime();
        }
    }
    public void doublingTrial(String s){
        if (differentSum.valueOf(s).equals(differentSum.naive3Sum)){
            double prev = timeTrial(125, s);
            for (int N = 250; N < 10000; N += N){
                double time = timeTrial(N, s);
                StdOut.printf("%6d %5.1f %5.1f\n", N, time, time / prev);
                prev = time;
            }
        }
        else {
            double prev = timeTrial(125, s);
            for (int N = 250; N < 10000; N += N){
                double time = timeTrial(N, s);
                StdOut.printf("%6d %5.1f %5.1f\n", N, time, time / prev);
                prev = time;
            }
        }
    }
    public int[] generateArray(int N){
        int[] array = new int[N];
        for (int i = 0; i < N; i++){
            array[i] = StdRandom.uniform(-N, N);
        }
        return array;
    }

    public static void main(String[] args) {
        Ex38_Naive3_SumImplementation naive3_sumImplementation = new Ex38_Naive3_SumImplementation();
        naive3_sumImplementation.doublingTrial("naive3Sum");
        naive3_sumImplementation.doublingTrial("threeSum");
    }
}
/*
*           Chapter1_4AnalysisOfAlgorithms.Ex38_Naive3_SumImplementation
*
               250   0.0   3.3
               500   0.1   1.0
              1000   0.3   6.5
              2000   2.4   7.4
              4000  16.6   6.9
              8000 120.9   7.3
               250   0.0   1.4
               500   0.0   5.3
              1000   0.3   7.9
              2000   2.3   7.9
              4000  18.6   8.0
              8000  34.2   1.8
*
* */
package Chapter1_4_AnalysisOfAlgorithms;

import AnalysisOfAlgorithmsTest.BinarySearch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/*
*   Running times. Estimate the amount of time it would take to run TwoSumFast, TwoSum, ThreeSumFast and ThreeSum
*   on your computer to solve the problems for a file of 1 million numbers. Use DoublingRatio to do so.
*
* */
public class Ex41 {
    static class DoublingRatio{
        static int TwoSumFast(int[] numbers){
            int low = 0, high = numbers.length - 1, count = 0;
            Arrays.sort(numbers);
            while (low < high){
                if (numbers[low] + numbers[high] > 0) high--;
                else if (numbers[low] + numbers[high] < 0) low++;
                else {
                    count++;
                    low++;
                    high--;
                }
            }
            return count;
        }
        static int TwoSum(int[] numbers){
            int count = 0;
            int N = numbers.length;
            for (int i = 0; i < N; i++){
                for (int j = i + 1; j < N; j++){
                    if (numbers[i] + numbers[j] == 0){
                        count++;
                    }
                }
            }
            return count;
        }
        static int ThreeSumFast(int[] numbers){
            int count = 0;
            int N = numbers.length;
            Arrays.sort(numbers);
            for (int i = 0; i < N; i++){
                for (int j = i + 1; j < N; j++){
                    if (BinarySearch.rank(numbers, -numbers[i]-numbers[j]) > j){
                        count++;
                    }
                }
            }
            return count;
        }
        static int ThreeSum(int[] numbers){
            int count = 0;
            int N = numbers.length;
            for (int i = 0; i < N; i++){
                for (int j = i + 1; j < N; j++){
                    for (int k = j + 1; k < N; k++){
                        if (numbers[i] + numbers[j] + numbers[k] == 0){
                            count++;
                        }
                    }
                }
            }
            return count;
        }
    }

    static double timeTrials1(int N){
        int [] a = new int[N];
        for (int i = 0; i < N; i++){
            a[i] = StdRandom.uniform(-1000000, 1000000);
        }
        Stopwatch timer1 = new Stopwatch();
        DoublingRatio.TwoSum(a);
        return timer1.elapsedTime();
    }
    static double timeTrials2(int N){
        int [] a = new int[N];
        for (int i = 0; i < N; i++){
            a[i] = StdRandom.uniform(-1000000, 1000000);
        }
        Stopwatch timer2 = new Stopwatch();
        DoublingRatio.TwoSumFast(a);
        return timer2.elapsedTime();
    }
    static double timeTrials3(int N){
        int [] a = new int[N];
        for (int i = 0; i < N; i++){
            a[i] = StdRandom.uniform(-1000000, 1000000);
        }
        Stopwatch timer3 = new Stopwatch();
        DoublingRatio.ThreeSum(a);
        return timer3.elapsedTime();
    }
    static double timeTrials4(int N){
        int [] a = new int[N];
        for (int i = 0; i < N; i++){
            a[i] = StdRandom.uniform(-1000000, 1000000);
        }
        Stopwatch timer4 = new Stopwatch();
        DoublingRatio.ThreeSumFast(a);
        return timer4.elapsedTime();
    }

    public static void main(String[] args) {
        //double prevTime = 0.0;
        for (int N = 200; true; N += N){
            StdOut.printf("%5d %5.1f %5.1f %5.1f %5.1f\n", N, timeTrials1(N), timeTrials2(N), timeTrials3(N), timeTrials4(N));
        }
    }
}

package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/*
*   Running times. Estimate the amount of time it would take to run TwoSumFast, TwoSum, ThreeSumFast and ThreeSum on your
*   computer to solve the problems for a file of 1 million numbers. Use DoublingRatio to do so.
*
* */
public class Ex41_RunningTimes {
    public int rank(int key, int[] array){
        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi){
            int mid = lo + (hi-lo)/2;
            if (key < array[mid]){
                hi = mid - 1;
            }
            else if (key > array[mid]){
                lo = mid + 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }
    public int twoSumFast(int[] array){
        int cnt = 0;
        int length = array.length;
        Arrays.sort(array);
        for (int i = 0; i < length; i++){
            if (rank(array[i], array) != -1){
                cnt++;
            }
        }
        return cnt;
    }
    public int twoSum(int[] array){
        int cnt = 0;
        int length = array.length;
        for (int i = 0; i < length; i++){
            for (int j = i + 1; j < length; j++){
                if (array[i] + array[j] == 0){
                    cnt++;
                }
            }
        }
        return cnt;
    }
    public int threeSumFast(int[] array){
        int cnt = 0;
        int length = array.length;
        Arrays.sort(array);
        for (int i = 0; i < length; i++){
            for (int j = i + 1; j < length; j++){
                if (rank((array[i] + array[j]), array) != -1){
                    cnt++;
                }
            }
        }
        return cnt;
    }
    public int threeSum(int[] array){
        int cnt = 0;
        int length = array.length;
        for (int i = 0; i < length; i++){
            for (int j = i + 1; j < length; j++){
                for (int k = j + 1; k < length; k++){
                    if (array[i] + array[j] + array[k] == 0){
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
    public enum DifferentSum{
        twoSum, twoSumFast, threeSum, threeSumFast
    }
    public double timeTrial(int N, String sum){
        int[] array = new int[N];
        int M = -N;
        for (int i = 0; i < N; i++){
            array[i] = M;
            M++;
        }
        if (DifferentSum.twoSum.equals(DifferentSum.valueOf(sum))){
            Stopwatch timer = new Stopwatch();
            int cnt = twoSum(array);
            return timer.elapsedTime();
        }
        if (DifferentSum.twoSumFast.equals(DifferentSum.valueOf(sum))){
            Stopwatch timer = new Stopwatch();
            int cnt = twoSumFast(array);
            return timer.elapsedTime();
        }
        if (DifferentSum.threeSum.equals(DifferentSum.valueOf(sum))){
            Stopwatch timer = new Stopwatch();
            int cnt = threeSum(array);
            return timer.elapsedTime();
        }
        if (DifferentSum.threeSumFast.equals(DifferentSum.valueOf(sum))){
            Stopwatch timer = new Stopwatch();
            int cnt = threeSumFast(array);
            return timer.elapsedTime();
        }
        return 0.0;
    }
    public void doublingRatio(String sum){
        if (DifferentSum.twoSum.equals(DifferentSum.valueOf(sum))){
            double prev = timeTrial(100, "twoSum");
            for (int N = 200; N < 51201; N += N){
                double time = timeTrial(N, "twoSum");
                StdOut.printf("%5d %5.2f %5.2f\n", N, time, time / prev);
                prev = time;
            }
        }if (DifferentSum.twoSumFast.equals(DifferentSum.valueOf(sum))){
            double prev = timeTrial(100, "twoSumFast");
            for (int N = 200; N < 51201; N += N){
                double time = timeTrial(N, "twoSumFast");
                StdOut.printf("%5d %5.2f %5.2f\n", N, time, time / prev);
                prev = time;
            }
        }if (DifferentSum.threeSum.equals(DifferentSum.valueOf(sum))){
            double prev = timeTrial(100, "threeSum");
            for (int N = 200; N < 12801; N += N){
                double time = timeTrial(N, "threeSum");
                StdOut.printf("%5d %5.2f %5.2f\n", N, time, time / prev);
                prev = time;
            }
        }if (DifferentSum.threeSumFast.equals(DifferentSum.valueOf(sum))){
            double prev = timeTrial(100, "threeSumFast");
            for (int N = 200; N < 25601; N += N){
                double time = timeTrial(N, "threeSumFast");
                StdOut.printf("%5d %5.2f %5.2f\n", N, time, time / prev);
                prev = time;
            }
        }
    }

    public static void main(String[] args) {
        Ex41_RunningTimes runningTimes = new Ex41_RunningTimes();
        StdOut.println("twoSum");
        runningTimes.doublingRatio("twoSum");
        StdOut.println("twoSumFast");
        runningTimes.doublingRatio("twoSumFast");
        StdOut.println("threeSum");
        runningTimes.doublingRatio("threeSum");
        StdOut.println("threeSumFast");
        runningTimes.doublingRatio("threeSumFast");
    }
}
/*
*           twoSum
              200  0.00 Infinity
              400  0.00  3.00
              800  0.01  2.33
             1600  0.00  0.29
             3200  0.00  2.00
             6400  0.01  3.00
            12800  0.05  3.75
            25600  0.20  4.47
            51200  0.72  3.58
            *
            * 1000000 /100 * 4
            *
            twoSumFast
              200  0.00  0.00
              400  0.00   NaN
              800  0.00 Infinity
             1600  0.00  1.00
             3200  0.00  1.00
             6400  0.00  1.00
            12800  0.00  2.00
            25600  0.01  2.50
            51200  0.01  1.80
            *
            * 1000000 / 100 * 2
            *
            threeSum
              200  0.00  0.38
              400  0.01  2.00
              800  0.07 12.00
             1600  0.38  5.22
             3200  2.37  6.30
             6400 18.54  7.83
            12800 148.68  8.02
            *
            * 1000000 / 100 * 8
            *
            threeSumFast
              200  0.00 Infinity
              400  0.01  2.50
              800  0.01  1.60
             1600  0.04  4.63
             3200  0.17  4.62
             6400  0.79  4.62
            12800  3.35  4.24
            25600 14.30  4.27
            *
            * 1000000 / 100 * 4.3
            *
*
* */
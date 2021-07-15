package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.lang.ref.PhantomReference;
import java.util.Arrays;

/*
*   Problem sizes. Estimate the size of the largest value of P for which you can run TwoSumFast, TwoSum, ThreeSumFast, and ThreeSum
*   on your computer to solve the problems for a file of 2^p thousand numbers. Use DoublingRatio to do so.
*
* */
public class Ex42_ProblemSizes {
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
        TwoSumFast, TwoSum, ThreeSumFast, ThreeSum
    }
    public double timeTrial(int p, String sum){
        int maxBound = (int) (Math.pow(2, p) * Math.pow(10, 3));
        int[] array = new int[maxBound];
        for (int i = 0; i < maxBound; i++){
            array[i] = StdRandom.uniform(-maxBound, maxBound);
        }
        if (DifferentSum.valueOf(sum).equals(DifferentSum.TwoSumFast)){
            Stopwatch timer = new Stopwatch();
            twoSumFast(array);
            return timer.elapsedTime();
        }
        if (DifferentSum.valueOf(sum).equals(DifferentSum.TwoSum)){
            Stopwatch timer = new Stopwatch();
            twoSum(array);
            return timer.elapsedTime();
        }
        if (DifferentSum.valueOf(sum).equals(DifferentSum.ThreeSumFast)){
            Stopwatch timer = new Stopwatch();
            threeSumFast(array);
            return timer.elapsedTime();
        }
        if (DifferentSum.valueOf(sum).equals(DifferentSum.ThreeSum)){
            Stopwatch timer = new Stopwatch();
            threeSum(array);
            return timer.elapsedTime();
        }
        return -1.0;
    }
    public void doublingRatio(String sum){
        if (DifferentSum.valueOf(sum).equals(DifferentSum.TwoSumFast)){
            double prev = timeTrial(1, "TwoSumFast");
            for (int N = 2; N < 10; N++){
                double time = timeTrial(N, "TwoSumFast");
                StdOut.printf("%6d %5.2f % 5.2f\n", N, time, time / prev);
                prev = time;
            }
        }
        if (DifferentSum.TwoSum.equals(DifferentSum.valueOf(sum))){
            double prev = timeTrial(1, "TwoSum");
            for (int N = 2; N < 6; N++){
                double time = timeTrial(N, "TwoSum");
                StdOut.printf("%5d %5.2f %5.2f\n", N, time, time / prev);
                prev = time;
            }
        }
        if (DifferentSum.ThreeSum.equals(DifferentSum.valueOf(sum))){
            double prev = timeTrial(1, "ThreeSum");
            for (int N = 2; N < 6; N++){
                double time = timeTrial(N, "ThreeSum");
                StdOut.printf("%5d %5.2f %5.2f\n", N, time, time / prev);
                prev = time;
            }
        }
        if (DifferentSum.ThreeSumFast.equals(DifferentSum.valueOf(sum))){
            double prev = timeTrial(1, "ThreeSumFast");
            for (int N = 2; N < 6; N++){
                double time = timeTrial(N, "ThreeSumFast");
                StdOut.printf("%5d %5.2f %5.2f\n", N, time, time / prev);
                prev = time;
            }
        }
    }

    public static void main(String[] args) {
        Ex42_ProblemSizes problemSizes = new Ex42_ProblemSizes();
        StdOut.println("TwoSumFast");
        problemSizes.doublingRatio("TwoSumFast");
        StdOut.println("TwoSum");
        problemSizes.doublingRatio("TwoSum");
        StdOut.println("ThreeSumFast");
        problemSizes.doublingRatio("ThreeSumFast");
        StdOut.println("ThreeSum");
        problemSizes.doublingRatio("ThreeSum");

        /*
        *   Not sure the way to find the p to which my computer just reach for the maximum, anyway, when we get the ratio, we use it to
        *   estimate the largest p.
        *
        * */
    }
}

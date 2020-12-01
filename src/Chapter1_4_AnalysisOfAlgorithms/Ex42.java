package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/*
*   Problem sizes. Estimate the size of the largest value of P for which you can run TwoSumFast, TwoSum, ThreeSumFast,
*   and ThreeSum on your computer to solve the problems for a file of 2^p thousand numbers. Use DoublingRatio to do so.
*
* */
public class Ex42 {
    private double timeTrials(int n, int sumMethod){
        int max = 1000000;
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++){
            numbers[i] = StdRandom.uniform(-max, max);
        }
        Stopwatch timer1 = new Stopwatch();
        switch (sumMethod){
            case 0:
                twoSum(numbers);
                break;
            case  1:
                twoSumFast(numbers);
                break;
            case 2:
                threeSum(numbers);
                break;
            case 3:
                threeSumFast(numbers);
                break;
        }
        return timer1.elapsedTime();
    }

    private int twoSum(int[] numbers){
        int count = 0;
        for (int i = 0; i < numbers.length; i++){
            for (int j = i + 1; j < numbers.length; j++){
                if (numbers[i] + numbers[j] == 0){
                    count++;
                }
            }
        }
        return count;
    }
    private int twoSumFast(int[] numbers){
        Arrays.sort(numbers);
        int count = 0;
        for (int i = 0; i < numbers.length; i++){
            if (binarySearch(numbers, -numbers[i], 0, numbers.length-1) > i)
                count++;
        }
        return count;
    }
    private int threeSum(int[] numbers){
        int count = 0;
        for (int i = 0; i < numbers.length; i++){
            for (int j = i + 1; j < numbers.length; j++){
                for (int k = j + 1; k < numbers.length; k++){
                    if (numbers[i] + numbers[j] + numbers[k] == 0){
                        count++;
                    }
                }
            }
        }
        return count;
    }
    private int threeSumFast(int[] numbers){
        Arrays.sort(numbers);
        int count = 0;
        for (int i = 0; i < numbers.length; i++){
            for (int j = i + 1; j < numbers.length; j++){
                if (binarySearch(numbers, -numbers[i] - numbers[j], 0, numbers.length-1) > j)
                    count++;
            }
        }
        return count;
    }

    private int binarySearch(int[] numbers, int target, int low, int high){
        if (low > high) return -1;
        int middle = low + (high - low) / 2;
        if (numbers[middle] > target) return binarySearch(numbers, target, low, middle - 1);
        else if (numbers[middle] < target) return binarySearch(numbers, target, middle + 1, high);
        else return middle;
    }

    private static void runExperiments(int sumMethod){
        Ex42 ex42 = new Ex42();
        double previousTime = ex42.timeTrials(128, sumMethod);
        StdOut.printf("%6s %7s %5s\n", "N", "Time", "Ratio");

        for (int n = 256; n <= 8192; n += n){
            double time = ex42.timeTrials(n, sumMethod);
            StdOut.printf("%6d %7.1f ", n, time);
            StdOut.printf("%5.1f\n", time/previousTime);
            previousTime = time;
        }
    }

    public static void main(String[] args) {
        //twoSum
        StdOut.println("twoSum");
        runExperiments(0);
        //twoSumFast
        StdOut.println("twoSumFast");
        runExperiments(1);
        //threeSum
        StdOut.println("threeSum");
        runExperiments(2);
        //threeSumFast
        StdOut.println("threeSumFast");
        runExperiments(3);
    }
}

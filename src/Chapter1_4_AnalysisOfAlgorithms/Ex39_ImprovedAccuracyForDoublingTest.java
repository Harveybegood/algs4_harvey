package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Improved accuracy for doubling test. Modify DoublingRatio to take a second command-line argument that specifies the number
*   of calls to make to timeTrial() for each value for N. Run your program for 10, 100, 1,000 trails and comment on the precision
*   of the results.
*
* */
public class Ex39_ImprovedAccuracyForDoublingTest {

    // 3-sum
    public int threeSum(int[] array){
        int cnt = 0;
        for (int i = 0; i < array.length; i++){
            for (int j = i + 1; j < array.length; j++){
                for (int k = j + 1; k < array.length; k++){
                    if (array[i] + array[j] + array[k] == 0){
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
    public double timeTrial(int N){
        int Max = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++){
            a[i] = StdRandom.uniform(-Max, Max);
        }
        Stopwatch timer = new Stopwatch();
        int cnt = threeSum(a);
        return timer.elapsedTime();
    }
    public void doublingRatio(int numberOfCalls){
        double time = 0.0;
        int numberOfTrials = numberOfCalls;
        for (int N = 250; N < 1001; N += N){
            while (numberOfCalls != 0){
                time += timeTrial(N);
                numberOfCalls--;
            }
            StdOut.printf("%6d %3d %5.2f\n", N, numberOfTrials, time);
            numberOfCalls = numberOfTrials;
        }
    }

    public static void main(String[] args) {
        int N1 = Integer.parseInt(args[0]);
        int N2 = Integer.parseInt(args[1]);
        int N3 = Integer.parseInt(args[2]);
        Ex39_ImprovedAccuracyForDoublingTest improvedAccuracyForDoublingTest = new Ex39_ImprovedAccuracyForDoublingTest();
        StdOut.println("The number of calls to make to timeTrial() for 10");
        improvedAccuracyForDoublingTest.doublingRatio(N1);
        StdOut.println("The number of calls to make to timeTrial() for 100");
        improvedAccuracyForDoublingTest.doublingRatio(N2);
        StdOut.println("The number of calls to make to timeTrial() for 1000");
        improvedAccuracyForDoublingTest.doublingRatio(N3);
    }
}
/*
*
*       The number of calls to make to timeTrial() for 10
           250  10  0.11
           500  10  0.43
          1000  10  1.31
        The number of calls to make to timeTrial() for 100
           250 100  0.11
           500 100  0.97
          1000 100  8.24
        The number of calls to make to timeTrial() for 1000
           250 1000  1.30
           500 1000 10.97
          1000 1000 83.93
* */
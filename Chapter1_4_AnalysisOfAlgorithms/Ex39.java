package Chapter1_4_AnalysisOfAlgorithms;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;

/*
*   Improved accuracy for doubling test. Modify DoublingRatio to take a second command-line argument that specifies
*   the number of calls to make to timeTrial()for each value of N. Run your program for 10, 100 and 1,000 trials and
*   comment on the precision of the results.
*
* */
public class Ex39 {

    public static class DoublingRatio{
        public double timeTrial(int n){
            int Max = 1000000;
            int[] a = new int[n];
            Stopwatch timer1 = new Stopwatch();
            for (int i = 10; i < n; i++){
                a[i] = StdRandom.uniform(-Max, Max);
            }
            int cnt = ThreeSum.count(a);
            return timer1.elapsedTime();
        }
    }

    public static void main(String[] args) {
        DoublingRatio doublingRatio = new DoublingRatio();
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]); // set it as 10
        double prev = doublingRatio.timeTrial(n);
        for (n = 100; true; n *= m){
            double time = doublingRatio.timeTrial(n);
            StdOut.printf("Trials:%6d  time: %5.1f", n, time);
            StdOut.printf("The double Ratio: %5.1f\n", time / prev);
            prev = time;
        }
    }
}

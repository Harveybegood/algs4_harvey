package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;

/*
*   Modify DoublingTest to use StdDraw to produce plots like the standard and log-log plots in the text,
*   rescaling as necessary so that the plot always fills a substantial portion of the window
*
* */
public class Ex3 {
    static class DoublingTest{
        public static double timeTrial(int N){
            int MAX = 1000000;
            int[] a = new int[N];
            for (int i = 0; i < N; i++){
                a[i] = StdRandom.uniform(-MAX, MAX);
            }
            Stopwatch timer = new Stopwatch();
            ThreeSum.count(a);
            return timer.elapsedTime();
        }
        public static void drawStd(int N){
            StdDraw.setXscale(0,N);
            StdDraw.setYscale(0,1);
            StdDraw.setPenRadius(.001);
            StdDraw.setPenColor(StdDraw.BLUE);
            for (int i = 0; i < N; i++){
                StdDraw.point(i, timeTrial(i));
            }
        }
        public static void drawLgN(int N){
            StdDraw.setXscale(0,N);
            StdDraw.setYscale(-10, 10);
            StdDraw.setPenRadius(.001);
            StdDraw.setPenColor(StdDraw.GREEN);
            for (int i = 0; i < N; i++){
                StdDraw.point(i, Math.log(timeTrial(i)));
            }
        }
    }

    public static void main(String[] args) {
        DoublingTest.drawStd(3000);
        DoublingTest.drawLgN(3000);
    }
}

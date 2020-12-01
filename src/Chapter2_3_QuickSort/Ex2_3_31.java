package Chapter2_3_QuickSort;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.awt.*;

/*
*   Histogram of running time. Write a program that takes command-line arguments N and T, does T trials of the experiment
*   of running quick sort on an array of N random double values, and plots a histogram of the observed running times. Run
*   your program for N = 10^3, 10^4, 10^5 and 10^6, with T as large as you can afford to make the curves smooth. Your
*   main challenge for this exercise is to appropriately scale the experimental results.
*
* */
public class Ex2_3_31 {
    private static double[] generateArrayWithRandomDoubleValues(int n){
        double[] array = new double[n];
        for (int i = 0; i < n; i++){
            array[i] = StdRandom.uniform(0.0, (1.0) * n);
        }
        return array;
    }
    private static double trialOfExperiment(int n){
        Stopwatch timer = new Stopwatch();

            // quick sort method running the number times of t
        quickSort(generateArrayWithRandomDoubleValues(n));

        return timer.elapsedTime();
    }
    private static int partition(double[] array, int low, int high){
        int i = low, j = high + 1;
        double pivot = array[low];
        double temp;
        while (true){
            while (array[++i] < pivot){
                if (i == high)
                    break;
            }
            while (array[--j] > pivot){
                if (j == low)
                    break;
            }
            if (i >= j)
                break;
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        temp = array[low];
        array[low] = array[j];
        array[j] = temp;
        return j;
    }
    private static void quickSort(double[] array){
        StdRandom.shuffle(array);
        quickSort(array, 0, array.length - 1);
    }
    private static void quickSort(double[] array, int low, int high){
        if (high <= low)
            return;
        int j = partition(array, low, high);
        quickSort(array, low, j - 1);
        quickSort(array, j + 1, high);
    }
    // plot histogram of running time
    private static void printHistogram(int n, int t){
    /*    x the <em>x</em>-coordinate of the center of the rectangle
     * @param  y the <em>y</em>-coordinate of the center of the rectangle
     * @param  halfWidth one half the width of the rectangle
     * @param  halfHeight one half the height of the rectangle*/

        double[] timeCost = new double[t];
        for (int i = 0; i < t; i++){
            timeCost[i] = trialOfExperiment(n);
        }
        double max = 0;
        for (double time : timeCost){
            if (time > max)
                max = time;
        }
        StdDraw.setXscale(-0.5, t + 1);
        StdDraw.setYscale(-0.001, max);
        StdDraw.setPenRadius(.2);
        StdDraw.setPenColor(Color.BLUE);
        for (int i = 1; i < t; i++){
            //StdDraw.clear();
            //double timeCost = trialOfExperiment(n);
            StdOut.println(timeCost[i]);
            double x = i + 0.5;
            double y = timeCost[i] / 2.0;
            double rw = 0.4;
            double rh = timeCost[i] / 2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
            //StdDraw.clear();
        }
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){

        }
    }

    public static void main(String[] args) {
        //int t = 100;
        int[] n = {3, 4, 5, 6};
        //int[] t = {3, 4, 5, 6};
        for (int i = 0; i < 4; i++){
            StdDraw.clear();
            printHistogram((int) Math.pow(10, n[i]),50);
        }
    }
}

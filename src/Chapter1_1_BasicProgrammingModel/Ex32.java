package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

import java.awt.*;

/*
*   Histogram. Suppose that the standard input stream is a sequence of double values. Write a program that takes an
*   integer N and two double values l and r from the command line and uses StdDraw to plot a histogram of the count
*   of the numbers in the standard input stream that fall in each of the N intervals defined by dividing (l,r) into
*   N equal-sized intervals.
*
* */
public class Ex32 {
    public static void main(String[] args) {
        double[] values = StdIn.readAllDoubles();
        int N = Integer.parseInt(args[0]);
        double l = Double.parseDouble(args[1]);
        double r = Double.parseDouble(args[2]);
        int[] numberOfValue = count(values, N, l, r);
        plotHistogram(numberOfValue, N, l, r);
    }

    public static int[] count(double[] values, int N, double l, double r){
        int[] numberOfValue = new int[N];

        for (int i = 0; i < N; i++){
            for (int j = 0; j < values.length; j++){
                if (values[j] == (l - r) * (i + 1) / N){
                    numberOfValue[i]++;
                }
            }
        }
        return numberOfValue;
    }
    public static void plotHistogram(int[] numberOfValue, int N, double l, double r){
        StdDraw.setXscale(0.0, 0.0);
        StdDraw.setYscale(0.0, 0.0);
        StdDraw.setPenColor(Color.black);
        for (int i = 0; i < N; i++){
            StdDraw.filledRectangle(i, 0, (l - r) / 2, numberOfValue[i] / 2);
        }
    }
}

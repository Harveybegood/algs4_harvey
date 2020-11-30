package test;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class VisualAccumulator {
    private int n;
    //private double total;
    public VisualAccumulator(double N, double Max){
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, Max);
        StdDraw.setPenRadius(.003);
    }
    public void addDataValue(double val){
        n++;
        //total += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(n, val);
        //StdDraw.setPenColor(StdDraw.BOOK_RED);
        //StdDraw.point(n, total / n);
    }
   /* public double mean(){
        return total / n;
    }
    public String toString(){
        return "Mean (" + n + " values)" + String.format("%5.1f",mean());
    }*/

    public static void main(String[] args) {
        int T =  5000;
        VisualAccumulator visualAccumulator = new VisualAccumulator(T, 1);
        for (int t = 0; t < T; t++){
            visualAccumulator.addDataValue(StdRandom.random());
        }
        StdOut.println(visualAccumulator);
    }
}

package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

/*
*   Random connections. Write a program that takes as command-line arguments an integer N and a double value p
*   (between 0 and 1), plots N equally spaced dots of size .05 on the circumference of a circle, and then, with
*   probability p for each pair of points, draws a gray line connecting them.
*
* */
public class Ex31 {
    public static void main(String[] args) {
        //int N = Integer.parseInt(args[0]);
        //double p = Double.parseDouble(args[1]);
         /*for (int i = 0; i < N; i++){
             plotDots(i);
         }*/
         int N = 12;
         double p = 0.2;
        plotDots(N, p);
    }
    public static void plotDots(int N, double p){
        // determine their positions for N equally spaced dots with size of .05
        // first step, to assume that the circumference of a circle and then put N equally spaced dots on it.
        //double circumference = 2*Math.PI;
        double angle = 360;
        double[][] point = new double[N][2];
        StdDraw.setXscale();
        StdDraw.setYscale();
        StdDraw.setPenColor(Color.BLUE);
        double lengthCircle = .4;
        StdDraw.circle(lengthCircle, lengthCircle, .3);
        int k = -1;
        // separate the angle from N and then draw all these equally spaced dots
        for (int i = 0; i < angle; i += angle / N){
            double radiant = Math.toRadians(i);
            StdOut.println(radiant);
            StdDraw.circle(lengthCircle + 0.3 * Math.cos(radiant), lengthCircle + 0.3 * Math.sin(radiant), .01);
            k++;
            point[k][0] = lengthCircle + 0.3 * Math.cos(radiant);
            point[k][1] = lengthCircle + 0.3 * Math.sin(radiant);
        }
        StdDraw.setPenColor(Color.gray);
        //StdDraw.setPenRadius();
        for (int i = 0; i < N; i++){
            for (int j = i; j < N; j++){
                if (StdRandom.bernoulli(p)){
                    StdDraw.line(point[i][0], point[i][1], point[j][0], point[j][1]);
                }
            }
        }
    }


}

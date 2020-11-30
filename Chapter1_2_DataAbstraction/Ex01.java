package Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Write a Point2D client that takes an integer value N from the command line, generates N random points in the unit
*   square, and computes the distance separating the closest pair of points.
*
* */
public class Ex01 {
    public static void main(String[] args) {
        //int N = Integer.parseInt(args[0]);
        int N = 100;
        //double x = Double.parseDouble(args[1]);
        //double y = Double.parseDouble(args[2]);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(.005);

        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++){
            int x = StdRandom.uniform(0, 100);
            int y = StdRandom.uniform(0, 100);
            points[i] = new Point2D(x, y);
            points[i].draw();
        }
        double temp = points[0].distanceTo(points[1]);
        double closestPairDistance = 0.0;
        double distanceOfPoints;
        for (int i = 0; i < N; i++){
            for (int j = i + 1; j < N; j++){
                distanceOfPoints = points[i].distanceTo(points[j]);
                if (temp > distanceOfPoints){
                    closestPairDistance = distanceOfPoints;
                    temp = distanceOfPoints;
                }else {
                    closestPairDistance = temp;
                }
            }
        }
        StdOut.println(closestPairDistance);
    }
}

package Test;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class PointsConnected {
    public static void main(String[] args) {
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        double originalX = 50;
        double originalY = 80;
        StdDraw.setPenColor(Color.red);
        StdDraw.setPenRadius(.02);
        StdDraw.point(originalX, originalY);
        for (int i = 1; i <= 6; i++){
            StdDraw.setPenRadius(.02);
            StdDraw.setPenColor(Color.red);
            double x = i * 5;
            double y = i * 7;
            StdDraw.point(x, y);

            double o = i * 12;
            double p = i * 15;
            /*StdDraw.setPenRadius(.02);
            StdDraw.setPenColor(Color.GREEN);*/
            StdDraw.point(o, p);
            Point2D point2D2 = new Point2D(o, p);
            Point2D point2D1 = new Point2D(x, y);
            StdDraw.setPenRadius(.005);
            StdDraw.setPenColor(Color.blue);
            point2D1.drawTo(point2D2);
        }
    }
}

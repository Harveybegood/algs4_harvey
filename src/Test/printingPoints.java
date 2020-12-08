package Test;

import edu.princeton.cs.algs4.Point2D;

public class printingPoints {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++){
            double x = Math.random();
            double y = Math.random();
            Point2D points = new Point2D(x, y);
            points.draw();
        }
    }
}

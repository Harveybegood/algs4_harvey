package test;

import edu.princeton.cs.algs4.StdDraw;

public class drawARectangle {
    public static void main(String[] args) {
        StdDraw.setXscale(-1, 10);
        StdDraw.setYscale(-1, 10);
        for (int i = 0; i < 100; i++){
            double x = i;
            double y = i / 2.0;
            double rw = 1.0 / 2;
            double rh = i / 2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }
}

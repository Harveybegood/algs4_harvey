package Test;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.awt.*;

public class drawRectangle {
    public static void main(String[] args) {
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0,1000);
        StdDraw.setPenRadius(.003);
        StdDraw.setPenColor(Color.red);
        for (int i = 0; i < 1000; i++){
            StdDraw.filledRectangle(2.0*i, 1000.0 - 10*i,15,15);
            StdOut.println();
            StdDraw.clear();
        }
    }
}

package test;


import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class pointsDraw {
    public static void draw(int N){
        int n = (int)Math.sqrt(N);
        StdDraw.setXscale(-2, n);
        StdDraw.setYscale(-2, n);
        StdDraw.setPenRadius(.006);
        StdDraw.setPenColor(Color.red);
        for (int i = n-1; i > 0; i--){
            for (int j = 0; j < n; j++){
                StdDraw.point(j, i);
            }
        }
    }

    public static void main(String[] args) {
        draw(1000);
    }
}

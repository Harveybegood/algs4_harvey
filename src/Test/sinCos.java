package Test;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.awt.*;

public class sinCos {
    public static void main(String[] args) {
        int n = 100;
        StdOut.println(2*Math.PI);
        plotCircle(n);
    }
    public static void plotCircle(int n){
        StdDraw.setXscale();
        StdDraw.setYscale();
        StdDraw.setPenRadius(.05);
        StdDraw.setPenColor(Color.GREEN);
        for (int i = n; i >10; i -= 10){
            StdDraw.circle(Math.cos(360/i)*.3, Math.sin(360/i)*.3, .3);
        }
    }
}



/*
*   r,
*
*
*
* */
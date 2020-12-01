package test;

import edu.princeton.cs.algs4.*;

public class interval2D {
    public static void main(String[] args) {
     /*   double xlo = Double.parseDouble(args[0]);
        double xhi = Double.parseDouble(args[1]);
        double ylo = Double.parseDouble(args[2]);
        double yhi = Double.parseDouble(args[3]);
        int T = Integer.parseInt(args[4]);*/
        double xlo = .2;
        double xhi = .5;
        double ylo = .5;
        double yhi = .6;
        int T = 10000;
        Interval1D xinterval = new Interval1D(xlo, xhi); // intervals on the line
        Interval1D yinterval = new Interval1D(ylo, yhi);
        Interval2D box = new Interval2D(xinterval, yinterval);
        box.draw();
        StdOut.println("Expected as 0.3 -- " + xinterval.length());
        StdOut.println("Expected as 0.5 -- " + xinterval.max());
        Counter c = new Counter("hits");
        for (int t = 0; t < T; t++){
            double x = Math.random();
            double y = Math.random();
            Point2D p = new Point2D(x, y); // points in the plane
            if (box.contains(p)) c.increment();
            //else p.draw();
        }
        StdOut.println(c);
        StdOut.println(box.area());
    }
}

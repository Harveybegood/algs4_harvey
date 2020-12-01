package Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.*;

/*
*   Write an Interval2D client that takes command-line arguments N, min, and max and generates N random 2D
*   intervals whose width and height are uniformly distributed between min and max in the unit square.
*   Draw them on StdDraw and print the number of pairs of intervals that intersect and the number of intervals that
*   are contained in one another.
*
* */
public class Ex03 {
    public static void main(String[] args) {
        int countOfIntersect = 0;
        int countOfContained = 0;
        int N = Integer.parseInt(args[0]);
        int min = Integer.parseInt(args[1]);
        int max = Integer.parseInt(args[2]);
        Interval2D[] intervals2 = new Interval2D[N];
        Interval1D[] intervalsX = new Interval1D[N];
        Interval1D[] intervalsY = new Interval1D[N];
        StdDraw.setYscale(0, 200);
        StdDraw.setXscale(0, 200);
        StdDraw.setPenRadius(.005);
        for (int i = 0; i < N; i++){
            intervalsX[i] = new Interval1D(StdRandom.uniform(min, min + 20), StdRandom.uniform(max - 20, max));
            intervalsY[i] = new Interval1D(StdRandom.uniform(min, min + 20), StdRandom.uniform(max - 20, max));
            intervals2[i] = new Interval2D(intervalsX[i], intervalsY[i]);
            intervals2[i].draw();
            double x = StdRandom.uniform(min, max);
            double y = StdRandom.uniform(min, max);
            Point2D p = new Point2D(x, y);
            if (intervals2[i].contains(p)){
                countOfContained++;
            }
            else {
                p.draw();
            }
        }
        for (int i = 0; i < N; i++){
            for (int j = i + 1; j < N; j++){
                if (intervals2[i].intersects(intervals2[j])){
                    countOfIntersect++;
                }
            }
        }
        StdOut.println(countOfIntersect);
        StdOut.println(countOfContained);
    }
}

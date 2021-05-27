package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/*
*   3-collinearity. Suppose that you have an algorithm that takes as input N distinct points in the plane and can return
*   the number of triples that fall on the same line. Show that you can use this algorithm to solve the 3-sum problem.
*   Strong hing: Use algebra to show that(a, a^3), (b, b^3), and (c, c^3)are collinear if and only if a + b + c = 0.
*
* */
public class Ex26_3Collinearity {
    // if there are same slope for each two points of three points then they will be on the same line. we put all points into
    public int countTriplesBasedOnSlope(int n){
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++){
            double x = StdRandom.uniform(100);
            double y = StdRandom.uniform(100);
            points[i] = new Point2D(x, y);
        }
        return countTriplesBasedOnSlope(points);
    }
    private int countTriplesBasedOnSlope(Point2D[] points){
        /*int numberOfLines = (points.length * (points.length - 1)) / 2;
        double[] slope = new double[numberOfLines];*/
        double slope1;
        double slope2;
        int numberOfTriples = 0;
        for (int i = 0; i < points.length; i++){
            for (int j = i + 1; j < points.length; j++){
                slope1 = (points[j].y() - points[i].y()) / (points[j].x() - points[i].x());
                for (int k = j + 1; k < points.length; k++){
                    slope2 = (points[k].y() - points[j].y()) / (points[k].x() - points[j].x());
                    if (slope1 == slope2){
                        numberOfTriples++;
                    }
                }
            }
        }
        return numberOfTriples;
    }

    public static void main(String[] args) {
        Ex26_3Collinearity collinearity = new Ex26_3Collinearity();
        int numberOfTriples = collinearity.countTriplesBasedOnSlope(50);
        StdOut.println("The number of triples on the same line: " + numberOfTriples);
    }

    /*
     *   if three points are on the same line, we can come up with the following two formulas:
     *           (b^3 - a^3) / (b - a) and (c^3 - b^3) / (c - b)
     *           (b^3 - a^3) / (b - a) = (b - a)(b^2 + ba + a^2) / (b - a) = b^2 + ba + a^2
     *           (c^3 - b^3) / (c - b) = (c - b)(c^2 + cb + b^2) / (c - b) = c^2 + cb + b^2
     *
     *           Due to both lines with same slope, then:
     *           b^2 + ba + a^2 = c^2 + cb + b^2
     *           => a^2 + ba - cb - c^2 = 0
     *           => (a - c)(a + b + c) = 0
     *           if a - c = 0, then point a and point c are the same point, which doesn't hold for three points
     *           hence a + b + c = 0;
     * */

}

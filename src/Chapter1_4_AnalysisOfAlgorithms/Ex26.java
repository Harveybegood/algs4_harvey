package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*   3-collinearity. Suppose that you have an algorithm that takes as input N distinct points in the plane and
*   can return the number of triples that fall on the same line. Show that you can use this algorithm to solve
*   the 3-sum problem. Strong hint: Use algebra to show that (a, a3), (b, b3), and (c, c3) are collinear if and
*   only if a + b + c = 0;
*
* */
public class Ex26 {
    private int countTriples(Point2D[] points){
        boolean allYCoordinatesAreXCubic = true;
        for (Point2D point2D : points){
            if (point2D.y() != Math.pow(point2D.x(),3)){
                allYCoordinatesAreXCubic = false;
                break;
            }
        }
        if (allYCoordinatesAreXCubic){
            return countTriplesWithCubicYs(points);
        }else {
            return countTriplesUsingSlopes(points);
        }
    }
    private int countTriplesUsingSlopes(Point2D[] points){
        // if (y1-y2)*(x-x3) == (y1-y3)*(x1-x2) then then points are collinear
        Map<Double, List<Integer>> slopes = new HashMap<>();
        for (int i = 0; i < points.length; i++){
            for (int j = i + 1; j < points.length; j++){
                double slope;
                if (points[i].x() - points[j].x() == 0){
                    slope = 0;
                }else {
                    slope = (points[i].y() - points[j].y()) / (points[i].x() - points[j].x());
                }
                List<Integer> pointIndexes = slopes.get(slope);
                if (pointIndexes == null){
                    List<Integer> indexes = new ArrayList<>();
                    indexes.add(i);
                    indexes.add(j);
                    slopes.put(slope, indexes);
                }else {
                    if (!slopes.get(slope).contains(i))slopes.get(slope).add(i);
                    if (!slopes.get(slope).contains(j))slopes.get(slope).add(j);
                }
            }
        }
        int triples = 0;
        for (int i = 0; i < points.length; i++){
            for (int j = i + 1; j < points.length; j++){
                double slope;
                if (points[i].x() - points[j].x() == 0) slope = 0;
                else slope = (points[i].y() - points[j].y()) / (points[i].x() - points[j].x());
                List<Integer> pointIndexes = slopes.get(slope);
                if (pointIndexes != null){
                    for (Integer index : pointIndexes){
                        if (index > i && index > j){
                            triples++;
                            break;
                        }
                    }
                }
            }
        }
        return triples;
    }
    private int countTriplesWithCubicYs(Point2D[] points){
        Map<Double, List<Integer>> pointsX = new HashMap<>();
        for (int i = 0; i < points.length; i++){
            List<Integer> pointIndexes = pointsX.get(points[i].x());
            if (pointIndexes == null){
                List<Integer> indexes = new ArrayList<>();
                indexes.add(i);
                pointsX.put(points[i].x(), indexes);
            }else {
                pointsX.get(points[i].x()).add(i);
            }
        }
        int triples = 0;
        for (int i = 0; i < points.length; i++){
            for (int j = i + 1; j < points.length; j++){
                double complementPoint = -1 * (points[i].x() + points[j].x());
                List<Integer> pointIndexes = pointsX.get(complementPoint);
                if (pointIndexes != null){
                    for (Integer index : pointIndexes){
                        if (index > i && index < j){
                            triples++;
                            break;
                        }
                    }
                }
            }
        }
        return triples;
    }

    public static void main(String[] args) {
        Ex26 ex26 = new Ex26();
        Point2D[] points = new Point2D[6];
        for (int i = 0; i < points.length; i++){
            Point2D point = new Point2D(i, i + 1.5);
            points[i] = point;
        }
        int numberOfTriples1 = ex26.countTriples(points);
        StdOut.println("Number of triples: " + numberOfTriples1 + " Expected as : 10");
        Point2D pointA = new Point2D(-3, 4);
        Point2D pointB = new Point2D(3, 2);
        Point2D pointC = new Point2D(6, 1);
        Point2D[] points2 = {pointA, pointB, pointC};
        int numberOfTriples2 = ex26.countTriples(points2);
        StdOut.println("Number of triples: " + numberOfTriples2 + " Expected as : 1");
        Point2D pointD = new Point2D(6, 1);
        Point2D[] points3 = {pointA, pointB, pointC, pointD};
        int numberOfTriples3 = ex26.countTriples(points3);
        StdOut.println("Number of triples: " + numberOfTriples3 + " Expected as : 3");
        // Case with cubic y coordinate
        Point2D pointE = new Point2D(1, 1);
        Point2D pointF = new Point2D(2, 8);
        Point2D pointG = new Point2D(-3, -27);
        Point2D[] points4 = {pointE, pointF, pointG};
        int numberOfTriples4 = ex26.countTriples(points4);
        StdOut.println("Number of triples: " + numberOfTriples4 + " Expected as : 1");
    }
}

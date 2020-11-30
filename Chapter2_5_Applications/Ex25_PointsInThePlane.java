package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Comparator;

/*
*   Points in the plane. Write three static comparators for the Point2D data type of page 77, one that compares points
*   by their x coordinate, one that compares them by their y coordinate, and one that compares them by distance from the
*   the origin. Write two non-static comparators for the Point2D data type, one that compares them by their distance to
*   a specified point and one that compares them by their polar angle with respect to a specified point.
* 
* */
public class Ex25_PointsInThePlane {
    public class Point2D implements Comparable<Point2D>{
        private double pointX;
        private double pointY;
        // initializes a new point (x, y)
        public Point2D(double pointX, double pointY){
            if (Double.isInfinite(pointX) || Double.isInfinite(pointY))
                throw new IllegalArgumentException("Coordinate must be finite");
            if (Double.isNaN(pointX) || Double.isNaN(pointY))
                throw new IllegalArgumentException("Coordinate cannot be null");
            if (pointX == 0.0)
                this.pointX = 0.0;
            else
                this.pointX = pointX;
            if (pointY == 0.0)
                this.pointY = 0.0;
            else
                this.pointY = pointY;

        }
        // return the x-coordinate
        public double x(){return pointX;}
        // return the y-coordinate
        public double y(){return pointY;}
        // return the polar radius of this point
        public double r(){return Math.sqrt(pointX * pointX + pointY * pointY);}
        // return the angle of this point in polar coordinate
        public double theta(){return Math.atan2(pointY, pointX);}
        // return the angle between this point and that point
        private double angleTo(Point2D that){
            double dx = that.pointX - this.pointX;
            double dy = that.pointY - this.pointY;
            return Math.atan2(dy, dx);
        }
        // return true if a->b->c is a counterclockwise turn
        public int ccw(Point2D a, Point2D b, Point2D c){
            double area2 = (b.pointX - a.pointX) * (c.pointY - a.pointY) - (b.pointY - a.pointY) * (c.pointX - a.pointX);
            if (area2 < 0) return -1;
            else if (area2 > 0) return 1;
            else return 0;
        }
        // return the Euclidean distance between this point and that point
        public double distanceTo(Point2D that){
            double dx = this.pointX - that.pointX;
            double dy = this.pointY - that.pointY;
            return Math.sqrt(dx * dx + dy * dy);
        }
        // return the square of the Euclidean distance between this point and that point
        public double distanceSquaredTo(Point2D that){
            double dx = this.pointX - that.pointX;
            double dy = this.pointX - that.pointY;
            return dx * dx + dy * dy;
        }
        // compare two points by y-coordinate, breaking ties by x-coordinate.
        // formally, the invoking point(x0, y0) is less than the argument point(x1, y1 if and only if either
        // (y0 < y1) or if(y0 == y1) and (x0 < x1).
        public int compareTo(Point2D that){
            if (this.pointX < that.pointX){
                return -1;
            }
            else if (this.pointX > that.pointX){
                return 1;
            }
            else if (this.pointY < that.pointY){
                return -1;
            }
            else if (this.pointY > that.pointY){
                return 1;
            }
            else {
                return 0;
            }
        }
        // compare two points by polar angle(between 0 and 2PI)with respect to this point
        public Comparator<Point2D> polarOrder(){return new PolarOrder();}
        // compare two points by atan2() angle(between pi and pi)with respect to this point
        public Comparator<Point2D> atan2Order(){return new Atan2Order();}
        // compare two points by distance to this point
        public Comparator<Point2D> distanceToOrder(){return new DistanceToOrder();}
        // compare points according to their x-coordinate
        private class XOrder implements Comparator<Point2D> {
            public int compare(Point2D p, Point2D q){
                if (p.pointX < q.pointX) return -1;
                else if (p.pointX > q.pointX) return 1;
                else return 0;
            }
        }
        // compare points according to their y-coordinate
        private class YOrder implements Comparator<Point2D>{
            public int compare(Point2D p, Point2D q){
                if (p.pointX < q.pointX) return -1;
                else if (p.pointX > q.pointX) return 1;
                else return 0;
            }
        }
        // compare points according to their polar radius
        private  class ROrder implements Comparator<Point2D>{
            public int compare(Point2D p, Point2D q){
                double delta = (p.pointX * p.pointX + p.pointY * p.pointY) - (q.pointX * q.pointX + q.pointY * q.pointY);
                if (delta < 0) return -1;
                else if (delta > 0) return 1;
                else return 0;
            }
        }
        // compare other points relative to atan2 angle (between -pi/2 and pi/2) they make with this Point
        private class Atan2Order implements Comparator<Point2D>{
            public int compare(Point2D p, Point2D q){
                double angle1 = angleTo(p);
                double angle2 = angleTo(q);
                if (angle1 < angle2) return -1;
                else if (angle1 > angle2) return 1;
                else return 0;
            }
        }
        // compare other points relative to polar angle (between 0 and 2pi) they make with this point
        private class PolarOrder implements Comparator<Point2D>{
            public int compare(Point2D p, Point2D q){
                double dx1 = p.pointX - pointX;
                double dy1 = p.pointY - pointY;
                double dx2 = q.pointX - pointX;
                double dy2 = q.pointY - pointY;
                if (dy1 >= 0 && dy2 < 0) return -1; // p above, q below
                else if (dy2 >= 0 && dy1 < 0) return 1;
                else if (dy2 == 0 && dy1 == 0){     // 3-collinear and horizontal
                    if (dx1 >= 0 && dx2 < 0) return -1;
                    else if (dx2 >= 0 && dx1 < 0) return 1;
                    else return 0;
                }
                else
                    return -ccw(Point2D.this, p, q); // both above or below
            }
        }
        // compare points according to their distance to this point
        private class DistanceToOrder implements Comparator<Point2D>{
            public int compare(Point2D p, Point2D q){
                double dist1 = distanceSquaredTo(p);
                double dist2 = distanceSquaredTo(q);
                if (dist1 < dist2) return -1;
                else if (dist1 > dist2) return 1;
                else return 0;
            }
        }
        // compare two points by x-coordinate
        public final Comparator<Point2D> X_ORDER = new XOrder();
        // compare two points by y-coordinate
        public final Comparator<Point2D> Y_ORDER = new YOrder();
        // compare two points by polar radius
        public final Comparator<Point2D> R_ORDER = new ROrder();

        // compare this point to the specified point
        public boolean equals(Object other){
            if (other == this) return true;
            if (other == null) return false;
            if (other.getClass() != this.getClass()) return false;
            Point2D that = (Point2D)other;
            return this.pointX == that.pointX && this.pointY == that.pointY;
        }
        // return a string representation of this point
        public String toString(){
            return "(" + pointX + ',' + pointY + ")";
        }
        // return an integer has code for this point
        public int hashCode(){
            int hashX = ((Double) pointX).hashCode();
            int hashY = ((Double) pointY).hashCode();
            return 31*hashX + hashY;
        }
        // plot this point using standard draw
        public void draw(){
            StdDraw.point(pointX, pointY);
        }
        // plot a line from this point to that point using standard draw
        public void drawTo(Point2D that){
            StdDraw.line(this.pointX, this.pointY, that.pointX, that.pointY);
        }
    }
    // unit tests the point data type
    public static void main(String[] args) {
        Ex25_PointsInThePlane pointsInThePlane = new Ex25_PointsInThePlane();
        int x0 = Integer.parseInt(args[0]);
        int y0 = Integer.parseInt(args[1]);
        int n = Integer.parseInt(args[2]);
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(0.005);
        StdDraw.enableDoubleBuffering();
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++){
            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            points[i] = pointsInThePlane.new Point2D(x, y);
            points[i].draw();
        }
        // draw p = (x0, y0) in red
        Point2D p = pointsInThePlane.new Point2D(x0, y0);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.02);
        p.draw();
        // draw line segments from p to each point, one at a time, in polar order
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        Arrays.sort(points, p.polarOrder());
        for (int i = 0; i < n; i++){
            p.drawTo(points[i]);
            StdDraw.show();
            StdDraw.pause(1000);
        }
    }
}

package Chapter2_5_Applications;


import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Comparator;

/*
*   Simple polygon. Given N points in the plane, draw a simple polygon with N points as vertices. Hint: Find the point p
*   with the smallest y coordinate, breaking ties with the smallest x coordinate. Connect the points in increasing order
*   of the polar angle they make with p.
*
* */
public class Ex26_SimplePolygon {
    public class Point2D implements Comparable<Point2D>{
        private double x;
        private double y;
        // create a construct to initialize the object of point in the plane
        public Point2D(double x, double y){
            if (Double.isInfinite(x) || Double.isInfinite(y))
                throw new IllegalArgumentException("Coordinate value cannot be infinite. ");
            if (Double.isNaN(x) || Double.isNaN(y))
                throw new IllegalArgumentException("Coordinate value cannot be null. ");
            if (x == 0)
                this.x = 0;
            else
                this.x = x;
            if (y == 0)
                this.y = 0;
            else
                this.y = y;
        }
        public Point2D(){}
        public int compareTo(Point2D that){
            if (this.x < that.x)
                return -1;
            else if (this.x > that.x)
                return 1;
            else if (this.y < that.y)
                return -1;
            else if (this.y > that.y)
                return 1;
            else
                return 0;
        }

        public double getX(){
            return x;
        }
        public double getY(){
            return y;
        }
        public void drawTo(Point2D p, Point2D q){
            StdDraw.line(p.x, p.y, q.x, q.y);
        }
        public void draw(Point2D that){
            StdDraw.point(that.x, that.y);
        }
        // return true if a->b->c is a counterclockwise
        public int ccw(Point2D a, Point2D b, Point2D c){
            double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
            if (area2 < 0) return -1;
            else if (area2 > 0) return 1;
            else return 0;
        }

        public class polarOrder implements Comparator<Point2D>{
            public int compare(Point2D p, Point2D q){
                double dx1 = p.x - x;
                double dy1 = p.y - y;
                double dx2 = q.x - x;
                double dy2 = q.y - y;
                if (dy1 >= 0 && dy2 < 0) return -1;
                else if (dy1 <= 0 && dy2 > 0) return 1;
                else if (dy1 == 0 && dy2 == 0){
                    if (dx1 >= 0 && dx2 < 0) return -1;
                    else if (dx1 <= 0 && dx2 > 0) return 1;
                    else return 0;
                }
                else
                    return ccw(Point2D.this, p , q);
            }
        }
        public Comparator<Point2D> polarOrder(){
            return new polarOrder();
        }
    }

    public static void main(String[] args) {
        Ex26_SimplePolygon simplePolygon = new Ex26_SimplePolygon();
        Point2D point = simplePolygon.new Point2D();
        Point2D[] point2D = new Point2D[10];
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(-100, 100);
        StdDraw.setYscale(-100, 100);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLUE);
        for (int i = 0; i < 10; i++){
            int x = StdRandom.uniform(-50, 50);
            int y = StdRandom.uniform(-50, 50);
            point2D[i] = simplePolygon.new Point2D(x, y);
            StdDraw.pause(200);
            point.draw(point2D[i]);
        }
        Arrays.sort(point2D, point.polarOrder());
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.setPenRadius();
        for (int i = 0; i < 10; i++){
            StdDraw.pause(300);
            point.drawTo(point2D[i], point2D[i+1]);
        }
    }
}

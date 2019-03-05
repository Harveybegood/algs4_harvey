package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.*;

/*
*   Selection filter. Write a TopM client that reads points(x,y,z) from standard input, takes a value M from the command
*   line, and prints the M points that are closest to the origin in Euclidean distance. Estimate the running time of
*   your client for N = 10^8 and M = 10^4.
*
*   Key points:
*
*   Write ADT for points construct.
*
*   generate initial point and the number N = 10^8 of points
*
*   https://en.wikipedia.org/wiki/Euclidean_distance
*   In mathematics, the Euclidean distance or Euclidean metric is the ordinary straight-line distance between two points
*   in Euclidean space. the TopW client that reads points(x,y,z) which applies to three dimensions space, then the
*   distance to present can be d(point, M)=((p1-m1)^2 + (p2-m2)^2 + (p3-m3)^2)^1/3
*
* */
public class Ex2_4_28 {
    // construct a ADT for points
    public class Point implements Comparable<Point>{
        // generate points(x,y,z)
        double x, y, z;
        public Point(double x, double y, double z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
        public int compareTo(Point other){
            double euclideanDistance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
            double otherEuclideanDistance = Math.sqrt(Math.pow(other.x, 2) + Math.pow(other.y, 2) + Math.pow(other.z, 2));
            if (euclideanDistance < otherEuclideanDistance){
                return -1;
            }else if (euclideanDistance > otherEuclideanDistance){
                return 1;
            }else {
                return 0;
            }
        }
        public String toString(){return "x = " + x + " y = " + y + " z " + z;}
        private Point[] generateRandomPointsArray(int length){
            Point[] array = new Point[length];
            for (int i = 0; i < length; i++){
                double x = StdRandom.uniform();
                double y = StdRandom.uniform();
                double z = StdRandom.uniform();
                Point point = new Ex2_4_28().new Point(x, y, z);
                array[i] = point;
            }
            return array;
        }
    }

    public static void main(String[] args) {
        int M = 10000;
        MaxPQ<Point> pq = new MaxPQ<>(M + 1);
        Ex2_4_28 selectionFilter = new Ex2_4_28();
        Point initialPoint = selectionFilter.new Point(.1, .2, .3);
        initialPoint.generateRandomPointsArray((int) Math.pow(10, 6));
        while (StdIn.hasNextLine()){
            String lines = StdIn.readLine();
            String[] pointsString = lines.split(" ");
            double x = Double.parseDouble(pointsString[0]);
            double y = Double.parseDouble(pointsString[1]);
            double z = Double.parseDouble(pointsString[2]);
            pq.insert(new Ex2_4_28().new Point(x, y, z));
            if (pq.size() > M){
                pq.delMax();
            }
        }
        Stack<Point> stack = new Stack<>();
        while (!pq.isEmpty()){
            stack.push(pq.delMax());
        }
        for (Point t : stack){
            StdOut.println(t);
        }
    }
}

package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;

/*
*   Using the assumptions developed in the text, give the amount of memory needed to represent an object of each of
*   the following types:
*
* */
public class Ex13_AmountOfMemory {
    public class Accumulator{
        private double total;
        private int N;
        public Accumulator(){}

        /*
        *   object overhead: 16
        *   double value: 8
        *   int value: 4
        *   padding: 4
        *   Total: 32 bytes
        * */
    }
    public class Date{
       private int month;
       private int day;
       private int year;
       public Date(int month, int day, int year){
           this.month = month;
           this.day = day;
           this.year = year;
       }
       /*
       *    object overhead: 16
       *    int month: 4
       *    int day: 4
       *    int year: 4
       *    padding: 4
       *    Total: 32 bytes
       * */
    }
    public class Transaction {
        Date date;
        String name;
        double amount;
        public Transaction(String who, Date when, double amount){
            this.name = who;
            this.date = when;
            this.amount = amount;
        }
        /*
        *   object overhead: 16
        *   date: 32
        *   reference to class: 8
        *   String name: 8
        *   double amount: 8
        *   Total: 72 bytes
        * */
    }
    public class FixedCapacityStackOfStrings{
        private String[] a;
        private int N;
        public FixedCapacityStackOfStrings(int cap){
            a = new String[cap];
        }
      
        /*
        *   object overhead: 16
        *   String[] array: 
                          object overhead: 16
                          int size: 4
                          padding: 4
                          Reference to String: 8 * N
                          Char: N * (object overhead: 16 + ) ?????
        *   int N value: 4
        *   padding: 4
        *   Total: 32 + 8 * N bytes
        * */
    }
//    point in the plane
    public class Point2D{
        double x;
        double y;
        public Point2D(double x, double y){
            this.x = x;
            this.y = y;
        }
        /*
        *   object overhead: 16
        *   double x: 8
        *   double y: 8
        *   Total: 32 bytes
        * */
    }
//    1D interval, intervals on the line
    public class Interval1D{
        double xlo;
        double xhi;
        public Interval1D(double xlo, double xhi){
            this.xlo = xlo;
            this.xhi = xhi;
        }
        /*
        *   object overhead: 16
        *   double xlo: 8
        *   double xhi: 8
        *   Total: 32 bytes
        * */
    }
//    2D interval, two-dimensional intervals in the plane, or axis-aligned rectangles
    public class Interval2D{
        Interval1D x;
        Interval1D y;
        public Interval2D(Interval1D x, Interval1D y){
            this.x = x;
            this.y = y;
        }
        /*
        *   object overhead: 16
        *   Interval1D x: 32
        *   Interval1D y: 32
        *   Total: 80 bytes
        * */
    }
//    double wrapper
    public class Double{
        private double x;
        public Double(double x){
            this.x = x;
        }
        /* 
            object overhead: 16
            double x: 8
            Total: 24 bytes
        */
    }

    public static void main(String[] args) {
        edu.princeton.cs.algs4.Point2D point2D = new edu.princeton.cs.algs4.Point2D(0.1, 0.3);
        edu.princeton.cs.algs4.Interval1D interval1D = new edu.princeton.cs.algs4.Interval1D(0.1, 0.3);
        double x = 0.1;
    }
}

package Chapter3_4_HashTables;



/*
*   Implement hashCode() for various types: Point2D, Interval1D, Interval2D, and Date.
*
* */
public class Ex22_HashCodeForVariousTypes {
    // Implement hashCode() for Point2D
    private class Point2D{
        private double x;
        private double y;
        public int hashCode(){
            int hash = 17;
            hash = 31 * hash + ((Double) x).hashCode();
            hash = 31 * hash + ((Double) y).hashCode();
            return hash;
        }
    }

    // Implement hashCode() for Interval
    // I doubt the Interval in the text, so changing it to Interval1D
    private class Interval1D{
        private double min;
        private double max;
        public int hashCode(){
            int hash = 17;
            hash = hash * 31 + ((Double) min).hashCode();
            hash = hash * 31 + ((Double) max).hashCode();
            return hash;
        }
    }

    // Implement hashCode() for Interval2D
    private class Interval2D{
        private edu.princeton.cs.algs4.Interval1D x;
        private edu.princeton.cs.algs4.Interval1D y;
        public int hashCode(){
            int hash = 17;
            hash = hash * 31 + x.hashCode();
            hash = hash * 31 + y.hashCode();
            return hash;
        }
    }

    // Implement hashCode() for Date
    private class Date{
        private int month;
        private int day;
        private int year;
        public int hashCode(){
            int hash = 17;
            hash = hash * 31 + ((Integer) month).hashCode();
            hash = hash * 31 + ((Integer) day).hashCode();
            hash = hash * 31 + ((Integer) year).hashCode();
            return hash;
        }
    }
}

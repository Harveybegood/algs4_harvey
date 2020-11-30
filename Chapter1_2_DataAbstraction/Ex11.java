package Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;


/*
*   Develop an implementation SmartDate of our Date API that raises an exception if the date is not legal.
*
* */
public class Ex11 {
    public class Date{
        private String Month;
        private int day;
        private int year;
        public Date(String mon, int d, int y){
            Month = mon;
            day = d;
            year = y;
        }
        public String getMonth(){return Month;}
        public int getDay(){return day;}
        public int getYear(){return year;}
        public String toString(){
            return Month + "/" + day + "/" + year;
        }
    }

    // raise an exception if the date is not legal
    public class smartDate{
        // in reference to the Date API, add a method isValid() to validate the input
        private String Month;
        private int Day;
        private int Year;
        public smartDate(String m, int d, int y){
            if (!isValid(m, d, y)) throw new IllegalArgumentException("Illegal argument date detected! ");
            this.Day = d;
            this.Month = m;
            this.Year = y;
        }
        public boolean isValid(String m, int d, int y){
            String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            int i = 0;
            if (!(m.equals(months[i++]) && i < 12)){return false;}
            if (d < 1 || d > 30){return false;}
            // Leap year not considered
            if (y > 1900 && y < 9999) return true;
            return true;
        }
        public String getMonth(){return Month;}
        public int getDay(){return Day;}
        public int getYear(){return Year;}
        public String toString(){
            return Month + "/" + Day + "/" + Year;
        }
    }


    public static void main(String[] args) {
        Ex11 ex11 = new Ex11();
        smartDate smartDate1 = ex11.new smartDate("Jan", 1, 1970);
        StdOut.println("Expected legal Date: " + smartDate1);
        smartDate smartDate2 = ex11.new smartDate("February", 11, 2019);
        StdOut.println("Expected Illegal Date: " + smartDate2);
    }

}

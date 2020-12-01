package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;

public class Date implements Comparable<Date>{
    private final int day;
    private final int month;
    private final int year;
    public Date(int d, int m, int y){
        day = d;
        month = m;
        year = y;
    }
    public int day(){return day;}
    public int month(){return month;}
    public int year(){return year;}
    public int compareTo(Date that){
        if (this.year() > that.year()) return +1;
        if (this.year() < that.year()) return -1;
        if (this.month() > that.month()) return +1;
        if (this.month() < that.month) return -1;
        if (this.day > that.day) return +1;
        if (this.day < that.day) return -1;
        return 0;
    }
    public String toString(){
        return month + "/" + day + "/" + year;
    }

    public static void main(String[] args) {
        Date date = new Date(18, 9, 2018);
        Date that = new Date(19, 10, 2017);
        int test = date.compareTo(that);
        StdOut.println(date);
        StdOut.println(test);
    }
}

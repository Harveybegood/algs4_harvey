package Test;

import edu.princeton.cs.algs4.StdOut;

/*
*   Write a class related to Date
* */
public class Date {
    private int month;
    private int day;
    private int year;
    public Date(int m, int d, int y){
        this.month = m;
        this.day = d;
        this.year = y;
    }
    private int month(){
        StdOut.println(month);
        return month;
    }
    private int day(){
        StdOut.println(day);
        return day;
    }
    private int year(){
        StdOut.println(year);
        return year;
    }
    public String toString(){
        return month() + "/" + day() + "/" + year();
    }
    public static void main(String[] args) {
        Date date = new Date(5, 5, 2019);
        StdOut.println(date.month() + " " + date.day() + " " + date.year());
        StdOut.println(date);
    }
}

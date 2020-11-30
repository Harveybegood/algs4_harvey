package Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

public class Date {
  /*  private int month;
    private int day;
    private int year;
    public Date(int month, int day, int year){
        this.month = month;
        this.day = day;
        this.year = year;
    }
    public int month(){return month;}
    public int day(){return day;}
    public int year(){return year;}
    public String toString(){
        return month() + "/" + day() + "/" + year();
    }*/

    public static void main(String[] args) {
        int m = Integer.parseInt("12");
        int d = Integer.parseInt("1");
        int y = Integer.parseInt("2019");
        Date date1 = new Date(m, d, y);
        Date date2 = new Date(1, 1, 2011);
        date1 = date2;
        StdOut.print(date1);
        StdOut.print(date2);
    }

    private int value;
    public Date(int m, int d, int y){
        value = y * 512 + m * 32 + d;
    }
    public int month(){return (value / 32) % 16;}
    public int day(){return value % 32;}
    public int year(){return value / 512;}
    public String toString(){
        return month() + "/" + day() + "/" + year();
    }
}

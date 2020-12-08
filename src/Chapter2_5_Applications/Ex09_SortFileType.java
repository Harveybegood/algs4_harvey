package Chapter2_5_Applications;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/*
*   Develop a data type that allows you to write a client that can sort a file such as the one shown right.
*
*       input (DJI volumes for each day)
        1-Oct-28 3500000
        2-Oct-28 3850000
        3-Oct-28 4060000
        4-Oct-28 4330000
        5-Oct-28 4360000
        ...
        30-Dec-99 554680000
        31-Dec-99 374049984
        3-Jan-00 931800000
        4-Jan-00 1009000000
        5-Jan-00 1085500032
        ...
        output
        19-Aug-40 130000
        26-Aug-40 160000
        24-Jul-40 200000
        10-Aug-42 210000
        23-Jun-42 210000
        ...
        23-Jul-02 2441019904
        17-Jul-02 2566500096
        15-Jul-02 2574799872
        19-Jul-02 2654099968
        24-Jul-02 2775559936

        DJI -- Dow Jones Industrial

        -- input data that consists of date and amount, which is similar to the data type of Transaction.

*
* */
@SuppressWarnings("unchecked")
public class Ex09_SortFileType {
    public static class DJI implements Comparable<DJI>{
        private int amount;
        private Date date;
        public DJI(int amount, Date date){
            this.amount = amount;
            this.date = date;
        }
        public Comparable getAmount(){
            return amount;
        }
        public Date getDate(){
            return date;
        }
        public String toString(){
            return date + " " + amount;
        }
        public int compareTo(DJI dji){
            if (this.amount < dji.amount){
                return -1;
            }else if (this.amount > dji.amount){
                return 1;
            }else {
                return 0;
            }
        }
    }
    public static class Date{
        private int day;
        private String month;
        private int year;
        public Date(int d, String m, int y){
            this.day = d;
            this.month = m;
            this.year = y;
        }
        public int getDay(){
            return day;
        }
        public String getMonth(){
            return month;
        }
        public int getYear(){
            return year;
        }
        public String toString(){
            return day + "-" + month + "-" + year;
        }
    }

    public static void main(String[] args) {
        DJI dji1 = new DJI(4500000, new Date(1,"Oct",99));
        DJI dji2 = new DJI(2500000, new Date(2,"Oct",99));
        DJI dji3 = new DJI(3800000, new Date(3,"Oct",11));
        DJI dji4 = new DJI(2800000, new Date(4,"Oct",11));
        DJI dji5 = new DJI(5800000, new Date(5,"Oct",11));
        DJI[] djis = new DJI[5];
        djis[0] = dji1; djis[1] = dji2; djis[2] = dji3; djis[3] = dji4; djis[4] = dji5;
        /*while (!StdIn.isEmpty()){
            String[] anotherFile = StdIn.readAllStrings();
        }*/
        Arrays.sort(djis);
        for (DJI dji : djis){
            StdOut.println(dji);
        }
    }
}

package Chapter1_3_BagsQueuesStacks;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/*
    Using readInts() on page 126 as a model, write a static method readDates() for
    Date that reads dates from standard input in the format specified in the table on page 119
    and returns an array containing them.

*   Do exercise 1.3.16 for Transaction.
*
*   public Date(String date)
    {
        String[] fields = date.split("/");
        month = Integer.parseInt(fields[0]);
        day = Integer.parseInt(fields[1]);
        year = Integer.parseInt(fields[2]);
    }
*
* */
public class Ex17_Transaction {
    private static class Date{
        private String month;
        private int day;
        private int year;
        public Date(String month, int day, int year){
            this.day = day;
            this.month = month;
            this.year = year;
        }
        public int getDay(){return day;}
        public String getMonth(){return month;}
        public int getYear(){return year;}
        public String toString(){
            return getMonth() + "/" + getDay() + "/" + getYear();
        }
    }
    private static class Transaction{
        private Date date;
        private String name;
        private double quantity;
        public Transaction(Date date, String name, double quantity){
            this.date = date;
            this.name = name;
            this.quantity = quantity;
        }
        public String toString(){
            return date + " " + name + " " + quantity;
        }
    }
    public static String[] readTransaction(String name){
        In in = new In(name);
        Queue<String> queue = new Queue<>();
        while (!in.isEmpty()){
            queue.enqueue(in.readString());
        }
        int N = queue.size();
        String[] s = new String[N];
        for (int i = 0; i < N; i++){
            s[i] = queue.dequeue();
        }
        return s;
    }

    public static void main(String[] args) {

    }
}

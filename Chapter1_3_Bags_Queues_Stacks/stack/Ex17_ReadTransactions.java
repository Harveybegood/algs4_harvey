package Chapter1_3_Bags_Queues_Stacks.stack;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
*   1.3.16 Using readInts() on page 126 as a model, write a static method readDates() for
    Date that reads dates from standard input in the format specified in the table on page 119
    and returns an array containing them.
    1.3.17 Do Exercise 1.3.16 for Transaction.
*
* */
public class Ex17_ReadTransactions {
    public static class Date{
        public String month;
        public int day;
        public int year;
       /* public Date(String month, int day, int year){
            this.day = day;
            this.month = month;
            this.year = year;
        }*/
        public Date(String date){
            String[] field = date.split("/");
            this.month = String.valueOf(field[0]);
            this.day = Integer.valueOf(field[1]);
            this.year = Integer.valueOf(field[2]);
        }
        public String getMonth(){return month;}
        public int getDay(){return day;}
        public int getYear(){return year;}
        public String toString(){
            return getMonth() + "/" + getDay() + "/" + getYear();
        }
    }
    public static class Transaction{
        public Date date;
        public String name;
        public double quantity;
        public Transaction(String transaction){
            String[] fields = transaction.split("\\s+");
            this.date = new Date(fields[0]);
            this.name = String.valueOf(fields[1]);
            this.quantity = Integer.valueOf(fields[2]);
        }
        public Date getDate(){return date;}
        public String getName(){return name;}
        public double getQuantity(){return quantity;}
        public String toString(){
            return getDate() + " " + getName() + " " + getQuantity();
        }
    }
    public static Transaction[] readTransaction(String transaction){
        In in = new In(transaction);
        Queue<Transaction> queue = new Queue<>();
        while (!in.isEmpty()){
            queue.enqueue(new Transaction(in.readString()));
        }
        int N = queue.size();
        Transaction[] transactions = new Transaction[N];
        for (int i = 0; i < N; i++){
            transactions[i] = queue.dequeue();
        }
        return transactions;
    }
    public static void main(String[] args) {
        String fileName = "Jan/1/2019 Harvey 10";
        Transaction transaction = new Transaction(fileName);
        Transaction[] trans = readTransaction(transaction.toString());
        for (Transaction s : trans){
            StdOut.println(s);
        }
        //StdOut.println(readTransaction(transaction.toString()));
    }
}

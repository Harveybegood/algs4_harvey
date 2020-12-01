package test;

import edu.princeton.cs.algs4.StdOut;

/*
*   Write a class related to transaction that deal with three traits including who, when, transaction.
* */
public class Transaction {
    private String who;
    private Date date;
    private double data;
    public Transaction(String who, Date date, double data){
        this.who = who;
        this.date = date;
        this.data = data;
    }
    private String who(){
        return who;
    }
    private Date when(){
        return date;
    }
    private double transactionData(){
        return data;
    }
    public String toString(){
        return who() + " " + when() + " " + transactionData();
    }
    public static void main(String[] args) {
        String s = "harvey";
        Date d = new Date(1, 18, 2019);
        double data = 4747.08;
        Transaction transaction = new Transaction(s, d, data);
        StdOut.println(transaction.who() + " " + transaction.when() + " " + transaction.transactionData());
        StdOut.println(transaction);
    }
}

package Chapter2_1_ElementarySorts;

import Chapter1_2_DataAbstraction.Date;
import Chapter1_2_DataAbstraction.Ex13_Transaction;
import edu.princeton.cs.algs4.StdOut;

/*
*   Comparable transactions. Using our code for Date(page 247) as a model, expand your implementation of Transaction
*   (Exercise 1.2.13) so that it implements Comparable, such that transaction are kept in order by amount.
*
* */
public class Ex21_ComparableTransactions implements Comparable<Ex13_Transaction> {
    private double amount;
    private Date date;
    private String who;
    public Ex21_ComparableTransactions(double amount, Date date, String who){
        this.amount = amount;
        this.date = date;
        this.who = who;
    }
    public int compareTo(Ex13_Transaction that){
        if (this.amount > that.amount()){return +1;}
        if (this.amount < that.amount()){return -1;}
        return 0;
    }

    public static void main(String[] args) {
        Date date = new Date(3, 28, 2019);
        Ex13_Transaction transaction = new Ex13_Transaction(3456.23, "harvey", date);
        String who = "harvey";
        Ex21_ComparableTransactions comparableTransactions = new Ex21_ComparableTransactions
                (3457.23, date, who);
        int a = comparableTransactions.compareTo(transaction);
        StdOut.print(a);
    }
}

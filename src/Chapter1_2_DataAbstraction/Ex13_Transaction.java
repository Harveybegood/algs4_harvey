package Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

/*
*   Using our implementation of Date as a model(page91), develop an implementation of Transaction.
*
* */
public class Ex13_Transaction {
    private double amount;
    private String who;
    private Date date;
    public Ex13_Transaction(double amount, String who, Date date){
        this.amount = amount;
        this.who = who;
        this.date = date;
    }
    public double amount(){return amount;}
    public Date date(){return date;}
    public String who(){return who;}
    public String toString(){
        return date() + " " + who() + " " + amount();
    }

    public static void main(String[] args) {
        double amount = 4478.09;
        Double amount1 = Double.parseDouble(String.format("%2.1f", 3213.56));
        String who = "harvey";
        Date date = new Date(3, 27, 2019);
        Ex13_Transaction transaction = new Ex13_Transaction(amount, who, date);
        Ex13_Transaction transaction1 = new Ex13_Transaction(amount1, who, date);
        StdOut.print(transaction);
        StdOut.println();
        StdOut.print(transaction1);
    }
}

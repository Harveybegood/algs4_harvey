package Chapter2_5_Applications;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Transaction;


/*
*   Stock market trading. Investors place buy and sell orders for a particular stock on an electronic exchange,
*   specifying a maximum buy or minimum sell price that they are willing to pay, and how many shares they wish to
*   trade at that price. Develop a program that uses priority queue to match up buyers and sellers and test it
*   through simulation. Maintain two priority queues, one for buyers and one for sellers, executing traders whenever
*   a new order can be matched with an existing order or orders.
*
* */
public class Ex22_StockMarketTrading {
    public static void main(String[] args) {
        double maxBuy = 3000.00;
        double minSell = 2000.00;
        int share = 0;
        MinPQ<Transaction> minPQSell = new MinPQ<>(1);
        MaxPQ<Transaction> maxPQBuy = new MaxPQ<>(1);
        while (StdIn.hasNextLine()){
            minPQSell.insert(new Transaction(StdIn.readLine()));
            if (minPQSell.min().amount() <= minSell){
                minPQSell.delMin();
                share++;
            }
            else if (maxPQBuy.max().amount() >= maxBuy){
                maxPQBuy.insert(new Transaction(StdIn.readLine()));
                share++;
            }
        }
    }
}

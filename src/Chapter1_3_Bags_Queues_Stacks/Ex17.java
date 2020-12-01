package Chapter1_3_Bags_Queues_Stacks;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

import java.util.regex.Pattern;


/*
*      Do exercise 1.3.16 for Transaction
*
*      Turing 3/2/2018 11.95
*
* */
public class Ex17 {

    public class TransactionSplit{
        private final String who;
        private final String when;
        private final String amount;

        public TransactionSplit(String strs){
            String[] Strs = strs.split("\\s");
            if (Strs.length < 3){throw new RuntimeException("Less arguments than required.");}
            if (Pattern.compile("[^a-zA-Z]").matcher(Strs[0]).find()){
                throw new RuntimeException("name string format error.");
            }
            if (Pattern.compile("\\d{1,4}[-/]\\d{1,2}[-/]\\d{1,2}").matcher(Strs[1]).find()){
                throw new RuntimeException("date format error.");
            }
            if (Pattern.compile("[1-9]\\d*(\\.\\d+)?").matcher(Strs[2]).find()){
                throw new RuntimeException("amount format error.");
            }
            this.who = Strs[0];
            this.when = Strs[1];
            this.amount = Strs[2];
        }
        public String toString(){
            return String.format("Name: %5s, Date: %5s, Amount: %5s", who, when, amount);
        }

    }

    public static Transaction[] readTransactions(String trans){
        In inputs = new In(trans);
        Queue<Transaction> queue = new Queue<>();
        while (!inputs.isEmpty()){
            queue.enqueue(new Transaction(inputs.readString()));
        }
        int n = queue.size();
        Transaction[] a = new Transaction[n];

        for (int i = 0; i < n; i++){
            a[i] = queue.dequeue();
        }
        return a;
    }

    public static void main(String[] args) {
        String transFile = args[0];
        Transaction[] trans = readTransactions(transFile);
        for (Transaction s : trans){
            StdOut.println(s);
        }

    }
}

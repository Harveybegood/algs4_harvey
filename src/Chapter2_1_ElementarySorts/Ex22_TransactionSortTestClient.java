package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

/*
*   Transaction sort Test client. Write a class SortTransactions that consists of a static method main() that reads
*   a sequence of transactions from standard input, sorts them, and prints the results on standard output
*
* */
public class Ex22_TransactionSortTestClient {
    public static class SortTransactions{
        public  Transaction[] readTransactions(){
            //In inputs = new In()
            //In inputs = new In(file);
            Queue<Transaction> queue = new Queue<>();
            while (StdIn.hasNextLine()){
                queue.enqueue(new Transaction(StdIn.readLine()));
            }
            /*for (int i = 0; i < queue.size(); i++){
                StdOut.println(queue.dequeue());
            }*/
            for (Transaction s : queue){
                StdOut.println(s);
            }
            int qSize = queue.size();
            Transaction[] transactions = new Transaction[qSize];
            for (int i = 0; i < qSize; i++){
                transactions[i] = queue.dequeue();
            }
            return transactions;
        }
        public  void shellSort(Transaction[] a){
            int N = a.length;
            int h = 0;
            while (h < N / 3){
                h = h * 3 + 1;
            }
            while (h >= 1){
                for (int i = h; i < N; i++){
                    for (int j = i; j >= h && a[j].compareTo(a[j-h]) < 0; j -= h){
                        Transaction temp = a[j];
                        a[j] = a[j-h];
                        a[j-h] = temp;
                    }
                }
                h = h / 3;
            }
        }
    }
    public static void main(String[] args) {
        //StdOut.println();
        SortTransactions sortTransactions = new SortTransactions();
        Transaction[] transactions = sortTransactions.readTransactions();
        StdOut.println(transactions.length);
        sortTransactions.shellSort(transactions);
        for (Transaction t : transactions){
            StdOut.println(t);
        }
    }
}

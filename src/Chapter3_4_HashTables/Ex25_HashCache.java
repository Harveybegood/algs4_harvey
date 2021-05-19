package Chapter3_4_HashTables;

import Chapter1_2_DataAbstraction.Date;
import edu.princeton.cs.algs4.StdOut;

/*
*   Hash cache. Modify Transaction on page 462 to maintain an instance variable hash, so that hashCode() can save the hash
*   value the first time it is called for each object and does not have to recompute it on subsequent calls. Note: This idea
*   works only for immutable types.
*
* */
public class Ex25_HashCache {
    public class Transaction{
        private String who;
        private Date when;
        private double amount;
        private int hash;
        public Transaction(String who, Date when, double amount){
            this.who = who;
            this.when = when;
            this.amount = amount;
            hash = -1;
        }
        public int hashCode(){
            int h;
            if (this.hash != -1){
                h = this.hash;
                StdOut.println("Does not recompute hash code for the same key object");
            }else {
                h = 17;
                h = 31 * h + who.hashCode();
                h = 31 * h + when.hashCode();
                h = 31 * h + ((Double)amount).hashCode();
                this.hash = h;
            }
            return h;
        }
    }

    public static void main(String[] args) {
        Ex25_HashCache hashCache = new Ex25_HashCache();
        Transaction transaction1 = hashCache.new Transaction("Harvey", new Date(5, 19, 2021), 100);
        Transaction transaction2 = hashCache.new Transaction("John", new Date(5, 20, 2021), 101);
        StdOut.println("Hash value for transaction1: " + transaction1.hashCode());
        StdOut.println("Hash value for transaction2: " + transaction2.hashCode());
        StdOut.println(transaction1.hashCode());
    }
}

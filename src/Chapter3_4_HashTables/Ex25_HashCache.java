package Chapter3_4_HashTables;

import Chapter1_2_DataAbstraction.Date;

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
            }else {
                h = 17;
                h = 31 * h + who.hashCode();
                h = 31 * h + who.hashCode();
                h = 31 * h + ((Double)amount).hashCode();
                this.hash = h;
            }
            return h;
        }
    }
}

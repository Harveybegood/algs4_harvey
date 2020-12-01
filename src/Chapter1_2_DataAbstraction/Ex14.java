package Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

/*
*   Using our implementation of equals() in Date as model(page 103), develop implementation of Transaction.
*
* */
public class Ex14 {
    public class Date{
        private final int month;
        private final int day;
        private final int year;
        public Date(int m, int d, int y){
            month = m; day = d; year = y;
        }
        public int month(){
            return month;
        }
        public int day(){
            return day;
        }
        public int year(){
            return year;
        }
        public String toString(){
            return month() + "/" + day() + "/" + year();
        }
        public boolean equal(Object x){
            if (this == x) return true;
            if (x == null) return false;
            if (this.getClass() != x.getClass()) return false;
            Date that = (Date) x;
            if (this.day != that.day) return false;
            if (this.month != that.month) return false;
            if (this.year != that.year) return false;
            return true;
        }
    }
    public class Transaction{
        private String who;
        private Date when;
        private double amount;
        public Transaction(String w, Date wh, double a){
            who = w; when = wh; amount = a;
        }
        public String getWho(){
            return who;
        }
        public double getAmount(){
            return amount;
        }
         public Date getWhen(){
            return when;
         }
         public String toString(){
            return getWho() + " " + getWhen() + " " + getAmount();
         }
        public boolean equal(Object x){
            if (this == x) return true;
            if (x == null) return false;
            if (this.getClass() != x.getClass()) return false;
            Transaction that = (Transaction) x;
            if (!this.who.equals(that.who)) return false;
            if (this.when != that.when) return false;
            if (this.amount != that.amount) return false;
            return true;
        }
    }

    public static void main(String[] args) {
        Ex14 ex14 = new Ex14();
        Transaction transaction1 = ex14.new Transaction("Harvey", ex14.new Date(8, 14, 2019), 2000.0);
        Transaction transaction2 = ex14.new Transaction("Harvey", ex14.new Date(8, 14, 2019), 2000.0);
        Transaction transaction3 = ex14.new Transaction("harvey", ex14.new Date(8, 14, 2019), 2000.0);
        Transaction transaction4 = ex14.new Transaction("harvey", ex14.new Date(8, 15, 2019), 2000.0);
        StdOut.println("Expected tr1 equals to tr2 " + transaction1.equal(transaction2));
        StdOut.println("Expected tr2 not equals to tr3 " + transaction2.equal(transaction3));
        StdOut.println("Expected tr2 not equals to tr4 " + transaction2.equal(transaction4));
        Date date1 = ex14.new Date(8, 15, 2019);
        Date date2 = ex14.new Date(8, 15, 2019);
        StdOut.println("True: " + date1.equal(date2));
    }
}

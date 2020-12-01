package Chapter1_2_DataAbstraction;


import edu.princeton.cs.algs4.StdOut;

/*
*   Parsing. Develop the parse constructors for your Date and Transaction implementations of exercise 1.2.13
*   that takes a single argument to specify the initialization values, using the formats given in the table below.
*
*       type                     format                         example
*
        Date            integers separated by slashes         5/22/1939

        Transaction     customer, date, and amount,
                        separated by whitespace               Turing 5/22/1939 11.99
* */
public class Ex19_StringParsing {
    public static void main(String[] args) {
        String s1 = "Turing 5/22/1939 11.39";
        //String s2 = StdIn.readString();
        Ex19_StringParsing stringParsing = new Ex19_StringParsing(s1);
        StdOut.println(stringParsing.amount);
        StdOut.println(stringParsing.date);
        StdOut.println(stringParsing.name);
    }
    private String name;
    private Date date;
    private Double amount;
    public Ex19_StringParsing(String s){
        String[] array = s.split(" ");
        this.name = array[0];
        this.date = new Date(array[1]);
        this.amount = Double.parseDouble(array[2]);
    }
    public static class Date{
        private int month;
        private int day;
        private int year;
        public Date(String date){
            String[] fields = date.split("/");
            this.month = Integer.parseInt(fields[0]);
            this.day = Integer.parseInt(fields[1]);
            this.year = Integer.parseInt(fields[2]);
        }
      /*  public int getMonth(){
            return month;
        }
        public int getDay(){
            return day;
        }
        public int getYear(){
            return year;
        }*/
        public String toString(){
            return month + " " + day + " " + year;
        }
       /* public String toString(){
            return month + "/" + day + "/" + year;
        }*/
        /*private String month;
        private int day;
        private int year;
        public Date(String m, int d, int y){
            this.month = m; this.day = d; this.year = y;
        }

        public String getMonth() {
            return month;
        }
        public int getDay(){
            return day;
        }
        public int getYear(){
            return year;
        }
        public String toString(){
            return getMonth() + "/" + getDay() + "/" + getYear();
        }*/
    }
    /*public static class Transaction{
        private String customer;
        private Date date;
        private Double amount;
        public Transaction(String transaction){
            String[] array = transaction.split(" ");
            this.customer = array[0];
            this.date = new Date(array[1]);
            this.amount = Double.parseDouble(array[2]);

        }

        public String getCustomer() {
            return customer;
        }
        public Date getDate(){
            return date;
        }
        public Double getAmount(){
            return amount;
        }
        public String toString(){
            return getCustomer() + " " + getDate() + " " + getAmount();
        }
    }*/
}

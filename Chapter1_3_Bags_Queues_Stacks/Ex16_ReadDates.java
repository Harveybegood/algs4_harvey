package Chapter1_3_Bags_Queues_Stacks;


import edu.princeton.cs.algs4.*;

/*
*   Using readInts() on page 126 as a model, write a static method readDates() for Date that reads dates
*   from standard input in the format specified in the table on page 119 and returns an array containing them.
*
*   The purpose of this is to convert the input equivalent to the table on page 119 to a format of array.
*
*   format on page 119 is 5/22/2019
*
* */
public class Ex16_ReadDates {

     private static Date[] readDates(String file){
       In ins = new In(file);
       Queue<Date> queue = new Queue<>();
       while (!ins.isEmpty()){
           queue.enqueue(new Date(ins.readString()));
       }
       int n = queue.size();
       Date[] dates = new Date[n];
       for (int i = 0; i < n; i++){
           dates[i] = queue.dequeue();
       }
       return dates;
   }

    public static void main(String[] args) {
        //String dateInputFiles = StdIn.readString();
        String file = StdIn.readString();
        Date[] dates = readDates(file);
        for (Date date : dates)
            StdOut.println(date);

    }
}

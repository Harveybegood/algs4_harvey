package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Write a program that reads in lines from standard input with each line containing a name and two integers
*   and then uses printf() to print a table with a column of the names, the integers, and the results of
*   dividing the first by the second, accurate to three decimal places. You could use a program like this
*   to tabulate batting average for baseball players or grades for students.
*
* */
public class Ex21 {
    public static void main(String[] args) {
        String[] array = readsInLines();
        StdOut.printf("%5s %7s %7s %6s\n", "Names", "Number1", "Number2", "Result");
        for (int i = 0; i < array.length; i++){
            String[] splittedValue = array[i].split(" ");
            Double value1 = Double.parseDouble(splittedValue[1]);
            Double value2 = Double.parseDouble(splittedValue[2]);
            StdOut.printf("%5s %7s %ss %6.3f\n", splittedValue[0], splittedValue[1], splittedValue[2],
                    value1 / value2);
        }

   }
    private static String[] readsInLines(){
        String[] array = new String[3];
        //s.split("//s");

        while (!StdIn.isEmpty()){
            for (int i = 0; i < 3; i++){
                array[i] = StdIn.readLine();
            }
        }
        return array;
    }
}

package Test;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*Test for readAllDoubles()*/
public class AlDoubleValues {
    public static void main(String[] args) {
        double[] doubleValues = StdIn.readAllDoubles();
       /* while (!StdIn.isEmpty()){
            double doubleValues = StdIn.readDouble();
            StdOut.println(doubleValues);
        }*/
       for (int i = 0; i < doubleValues.length; i++){
           StdOut.println(i + " " + doubleValues[i]);
       }
       //StdOut.println(doubleValues);
    }
}

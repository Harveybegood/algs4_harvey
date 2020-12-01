package test;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class remainderChangingWithHalf {
    private static int arrayLength = 100;
    private static Comparable[] array = new Comparable[arrayLength];
    private static int increment = 0;
    public String toString(){
        return array[increment] + " ";
    }
    public static void main(String[] args) {

        int input = 0;

        for (increment = 0; increment < arrayLength; increment++){
            array[increment] = StdRandom.uniform(input, input + 1);
            arrayLength *= .5;
            input += 1;
            StdOut.println(array.toString());
        }
    }

}

package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdIn;

/*
*   Filtering. Which of the following require saving all the values from standard input(in an array, say), and which
*   could be implemented as a filter using only a fixed number of variables and arrays of fixed size(not dependent
*   on N)? For each, the input comes from standard input and consists of N real numbers between 0 and 1.
*
* */
public class Ex34 {
    public static void main(String[] args) {
        int[] array = StdIn.readAllInts();
    }
    // requisite 1 stands for saving all the value from standard input
    // requisite 2 stands for ... as a filter ...

    // Print the maximum and minimum numbers.
    // requisite 2

    // Print the median of the numbers.
    // requisite 1

    // Print the kth smallest value, for k less than 100.
    // requisite 1

    // Print the sum of the squares of the numbers.
    // requisite 2

    // Print the average of the N numbers.
    // requisite 2

    // Print the percentage of numbers greater than the average.
    // requisite 1

    // Print the N numbers in increasing order.
    // requisite 1

    // Print the N numbers in random order.
    // requisite 2
}

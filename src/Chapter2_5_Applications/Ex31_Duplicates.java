package Chapter2_5_Applications;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Duplicates. Write a client that takes integers M, N and T as command-line arguments, then uses the code given in the
*   text to perform T trials of the following experiment:
*
*       Generate N random int values between 0 and M-1 and count the number of duplicates. Run your program for T=10 and
*       N=10^3, 10^4, 10^5, and 10^6, with M=N/2, and N, and 2N. Probability theory says that the number of duplicates
*       should be about (1-e^α)where α=N/M print a table to help you confirm that your experiments validate that formula.
*
* */
@SuppressWarnings("unchecked")
public class Ex31_Duplicates {
    public static void main(String[] args) {
        //int N = Integer.parseInt(args[0]);
        Ex31_Duplicates duplicates = new Ex31_Duplicates();
        /*int count;
        double p;

        for (int i = 3; i < 6; i++){
            for (int j = i / 2; j < 2 * i; i += i){
                Comparable[] a = duplicates.generateValues((int)Math.pow(10, i), (int)Math.pow(10, j));
                count = duplicates.theNumberOfDuplicates(a);
                p = count / (int)Math.pow(10, i);
                StdOut.println(p);
            }
        }
       Comparable[] a1 = duplicates.generateValues((int)Math.pow(10, 3), (int)Math.pow(10,1.5));
       count = duplicates.theNumberOfDuplicates(a1);
       p = count*1.0 / (int)Math.pow(10,3);
       StdOut.println(p);*/
    for (int t = 0; t < 10; t++){
        StdOut.println(duplicates.theNumberOfDuplicates(duplicates.generateValues((int)Math.pow(10,3), (int)Math.pow(10, 1.5)))
                * 1.0 /(int)Math.pow(10, 3));
        StdOut.println(duplicates.theNumberOfDuplicates(duplicates.generateValues((int)Math.pow(10,3), (int)Math.pow(10, 3)))
                * 1.0/(int)Math.pow(10,3));
        StdOut.println(duplicates.theNumberOfDuplicates(duplicates.generateValues((int)Math.pow(10,3), (int)Math.pow(10, 6)))
                * 1.0/(int)Math.pow(10,3));
        StdOut.println(duplicates.theNumberOfDuplicates(duplicates.generateValues((int)Math.pow(10,4), (int)Math.pow(10, 2)))
                * 1.0/(int)Math.pow(10,4));
        StdOut.println(duplicates.theNumberOfDuplicates(duplicates.generateValues((int)Math.pow(10,4), (int)Math.pow(10, 4)))
                * 1.0/(int)Math.pow(10,4));
        StdOut.println(duplicates.theNumberOfDuplicates(duplicates.generateValues((int)Math.pow(10,4), (int)Math.pow(10, 8)))
                * 1.0/(int)Math.pow(10,4));
        StdOut.println(duplicates.theNumberOfDuplicates(duplicates.generateValues((int)Math.pow(10,5), (int)Math.pow(10, 2.5)))
                * 1.0/(int)Math.pow(10,5));
        StdOut.println(duplicates.theNumberOfDuplicates(duplicates.generateValues((int)Math.pow(10,5), (int)Math.pow(10, 5)))
                * 1.0/(int)Math.pow(10,5));
        StdOut.println(duplicates.theNumberOfDuplicates(duplicates.generateValues((int)Math.pow(10,5), (int)Math.pow(10, 10)))
                * 1.0/(int)Math.pow(10,5));
        StdOut.println(duplicates.theNumberOfDuplicates(duplicates.generateValues((int)Math.pow(10,6), (int)Math.pow(10, 3)))
                * 1.0/(int)Math.pow(10,6));
        StdOut.println(duplicates.theNumberOfDuplicates(duplicates.generateValues((int)Math.pow(10,6), (int)Math.pow(10, 6)))
                * 1.0/(int)Math.pow(10,6));
        StdOut.println(duplicates.theNumberOfDuplicates(duplicates.generateValues((int)Math.pow(10,6), (int)Math.pow(10, 12)))
                * 1.0/(int)Math.pow(10,6));
        StdOut.println();
        StdOut.println();
    }

    }
    // find the number of duplicates
    public int theNumberOfDuplicates(Comparable[] a){
        Quick.sort(a);
        int count = 0;
        for (int i = 1; i < a.length; i++){
            if (a[i].compareTo(a[i-1]) == 0)
                count++;
        }
        return count;
    }
    // generate random values
    public Comparable[] generateValues(int N, int M){
        Comparable[] a = new Comparable[N];
        for (int i = 0; i < N; i++){
            a[i] = StdRandom.uniform(0, M - 1);
        }
        return a;
    }
}

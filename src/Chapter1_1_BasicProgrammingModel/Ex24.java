package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/*
*   Give the sequence of values of p and q that are computed when Euclid's algorithm is used to compute the
*   greatest common divisor of 105 and 24. Extend the code given on page 4 to develop a program Euclid that
*   takes two integers from the command line and computes their greatest common divisor, printing out the two
*   arguments for each call on the recursive method. Use your program to compute the greatest common divisor
*   or 1111111 and 1234567.
*
* */
public class Ex24 {
    public static void main(String[] args) {
        //gcd(105, 24);
        int p = Integer.parseInt(args[0]);
        int q = Integer.parseInt(args[1]);
        int value = gcd(p, q);
        StdOut.println("The value of greatest common divisor: " + value);
        StdOut.println("1111111 and 1234567: " + gcd(1111111, 1234567));
    }

    private static int gcd(int p, int q){
        StdOut.println("p: " + p + "  |  " + "q: " + q);
        if (q == 0)
            return p;
        else {
            int r = p % q;
            return gcd(q, r);
        }
    }
}
/*
* p: 105  |  q: 24
p: 24  |  q: 9
p: 9  |  q: 6
p: 6  |  q: 3
p: 3  |  q: 0
The value of greatest common divisor: 3
p: 1111111  |  q: 1234567
p: 1234567  |  q: 1111111
p: 1111111  |  q: 123456
p: 123456  |  q: 7
p: 7  |  q: 4
p: 4  |  q: 3
p: 3  |  q: 1
p: 1  |  q: 0
1111111 and 1234567: 1

* */
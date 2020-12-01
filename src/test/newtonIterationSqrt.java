package test;

import edu.princeton.cs.algs4.StdOut;

public class newtonIterationSqrt {
    public static long sqrt(double k){
        double result = k;
        double e = 0.000000001;
        while (Math.abs(result - k / result) > e){
            result = (result + k / result) / 2;
        }
        return (long)result;
    }

    public static void main(String[] args) {
        StdOut.println(newtonIterationSqrt.sqrt(9) + " expected as 3. ");
        long a = 1;
        double b = 1;
        StdOut.println(a + " " + b);
    }
}

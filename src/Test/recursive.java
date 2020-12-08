package Test;

import edu.princeton.cs.algs4.StdOut;

public class recursive {
    public static void main(String[] args) {
        //int n = func(4);
        //fib(5);
        StdOut.printf("%d", fib(3));
    }
   /* public static int func(int n){
        if (n == 0){
            return 1;
        }else {
            return 7 + func(n - 2);
        }
    }*/
   private static long fib(int n){
       return n <= 1 ? n : fib(n-1) + fib(n-2);
   }
}
/*
*       How does the process of this program go?
*
*       precondition, we have the argument as 3. begin from the main.
*
*       step1, 3 > 1.   fib(n-1) = fib(2) and fib(n-2) = fib(1) meanwhile fib(1) = 1, which then calls fib().
*       step2, 2 > 1.   fib(n-1) = fib(1) mean while fib(1) = 1, which then calls fib() again.
*       recursive for both fib(n-1) and fib(n-2) is done, put both return value with 1 together,
 *       get: 1+1 = 2;
*
* */
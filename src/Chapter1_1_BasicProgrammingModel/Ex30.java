package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/*
*   Array exercise. Write a code fragment that crates an N-by-N boolean array[][] such that a[i][j] is true if
*   i and j are relatively prime(have no common factors), and false otherwise.
*
*
*   note:
*   https://en.wikipedia.org/wiki/Coprime_integers
*   In number theory, two integers a and b are said to be relatively prime, mutually prime, or
*   coprime if the only positive integer that divides both of them is 1. Consequently,
*   any prime number that divides one does not divide the other.
*
*   This is equivalent to their greatest common divisor being
 *
 *  hence, we can use gcd to tell if both integers are relatively prime
 *
* */
public class Ex30 {
    public static void main(String[] args) {
        int i = 10;
        int j = 10;
        printArray(i, j);
    }
    public static int gcd(int a, int b){
        if (b == 0) return a;
        int r = a % b;
        return gcd(b, r);
    }
    public static void printArray(int n, int m){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (gcd(i, j) == 1){
                    StdOut.print("true" + " ");
                }
                else
                    StdOut.print("false" + " ");
            }
            StdOut.println();
        }
    }
}

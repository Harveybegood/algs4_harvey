package Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Suppose that a[] and b[] are each integer arrays consisting of millions of integers. What does the follow code
*   do? Is it reasonably efficient?
*
* */
public class Ex08 {
    public static void main(String[] args) {
        int N = 10;
        int[] a = new int[N];
        int[] b = new int[N];
        int[] t;
        for (int i = 0; i < N; i++){
            a[i] = StdRandom.uniform(1, 10);
            b[i] = StdRandom.uniform(1, 10);
        }
        StdOut.print("Array a: ");
        for (int i = 0; i < N; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
        StdOut.print("Array b: ");
        for (int i = 0; i < N; i++){
            StdOut.print(b[i] + " ");
        }
        t = a;
        a = b;
        b = t;
        StdOut.println();
        StdOut.println("The effective after copying reference");
        StdOut.print("Array a: ");
        for (int i = 0; i < N; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
        StdOut.print("Array b: ");
        for (int i = 0; i < N; i++){
            StdOut.print(b[i] + " ");
        }
        StdOut.println();
        StdOut.print("Array t: ");
        for (int i = 0; i < N; i++){
            StdOut.print(t[i] + " ");
        }
    }
}

package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Empirical shuffle check. Run computational experiments to check that our shuffling code on page 32 works as
*   advertised. Write a program ShuffleTest that takes command-line arguments l and n, does n shuffles of an array
*   of size l that is initialized with a[i] = i before each shuffle, and prints an l-by-l table such that row i
*   gives the number of times i would up in position j for all j. All entries in the array should be close to n/l.
*
* */
public class Ex36 {
    public static void main(String[] args) {
        int l = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        int[] a = new int[l];
        int[][] array = shuffleTest(a, n);
        for (int i = 0; i < l; i++){
            for (int j = 0; j < l; j++){
                StdOut.print(array[i][j] + " ");
            }
            StdOut.println();
        }
    }
    // initialize an array with a[i] = i;
    public static void generateArray(int[] a){
        int l = a.length;
        for (int i = 0; i < l; i++){
            a[i] = i;
        }
    }
/*
    public static void shuffle(int[] a){
       int n = a.length;
       for (int i = 0; i < n; i++){
           int r = i + StdRandom.uniform(n - i);
           int temp = a[i];
           a[i] = a[r];
           a[r] = temp;
       }
    }*/
    public static int[][] shuffleTest(int[] a, int n){
        int l = a.length;
        int[][] table = new int[l][l];
        //int count;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < l; j++){
                for (int k = 0; k < l; k++){
                    generateArray(a);
                    int r = k + StdRandom.uniform(l - k);
                    table[j][r]++;
                }
            }
        }
        return table;
    }
}

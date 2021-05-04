package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   How many empty lists do you expect to see when you insert N keys into a hash table with SeparateChainingHashST, for N = 10,
*   10^2, 10^3, 10^4, 10^5 and 10^6? Hint:
*
*   See Ex2.5.31
        Duplicates. Write a client that takes integers M, N and T as command-line arguments, then uses the code given in the
        text to perform T trials of the following experiment:

    Generate N random int values between 0 and M-1 and count the number of duplicates. Run your program for T=10 and
    N=10^3, 10^4, 10^5, and 10^6, with M=N/2, and N, and 2N. Probability theory says that the number of duplicates
    should be about (1-e^α)where α=N/M print a table to help you confirm that your experiments validate that formula.
*
* */
public class Ex08_HowManyEmptyLists {
    public static void main(String[] args) {
        Ex08_HowManyEmptyLists howManyEmptyLists = new Ex08_HowManyEmptyLists();
        for (int i = 1; i < 7; i++){
            int count = howManyEmptyLists.emptyLists((int)Math.pow(10, i));
            StdOut.println("N = " + (int)(Math.pow(10, i)) + " - Empty list: " + count);
        }
    }

    // how many empty lists
    public int emptyLists(int n){
        int count  = 0;
        SeparateChainingHashST<Integer, Integer> separateChainingHashST = new SeparateChainingHashST<>();
        for (int i = 0; i < n; i++){
            separateChainingHashST.put(StdRandom.uniform(i, n), i);
        }
        for (int i = 0; i < separateChainingHashST.M; i++){
            if (separateChainingHashST.st[i].first == null){
                count++;
            }
        }
        return count;
    }

}

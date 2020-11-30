package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

/*
*   Hot or cold. Your goal is to guess a secret integer between 1 and N. You repeatedly guess integers between 1 and N.
*   After each guess you learn if your guess equals the secret integer(and the game stops). Otherwise, you learn if the
*   guess is hotter(closer to) or colder(farther from) the secret number than your previous guess. Design an algorithm
*   that finds the secret number in at most ~2 lg N guesses. Then design an algorithm that finds the secret number in
*   at most ~1 lg N.
*
* */
public class Ex34 {
    static int findSecretInteger(int[] secretInteger, int goal){
        int N = secretInteger.length;
        int low = 0;
        int high = N - 1;
        while (low < high){
            int middle = (high - low) / 2;
            if (goal > secretInteger[middle]) low = middle + 1;
            else if (goal < secretInteger[middle]) high = middle - 1;
            else return middle;
        }
        return goal;
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9};
        int N = 2;
        StdOut.println("The result:  " + findSecretInteger(array,N));
    }
}

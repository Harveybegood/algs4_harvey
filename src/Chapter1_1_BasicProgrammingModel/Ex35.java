package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Dice simulation. The following code computes the exact probability distribution for the sum of two dice:
*
*   The value dist[i] is the probability that the dice sum to k. Run experiments to validate this calculation
*   simulating N dice throws, keeping track of the frequencies of occurrence of each value when you compute the sum
*   of two random integers between 1 and 6. How large does N have to be before your empirical results match the exact
*   results to three decimal places?
*
* */
public class Ex35 {
    static int sides = 6;
    static double[] exactDist = new double[2*sides + 1];
    static double[] empiricalDist = new double[2*sides + 1];
    public static void main(String[] args) {
       StdOut.println("Exact results: ");
       exactDist = exactResults();
       for (int k = 2; k <= sides * 2; k++){
           StdOut.printf("%.3f\n", exactDist[k]);
       }
       StdOut.println("Empirical results: ");
       for (int n = 100; true; n *= 10){
           empiricalDist = empiricalResults(n);
           for (int k = 2; k <= sides * 2; k++){
               StdOut.printf("%.3f\n", empiricalDist[k]);
           }
           StdOut.println("Number of experiments: " + n);
       }
    }

    public static double[] exactResults(){
        for (int i = 1; i <= sides; i++){
            for (int j = 1; j <= sides; j++){
                exactDist[i+j] += 1;
            }
        }
        for (int k = 2; k <= 2*sides; k++){
            exactDist[k] /= 36;
        }
        return exactDist;
    }
    public static double[] empiricalResults(int n){
        int diceOne;
        int diceTwo;
        int sum;
        for (int i = 0; i < n; i++){
            diceOne = StdRandom.uniform(1, 7);
            diceTwo = StdRandom.uniform(1, 7);
            sum = diceOne + diceTwo;
            empiricalDist[sum]++;
        }
        for (int k = 2; k <= 2*sides; k++){
            empiricalDist[k] /= n;
        }
        return empiricalDist;
    }
}


// the result is rough at N = 1000000
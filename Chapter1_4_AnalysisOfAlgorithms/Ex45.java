package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
*   Coupon collector problem. Generating random integers as in the previous exercise, run experiments to validate the hypothesis
*   that the number of integers generated before all possible values are generated is ~N HN.
*
* */
public class Ex45 {

    private final int INITIAL_NUMBER_SIZE = 4;
    private final int FINAL_NUMBER_SIZE;
    private float[] harmonicNumber;

    public Ex45(int maxNumberSize){
        FINAL_NUMBER_SIZE = maxNumberSize;
        harmonicNumber = new float[maxNumberSize + 1];
        harmonicNumber[1] = 1;
    }

    // Run the amount of n trials and count the amount of numbers generated
    private int couponCollectorProblem(int n){
        Set<Integer> numbersGenerated = new HashSet<>();
        int numbersGeneratedCount = 0;
        while (true){
            int number = StdRandom.uniform(0, n);
            numbersGenerated.add(number);
            numbersGeneratedCount++;
            if (numbersGenerated.size() == n)
                break;
        }
        return numbersGeneratedCount - 1;
    }
    private List<Integer> runExperiments(){
        List<Integer> numbersGeneratedBeforeAllPossibleValues = new LinkedList<>();
        for (int i = FINAL_NUMBER_SIZE; i <= FINAL_NUMBER_SIZE; i *= 2){
            int numbersGenerated = couponCollectorProblem(i);
            numbersGeneratedBeforeAllPossibleValues.add(numbersGenerated);
        }
        return numbersGeneratedBeforeAllPossibleValues;
    }

    private float getHarmonicNumber(int number){
        if (harmonicNumber[number] != 0){
            return harmonicNumber[number];
        }
        int index = number - 1;
        while (harmonicNumber[index] == 0) index--;
        while (index <= number){
            harmonicNumber[index] = ((float) 1 / (float) index) + harmonicNumber[index - 1];
            index++;
        }
        return harmonicNumber[number];
    }
    private double getExpectedResultByHypothesis(int numberSize){
        return numberSize * getHarmonicNumber(numberSize);
    }
    private void printResults(List<Integer> numbersGeneratedBeforeAllPossibleValues){
        StdOut.printf("%10s %15s %16s %8s\n", "N |", "Numbers Generated |", "Result Expected |", "Accuracy");
        int numberSize = INITIAL_NUMBER_SIZE;
        for (int numbersGenerated : numbersGeneratedBeforeAllPossibleValues){
            int expectedResults = (int) Math.round(getExpectedResultByHypothesis(numberSize));
            StdOut.printf("%8d %13s %15d", numberSize, numbersGenerated, expectedResults);
            double accuracy = (double) numbersGenerated / (double) expectedResults;
            StdOut.printf("%20.1f", accuracy);
            numberSize *= 2;
        }
    }

    /*public static class Harmonic {

        // returns the nth Harmonic number
        public static double harmonic(int n) {
            if (n < 1000) return harmonicSmall(n);
            else          return harmonicLarge(n);
        }

        // compute nth Harmonic number when n is small
        public static double harmonicSmall(int n) {
            double sum = 0.0;
            for (int i = 1; i <= n; i++)
                sum += 1.0 / i;
            return sum;
        }

        // returns the nth Harmonic number when n is large
        public static double harmonicLarge(int n) {

            // Euler-Mascheroni constant (http://en.wikipedia.org/wiki/Euler-Mascheroni_constant)
            double GAMMA = 0.577215664901532;

            return Math.log(n) + GAMMA + 1.0/(2.0*n) - 1.0/(12.0*n*n) + 1.0/(120.0*n*n*n*n);
        }

        public static void main(String[] args) {
            int n = 4194304;
            StdOut.println(harmonic(n));
        }

    }*/


    public static void main(String[] args) {
        //int n = 4194304;
        Ex45 couponCollectorProblem = new Ex45(4194304);
        List<Integer> numbersGeneratedBeforeAllPossibleValue = couponCollectorProblem.runExperiments();
        couponCollectorProblem.printResults(numbersGeneratedBeforeAllPossibleValue);
        //StdOut.printf("10.1f\n", n * Harmonic.harmonic(4194304));
    }
}

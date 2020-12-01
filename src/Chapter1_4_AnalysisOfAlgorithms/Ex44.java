package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
*   Birthday problem. Write a program that takes an integer N from the command line and uses StdRandom.uniform() to generate
*   a random sequence of integers between 0 and N - 1. Run experiments to validate the hypothesis that the number of integers
*   generated before the first repeated value is found is ~√Π N/2.
*
* */
public class Ex44 {
    private final int INITIAL_NUMBER_SIZE = 4;
    private final int FINAL_NUMBER_SIZE = 268435456;

    private int birthdayProblem(int n){
        Map<Integer, Boolean> numberGenerated = new HashMap<>();
        int numberGeneratedCount = 0;

        while (true){
            int number = StdRandom.uniform(0, n);
            numberGeneratedCount++;
            if (numberGenerated.containsKey(number)){
                break;
            }
            else {
                numberGenerated.put(number, true);
            }
        }
        return numberGeneratedCount - 1;
    }
    private List<Integer> runExperiments(){
        List<Integer> numbersGeneratedBeforeFirstDuplicate = new LinkedList<>();
        for (int n = INITIAL_NUMBER_SIZE; n < FINAL_NUMBER_SIZE; n *= 2){
            int numbersGenerated = birthdayProblem(n);
            numbersGeneratedBeforeFirstDuplicate.add(numbersGenerated);
        }
        return numbersGeneratedBeforeFirstDuplicate;
    }

    private void printResults(List<Integer> numbersGeneratedBeforeFirstDuplicate){
        StdOut.printf("%12s %15s %15s %8s\n", "N | ", "Numbers Generated | ", "Result Expected | ",
                "Accuracy");
        int numberSize = INITIAL_NUMBER_SIZE;
        for (int numbersGenerated : numbersGeneratedBeforeFirstDuplicate){
            int resultExpectedByHypothesis = (int) Math.round(Math.sqrt(Math.PI * numberSize / 2));
            StdOut.printf("%10d %12d %12d", numberSize, numbersGenerated, resultExpectedByHypothesis);
            double accuracy = (double) numbersGenerated / (double) resultExpectedByHypothesis;
            StdOut.printf("%15.1f\n", accuracy);
            numberSize *= 2;
        }
    }

    public static void main(String[] args) {
        Ex44 ex44 = new Ex44();
        List<Integer> numbersGeneratedBeforeFirstDuplicate = ex44.runExperiments();
        ex44.printResults(numbersGeneratedBeforeFirstDuplicate);
    }
}

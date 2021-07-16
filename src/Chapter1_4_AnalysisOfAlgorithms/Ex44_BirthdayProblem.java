package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Birthday problem. Write a program that takes an integer N from the command line and uses StdRandom.uniform() to generate
*   a random sequence of integers between 0 and N - 1. Run experiments to validate the hypothesis that the number of integers
*   generated before the first repeated value is found is ~√N/2
*
* */
public class Ex44_BirthdayProblem {

    public static int runExperiments(int N){
        int value = StdRandom.uniform(0,N-1);
        int count  = 1;
        int[] array = new int[N];
        array[0] = value;
        for (int i = 1; i < Integer.MAX_VALUE; i++){
            int newValue = StdRandom.uniform(0, N - 1);
            array[i] = newValue;
            count++;
            for (int j = 0; j < i; j++){
                if (array[j] == newValue){
                    return count;
                }
            }
        }
        return -1;
    }
    public static void repeatExperiments(int n, int N){
        for (int i = 0; i < n; i++){
            StdOut.printf("%12d %15d %15d\n", N, runExperiments(N), (int) Math.sqrt(Math.PI * N / 2));
        }
    }
    public static void main(String[] args) {
        //int N = Integer.parseInt(args[0]);
        int N = 100000;
        StdOut.printf("%12s %15s %15s\n", "randomRanges", "numberIntegers", "expectedValue");
        repeatExperiments(20, N);
    }
}
/*
*           randomRanges  numberIntegers   expectedValue
                  100000             357             396
                  100000             433             396
                  100000             534             396
                  100000             266             396
                  100000             129             396
                  100000             418             396
                  100000             363             396
                  100000             391             396
                  100000             507             396
                  100000              44             396
                  100000             243             396
                  100000             378             396
                  100000             196             396
                  100000             228             396
                  100000             390             396
                  100000             414             396
                  100000             299             396
                  100000             390             396
                  100000             764             396
                  100000             288             396

*
* */
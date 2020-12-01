package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Nonuniform distribution. Write a client that generates test data by randomly ordering objects using other
*   distributions than uniform, including the following:
*
*       Gaussian
*       Poisson
*       Geometric
*       Discrete (see exercise 2.1.28 for a special case)
*
*   Develop and test hypotheses about the effect of such input on the performance of the algorithms in this section.
* */
@SuppressWarnings("unchecked")
public class Ex2_1_35 {
    public enum DistributionType{
        Gaussian, Poisson, Geometric, Discrete
    }
    public static void sort(Comparable[] a){
        int N = a.length;
        int increment = 1;
        while (increment < N / 3){
            increment = increment * 3 + 1;
        }
        while (increment >= 1){
            for (int i = increment; i < N; i++){
                for (int j = i; j >= increment && a[j].compareTo(a[j - increment]) < 0; j -= increment){
                    Comparable temp = a[j];
                    a[j] = a[j-increment];
                    a[j-increment] = temp;
                }
            }
            increment = increment / 3;
        }
    }
    public static double time(Comparable[] a){
        Stopwatch timer = new Stopwatch();
        sort(a);
        return timer.elapsedTime();
    }
    public static double timeDistributionInput(int arrayLength, DistributionType distributionType){
        double total = 0.0;
        Comparable[] array = new Comparable[arrayLength];

            for (int i = 0; i < arrayLength; i++){
                if (distributionType == DistributionType.Gaussian){
                    array[i] = StdRandom.gaussian();
                }else if (distributionType == DistributionType.Poisson){
                    array[i] = StdRandom.poisson(100.0);
                }else if (distributionType == DistributionType.Geometric){
                    array[i] = StdRandom.geometric(.5);
                }else if (distributionType == DistributionType.Discrete){
                    array[i] = StdRandom.discrete(StdRandom.permutation(5));
                }

            }
        total += time(array);
        return total;
    }

    public static void main(String[] args) {
        int arrayLength = 2000;
        for (int experiment = 0; experiment < 11; experiment++){
           double t1 = timeDistributionInput(arrayLength, DistributionType.Gaussian);
           double t2 = timeDistributionInput(arrayLength, DistributionType.Poisson);
           double t3 = timeDistributionInput(arrayLength, DistributionType.Geometric);
           double t4 = timeDistributionInput(arrayLength, DistributionType.Discrete);
           arrayLength *= 2;
           StdOut.printf("Number: %d, Time: %.2f, %.2f, %.2f, %.2f\n", arrayLength, t1, t2, t3, t4);
           StdOut.printf("Ratio in between: %.2f - %.2f - %.2f\n", t1 / t2, t2 / t3, t3 / t4);
           StdOut.println();
           StdOut.println();
        }
    }
}

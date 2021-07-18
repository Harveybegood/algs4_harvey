package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Coupon collector problem. Generating random integers as in the previous exercise, run experiments to validate the
*   hypothesis that the number of integers generated before all possible values are generated is ~NHn
*
*   Hn = 1/1 + 1/2 + 1/3 + ... + 1/n.
*   NHn = n(1/1 + 1/2 + 1/3 + ... + 1/n).
*
* */
public class Ex45_CouponCollectorProblem {
    public static void main(String[] args) {
        //int N = Integer.parseInt(args[0]);
        StdOut.printf("%13s %15s %13s %5s\n","IntegerRandom", "NumberOfIntegers", "ExpectedValue", "Ratio");
        printResults();
    }

    public static int runExperiments(int N){
        int[] couponCollector = new int[N];
        for (int i = 0; i < N; i++){
            couponCollector[i] = -1;
        }
        int firstRandomInteger = StdRandom.uniform(0, N - 1);
        couponCollector[0] = firstRandomInteger;
        int cnt = 1;
        int index = 0;
        for (int i = 1; i < Integer.MAX_VALUE; i++){
            int currentRandomInteger = StdRandom.uniform(0, N);
            cnt++;
            for (int j = 0; j < N; j++){
                if (couponCollector[j] == currentRandomInteger){
                    break;
                }
                if (couponCollector[j] != currentRandomInteger && j == (N - 1)){
                    index++;
                    couponCollector[index] = currentRandomInteger;
                    break;
                }
            }
            if (index == N - 1){
                break;
            }
        }
        return cnt;
    }
    public static double harmonicProgression(int n){
        int numerator = 1;
        double Hn = 0;
        for (int i = 1; i <= n; i++){
            Hn += (double) numerator / i;
        }
        return Hn * n;
    }
    public static void printResults(){
        for (int N = 2; N < 20; N++){
            int number = (int) Math.pow(2, N);
            int numberOfExperiment = runExperiments(number);
            double harmonicValue = harmonicProgression(number);
            StdOut.printf("%13d %15d %13.1f %5.1f\n", number, numberOfExperiment, harmonicValue, numberOfExperiment / harmonicValue);
        }
    }
}

/*
*           IntegerRandom NumberOfIntegers ExpectedValue Ratio
                        4              11           8.3   1.3
                        8              52          21.7   2.4
                       16              54          54.1   1.0
                       32             175         129.9   1.3
                       64             248         303.6   0.8
                      128             573         695.4   0.8
                      256            1382        1567.8   0.9
                      512            3392        3490.1   1.0
                     1024           13962        7689.4   1.8
                     2048           15590       16797.9   0.9
                     4096           30530       36434.3   0.8
                     8192           82793       78546.5   1.1
                    16384          153970      168448.9   0.9
                    32768          322236      359610.4   0.9
                    65536          702967      764646.4   0.9
                   131072         1659756     1620144.5   1.0
                   262144         3742794     3421992.9   1.1
                   524288         7606100     7207394.0   1.1
*
*
* */

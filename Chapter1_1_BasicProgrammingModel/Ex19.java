package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import static Chapter1_1_BasicProgrammingModel.Ex19.Fibonacci.F;

/*
*   Run the following program on your computer:
*
*   What is the largest value of N for which this program takes less 1 hour to compute the value of F(N)?
*   Develop a better implementation of F(N) that saves computed values in an array.
*
* */
public class Ex19 {

    public static class Fibonacci{
        public static long F(int N){
            if (N == 0)
                return 0;
            if (N == 1)
                return 1;
            return F(N-1) + F(N-2);
        }
    }

    public static double timeCost(int N){
        Stopwatch timer = new Stopwatch();
        for (int i = N - 10; i < N; i++){
            StdOut.println(i + " " + F(i));
        }
        return timer.elapsedTime();
    }
    private static void doubleRatio() {
        double time1 = timeCost(10);
        int N = 20;
        while (N < 100) {
            double time2 = 0.0;
            for (int i = N - 10; i < N; i += 10) {
                time2 = timeCost(i);
            }
            StdOut.printf("%.2f %.2f %.2f %6s\n", time1, time2, time2 / time1, " - Ratio");
            time1 = time2;
            N += 10;
        }
    }

    // according to the experimentation, observe that
    // 0.01 1.26 105.00  - Ratio -- > N = 40
    // 1.26 106.36 84.42  - Ratio --> N = 50 -> 106*84 -- 8500s
    // 60(s)*60(min) = 3600s
    // I assume the N value should be a bit bigger than 50.

    public static class betterFibonacci{
        private static long[] results = new long[100];
        public static long betterF(int N){
            if (N <= 0)
                return 0;
            if (N == 1)
                return 1;

            if (results[N] != 0){
                return results[N];
            }else {
                results[N] = betterF(N-1) + betterF(N-2);
                return results[N];
            }
        }

        public static void main(String[] args) {
            for (int N = 0; N < 100; N++){
                StdOut.println(N + " " + betterF(N));
            }
            doubleRatio();
        }
    }
}

/*
*   F(0) -> betterF(0) -> return 0;
*   F(1) -> betterF(1) -> return 1;
*   F(2) -> betterF(N-1) + betterF(N-2) -> betterF(1) + betterF(0) - > return 1;
*   F(3) -> betterF(N-1) + betterF(N-2) -> betterF(2) + betterF(1) -> betterF(1) + betterF(0) + betterF(1)
*   -> return 2;
*   F(4) -> betterF(N-1) + betterF(N-2) -> betterF(3) + betterF(2) -> betterF(2) + betterF(1) + betterF(1) +
*   betterF(0) -> betterF(1) + betterF(0) + betterF(1) + betterF(1) + betterF(0) -> return 3
*   F(5) -> betterF(N-1) + betterF(N-2) -> betterF(4) + betterF(3) ->
*   and so forth....
*
*   Each F() equals to foregoing two F() except for the first two F().
*
*
*
*
*
* */

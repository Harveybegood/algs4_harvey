package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

public class testForMyStopwatch {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        myStopwatch timer1 = new myStopwatch();
        double sum1 = 0.0;
        for (int i = 0; i < n; i++){
            sum1 += Math.sqrt(i);
        }
        double time1 = timer1.elapseTime();
        StdOut.println("The sum1: " + sum1 + " The time cost: " + time1);

        myStopwatch timer2 = new myStopwatch();
        double sum2 = 0.0;
        for (int i = 0; i < n; i++){
            sum2 += Math.pow(i, 0.5);
        }
        double time2 = timer2.elapseTime();
        StdOut.printf("%e, (%.2fseconds)\n", sum2, time2);
    }
}

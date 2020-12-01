package Chapter1_4_AnalysisOfAlgorithms;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Naive 3-sum implementation. Run experiments to evaluate the following implementation of the inner loop of ThreeSum:
*   Do so by developing a version of DoublingTest that computes the ratio of the running times of this program and ThreeSum
*
*       for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                for (int k = 0; k < N; k++){
                    if (i < j && j < k){
                        if (a[i] + a[j] + a[k] == 0){
                            cnt++;
                        }
                    }
                }
            }
        }
* */
public class Ex38 {
    public static class naive3Sum{
        //private int N;
        private int[] s;
        private int cnt;
        private naive3Sum(){
            s = new int[10000000];
        }
        public double timeTrialNaive3Sum(int N){
            Stopwatch timer1 = new Stopwatch();
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    for (int k = 0; k < N; k++){
                        if (i < j && j < k){
                            if (s[i] + s[j] + s[k] == 0){
                                cnt++;
                            }
                        }
                    }
                }
            }
            double timeCOst1 = timer1.elapsedTime();
            return timeCOst1;
        }

    }

    public static class threeSum{
        private int [] s;
        private int cnt;
        private threeSum(){
            s = new int[10000000];
        }
        public double timeTrialThreeSum(int N){
            Stopwatch timer2 = new Stopwatch();
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    for (int k = 0; k < N; k++){
                        if (s[i] + s[j] + s[k] == 0){
                            cnt++;
                        }
                    }
                }
            }
            double timeCost2 = timer2.elapsedTime();
            return timeCost2;
        }
    }

    public static void main(String[] args) {
        //Ex38 ex38 = new Ex38();
        naive3Sum naive3Sum1 = new naive3Sum();
        threeSum threeSum2 = new threeSum();
        double time1 = 0.0;
        double time2 = 0.0;
        int N = 1000000;
        for (int n = 250; n < N; n += n){
            time1 = naive3Sum1.timeTrialNaive3Sum(n);
            time2 = threeSum2.timeTrialThreeSum(n);
            StdOut.printf("%6d %7.1f %7.1f %7.1f \n",n, time1, time2, time1 / time2);
        }
        /*for (int i = 250; i < N; i += i){
            time2 = threeSum2.timeTrialThreeSum(i);
            StdOut.printf("%6d %7.1 ",i, time2);
        }*/
        //StdOut.printf("The ratio: %5.1f \n", time1 / time2);
    }
}

package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;

/*
*   3-sum for random values. Formulate and validate a hypothesis describing the number of triples of N random int values
*   that sum to 0. If you are skilled in mathematical analysis, develop an appropriate mathematics model for this problem,
*   where the values are uniformly distributed between -M and M, where M is not small.
*
*   Others depend on the input data: for example the number of times the instruction cnt++ in ThreeSum.count() is executed is precisely
*   the number of triples that sum to 0 in the input, which could range from 0 of them to all of them. In the case of DoublingTest,
*   where we generate the numbers randomly, it is possible to do a probabilistic analysis to determine
    the expected value of this quantity (see Exercise 1.4.40).
*
* */
public class Ex40_3_SumForRandomValues {
    // 3-sum method
    public int threeSum(int[] array){
        int N = array.length;
        int cnt = 0;
        for (int i = 0; i < N; i++){
            for (int j = i + 1; j < N; j++){
                for (int k = j + 1; k < N; k++){
                    if (array[i] + array[j] + array[k] == 0){
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
    public int countTrials(int N, int M){
        int[] array = new int[N];
        int value = -M;
        for (int i = 0; i < N; i++){
            array[i] = value;
            value++;
        }
        return threeSum(array);
    }
    public void doublingTest(){
        int cnt = countTrials(200, 200/2);
        StdOut.printf("%5d %5d\n", 200, cnt);
        for (int N = 400; N < 6401; N += N){
            int currentCnt = countTrials(N, N/2);
            StdOut.printf("%5d %5d %3.2f\n", N, currentCnt, (double)currentCnt/cnt);
            cnt = currentCnt;
        }
    }

    public static void main(String[] args) {
        Ex40_3_SumForRandomValues sumForRandomValues = new Ex40_3_SumForRandomValues();
        sumForRandomValues.doublingTest();
    }
}

/*
*           200 4950
            400 19900 4.02
            800 79800 4.01
            1600 319600 4.01
            3200 1279200 4.00
            6400 5118400 4.00

            Based on the experiments above, we find that the doubling ratio approximate 4 and then ,after 4 times, is 4.
            If given that the initial number of int value is N, We come to conclude a mathematical model as:
            N * X * 4 = the number of triples of N random int values

*
* */



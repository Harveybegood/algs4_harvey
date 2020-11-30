package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   4-sum. Develop an algorithm for the 4-sum problem
*
* */
public class Ex14 {
    public static int count(int[] array){
        int cnt = 0;
        for (int i = 0; i < array.length; i++){
            for (int j = i + 1; j < array.length; j++){
                for (int k = j + 1; k < array.length; k++){
                    for (int l = k + 1; l < array.length; l++){
                        if (array[i] + array[j] + array[k] + array[l] == 0){
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int N = 100;
        int[] array = new int[N];
        for (int i = 0; i < N; i++){
            array[i] =  StdRandom.uniform(-10, 10);
            //StdOut.println(array[i] + " " + i);
        }
        StdOut.println(count(array));
    }
}

package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/*
*   3-sum for random values. Formulate and validate a hypothesis describing the number of triples of N random int
*   values that sum to 0. If you are skilled in mathematical analysis, develop an appropriate mathematical model for
*   this problem, where the values are uniformly distributed between -M and M, where M is not small.
*
*   the different composition selecting 3 numbers from N numbers is C(N, 3) = N^3 / 6
*
* */
public class Ex40 {
    static class threeSum{
        static int cursor1 = 0;
        static int cursor2 = 0;
        static int fastCount(int[] a){
            cursor1++;
            Arrays.sort(a);
            int cnt = 0;
            for (int i = 0; i < a.length; i++){
                int low = i + 1;
                int high = a.length - 1;
                while (low < high){
                    if (a[low] + a[high] > -a[i]) high--;
                    else if (a[low] + a[high] < -a[i]) low++;
                    else {
                        cnt++;
                        low++;
                        high--;
                    }
                }
            }
            return cnt;
        }
        static int slowCount(int[] a){
            cursor2++;
            int N = a.length;
            int cnt = 0;
            for (int i = 0; i < N; i++){
                for (int j = i + 1; j < N; j++){
                    for (int k = j + 1; k < N; k++){
                        if (a[i] + a[j] + a[k] == 0){
                            cnt++;
                        }
                    }
                }
            }
            return cnt;
        }
    }

    //uniformly distributed values
    static int[] NRandomIntValues(int N){
        Set<Integer> set = new HashSet<>();
        int[] array = new int[N];
        int size = 0;
        int r = 0;
        while (size < N){
            while (set.contains(r = StdRandom.uniform(-1000000, 1000000)));
            {
                array[size++] = r;
                set.add(r);
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int N = 1000, loops = 100;
        double rel = 0;
        int i = loops;
        while (i-- > 0){
            rel += threeSum.fastCount(NRandomIntValues(N));
        }
        rel /= loops * 1.0;
        StdOut.println("The value of hypothesis: " + Math.pow(N, 3) / (16 * 1000000) + " The test value: " + rel);
    }
}

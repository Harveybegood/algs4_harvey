package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/*
*   Write a program to determine the number of pairs of values in an input file that are equal.
*   if your first try is quadratic, think again and use Arrays.sort() to develop a linearithmic solution.
*
* */
public class Ex8 {
    // order of growth is quadratic.
   static class EqualPairCount{
       public static long count(int[] array){
           Stopwatch timer = new Stopwatch();
           long cnt = 0;
           int N = array.length;
           for (int i = 0; i < N; i++){
               for (int j = i + 1; j < N; j++){
                   if (array[i] + array[j] == 0){
                       cnt++;
                   }
               }
           }
           StdOut.println(String.format("Slow algorithm result: " + "%d\n total time : %f\n ===",cnt,timer.elapsedTime()));
           return cnt;
       }
    }
    // order of growth is linearithmic
    static class EqualPairCountFast{
       public static long count(int[] array){
           StdRandom.uniform();
           if (array == null || array.length < 2)return 0;
           Stopwatch timer = new Stopwatch();
           int[] arr = array;
           Arrays.sort(arr);
           long cnt  = 0;
           int i = 1, pre = 0, cur = 1;
           while (i < arr.length){
               pre = i - 1; cur = i;
               if (arr[pre] != arr[cur]) i++;
               else {
                   while (i < arr.length && arr[cur] == arr[i]) i++;
                   int equalCount = i - pre;
                   cnt += equalCount * (equalCount - 1) / 2;
               }
           }
           StdOut.println(String.format("Fast algorithm result: %d\n total time: %f\n ===", cnt, timer.elapsedTime()));
           return cnt;
       }
       public static int[] sourceArray(int N){
           int[] arr =new int[N];
           for (int i = 0; i < N; i++){
               arr[i] = StdRandom.uniform(0, 10);
           }
           return arr;
       }
       public static void testTwoApproaches(){
           int[] arr = sourceArray(2000000);
           EqualPairCountFast.count(arr);
           EqualPairCount.count(arr);
       }

        public static void main(String[] args) {
            testTwoApproaches();
        }
    }
}

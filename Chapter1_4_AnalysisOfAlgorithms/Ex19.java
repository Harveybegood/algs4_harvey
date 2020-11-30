package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.Set;

/*
*   Local minimum of a matrix. Given an B-by-N array a[] of N^2 distinct integers, design an algorithm that runs
*   in time proportional to N to find a local minimum:
*   a pair of indices i and j such that
*   a[i][j] < a[i+1][j], a[i][j] < a[i][j+1], a[i][j] < a[i-1][j], and a[i][j] < a[i][j-1].
*   The running time of your program should be proportional to N in the worst case.
*
* */
public class Ex19 {
   public static class Result{
       int row = -1, column = -1, value;
       Result(){}
       Result(int row, int column, int value){
           this.row = row;
           this.column = column;
           this.value = value;
       }
       public String toString(){
           return String.format("[%d, %d] = %d\n", row, column, value);
       }
   }
   public static Result localMinimumOfMatrix(int[][] array){
       int N = array.length;
       int lo = 0, hi = N - 1, mid = 0;
       while (lo <= hi){
           mid = (lo + hi) / 2;
           int minColIndex = minimumColumnIndex(array[mid]);
           if (mid == 0){
               if (array[mid][minColIndex] < array[mid+1][minColIndex]){
                   return new Result(mid, minColIndex, array[mid][minColIndex]);
               }
               return null;
           }
           if (mid == N - 1){
               if (array[mid][minColIndex] < array[mid - 1][minColIndex]){
                   return new Result(mid, minColIndex, array[mid][minColIndex]);
               }
               return null;
           }
           if (array[mid-1][minColIndex] > array[mid][minColIndex] && array[mid+1][minColIndex] > array[mid][minColIndex]){
               return new Result(mid, minColIndex, array[mid][minColIndex]);
           }
           if (array[mid-1][minColIndex] < array[mid+1][minColIndex]){
               hi = mid - 1;
           }else {
               lo = mid + 1;
           }
       }
       return null;
   }
   public static int minimumColumnIndex(int[] array){
       int min = Integer.MAX_VALUE;
       int index = 0;
       for (int i = 0; i < array.length; i++){
           if (array[i] < min){
               min = array[i];
               index = i;
           }
       }
       return index;
   }
   public static int[][] sourceArray(int N){
       Set<Integer> set = new HashSet<Integer>();
       int[][] array = new int[N][N];
       for (int i = 0; i < N; i++){
           array[i] = new int[N];
           for (int j = 0; j < N; j++){
               int random = StdRandom.uniform(1,150);
               while (set.contains(random)){
                   random = StdRandom.uniform(1,150);
               }
               set.add(random);
               array[i][j] = random;
           }
       }
       return array;
   }
   public static void printArray(int[][] array){
       StdOut.printf("%-5s", " ");
       for (int i = 0; i < array[0].length; i++){
           StdOut.printf("%-4d",i);
       }
       StdOut.println("\n");
       for (int i = 0; i < array.length; i++){
           StdOut.printf("%-3d ", i);
           for (int j = 0; j < array[i].length; j++){
               StdOut.printf("%-4d", array[i][j]);
           }
           StdOut.println();
       }
       StdOut.println();
   }

    public static void main(String[] args) {
        int[][] array = sourceArray(6);
        printArray(array);
        StdOut.println(localMinimumOfMatrix(array));
    }
}

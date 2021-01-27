package Chapter1_4AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Local minimum of a matrix. Given an N-by-N array a[] of N^2 distinct integers, design an algorithm that runs in time
*   proportional to N to find a local minimum: a pair of indices i and j such that a[i][j] < a[i+1][j], a[i][j] < a[i][j+1],
*   a[i][j] < a[i-1][j], and a[i][j] < a[i][j-1]. The running time of your program should be proportional to N in the worst case.
*
* */
public class Ex19_LocalMinimumMatrix {
    // generate an N-by-N array a[] of N^2 distinct integers
    public static int[][] matrix(int N){
        int[][] twoDimensionArray = new int[N][N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                twoDimensionArray[i][j] = StdRandom.uniform(-100, 100);
            }
        }
        return twoDimensionArray;
    }
    public static void localMinimum(int[][] matrix){
        int startOfRow;
        int startOfCol;
        for (startOfRow = 1, startOfCol = 1; startOfRow < matrix.length - 1 && startOfCol < matrix.length - 1; startOfRow++, startOfCol++){
            if (matrix[startOfRow][startOfCol] < matrix[startOfRow + 1][startOfCol] &&
            matrix[startOfRow][startOfCol] < matrix[startOfRow][startOfCol + 1] &&
            matrix[startOfRow][startOfCol] < matrix[startOfRow - 1][startOfCol] &&
            matrix[startOfRow][startOfCol] < matrix[startOfRow][startOfCol - 1]){
                StdOut.printf("%d %d %d \n", startOfRow, startOfCol, matrix[startOfRow][startOfCol]);
            }
        }
    }

    public static void main(String[] args) {
        localMinimum(matrix(10));
    }
}

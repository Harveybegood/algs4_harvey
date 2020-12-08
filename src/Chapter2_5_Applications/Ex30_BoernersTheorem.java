package Chapter2_5_Applications;

import Tools.QuickSort;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/*
*   Boerner's theorem. True or false: If you sort each column of a matrix, then sort each row, the columns are still
*   sorted. Justify your answer.
*
* */
public class Ex30_BoernersTheorem {
    public static void main(String[] args) {
        int n = 5;
        Comparable[][] matrix = new Comparable[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                matrix[i][j] = StdRandom.uniform(1, 10);
            }
        }

        // print out the original matrix
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                StdOut.print(matrix[i][j] + "  ");
            }
            StdOut.println();
        }
        // quick sort
        int p = 0;
        while (p < 5){
            Comparable[] col1 = new Comparable[n];
            for (int i = 0; i < n; i++){
                col1[i] = matrix[i][p];
            }
            QuickSort.sort(col1);
            for (int i = 0; i < n; i++){
                matrix[i][p] = col1[i];
            }
            p++;
        }
        StdOut.println();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                StdOut.print(matrix[i][j] + "  ");
            }
            StdOut.println();
        }
        int q = 0;
        while (q < 5){
            Comparable[] row = new Comparable[n];
            for (int j = 0; j < n; j++){
                row[j] = matrix[q][j];
            }
            QuickSort.sort(row);
            for (int j = 0; j < n; j++){
                matrix[q][j] = row[j];
            }
            q++;
        }
        StdOut.println();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                StdOut.print(matrix[i][j] + "  ");
            }
            StdOut.println();
        }
        StdOut.println();
        Ex30_BoernersTheorem boernersTheorem = new Ex30_BoernersTheorem();

        StdOut.println(boernersTheorem.sortedInColumn(matrix));
    }
    @SuppressWarnings("unchecked")
    private boolean sortedInColumn(Comparable[][] matrix){
        int j = 0;
        int n = matrix.length;
        while (j < 5){
            for (int i = 0; i < n - 1; i++){
                if ((matrix[i][j].compareTo(matrix[i+1][j]) > 0)){
                    return false;
                }
            }
            j++;
        }
        return true;
    }
}

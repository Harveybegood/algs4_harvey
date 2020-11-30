package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/*
*   Write a code fragment to print the transposition(rows and columns changed) of a two-dimensional array with
*   M rows and N columns.
*
* */
public class Ex13 {
    public static void main(String[] args) {
        int[][] array = {
                {59,  47,  15,  85,  60},
                {10,  24,  45,  67,  90},
                {98,  78,  87,  37,  28},
                {77,  98,  56,  49,  88},
                {82,  20,  66,  88,  99}
        };
        StdOut.println("Original array");
        for (int i = 0; i < array[0].length; i++){
            for (int j = 0; j < array.length; j++){
                StdOut.print(array[i][j] + "  ");
            }
            StdOut.println();
        }
        transposition(array);
        StdOut.println();
        StdOut.println("After transposition");
        for (int i = 0; i < array[0].length; i++){
            for (int j = 0; j < array.length; j++){
                StdOut.print(array[i][j] + "  ");
            }
            StdOut.println();
        }
    }

    private static void transposition(int[][] array){
        // all operations will happen between indices swap which are index of col and row.
        int[][] temp = new int[array[0].length][array.length];
        for (int i = 0; i < array[0].length; i++){
            for (int j = i; j < array.length; j++){
                temp[i][j] = array[j][i];
                array[j][i] = array[i][j];
                array[i][j] = temp[i][j];
            }
        }
    }
}

/*
*
*
*   Original array
    59  47  15  85  60
    10  24  45  67  90
    98  78  87  37  28
    77  98  56  49  88
    82  20  66  88  99

    After transposition
    59  10  98  77  82
    47  24  78  98  20
    15  45  87  56  66
    85  67  37  49  88
    60  90  28  88  99

*
*
* */
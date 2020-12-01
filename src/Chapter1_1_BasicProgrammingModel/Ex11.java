package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

/*
*   Write a code fragment that prints the contents of a two-dimensional boolean array, using * to represent a
*   true and a space to represent false. Include row and column numbers.
*
* */
public class Ex11 {
    public static void main(String[] args) {
        String[][] s = {
                {"true",    "false",   "true",   "true",   "true",    "true"},
                {"true",   "false",   "false",  "true",    "true",   "true"},
                {"true",   "true",    "true",    "false",   "false",   "true"},
                {"true",    "true",    "true",    "true",    "false",   "true"},
                {"true",    "true",    "false",   "false",   "true",    "true"}
        };

        printBooleanArray(s);
    }
    private static void printBooleanArray(String[][] s){
        for (int col = 0; col < s[0].length; col++){
            StdOut.print("  " + col);
        }
        StdOut.println();
        for (int i = 0; i < s.length; i++){
            StdOut.print(i + "  ");
            for (int j = 0; j < s[i].length; j++){
                if (s[i][j].equals("true"))
                {StdOut.print("*" + "  ");}
                if(s[i][j].equals("false"))
                {StdOut.print(" " + "  ");}
            }
            StdOut.println();
        }
    }
}

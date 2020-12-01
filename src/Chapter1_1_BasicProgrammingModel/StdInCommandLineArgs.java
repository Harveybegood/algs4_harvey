package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdOut;

public class StdInCommandLineArgs {
    public static void main(String[] args) {
        //String s1 = StdIn.readString();
        String s2 = String.valueOf(args[0]);
        /*for (int i = 0; i < s1.length(); i++){
            StdOut.print(s1.charAt(i) + " / ");
        }*/
        StdOut.println();
        for (int i = 0; i < s2.length(); i++){
            StdOut.print(s2.charAt(i) + " / ");
        }
    }
}

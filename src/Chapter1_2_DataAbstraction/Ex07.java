package Chapter1_2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

/*
*   What does the following recursive function return?
*
* */
public class Ex07 {
    public static void main(String[] args) {
        String s = "ABCDEFGHIJ";
        StdOut.print(mystery(s));
    }
    public static String mystery(String s){
        int N = s.length();
        if (N <= 1) return s;
        String a = s.substring(0, N/2);
        String b = s.substring(N/2, N);
        return mystery(b) + mystery(a);
    }
}
/*
*                                         String s = "ABCDEFGHIJ" n = 10;
*
*                    a = "ABCDE"                                                   b = "FGHIJ"
*
*         a = "ABC"                b = "DE"                          a = "FGH"                     b = "IJ"
*
*   a = "AB"    b = "C"        a = "D"   b = "E"                a = "FG"      b = "H"           a = "I"     b = "J"
*
* a = "A" b = "B"                                             a = "F" b = "G"
*
*
*           Conclusion: The string s will be showed as a way of inverse
*
* */
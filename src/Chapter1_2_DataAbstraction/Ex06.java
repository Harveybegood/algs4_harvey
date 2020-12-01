package Chapter1_2_DataAbstraction;


import edu.princeton.cs.algs4.StdOut;

/*
*   A string s is a circular rotation of a string t if it matches when the characters are circularly shifted by
*   any number of positions; e.g., ACTGACG is a circular shift of TGACGAC, and vice versa. Detecting this condition
*   is important in the study of genomic sequence. Write a program that checks whether two given strings s and t are
*   circular shifts of one another. Hint: The solution is a one-liner with indexOf(), length(), and string
*   concatenation.

    String a = "now is ";
    String b = "the time ";
    String c = "to"
    a.length()            7
    a.charat(4)           i
    a.concat(c)           "now is to"
    a.indexof("is")       4
    a.substring(2, 5)     "w i"
    a.split(" ")[0]       "now"
    a.split(" ")[1]       "is"
    b.equals(c)           false

*
* */
public class Ex06 {
    public static void main(String[] args) {
        String s = "ACTGACG";
        String t = "TGACGAC";
        String u = "ACGACTG";
        StdOut.println(s.indexOf("A") + " " + t.indexOf("A") + " " + u.indexOf("A"));
        StdOut.println(s.indexOf("AC") + " " + s.indexOf("CT", 2));
    }
    public static boolean circularRotation(String s, String t){
        int sLength = s.length();
        int tLength = t.length();
        if (sLength != tLength){
            return false;
        }
        return false;
    }
}

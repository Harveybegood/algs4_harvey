package test;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   read a sequence of characters from standard input, and then reverse its sequence in output
*
* */
public class ASequenceOfChars {
    public static void main(String[] args) {
        String s = StdIn.readString();
        StdOut.println(isPalindrome(s));
        String s1 = String.valueOf(1);
        String s2 = String.valueOf("a");
        //int N = Integer.parseInt(args[0]);
        StdOut.println(s1);
        StdOut.println(s2);
        double sum = 0.0;
        int cnt = 0;
        while (!StdIn.isEmpty()){
            sum += StdIn.readDouble();
            cnt++;
        }
        double avg = sum / cnt;
        StdOut.printf("Average is %.2f\n", avg);
    }
    public static boolean isPalindrome(String s){
        int N = s.length();
        for (int i = 0; i < N/2; i++){
            if (s.charAt(i) != s.charAt(N - i - 1)){
                return false;
            }
        }
        return true;
    }
}

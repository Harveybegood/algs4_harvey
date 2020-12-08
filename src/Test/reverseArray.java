package Test;

import edu.princeton.cs.algs4.StdOut;

public class reverseArray {
    public static void main(String[] args) {
        String[] s = {"a", "b", "c", "d", "e", "f"};
        for (String st : s){
            StdOut.print(st + " ");
        }
        //String[] ss = reverseArrayMethod(s);
        reverseArrayMethod(s);
        StdOut.println();
        for (String st : s){
            StdOut.print(st + " ");
        }
    }
    private static void reverseArrayMethod(String[] s){
        String temp;
        for (int i = 0; i < s.length / 2; i++){
            temp = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = temp;
        }
        //return s;
    }
}

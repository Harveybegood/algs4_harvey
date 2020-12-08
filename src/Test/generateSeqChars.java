package Test;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class generateSeqChars {
    static String t = "fswfkl3kjhrj3hj23n23u9ufdfk2njrewfdfndfjrfjfdsoufiaf2930890afsklfm23r1f2323f9f2k3rnffd32k3d89233kjkfdf9423m432k487dsfdfdsjf";
    public static String generateRandomChars(int minLen, int maxLen){
        int n = StdRandom.uniform(minLen, maxLen);
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(t, n, 50).toString();
    }
    // generate array containing these random characters
    public static String[] generateArray(){
        String[] s = new String[100];
        for (int i = 0; i < 100; i++){
            s[i] = generateRandomChars(2, 49);
        }
        return s;
    }

    public static void main(String[] args) {
        String[] s = generateArray();
        for (int i = 0; i < s.length; i++){
            StdOut.println(s[i]);
        }
    }
}

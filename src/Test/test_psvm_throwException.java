package Test;

import edu.princeton.cs.algs4.StdOut;

public class test_psvm_throwException {
    public static void main(String[] apple) {
        int n = Integer.parseInt(apple[0]);
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                StdOut.printf("%d", j);
            }
            StdOut.println();
        }
    }
}

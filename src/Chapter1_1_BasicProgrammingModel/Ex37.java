package Chapter1_1_BasicProgrammingModel;

import edu.princeton.cs.algs4.StdRandom;

/*
*   Bad shuffle. Suppose that you choose a random integer between 0 and N-1 in our shuffling code instead of one
*   between i and N-1. Show that the resulting order is not equally likely to be one of the N! possibilities. Run
*   the Test of the previous exercise for this version.
*
* */
public class Ex37 {
    public static void main(String[] args) {

    }

    public static void badShuffle(int[] a){
        int n = a.length;
        for (int i = 0; i < n; i++){
            int r = StdRandom.uniform(0, n - i);
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
}


//
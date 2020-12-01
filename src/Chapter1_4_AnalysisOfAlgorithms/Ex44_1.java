package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.Set;

public class Ex44_1 {
    static int totalNumberBeforeDuplicate(int n){
        Set<Integer> set = new HashSet<>();
        int numbers;
        while (true){
            numbers = StdRandom.uniform(0, n);
            if (set.contains(numbers)) return set.size();
            else set.add(numbers);
        }
    }

    public static void main(String[] args) {
        int n = 10, loop = 1000;
        double a = 10;
        int count = 0;
        for (int i = 0; i < loop; i++){
            count += totalNumberBeforeDuplicate(n);
        }
        StdOut.printf("Value of hypothesis: %f\nValue of test: %f\n", Math.sqrt(Math.PI * n / 2), count * 1.0 / loop);
        StdOut.printf(String.valueOf(Math.log(a)) + "\n");
        StdOut.println(" ---- \n");
        StdOut.printf("%3.4f", Math.log(a));
    }
}

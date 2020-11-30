package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.Set;

public class Ex45_1 {
    static int totalAfterCollector(int n){
        Set<Integer> set = new HashSet<>();
        int count = 0;
        while (!containAll(set, n)){
            set.add(StdRandom.uniform(0, n));
            count++;
        }
        return count;
    }

    static boolean containAll(Set<Integer> set, int n){
        for (int i = 0; i < n; i++){
            if (!set.contains(i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int loops = 5;
        for (int n = 5; n < 10; n += 2){
            double average = 0;
            for (int i = 0; i < loops; i++){
                average += totalAfterCollector(n);
            }
            average /= loops;
            StdOut.printf("Lab value: %f N = %d, Hn = %f\n", average, n, average / n);
        }
    }
}

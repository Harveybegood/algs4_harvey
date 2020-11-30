package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/*
*   Throwing two eggs from a building. Consider the previous question, but now suppose you only have two eggs, and
*   your cost model is the number of throws. Devise a strategy to determine F such that the number of throws is at
*   most 2√2, then find a way to reduce the cost to ~c √F. This is analogous to a situation where search hits(egg
*   intact) are much cheaper than misses(egg broken)
*
* */
public class Ex25 {
    // Newton iteration to calculate sqrt root
    public static long sqrt(double k){
        double result = k;
        double e = 0.000000001;
        while (Math.abs(result - k / result) > 0){
            result = (result + k / result) / 2;
        }
        return (long) result;
    }
    // find the floor which will make egg broken
    static class throwingFromBuilding{
        public static int throwTimes;
        public static int brokenEggs;
        public static int searchFloor_2sqrt(boolean[] building){
            throwTimes = 0;
            brokenEggs = 0;
            int N = building.length;
            int root = (int)sqrt(N), k = root, i = 0;
            if (building[root]){
                throwTimes++;
                brokenEggs++;
                for (int j = 0; j <= root; j++){
                    throwTimes++;
                    if (building[j]){
                        brokenEggs++;
                        return j;
                    }
                }
            }
            while (k < N && !building[k-1]){
                throwTimes++;
                k = (++i) * root;
            }
            if (k < N) brokenEggs++;
            for (int j = (i-1)*root - 1; j < Math.min(i*root, N); j++){
                throwTimes++;
                if (building[j]){
                    brokenEggs++;
                    return j;
                }
            }
            return -1;
        }
    }
    public static boolean[] sourceArray(int N){
        int leftCount = StdRandom.uniform(1,N);
        boolean[] array = new boolean[N];
        for (int i = 0; i < leftCount; i++){
            array[i] = false;
        }
        for (int i = leftCount; i < N; i++){
            array[i] = true;
        }
        return array;
    }
    public static void printArray(boolean[] array){
        for (int i = 0; i < array.length; i++){
            StdOut.printf("%4d ", i + 1);
        }
        for (int i = 0; i < array.length; i++){
            StdOut.print(array[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        boolean[] array = sourceArray(10);
        int floor_2sqrt = throwingFromBuilding.searchFloor_2sqrt(array);
        StdOut.printf("which floor is the threshold: \n", + floor_2sqrt + 1);
        StdOut.printf("Throw %d,  broken eggs: %d \n", throwingFromBuilding.throwTimes, throwingFromBuilding.brokenEggs);
    }
}

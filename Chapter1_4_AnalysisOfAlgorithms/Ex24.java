package Chapter1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Throwing eggs from a building. Suppose that you have an N-story building and plenty of eggs. Suppose also that
*   an egg is broken if it is thrown off floor F or higher, and unhurt otherwise. First, devise a strategy to determine
*   the value of F such that the number of broken eggs is ~lg N when using ~lg N throws, then find a way to reduce
*   the cost to ~2lg F.
*
* */
public class Ex24 {
    // The number of broken eggs is ~lg N when using ~lg N throws
    private static int brokenEggs;
    private static int throwTimes;
    public static int searchStory_lgN(boolean[] building){
        brokenEggs = 0;
        throwTimes = 0;
        int N =  building.length;
        int low = 0;
        int high = N - 1;
        while (low < high){
            int middle = (low + high) / 2;
            throwTimes++;
            if (building[middle]) {
                brokenEggs++;
                high = middle;
            }
            if (!building[middle])low = middle + 1;
        }
        return high;
    }
    public static int searchStory_2lgF(boolean[] building){
        brokenEggs = 0;
        throwTimes = 0;
        int N = building.length;
        int k = 1;
        while (k < N && !building[k]){
            throwTimes++;
            k *= 2;
        }
        if (k < N) brokenEggs++;
        k /= 2;
        int low = k;
        int high = k * 2 >= N ? N - 1 : k * 2;
        int middle;
        while (low < high){
            middle = (low + high) / 2;
            throwTimes++;
            if (building[middle]){
                high = middle;
                brokenEggs++;
            }
            if (!building[middle]) low = middle + 1;
        }
        return high;
    }
    public static boolean[] sourceArray(int N){
        int leftCount = StdRandom.uniform(1, N);
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
        StdOut.println();
        for (int i = 0; i < array.length; i++){
            StdOut.print(array[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        boolean[] array = sourceArray(10);
        printArray(array);
        StdOut.println(searchStory_lgN(array));
        StdOut.println(searchStory_2lgF(array));
    }
}

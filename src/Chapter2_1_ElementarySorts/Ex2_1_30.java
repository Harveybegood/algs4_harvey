package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;

/*
*   Geometric increments. Run experiments to determine a value of t that leads to the lowest running time of shellsort
*   for random arrays for the increment sequence 1, ɭtȷ, ɭt^2ȷ, ɭt^3ȷ, ɭt^4ȷ ... for N = 10^6. Give the values of t
*   and the increment sequences for the best three values that you find.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_1_30 {
    private static double[] minimumTimes = new double[3];
    private static int[] bestTvalues = new int[3];
    private static List<Integer[]> bestIncrementSequences = new ArrayList<>();
    public static Integer[] getIncrementSequence(int tValue, int arrayLength){
        int maxIncrement = 1;
        int numberOfIncrement = 1;
        int value = tValue;
        while (value < arrayLength){
            maxIncrement = value;
            value *= tValue;
            numberOfIncrement++;
        }
        Integer[] incrementSequence = new Integer[numberOfIncrement];
        int index = 0;
        while (maxIncrement > 0){
            incrementSequence[index] = maxIncrement;
            maxIncrement = maxIncrement / tValue;
            index++;
        }
        return incrementSequence;
    }
    public static double time(Comparable[] array, Integer[] incrementSequence){
        Stopwatch timer = new Stopwatch();
        shellSortWithIncrement(array,incrementSequence);
        return timer.elapsedTime();
    }
    public static void updateMinimumTimes(double currentTime, int tValue, Integer[] incrementSequence){
        int timeToReplace = -1;
        for (int i = 0; i < minimumTimes.length; i++){
            if (currentTime < minimumTimes[i]){
                timeToReplace = i;
                break;
            }
        }
        if (timeToReplace == -1){
            return;
        }
        minimumTimes[timeToReplace] = currentTime;
        bestTvalues[timeToReplace] = tValue;
        bestIncrementSequences.set(timeToReplace, incrementSequence);
    }
    public static void timeRandomInput(int arrayLength, int numberOfExperiments){
        int tValue = 2;
        for (int experiment = 0; experiment < numberOfExperiments; experiment++){
            Comparable[] array = new Comparable[arrayLength];
            for (int i = 0; i < arrayLength; i++){
                array[i] = StdRandom.uniform();
            }
            Integer[] incrementSequence = getIncrementSequence(tValue, arrayLength);
            double time = time(array, incrementSequence);
            updateMinimumTimes(time, tValue, incrementSequence);
            tValue++;
        }
    }

    public static void shellSortWithIncrement(Comparable[] array, Integer[] incrementSequence){
        for (int increment : incrementSequence){
            for (int j = increment; j < array.length; j++){
                int currentIndex = j;
                while (currentIndex >= increment && array[currentIndex].compareTo(array[currentIndex - increment]) < 0){
                    Comparable temp = array[currentIndex];
                    array[currentIndex] = array[currentIndex - increment];
                    array[currentIndex - increment] = temp;
                    currentIndex = currentIndex - increment;
                }
            }
        }
    }
    private static void showBestTValueAndIncrementSequence(){
        for (int i = 0; i < bestTvalues.length; i++){
            StdOut.printf("Best %d tValue: %d \n", i + 1, bestTvalues[i]);
            StdOut.printf("Best %d sequence: \n", i + 1);
            Integer[] incrementSequence = bestIncrementSequences.get(i);
            for (int j = 0; j < incrementSequence[j]; j++){
                StdOut.print(incrementSequence[j] + " ");
            }
            StdOut.println();
            StdOut.println();
        }
    }
    public static void main(String[] args) {
        int arrayLength = 1000000;
        int numberOfExperiments = 10;
        for (int i = 0; i < minimumTimes.length; i++){
            minimumTimes[i] = Double.MAX_VALUE;
            bestIncrementSequences.add(new Integer[]{});
        }
        timeRandomInput(arrayLength, numberOfExperiments);
        showBestTValueAndIncrementSequence();
    }
}

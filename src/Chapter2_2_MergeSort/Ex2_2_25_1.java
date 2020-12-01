package Chapter2_2_MergeSort;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;

public class Ex2_2_25_1 {
    // main function
    public static void main(String[] args) {
        Comparable[] experimentArray = generateArray(15);
        int numberOfExperiment = 10;
        for (int kWayArrayToMerge = 2; kWayArrayToMerge <= 10; kWayArrayToMerge++){
            String text = kWayArrayToMerge + "-Way MergeSort";
            StdOut.println(text);
            doExperiment(experimentArray, kWayArrayToMerge, numberOfExperiment);
            StdOut.println();
        }

    }
    private static void doExperiment(Comparable[] array, int kWayArrayToMerge, int numberOfExperiment){
        StdOut.printf("%12s", "Running Time - ");
            double totalRunningTIme = 0.0;
            double runningTime;
            for (int i = 0; i < numberOfExperiment; i++){
                Stopwatch timer = new Stopwatch();
                kWaySort(array, kWayArrayToMerge);
                runningTime = timer.elapsedTime();
                totalRunningTIme += runningTime;
            }
        printResults(totalRunningTIme / numberOfExperiment);

    }
    // generate new array
    private static Comparable[] generateArray(int n){
        Comparable[] newArray = new Comparable[n];
        for (int i = 0; i < n; i++){
            newArray[i] = StdRandom.uniform(1, 100);
        }
        return newArray;
    }
    // recursive call and initialize aux array
    private static void kWaySort(Comparable[] array, int kWayArrayToMerge){
        Comparable[] aux = new Comparable[array.length];
        kWaySort(array, aux, kWayArrayToMerge, 0, array.length - 1);
    }
    private static void kWaySort(Comparable[] array, Comparable[] aux, int kWayArrayToMerge, int low, int high){
        if (high <= low)
            return;
        List<int[]> indices = generateIndices(kWayArrayToMerge, low, high);
        int[] startIndex = indices.get(0);
        int[] endIndex = indices.get(1);
        for (int i = 0; i < kWayArrayToMerge; i++){
            kWaySort(array, aux, kWayArrayToMerge, startIndex[i], endIndex[i]);
        }
        kWayToMerge(array, aux, startIndex, endIndex);
    }
    private static List<int[]> generateIndices(int kWayArrayToMerge, int low, int high){
        List<int[]> indices = new ArrayList<>();
        int length = high - low + 1;
        int subArrayLength = length / kWayArrayToMerge;
        int remainingValue = length % kWayArrayToMerge;
        int[] startIndex = new int[kWayArrayToMerge];
        int[] endIndex = new int[kWayArrayToMerge];
        for (int i = 0; i < startIndex.length; i++){
            if (i == 0)
                startIndex[i] = low;
            else
                startIndex[i] = Math.min(endIndex[i - 1] + 1, high);
            int extraValue = 0;
            if (remainingValue > 0){
                extraValue = 1;
                remainingValue--;
            }
            endIndex[i] = Math.min(startIndex[i] + subArrayLength - 1 + extraValue, high);
        }
        indices.add(startIndex);
        indices.add(endIndex);
        return indices;
    }
    @SuppressWarnings("unchecked")
    private static void kWayToMerge(Comparable[] array, Comparable[] aux, int[] startIndexes, int[] endIndexes){
        int low = startIndexes[0];
        int high = -1;

        //We may not have all arrays filled
        for(int i = 0; i < endIndexes.length; i++) {
            if (endIndexes[i] > high) {
                high = endIndexes[i];
            }
        }

        for(int i = low; i <= high; i++) {
            aux[i] = array[i];
        }

        for(int i = low; i <= high; i++) {
            Comparable bestValue = -1;
            int bestIndex = -1;

            for(int j = 0; j < startIndexes.length; j++) {
                int index = startIndexes[j];

                if (index <= endIndexes[j] && (bestIndex == -1 || aux[index].compareTo(bestValue) < 0)) {
                    bestValue = aux[index];
                    bestIndex = j;
                }
            }

            if (bestIndex != -1) {
                array[i] = bestValue;
                startIndexes[bestIndex]++;
            }
        }
    }
    private static void printResults( double runningTime) {
        StdOut.printf("%8.1f\n", runningTime);
    }

}

package Chapter2_2_MergeSort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;

public class Ex2_2_25 {
    private static Comparable[] aux;
    private static void kWayToSort(Comparable[] array, int kWayArrays){
        aux = new Comparable[array.length];
        kWayToSort(array, aux, kWayArrays, 0, array.length - 1);
    }
    private static void kWayToSort(Comparable[] array, Comparable[] aux, int kWayArrays, int low, int high){
        if (high <= low)
            return;
        List<int[]> indices = generateIndicesList(kWayArrays, low, high);
        int[] startIndices = indices.get(0);
        int[] endIndices = indices.get(1);
        for (int i = 0; i < kWayArrays; i++){
            kWayToSort(array, aux, kWayArrays, startIndices[i], endIndices[i]);
        }
        kWayToMerge(array, aux, startIndices, endIndices);
    }
    private static List<int[]> generateIndicesList(int kWayArrays, int low, int high){
        List<int[]> indices = new ArrayList<>();
        int length = high - low + 1;
        int subArrayLength = length / kWayArrays;
        int remainingValues = length % kWayArrays;
        int[] startIndices = new int[kWayArrays];
        int[] endIndices = new int[kWayArrays];
        for (int i = 0; i < kWayArrays; i++){
            if (i == 0)
                startIndices[i] = low;
            else
                startIndices[i] = endIndices[i-1] + 1;
            int extraValue = 0;
            if (remainingValues > 0){
                extraValue += 1;
                remainingValues--;
            }
            endIndices[i] = startIndices[i] + subArrayLength - 1 + extraValue;
        }
        indices.add(startIndices);
        indices.add(endIndices);
        return indices;
    }
    @SuppressWarnings("unchecked")
    private static void kWayToMerge(Comparable[] array, Comparable[] aux, int[] startIndices, int[] endIndices){
        int low = startIndices[0];
        int high = -1;
        for (int i = low; i < endIndices.length; i++){
            if (endIndices[i] > high){
                high = endIndices[i];
            }
        }
        for (int i = low; i <= high; i++){
            aux[i] = array[i];
        }
        for (int  i = low; i <= high; i++){
            Comparable bestValue = -1;
            int bestIndex = -1;
            for (int j = 0; j < startIndices.length; j++){
                int index = startIndices[j];
                if (index <= endIndices[j] && (bestIndex == -1 || aux[index].compareTo(bestValue) < 0)){
                    bestValue = aux[index];
                    bestIndex = j;
                }
                if (bestIndex != -1){
                    array[i] = bestValue;
                    startIndices[bestIndex]++;
                }
            }
        }
    }
    private static Comparable[] generateArray(int n){
        Comparable[] newArray = new Comparable[n];
        for (int i = 0; i < n; i++){
            newArray[i] = StdRandom.uniform(1, 100);
        }
        return newArray;
    }
    private static void printExperimentalResult(int j, int i, double runningTime){
        StdOut.printf("%4d %13d % 12.1f\n", j, i, runningTime);
    }
    private static void doExperiment(int kWayArrayToMerge){
        StdOut.printf("%2d - kWayArrayToMerge\n", kWayArrayToMerge);
        Comparable[] array;
        for (int i = 1000000; i <= 10000000; i += 1000000 ){
            array = generateArray(i);
            Stopwatch timer = new Stopwatch();
            kWayToSort(array, kWayArrayToMerge);
            double time = timer.elapsedTime();
            printExperimentalResult(i/1000000, i, time);
        }
    }

    public static void main(String[] args) {
        StdOut.printf("%5s %12s %15s\n", "Times", "Array Size", "Running Time");
        for (int i = 2; i <= 10; i++){
            doExperiment(i);
        }
    }
}

package Chapter2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
*   Various types of items. Write a client that generates arrays of items of various types with random key values,
*   including the following:
*
*       String key(at least ten characters), one double value
*       double key, ten String values(all at least ten characters)
*       int key, one int[20] value
*
*   Develop and Test hypotheses about the effect of such input on the performance of the algorithms in this section.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_1_38 {
    public enum SortType{
        SELECTION, INSERTION, SHELL
    }
    public enum KeyType{
        String, Double, Integer,
    }
    // K - the type of keys maintained by this map
    // V - the type of mapped values
    private static boolean isLower(Map[] array, int index1, int index2, KeyType keyType){
        switch (keyType){
            case String:
                String stringKeyObject1 = null;
                for (Object key : array[index1].keySet()){
                    stringKeyObject1 = (String) key;
                }
                String stringKeyObject2 = null;
                for (Object key : array[index2].keySet()){
                    stringKeyObject2 = (String) key;
                }
                if (stringKeyObject1 == null || stringKeyObject2 == null){return false;}
                for (int c = 0; c < stringKeyObject2.toCharArray().length; c++){
                    if (stringKeyObject1.length() == c){return true;}
                    if (stringKeyObject1.charAt(c) < stringKeyObject2.charAt(c)){return true;}
                    else if (stringKeyObject1.charAt(c) > stringKeyObject2.charAt(c)){return false;}
                }
                break;
            case Double:
                Double doubleKeyObject1 = null;
                for (Object key : array[index1].keySet()){
                    doubleKeyObject1 = (double) key;
                }
                Double doubleKeyObject2 = null;
                for (Object key : array[index2].keySet()){
                    doubleKeyObject2 = (double) key;
                }
                if (doubleKeyObject1 == null || doubleKeyObject2 == null){return false;}
                if (doubleKeyObject1 < doubleKeyObject2){return true;}
                break;
            case Integer:
                Integer integerKeyObject1 = null;
                for (Object key : array[index1].keySet()){
                    integerKeyObject1 = (int) key;
                }
                Integer integerKeyObject2 = null;
                for (Object key : array[index2].keySet()){
                    integerKeyObject2 = (int) key;
                }
                if (integerKeyObject1 == null || integerKeyObject2 == null){return false;}
                if (integerKeyObject1 < integerKeyObject2){return true;}
                break;
        }
        return false;
    }
    private static void swapElements(Map[] array, int index1, int index2){
        Map temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
    private static void selectionSort(Map[] a, KeyType keyType){
        for (int i = 0; i < a.length; i++){
            int minIndex = i;
            for (int j = i + 1; j <a.length; j++){
                if (isLower(a, j, minIndex, keyType)){
                    minIndex = j;
                }
            }
            swapElements(a, i, minIndex);
        }
    }
    public static void insertionSort(Map[] a, KeyType keyType){
        int N = a.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j > 0 && isLower(a, j, j-1, keyType); j--){
                swapElements(a, j, j - 1);
            }
        }
    }
    public static void shellSort(Map[] a, KeyType keyType){
        int N = a.length;
        int increment = 1;
        while (increment < N / 3){increment = increment * 3 + 1;}
        while (increment >= 1){
            for (int i = increment; i < N; i++){
                for (int j = i; j >= increment && isLower(a, j, j - increment, keyType); j -= increment){
                    swapElements(a, j, j - increment);
                }
            }
            increment = increment / 3;
        }
    }
    private static String generate10CharRandomString(){
        char[] chars = new char[10];
        for (int i = 0; i < chars.length; i++){
            int randomCharIntValue = StdRandom.uniform(65, 90);
            char randomCharValue1 = (char) randomCharIntValue;
            chars[i] = randomCharValue1;
        }
        return new String(chars);
    }
    private static Map[] generateStringKeyDOubleValueArray(int arrayLength){
        Map[] array = new HashMap[arrayLength];
        for (int i = 0; i < arrayLength; i++){
            Map<String, Double> keyValues = new HashMap<>();
            String randomKey = generate10CharRandomString();
            double randomValue = StdRandom.uniform();
            keyValues.put(randomKey, randomValue);
            array[i] = keyValues;
        }
        return array;
    }
    private static Map[] generateDoubleKeyStringValuesArray(int arrayLength){
        Map[] array = new HashMap[arrayLength];
        for (int i = 0; i < arrayLength; i++){
            Map<Double, String[]> keyValues = new HashMap<>();
            double randomKey = StdRandom.uniform();
            String[] randomValue = new String[10];
            for (int j = 0; j < randomValue.length; j++){
                randomValue[j] = generate10CharRandomString();
            }
            keyValues.put(randomKey, randomValue);
            array[i] = keyValues;
        }
        return array;
    }
    private static Map[] generateIntKeyIntArrayValuesArray(int arrayLength){
        Map[] array = new HashMap[arrayLength];
        for (int i = 0; i < arrayLength; i++){
            Map<Integer, Integer[]> keyValues = new HashMap<>();
            int randomKey = StdRandom.uniform(Integer.MAX_VALUE);
            Integer[] randomValue = new Integer[20];
            for (int j = 0; j < randomValue.length; j++){
                randomValue[j] = StdRandom.uniform(Integer.MAX_VALUE);
            }
            keyValues.put(randomKey, randomValue);
            array[i] = keyValues;
        }
        return array;
    }
    public static double time(SortType sortType, KeyType keyType, Map[] array){
        Stopwatch timer = new Stopwatch();
        switch (sortType){
            case SELECTION:
                selectionSort(array, keyType);
                break;
            case INSERTION:
                insertionSort(array, keyType);
                break;
            case SHELL:
                shellSort(array, keyType);
        }
        return timer.elapsedTime();
    }
    private static void printExperiment(SortType sortType, int arrayLength, double time){
        StdOut.printf("%15s %17d %8.2f\n", sortType, arrayLength, time);
    }
    private static void runExperiment(Map[] array, KeyType keyType){
        Map[] arrayCopy1 = Arrays.copyOf(array, array.length);
        Map[] arrayCopy2 = Arrays.copyOf(array, array.length);
        double timeSelection = time(SortType.SELECTION, keyType, array);
        printExperiment(SortType.SELECTION, array.length, timeSelection);
        double timeInsertion = time(SortType.INSERTION, keyType, arrayCopy1);
        printExperiment(SortType.INSERTION, array.length, timeInsertion);
        double timeShell = time(SortType.SHELL, keyType, arrayCopy2);
        printExperiment(SortType.SHELL, array.length, timeShell);
    }
    public static void runAllExperiments(int initialArrayLength, int experiments){
        int arrayLength = initialArrayLength;
        StdOut.printf("%15s %17s %8s\n", "Sort Type", "Array Length", "Time");
        StdOut.println();
        StdOut.println("String key, one double value");
        for (int experiment = 0; experiment < experiments; experiment++){
            Map[] stringKeyDoubleValueArray = generateStringKeyDOubleValueArray(arrayLength);
            runExperiment(stringKeyDoubleValueArray, KeyType.String);
            arrayLength *= 2;
        }
        StdOut.println();
        StdOut.println();

        arrayLength = initialArrayLength;
        StdOut.println("Double key, ten string values");
        for (int experiment = 0; experiment < experiments; experiment++){
            Map[] doubleKeyStringValueArray = generateDoubleKeyStringValuesArray(arrayLength);
            runExperiment(doubleKeyStringValueArray, KeyType.Double);
            arrayLength *= 2;
        }
        StdOut.println();
        StdOut.println();

        arrayLength = initialArrayLength;
        StdOut.println("Int key, one int[20] value");
        for (int experiment = 0; experiment < experiments; experiment++){
            Map[] intKeyIntArrayValueArray = generateIntKeyIntArrayValuesArray(arrayLength);
            runExperiment(intKeyIntArrayValueArray, KeyType.Integer);
            arrayLength *= 2;
        }
    }

    public static void main(String[] args) {
        int initialArrayLength = 400;
        int experiments = 5;
        runAllExperiments(initialArrayLength, experiments);
    }
}

package Chapter3_1_SymbolTables;

import AnalysisOfAlgorithmsTest.Stopwatch;
import Tool.FileTool;
import edu.princeton.cs.algs4.StdOut;

/*
*   Performance validation I. Run doubling tests that use the first N words of Tale of Two Cities for various values of
*   N to test the hypothesis that the running time of FrequencyCounter is quadratic when it uses SequentialSearchST for
*   its symbol table.
*
* */
public class Ex35_PerformanceValidationI {
    public void doExperiment(String[] words, int minLength){
        int numberOfWords = 200;
        String[] currentWords = new String[numberOfWords];
        System.arraycopy(words, 0, currentWords, 0, currentWords.length);
        Stopwatch timer = new Stopwatch();
        frequencyCounter(currentWords, minLength);
        double previousTime = timer.elapsedTime();
        for (int i = 0; i < 8; i++){
            currentWords = new String[numberOfWords * 2];
            System.arraycopy(words, 0, currentWords, 0, currentWords.length);
            Stopwatch newTimer = new Stopwatch();
            frequencyCounter(currentWords, minLength);
            double nextTime = newTimer.elapsedTime();
            double ration = nextTime / previousTime;
            StdOut.printf("%10d %10.2f %12.2f\n", numberOfWords, nextTime, ration);
            previousTime = nextTime;
            numberOfWords *= 2;
        }
    }
    public String frequencyCounter(String[] words, int minLength){
        SequentialSearchST<String, Integer> sequentialSearchST = new SequentialSearchST<>();
        for (String word : words){
            if (word.length() < minLength) continue;
            if (!sequentialSearchST.contains(word)){
                sequentialSearchST.put(word, 1);
            }else {
                sequentialSearchST.put(word, sequentialSearchST.get(word) + 1);
            }
        }
        String max = "";
        sequentialSearchST.put(max, 0);
        for (String word : sequentialSearchST.keys()){
            if (sequentialSearchST.get(word) > sequentialSearchST.get(max)){
                max = word;
            }
        }
        return max + " " + sequentialSearchST.get(max);
    }

    public static void main(String[] args) {
        StdOut.printf("%10s %10s %10s\n", "numberOfWords", "runningTime", "ratio");
        String filepath = "/usr/local/algs4/src/algs4-data/tale.txt";
        String[] words = FileTool.getAllStringsFromFile(filepath);
        new Ex35_PerformanceValidationI().doExperiment(words, 2);
    }
}

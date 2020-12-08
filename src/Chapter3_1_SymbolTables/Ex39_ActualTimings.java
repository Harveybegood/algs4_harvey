package Chapter3_1_SymbolTables;

import Tools.FileTool;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import Test.VisualAccumulator;

/*
*   Actual timings. Instrument FrequencyCounter to use Stopwatch and StdDraw to make a plot where the x-axis is the
*   number of calls on get() or put() and the y-axis is the total running time, with a point plotted of the cumulative
*   time after each call. Run your program for Tale of Two Cities using SequentialSearchST and again using
*   BinarySearchST and discuss the results. Note: Sharp jumps in the curve may be explained by caching, which is beyond
*   the scope of this question.
*
* */
public class Ex39_ActualTimings {
    public static void main(String[] args) {
        String filePath = "/usr/local/algs4/src/algs4-data/tale.txt";
        String[] currentWords = FileTool.getAllStringsFromFile(filePath);
        Ex39_ActualTimings actualTimings = new Ex39_ActualTimings();
        actualTimings.doExperiment(currentWords, 8, "SequentialSearch");
        actualTimings.doExperiment(currentWords, 8, "BinarySearch");
    }
    public void doExperiment(String[] words, int minLen, String s){
        if (s.equals("SequentialSearch")){
            double maxRunningTime = 3000;
            int totalGetPut = 45000;
            double totalTime = 0;
            //int numberOfGet = 0;
            //int numberOfPut = 0;
            Stopwatch timer;
            VisualAccumulator visualAccumulator = new VisualAccumulator(totalGetPut, maxRunningTime);
            SequentialSearchST<String, Integer> sequentialSearchST = new SequentialSearchST<>();
            for (int i = 0; i < words.length; i++){
                if (words[i].length() < minLen) continue;
                if (!sequentialSearchST.contains(words[i])){
                    //numberOfPut++;
                    timer = new Stopwatch();
                    sequentialSearchST.put(words[i], 1);
                    totalTime += timer.elapsedTime() * 1000;
                    visualAccumulator.addDataValue(totalTime);

                }else {
                    //numberOfGet++;
                    timer = new Stopwatch();
                    int frequency = sequentialSearchST.get(words[i]);
                    totalTime += timer.elapsedTime() * 1000;
                    visualAccumulator.addDataValue(totalTime);
                    //numberOfPut++;
                    timer = new Stopwatch();
                    sequentialSearchST.put(words[i], frequency + 1);
                    totalTime += timer.elapsedTime() * 1000;
                    visualAccumulator.addDataValue(totalTime);
                }
            }
            String max = "";
            timer = new Stopwatch();
            sequentialSearchST.put(max, 0);
            totalTime += timer.elapsedTime() * 1000;
            //numberOfPut++;
            visualAccumulator.addDataValue(totalTime);
            for (String word : sequentialSearchST.keys()){
                if (word.compareTo(max) > 0){
                    max = word;
                }
            }
            timer = new Stopwatch();
            StdOut.println(max + " " + sequentialSearchST.get(max));
            totalTime += timer.elapsedTime() * 1000;
            visualAccumulator.addDataValue(totalTime);
            //numberOfGet++;
            //totalGetPut = numberOfPut + numberOfGet;
        }
        if (s.equals("BinarySearch")){
            int totalGetPut = 45000;
            double totalTime = 0;
            //int numberOfGet = 0;
            //int numberOfPut = 0;
            double maxRunningTime = 3000;
            Stopwatch timer;
            VisualAccumulator visualAccumulator = new VisualAccumulator(totalGetPut, maxRunningTime);
            binarySearchST<String, Integer> binarySearchST = new binarySearchST<>(2);
            for (int i = 0; i < words.length; i++){
                if (words[i].length() < minLen) continue;
                if (!binarySearchST.contains(words[i])){
                    timer = new Stopwatch();
                    //numberOfPut++;
                    totalTime += timer.elapsedTime() * 1000;
                    visualAccumulator.addDataValue(totalTime);
                    binarySearchST.put(words[i], 1);
                }else {
                    timer = new Stopwatch();
                    int frequency = binarySearchST.get(words[i]);
                    //numberOfGet++;
                    totalTime += timer.elapsedTime() * 1000;
                    visualAccumulator.addDataValue(totalTime);
                    timer = new Stopwatch();
                    //numberOfPut++;
                    binarySearchST.put(words[i], frequency + 1);
                    totalTime += timer.elapsedTime() * 1000;
                    visualAccumulator.addDataValue(totalTime);
                }
            }
            String max = "";
            //numberOfPut++;
            timer = new Stopwatch();
            binarySearchST.put(max, 0);
            totalTime += timer.elapsedTime() * 1000;
            visualAccumulator.addDataValue(totalTime);
            for (String word : binarySearchST.keys()){
                if (word.compareTo(max) > 0){
                    max = word;
                }
            }
            timer = new Stopwatch();
            StdOut.println(max + " " + binarySearchST.get(max));
            //numberOfGet++;
            totalTime += timer.elapsedTime() * 1000;
            visualAccumulator.addDataValue(totalTime);
            //totalGetPut = numberOfPut + numberOfGet;
        }
    }
}

//https://github.com/reneargento/algorithms-sedgewick-wayne/blob/master/src/chapter3/section1/Exercise39_ActualTimings.java
package Chapter3_2_BinarySearchTrees;

import Chapter3_1_SymbolTables.Ex05_SequentialSearchST;
import Chapter3_1_SymbolTables.binarySearchST;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stopwatch;

import java.awt.*;

/*
*   Actual timings. Instrument FrequencyCounter to use Stopwatch and StdDraw to make a plot where the x axis is the number
*   of calls on get() or put() and the y axis is the total running time, with a point plotted of the cumulative time after
*   each call. Run your program for Tale of Two Cities using SequentialSearchST and again using BinarySearchST and again using
*   BST and discuss the results. Note: Sharp jumps in the curve may be explained by caching, which is beyond the scope of this
*   question(see Ex3.1.39).
*
* */
public class Ex45_ActualTimings {
    private static double timeCostOfSequentialSearchST = 0;
    private static double timeCostOfBinarySearchST = 0;
    private static double timeCostOfBST = 0;
    private static int callsOfGetPutSequentialSearchST = 0;
    private static int callsOfGetPutBinarySearchST = 0;
    private static int callsOfGetPutBST = 0;
    public static void frequencyCounter(int n){
        //Ex03_OrderedSequentialSearchST<String, Integer> orderedSequentialSearchST = new Ex03_OrderedSequentialSearchST<>();
        Ex05_SequentialSearchST<String, Integer> sequentialSearchST = new Ex05_SequentialSearchST<>();
        binarySearchST<String, Integer> binarySearchST = new binarySearchST<>();
        Ex10_TestBST<String, Integer> testBST = new Ex10_TestBST<>();
        while (!StdIn.isEmpty()){
            String word = StdIn.readString();
            if (word.length() < n) continue;
            if (!sequentialSearchST.contains(word)){
                callsOfGetPutSequentialSearchST++;
                Stopwatch timer1 = new Stopwatch();
                sequentialSearchST.put(word, 1);
                timeCostOfSequentialSearchST += timer1.elapsedTime() * 1000;
            }
            else {
                callsOfGetPutSequentialSearchST++;
                Stopwatch timer2 = new Stopwatch();
                int value = sequentialSearchST.get(word);
                timeCostOfSequentialSearchST += timer2.elapsedTime() * 1000;
                callsOfGetPutSequentialSearchST++;
                Stopwatch timer3 = new Stopwatch();
                sequentialSearchST.put(word, value + 1);
                timeCostOfSequentialSearchST += timer3.elapsedTime() * 1000;
            }
            if (!binarySearchST.contains(word)){
                callsOfGetPutBinarySearchST++;
                Stopwatch timer1 = new Stopwatch();
                binarySearchST.put(word, 1);
                timeCostOfBinarySearchST += timer1.elapsedTime() * 1000;
            }
            else {
                callsOfGetPutBinarySearchST++;
                Stopwatch timer2 = new Stopwatch();
                int value = binarySearchST.get(word);
                timeCostOfBinarySearchST += timer2.elapsedTime() * 1000;
                callsOfGetPutBinarySearchST++;
                Stopwatch timer3 = new Stopwatch();
                binarySearchST.put(word, value + 1);
                timeCostOfBinarySearchST += timer3.elapsedTime() * 1000;
            }
            if (!testBST.contains(word)){
                callsOfGetPutBST++;
                Stopwatch timer1 = new Stopwatch();
                testBST.put(word, 1);
                timeCostOfBST += timer1.elapsedTime() * 1000;
            }
            else {
                callsOfGetPutBST++;
                Stopwatch timer2 = new Stopwatch();
                int value = testBST.get(word);
                timeCostOfBST += timer2.elapsedTime() * 1000;
                callsOfGetPutBST++;
                Stopwatch timer3 = new Stopwatch();
                testBST.put(word, value + 1);
                timeCostOfBST += timer3.elapsedTime() * 1000;
            }
            StdDraw.setXscale(0, 45000);
            StdDraw.setYscale(0, 3000);
            StdDraw.setPenRadius(0.003);
            StdDraw.setPenColor(Color.red);
            StdDraw.point(callsOfGetPutSequentialSearchST, timeCostOfSequentialSearchST);
            StdDraw.point(callsOfGetPutBinarySearchST, timeCostOfBinarySearchST);
            StdDraw.point(callsOfGetPutBST, timeCostOfBST);
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        frequencyCounter(n);
    }
}

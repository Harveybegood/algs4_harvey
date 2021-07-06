package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   List length range. Write a program that inserts N random int keys into a table of size N/100 using separate chaining,
*   then finds the length of the shortest and longest lists, for N = 10^3, 10^4, 10^5, 10^6.
*
* */
public class Ex36_ListLengthRange<Key, Value> extends SeparateChainingHashST<Key, Value> {
    //private int N;
    //private SeparateChainingHashST<Integer, Integer> separateChainingHashST = new SeparateChainingHashST<>(N / 100);
    public void generateSeparateChaining(int N, SeparateChainingHashST<Integer, Integer> separateChainingHashST){
        for (int i = 0; i < N; i++){
            int inputValue = StdRandom.uniform(-200, 200);
            separateChainingHashST.putWithNdivideMEquals100(inputValue, inputValue);
        }
    }
    //
    public int shortestList(SeparateChainingHashST<Integer, Integer> separateChainingHashST,int N){
        int lengthForShortestList = separateChainingHashST.lengthOfEachLinkedList(0);
        for (int i = 1; i < N / 100; i++){
            if (separateChainingHashST.lengthOfEachLinkedList(i - 1) > separateChainingHashST.lengthOfEachLinkedList(i)){
                lengthForShortestList = separateChainingHashST.lengthOfEachLinkedList(i);
            }
        }
        return lengthForShortestList;
    }
    public int longestList(SeparateChainingHashST<Integer, Integer> separateChainingHashST, int N){
        int lengthForLongestList = separateChainingHashST.lengthOfEachLinkedList(0);
        for (int i = 1; i < N / 100; i++){
            if (separateChainingHashST.lengthOfEachLinkedList(i - 1) > separateChainingHashST.lengthOfEachLinkedList(i)){
                lengthForLongestList = separateChainingHashST.lengthOfEachLinkedList(i - 1);
            }
        }
        return lengthForLongestList;
    }

    public static void main(String[] args) {
        Ex36_ListLengthRange<Integer, Integer> listLengthRange = new Ex36_ListLengthRange<>();
        int i = 10;
        for (int j = 3; j < 7; j++){
            int N = (int) Math.pow(i, j);
            SeparateChainingHashST<Integer, Integer> separateChainingHashST = new SeparateChainingHashST<>(N / 100);
            listLengthRange.generateSeparateChaining(N, separateChainingHashST);
            StdOut.println("N random int keys: " + N + " The length of the shortest lists: " + listLengthRange.shortestList(separateChainingHashST, N));
            StdOut.println("N random int keys: " + N + " The length of the longest lists: " + listLengthRange.longestList(separateChainingHashST, N));
        }
    }
}

/*
*   N random int keys: 1000 The length of the shortest lists: 36
    N random int keys: 1000 The length of the longest lists: 38
    N random int keys: 10000 The length of the shortest lists: 0
    N random int keys: 10000 The length of the longest lists: 13
    N random int keys: 100000 The length of the shortest lists: 0
    N random int keys: 100000 The length of the longest lists: 13
    N random int keys: 1000000 The length of the shortest lists: 0
    N random int keys: 1000000 The length of the longest lists: 13
*
*
*
* */
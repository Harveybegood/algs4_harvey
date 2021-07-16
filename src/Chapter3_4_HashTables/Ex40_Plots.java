package Chapter3_4_HashTables;

import Tools.Constants;
import Tools.FileTool;
import Tools.VisualAccumulator;

/*
*   Plots. Instrument LinearProbingHashST and SeparateChainingHashST to produce plots like the ones shown in the text.
*
* */
@SuppressWarnings("unchecked")
public class Ex40_Plots<Key, Value> extends SeparateChainingHashST<Key, Value> {
    private class SequentialSearchST<Key, Value>{
        private class Node{
            Key key;
            Value value;
            Node next;
            public Node(Key key, Value value, Node next){
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }
        private Node first;
        private int n;
        public boolean isEmpty(){return n == 0;}
        public int size(){return n;}
        public boolean isContains(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            return get(key) != null;
        }
        public Value get(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            for (Node node = first; node != null; node = node.next){
                if (node.key.equals(key)){
                    return node.value;
                }
            }
            return null;
        }
        public int putAndCountCost(Key key, Value value){
            int costOfCompares = 1;
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            if (value == null){throw new IllegalArgumentException("Argument cannot be null");}
            for (Node node = first; node != null; node = node.next){
                if (node.key.equals(key)){
                    node.value = value;
                    return costOfCompares;
                }
            }
            first = new Node(key, value, first);
            n++;
            return costOfCompares;
        }
    }
    private int N;
    private int M;
    private SequentialSearchST<Key, Value>[] st;
    private static final int DEFAULT_HASH_TABLE_SIZE = 997;
    public Ex40_Plots(){
        this(DEFAULT_HASH_TABLE_SIZE);
    }
    public Ex40_Plots(int M){
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++){
            st[i] = new SequentialSearchST<>();
        }
    }
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public boolean isContains(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        return get(key) != null;
    }
    public int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        int i = hash(key);
        return st[i].get(key);
    }
    public int putAndCountCompares(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (value == null){throw new IllegalArgumentException("Argument cannot be null");}
        int i = hash(key);
        if (!isContains(key)){
            N++;
        }
        return st[i].putAndCountCost(key, value);
    }
    private String frequencyCounter(String[] words, int minLen){
        String title = "Cost of Compares for SeparateChaining Hash ST in put operations";
        String xAxisLabel = "operations";
        String yAxisLabel = "compare equality";
        double maxNumberOfOperations = 18000;
        double maxCost = 25;
        int originValue = 0;
        VisualAccumulator visualAccumulator = new VisualAccumulator(originValue, maxNumberOfOperations, maxCost, title,
                xAxisLabel, yAxisLabel);
        Ex40_Plots<String, Integer> plots = new Ex40_Plots<>();
        for (String word : words){
            if (word.length() < minLen){
                continue;
            }
            int compares;
            if (!plots.isContains(word)){
                compares = plots.putAndCountCompares(word, 1);
            }
            else {
                compares = plots.putAndCountCompares(word, plots.get(word) + 1);
            }
            visualAccumulator.addDataValue(compares, true);
        }
        String max = "";
        int compares = plots.putAndCountCompares(max, 0);
        visualAccumulator.addDataValue(compares, true);
        for (String word : plots.keys()){
            if (plots.get(word) > plots.get(max)){
                max = word;
            }
        }
        visualAccumulator.writeExactFinalMean();
        return max + " " + plots.get(max);
    }
    private static final String TALE_FILE_PATH = Constants.FILE_PATH + "tale.txt";
    public static void main(String[] args) {
        String[] wordsInTale = FileTool.getAllStringsFromFile("/usr/local/algs4-data/tale.txt");
        new Ex40_Plots<>().frequencyCounter(wordsInTale, 8);
    }
}



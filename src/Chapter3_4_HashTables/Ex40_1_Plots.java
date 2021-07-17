package Chapter3_4_HashTables;

import Tools.Constants;
import Tools.FileTool;
import Tools.VisualAccumulator;

/*
*   Plots. Instrument LinearProbingHashST and SeparateChainingHashST to produce plots like the ones shown in the text.
*
* */
public class Ex40_1_Plots<Key, Value> extends LinearProbingHashST<Key, Value> {
    /*private int M;
    private int N;*/
    private int costOfCompares;
    public Ex40_1_Plots(int M){
        super(M);
    }
    public int putAndCountCompares(Key key, Value value, boolean reset){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (value == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (reset){
            costOfCompares = 0;
        }
        if (N >= M /2){
            resize(2 * M);
        }
        costOfCompares++;
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M){
            costOfCompares++;
            if (keys[i].equals(key)){
                values[i] = value;
                return costOfCompares;
            }
        }
        keys[i] = key;
        values[i] = value;
        N++;
        return costOfCompares;
    }
    public void resize(int cap){
        Ex40_1_Plots<Key, Value> temp = new Ex40_1_Plots<>(cap);
        for (int i = 0; i < M; i++){
            if (keys[i] != null){
                temp.putAndCountCompares(keys[i], values[i], false);
            }
        }
        keys = temp.keys;
        values = temp.values;
        M = temp.M;
        costOfCompares += temp.costOfCompares;
    }
    private static String TALE_FILE_PATH = Constants.FILE_PATH + "tale.txt";
    public String frequencyCounter(String[] words, int midLen){
        String title = "Cost of compare for Linear probing in put operations";
        String xAxisLabel = "operations";
        String yAxisLabel = "compare equality";
        int originValue = 0;
        int maxNumberOfOperations = 16000;
        int maxCost = 22;
        VisualAccumulator visualAccumulator = new VisualAccumulator(originValue, maxNumberOfOperations, maxCost,
                title, xAxisLabel, yAxisLabel);
        Ex40_1_Plots<String, Integer> plots = new Ex40_1_Plots<>(10);
        for (String word : words){
            if (word.length() < midLen){
                continue;
            }
            int compares;
            if (!plots.contains(word)){
                compares = plots.putAndCountCompares(word, 1, true);
            }
            else {
                compares = plots.putAndCountCompares(word, plots.get(word) + 1, true);
            }
            visualAccumulator.addDataValue(compares, true);
        }
        String max = "";
        int compares = plots.putAndCountCompares(max, 0, true);
        visualAccumulator.addDataValue(compares, true);
        for (String word : plots.keys()){
            if (plots.get(word) > plots.get(max)){
                max = word;
            }
        }
        visualAccumulator.writeExactFinalMean();
        return max + " " + plots.get(max);
    }

    public static void main(String[] args) {
        String[] wordsInTale = FileTool.getAllStringsFromFile(TALE_FILE_PATH);
        int minLen = 8;
        new Ex40_1_Plots<>(10).frequencyCounter(wordsInTale, minLen);
    }
}

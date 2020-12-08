package Chapter3_1_SymbolTables;


import Tools.FileTool;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Frequency count from a dictionary. Modify FrequencyCounter to take the name of a dictionary file as its argument,
*   count frequencies of the words from standard input that are also in that file, and print two tables of the words
*   with their frequencies, one sorted by frequency, the other sorted in the order found in the dictionary file.
*
* */
public class Ex26_FrequencyCountDictionary {
    private static final String DictionaryFile = "/usr/local/algs4/src/algs4-data/tinyTale.txt";
    public static void main(String[] args) {

        String[] words = FileTool.getAllStringsFromFile(DictionaryFile);
        binarySearchST<String, Integer> binarySearchST = new binarySearchST<>(2);
        binarySearchST<String, Integer> binarySearchST1 = new binarySearchST<>(2);
        for (String word : words){
            if (!binarySearchST.contains(word)){
                binarySearchST.put(word, 1);
            }else {
                binarySearchST.put(word, binarySearchST.get(word) + 1);
            }
        }
        while (!StdIn.isEmpty()){
            String word = StdIn.readString();
            if (binarySearchST.contains(word)){
                binarySearchST1.put(word, binarySearchST.get(word));
            }
        }
        for (String word : binarySearchST1.keys()){
            StdOut.println(word + "  " + binarySearchST1.get(word));
        }
    }
    public static class Value implements Comparable<Value>{
        private String word;
        private int frequency;
        public Value(int frequency){
            this.frequency = frequency;
        }
        public int compareTo(Value that){
            if (this.frequency < that.frequency) return -1;
            else if (this.frequency > that.frequency) return 1;
            else return 0;
        }
    }
}

package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.StdOut;

/*
*   Add a method to LinearProbingHashST that computes the average cost of a search hit in the table, assuming that each key
*   in the table is equally likely to be sought.
*   Linear probing is characterized by identifying three possible outcome:
*   - Key equal to search key: search hit
*   - Empty position(null key at indexed position): search miss
*   - Key not equal to search key: try next entry
*
* */
public class Ex20_AverageCostSearchHit<Key, Value> extends LinearProbingHashST<Key, Value> {
    // The initial operation for search key is calculating the index associated with the search key by hash function, then do the
    // compare. if the entry based on the index matches the search key. we shall say search hit, or try next entry ...

    public static void main(String[] args) {
        Ex20_AverageCostSearchHit<String, Integer> averageCostSearchHit = new Ex20_AverageCostSearchHit<>();
        averageCostSearchHit.put("S", 0);
        averageCostSearchHit.put("E", 1);
        averageCostSearchHit.put("A", 2);
        averageCostSearchHit.put("R", 3);
        averageCostSearchHit.put("C", 4);
        averageCostSearchHit.put("H", 5);
        averageCostSearchHit.put("E", 6);
        averageCostSearchHit.put("X", 7);
        averageCostSearchHit.put("A", 8);
        averageCostSearchHit.put("M", 9);
        averageCostSearchHit.put("P", 10);
        averageCostSearchHit.put("L", 11);
        averageCostSearchHit.put("E", 12);
        averageCostSearchHit.put("S", 13);
        StdOut.println("Average cost: " + averageCostSearchHit.averageCostOfSearchHit());
    }
}

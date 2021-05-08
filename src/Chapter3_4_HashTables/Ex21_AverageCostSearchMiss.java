package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.StdOut;

/*
*   Add a method to LinearProbingHashST that computes the average cost of a search miss in a table, assuming a random hash
*   function. Note: You do not have to compute any hash functions to solve this problem.
*
* */
public class Ex21_AverageCostSearchMiss<Key, Value> extends LinearProbingHashST<Key, Value> {
    // not sure of what is that "assuming a random hash function"?
    // Is that a hash function tries to distribute keys "randomly" over table locations? and
    // due to the cluster? To reach to a null key at a indexed position where new key-value pair can be inserted,
    // so during which count the search miss
    public static void main(String[] args) {
        Ex21_AverageCostSearchMiss<String, Integer> averageCostSearchMiss = new Ex21_AverageCostSearchMiss<>();
        averageCostSearchMiss.put("S", 0);
        averageCostSearchMiss.put("E", 1);
        averageCostSearchMiss.put("A", 2);
        averageCostSearchMiss.put("R", 3);
        averageCostSearchMiss.put("C", 4);
        averageCostSearchMiss.put("H", 5);
        averageCostSearchMiss.put("Q", 6);
        averageCostSearchMiss.put("U", 7);
        averageCostSearchMiss.put("I", 8);
        averageCostSearchMiss.put("T", 9);
        averageCostSearchMiss.put("O", 10);
        averageCostSearchMiss.put("S", 11);
        StdOut.println("Average cost: " + averageCostSearchMiss.averageCostOfSearchMiss());
    }
}

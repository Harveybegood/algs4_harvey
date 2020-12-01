package Chapter3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Average case. Run empirical studies to estimate the average and standard deviation of the number of compares used for
*   search hits and for search misses in a BST built by running 100 trials of the experiment of inserting N random keys
*   into an initially empty tree, for N = 10^4, 10^5, and 10^6. Compare your results against the formula for the average
*   given in Ex3.2.25
*
*   Perfect balance. Write a program that inserts a set of keys into an initially empty
    BST such that the tree produced is equivalent to binary search, in the sense that the
    sequence of compares done in the search for any key in the BST is the same as the sequence
    of compares used by binary search for the same set of keys
*
* */
public class Ex39_AverageCase<Key extends Comparable<Key>, Value> {
     private class Node{
         private Node left, right;
         private Key key;
         private Value value;
         int countOfNode;
         public Node(Key key, Value value, int countOfNode){
             this.key = key;
             this.value = value;
             this.countOfNode = countOfNode;
         }
     }
     private Node root;
     private static int NumberOfHits;
     private static int NumberOfMisses;
     public int size(){
         return size(root);
     }

     private int size(Node node){
         if (node == null)
             return 0;
         return node.countOfNode;
     }
     public Value get(Key key){
         return get(root, key);
     }
     private Value get(Node node, Key key){
         if (node == null)
             return null;
         int cmp = key.compareTo(node.key);
         if (cmp < 0){
             return get(node.left, key);
         }
         else if (cmp > 0){
             return get(node.right, key);
         }
         else {
             return node.value;
         }
     }
     public void put(Key key, Value value){
         root = put(root, key, value);
     }
     private Node put(Node node, Key key, Value value){
         if (node == null) {
             NumberOfMisses++;
             return new Node(key, value, 1);
         }
         int cmp = key.compareTo(node.key);
         if (cmp < 0){
             node.left = put(node.left, key, value);
         }
         else if (cmp > 0){
             node.right = put(node.right, key, value);
         }
         else {
             NumberOfHits++;
             node.value = value;
         }
         node.countOfNode = size(node.left) + size(node.right) + 1;
         return node;
     }
     // compute the average and standard deviation

     public static void averageAndStDev(int n){
         Ex39_AverageCase<String, Integer> averageCase = new Ex39_AverageCase<>();
         int Hits[] = new int[100];
         int Misses[] = new int[100];
         int averageHits = 0;
         int averageMisses = 0;
         for (int i = 0; i < 100;i++){
             for (int j = 0; j < n; j++){
                 averageCase.put("Average" + StdRandom.uniform(1, n), StdRandom.uniform(1, n));
             }
             Hits[i] = NumberOfHits;
             Misses[i] = NumberOfMisses;
             averageHits += NumberOfHits;
             averageMisses += NumberOfMisses;
         }
         StdOut.println("averageHits: " + averageHits / 100 + " " + "averageMisses: " + averageMisses / 100);
         int sumOfAverageHits = 0;
         int sumOfAverageMisses = 0;
         for (int i = 0; i < 100; i++){
             sumOfAverageHits += Math.pow(Hits[i] - (averageHits / 100), 2);
             sumOfAverageMisses += Math.pow(Misses[i] - (averageMisses / 100), 2);
         }
         StdOut.println("Standard deviation of Hits: " + Math.sqrt(sumOfAverageHits / n));
         StdOut.println("Standard deviation of Misses: " + Math.sqrt(sumOfAverageMisses / n));
     }
     public static void main(String[] args) {
         for (int i = 4; i <= 6; i++){
             averageAndStDev((int)Math.pow(10, i));
         }
     }

}

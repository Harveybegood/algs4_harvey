package Chapter3_1_SymbolTables;

import edu.princeton.cs.algs4.StdOut;

/*
*   Crossover to binary search. Find the values of N for which binary search in a symbol table of size N becomes 10, 100
*   and 1000 times faster than sequential search. Predict the values with analysis and verify them experimentally.
*
* */
public class Ex40_CrossoverToBinarySearch {
    /*
    *   sequential search: linear order
    *   binary search: logarithmic order
    *   N/2 = lgN * 10, N/2 = lgN * 100, N/2 = lgN * 1000
    *
    *   =>
    *
    * */
    public static void main(String[] args) {
     /*   int N = 100000000;
        for (int i = 0; i < N; i++){
            if (Math.log(i) * 10 == i/2){
                StdOut.println(i);
            }
            if (Math.log(i) * 100 == i/2){
                StdOut.println(i);
            }
            if (Math.log(i) * 1000 == i/2){
                StdOut.println(i);
            }
        }*/
     new Ex40_CrossoverToBinarySearch().doExperiment();
    }
    public void doExperiment(){
        binarySearchST<Integer, Integer> binarySearchST = new binarySearchST<>(10);
        SequentialSearchST<Integer, Integer> sequentialSearchST = new SequentialSearchST<>();
        for (int i = 0; i < 100000; i++){
            binarySearchST.put(i, i);
            sequentialSearchST.put(i, i);
            long timeCostSequentialSearch;
            long timeCostBinarySearch;
            //Stopwatch timer1 = new Stopwatch();
            long time1 = System.nanoTime();
            binarySearchST.get(1000001);
            timeCostBinarySearch = System.nanoTime() - time1;
            long timer2 = System.nanoTime();
            sequentialSearchST.get(1000001);
            timeCostSequentialSearch = System.nanoTime() - timer2;
            //if (timeCostBinarySearch == timeCostSequentialSearch) continue;
            if (timeCostBinarySearch == timeCostSequentialSearch * 10){
                StdOut.println("10 times: " + i);
            }
            if (timeCostBinarySearch == timeCostSequentialSearch * 100){
                StdOut.println("100 times: " + i);
            }
            if (timeCostBinarySearch == timeCostSequentialSearch * 1000){
                StdOut.println("1000 times: " + i);
            }
        }
    }
}

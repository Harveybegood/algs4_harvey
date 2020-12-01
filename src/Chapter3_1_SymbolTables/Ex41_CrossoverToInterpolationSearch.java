package Chapter3_1_SymbolTables;

import edu.princeton.cs.algs4.StdOut;

import java.util.Random;

/*
*   Crossover to interpolation search. Find the values of N for which interpolation search in a symbol table of size N
*   becomes 1, 2 and 10 times faster than binary search, assuming the keys to be random 32-bits integers(see ex3.1.24).
*   Predict the values with analysis, and verify them experimentally.
*
* */
public class Ex41_CrossoverToInterpolationSearch {
    /*
    *   interpolation search:
    *   binary search:
    *
    *
    * */

    public static void main(String[] args) {
       doExperiment();
    }
    // generate symbol table
    static Ex24_InterpolationSearch.InterpolationSearch<Integer> interpolationSearch = new Ex24_InterpolationSearch.InterpolationSearch<>(10);
    static Random random = new Random();
    public static Ex24_InterpolationSearch.InterpolationSearch getInterpolationSearch() {
        for (int i = 0; i < 10; i++){
            interpolationSearch.put(random.nextInt(), i);
        }
        return interpolationSearch;
    }
    static binarySearchST<Integer, Integer> binarySearchST = new binarySearchST<>(10);
    public static binarySearchST getBinarySearch(){
        for (int i = 0; i < 10; i++){
            binarySearchST.put(random.nextInt(), i);
        }
        return binarySearchST;
    }
    public static void doExperiment(){
        for (int i = 0; i < 10; i++){
            long timer1 = System.nanoTime();
            getInterpolationSearch().get(10000001);
            long timeCostInterpolation = System.nanoTime() - timer1;
            long timer2 = System.nanoTime();
            getBinarySearch().get(10000001);
            long timeCostBinary = System.nanoTime() - timer2;
            if (timeCostInterpolation == timeCostBinary * 2){
                StdOut.println("2 times: " + i);
            }
            if (timeCostInterpolation == timeCostBinary * 10){
                StdOut.println("10 times: " + i);
            }
        }
    }
}

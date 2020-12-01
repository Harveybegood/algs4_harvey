package Chapter3_2_BinarySearchTrees;


import AnalysisOfAlgorithmsTest.Stopwatch;
import Chapter3_1_SymbolTables.binarySearchST;
import edu.princeton.cs.algs4.StdOut;

/*
*   Crossover to binary search trees. Find the values of N for which using a binary search tree to build a symbol table of
*   N random double keys becomes 10, 100, and 1000 times faster than binary search. Predict the values with analysis and
*   verify them experimentally.
*
* */
public class Ex46_CrossoverToBST {
    /*
    *   Both of BST and binary search are taking logarithmic time cost to find the appropriate position for the inserted
    *   key, but binary search using the data structure of array for which it has to move some of data to its corresponding
    *   position.
    *
    *
    * */

    binarySearchST<String, Integer> binarySearchST = new binarySearchST<>();
    Ex10_TestBST<String, Integer> testBST = new Ex10_TestBST<>();
    public void doExperiment(){
        Stopwatch timer1 = new Stopwatch();
        Stopwatch timer2 = new Stopwatch();
        double timeOfBinarySearch;
        double timeOfTestBST;
        for (int i = 0; true; i++){
            binarySearchST.put("a" + i, i);
            testBST.put("a" + i, i);
            timeOfBinarySearch = timer1.elapsedTime();
            timeOfTestBST = timer2.elapsedTime();
            if (timeOfTestBST == 10 * timeOfBinarySearch){
                StdOut.println(i);
            }
            if (timeOfTestBST == 100 * timeOfBinarySearch){
                StdOut.println(i);
            }
            if (timeOfTestBST == 1000 * timeOfBinarySearch){
                StdOut.println(i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        Ex46_CrossoverToBST crossoverToBST = new Ex46_CrossoverToBST();
        crossoverToBST.doExperiment();
    }
}
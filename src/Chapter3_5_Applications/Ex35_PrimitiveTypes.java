package Chapter3_5_Applications;

import Chapter3_3_BalancedSearchTrees.RedBlackBST;
import Chapter3_4_HashTables.LinearProbingHashST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;


/*
*   Primitive types. Evaluate the utility of using primitive types for Integer and Double values, for LinearProbingHashST
*   and RedBlackBST. How much space and time are saved, for large numbers of searches in large tables?
*
* */
public class Ex35_PrimitiveTypes {
    public void prepareDataValue(){
        int sizeOfValue = 1000000;
        int[] inputIntKeyValue = new int[sizeOfValue];
        for (int i = 0; i < sizeOfValue; i++){
            inputIntKeyValue[i] = StdRandom.uniform(sizeOfValue);
        }
        double[] inputDoubleKeyValue = new double[sizeOfValue];
        for (int i = 0; i < sizeOfValue; i++){
            inputDoubleKeyValue[i] = StdRandom.uniform(sizeOfValue);
        }
        runTestForTime(inputIntKeyValue, inputDoubleKeyValue);
    }
    
    public void runTestForTime(int[] inputIntKeyValue, double[] inputDoubleKeyValue){
        StdOut.printf("%11s %10s %18s %8s\n", "SymbolTable", "DataType", "NumberOfSearches", "TimeCost");
        int[] numberOfPutAndQuery = {10000, 100000, 1000000};
        // LinearProbing
        for (int i = 0; i < numberOfPutAndQuery.length; i++){
            LinearProbingHashST<Integer, Integer> linearProbingHashSTInteger = new LinearProbingHashST<>();
            for (int j = 0; j < numberOfPutAndQuery[i]; j++){
                linearProbingHashSTInteger.put(inputIntKeyValue[j], inputIntKeyValue[j]);
            }
            Stopwatch timer = new Stopwatch();
            for (int j = 0; j < numberOfPutAndQuery[i]; j++){
                linearProbingHashSTInteger.get(inputIntKeyValue[j]);
            }
            double time = timer.elapsedTime();
            StdOut.printf("%11s %8s %16d %8.2f\n", "LinearProbing", "Integer", numberOfPutAndQuery[i], time);
        }
        StdOut.println();
        for (int i = 0; i < numberOfPutAndQuery.length; i++){
            Ex04_ClassesHashSTintHashSTdouble.HashSTint hashSTint = new Ex04_ClassesHashSTintHashSTdouble().new HashSTint();
            for (int j = 0; j < numberOfPutAndQuery[i]; j++){
                hashSTint.put(inputIntKeyValue[j], inputIntKeyValue[j]);
            }
            Stopwatch timer = new Stopwatch();
            for (int j = 0; j < numberOfPutAndQuery[i]; j++){
                hashSTint.get(inputIntKeyValue[j]);
            }
            double time = timer.elapsedTime();
            StdOut.printf("%11s %8s %16d %8.2f\n", "LinearProbing", "int", numberOfPutAndQuery[i], time);
        }
        StdOut.println();
        for (int i = 0; i < numberOfPutAndQuery.length; i++){
            LinearProbingHashST<Double, Double> linearProbingHashSTDouble = new LinearProbingHashST<>();
            for (int j = 0; j < numberOfPutAndQuery[i]; j++){
                linearProbingHashSTDouble.put(inputDoubleKeyValue[j], inputDoubleKeyValue[j]);
            }
            Stopwatch timer = new Stopwatch();
            for (int j = 0; j < numberOfPutAndQuery[i]; j++){
                linearProbingHashSTDouble.get(inputDoubleKeyValue[j]);
            }
            double time = timer.elapsedTime();
            StdOut.printf("%11s %8s %16d %8.2f\n", "LinearProbing", "Double", numberOfPutAndQuery[i], time);
        }
        StdOut.println();
        for (int i = 0; i < numberOfPutAndQuery.length; i++){
            Ex04_ClassesHashSTintHashSTdouble.HashSTdouble hashSTdouble = new Ex04_ClassesHashSTintHashSTdouble().new HashSTdouble();
            for (int j = 0; j < numberOfPutAndQuery[i]; j++){
                hashSTdouble.put(inputDoubleKeyValue[j], inputDoubleKeyValue[j]);
            }
            Stopwatch timer = new Stopwatch();
            for (int j = 0; j < numberOfPutAndQuery[i]; j++){
                hashSTdouble.get(inputDoubleKeyValue[j]);
            }
            double time = timer.elapsedTime();
            StdOut.printf("%11s %8s %16d %8.2f\n", "LinearProbing", "double", numberOfPutAndQuery[i], time);
        }
        StdOut.println();
        StdOut.println();
        // RedBlack BST
        for (int i = 0; i < numberOfPutAndQuery.length; i++){
            RedBlackBST<Integer, Integer> redBlackBST = new RedBlackBST<>();
            for (int j = 0; j < numberOfPutAndQuery[i]; j++){
                redBlackBST.put(inputIntKeyValue[j], inputIntKeyValue[j]);
            }
            Stopwatch timer = new Stopwatch();
            for (int j = 0; j < numberOfPutAndQuery[i]; j++){
                redBlackBST.get(inputIntKeyValue[j]);
            }
            double time = timer.elapsedTime();
            StdOut.printf("%11s %11s %16d %8.2f\n", "RedBlackBST", "Integer", numberOfPutAndQuery[i], time);
        }
        StdOut.println();
        for (int i = 0; i < numberOfPutAndQuery.length; i++){
            Ex05_RedBlackBSTWithPrimitiveTypes.STint sTint = new Ex05_RedBlackBSTWithPrimitiveTypes().new STint();
            for (int j = 0; j < numberOfPutAndQuery[i]; j++){
                sTint.put(inputIntKeyValue[j], inputIntKeyValue[j]);
            }
            Stopwatch timer = new Stopwatch();
            for (int j = 0; j < numberOfPutAndQuery[i]; j++){
                sTint.get(inputIntKeyValue[j]);
            }
            double time = timer.elapsedTime();
            StdOut.printf("%11s %11s %16d %8.2f\n", "RedBlackBST", "int", numberOfPutAndQuery[i], time);
        }
        StdOut.println();
        for (int i = 0; i < numberOfPutAndQuery.length; i++){
            RedBlackBST<Double, Double> redBlackBST = new RedBlackBST<>();
            for (int j = 0; j < numberOfPutAndQuery[i]; j++){
                redBlackBST.put(inputDoubleKeyValue[j], inputDoubleKeyValue[j]);
            }
            Stopwatch timer = new Stopwatch();
            for (int j = 0; j < numberOfPutAndQuery[i]; j++){
                redBlackBST.get(inputDoubleKeyValue[j]);
            }
            double time = timer.elapsedTime();
            StdOut.printf("%11s %11s %16d %8.2f\n", "RedBlackBST", "Double", numberOfPutAndQuery[i], time);
        }
        StdOut.println();
        for (int i = 0; i < numberOfPutAndQuery.length; i++){
            Ex05_RedBlackBSTWithPrimitiveTypes.STdouble sTdouble = new Ex05_RedBlackBSTWithPrimitiveTypes().new STdouble();
            for (int j = 0; j < numberOfPutAndQuery[i]; j++){
                sTdouble.put(inputDoubleKeyValue[j], inputDoubleKeyValue[j]);
            }
            Stopwatch timer = new Stopwatch();
            for (int j = 0; j < numberOfPutAndQuery[i]; j++){
                sTdouble.get(inputDoubleKeyValue[j]);
            }
            double time = timer.elapsedTime();
            StdOut.printf("%11s %11s %16d %8.2f\n", "RedBlackBST", "double", numberOfPutAndQuery[i], time);
        }

    }

    public static void main(String[] args) {
        Ex35_PrimitiveTypes primitiveTypes = new Ex35_PrimitiveTypes();
        primitiveTypes.prepareDataValue();
    }

    /*
    *       SymbolTable   DataType   NumberOfSearches TimeCost
            LinearProbing  Integer            10000     0.02
            LinearProbing  Integer           100000     0.08
            LinearProbing  Integer          1000000     0.77

            LinearProbing      int            10000     0.01
            LinearProbing      int           100000     0.05
            LinearProbing      int          1000000     0.10

            LinearProbing   Double            10000     0.00
            LinearProbing   Double           100000     0.01
            LinearProbing   Double          1000000     0.21

            LinearProbing   double            10000     0.01
            LinearProbing   double           100000     0.01
            LinearProbing   double          1000000     0.11


            RedBlackBST     Integer            10000     0.02
            RedBlackBST     Integer           100000     0.04
            RedBlackBST     Integer          1000000     1.06

            RedBlackBST         int            10000     0.01
            RedBlackBST         int           100000     0.03
            RedBlackBST         int          1000000     0.66

            RedBlackBST      Double            10000     0.01
            RedBlackBST      Double           100000     0.05
            RedBlackBST      Double          1000000     2.21

            RedBlackBST      double            10000     0.02
            RedBlackBST      double           100000     0.07
            RedBlackBST      double          1000000     1.46

            saving ...

    *
    * */



    /*
    *        abstract data type - space cost
    *

             Integer: object overhead: 16 bytes
                      int            : 4 bytes
                      padding        : 4 bytes

             Double:  object overhead: 16 bytes
                      double         : 8 bytes

    *
    *        primitive type - space cost
    *
             int:       4 bytes

             double:    8 bytes


             saving: the space saving for int comparing Integer is 20 bytes, and a pair of key-value is 40 bytes
                     double / Double is 16 bytes, a pair of key-value is 32 bytes
    *
    * */




}

package Chapter3_4_HashTables;

import Chapter3_1_SymbolTables.SequentialSearchST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Chi-square statistic. Add a method to SeparateChainingST to compute the X^2 statistic for the hash table. With N keys
*   and table size M, this number is defined by the equation.
*       X^2 = (M/N)((f0 - N/M)^2 + (f1 - N/M)^2 + ... + (fm-1 - N/M)^2)
*   where fi is the number of keys with hash value i. This statistic is one way of checking our assumption that the hash
*   function produces random values. If so, this statistic, for N > cM, should be between M - sqrtM and M + sqrtM with
*   probability 1 - 1/c.
*
* */
public class Ex30_ChiSquareStatistic<Key, Value> extends Chapter3_4_HashTables.SeparateChainingHashST<Key, Value> {
   /* private int M;
    private int N;*/
    //int M = new SeparateChainingHashST<>().M
    public Ex30_ChiSquareStatistic(int initialSymbolTable, int averageList){
        super(initialSymbolTable, averageList);
    }
    private int[] numberKeys = new int[M];
    private double chiSquareStatistic(){
        double X;
        double t = 0.0;
        for (int i = 0; i < M; i++){
            SequentialSearchST.Node node = st[i].first;
            while (node != null){
                numberKeys[i]++;
                SequentialSearchST.Node temp = node;
                node = temp.next;
            }
            t += Math.pow(numberKeys[i] - (double)n/(double) M, 2);

        }
        double Y = (double) M/(double) n;
        X = (Y * t);
        return X * X;
    }

    public static void main(String[] args) {
        Ex30_ChiSquareStatistic<Integer, Integer> chiSquareStatistic = new Ex30_ChiSquareStatistic<>(10, 5);
       /* chiSquareStatistic.put("E", 0);
        chiSquareStatistic.put("X", 1);
        chiSquareStatistic.put("A", 2);
        chiSquareStatistic.put("M", 3);
        chiSquareStatistic.put("P", 4);
        chiSquareStatistic.put("L", 5);
        chiSquareStatistic.put("Q", 6);
        chiSquareStatistic.put("U", 7);
        chiSquareStatistic.put("I", 8);
        chiSquareStatistic.put("O", 9);
        chiSquareStatistic.put("N", 10);
        chiSquareStatistic.put("Y", 11);*/
        for (int i = 0; i < 50; i++){
            chiSquareStatistic.put(StdRandom.uniform(1,200), i);
        }
        double c = (double) chiSquareStatistic.n / chiSquareStatistic.M;
        double probability = 1 - (double)1/c;
        double lowerBound = (chiSquareStatistic.M - Math.sqrt(chiSquareStatistic.M));
        double upperBound = (chiSquareStatistic.M + Math.sqrt(chiSquareStatistic.M));
        StdOut.println(lowerBound);
        StdOut.println(upperBound);
        StdOut.println(probability);
        StdOut.println(chiSquareStatistic.chiSquareStatistic());
    }
}

package Chapter3_4_HashTables;
import Tools.VisualAccumulator;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

/*
*   Separate-chaining distribution. Write a program that inserts 10^5 random non-negative integers less than 10^6 into a
*   table of size 10^5 using linear probing, and that plots the total number of probes used for each of 10^3 consecutive
*   insertions. Discuss the extend to which your results validate Proposition K.
*
*   #I am confused with the question that Separate-chaining distribution and linear probing. I assume using Separate-chaining
*   instead of using linear probing and the total of compares stead of the total of probes
*
*   In a separate-chaining hash table with M lists and N keys, the probability (under Assumption J) that the number of keys
*   in a list is within a small constant factor of N/M is extremely close to 1.
*
* */
@SuppressWarnings("unchecked")
public class Ex38_SeparateChainingDistribution<Key, Value>{
    private class SequentialSearchST<Key, Value>{
        private class Node{
            Key key;
            Value value;
            Node next;
            public Node(Key key, Value value, Node next){
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }
        private Node first;
        private int n;
        public int size(){return n;}
        public boolean isEmpty(){return n == 0;}
        public boolean isContains(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            return get(key) != null;
        }
        public Value get(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            for (Node node = first; node != null; node = node.next){
                if (node.key.equals(key)){
                    return node.value;
                }
            }
            return null;
        }
        public void put(Key key, Value value){
            countOfCompares = 0;
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            for (Node node = first; node != null; node = node.next){
                countOfCompares++;
                if (node.key.equals(key)){
                    node.value = value;
                    return;
                }
            }
            first = new Node(key, value, first);
            n++;
        }
        public void delete(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            if (isEmpty()){throw new RuntimeException("Symbol table underflow");}
            for (Node node = first; node != null; node = node.next){
                if (node.key.equals(key)){
                    if (node.next != null){
                        node.key = null;
                        node.value = null;
                        node = node.next;
                    }
                    else {
                        node.key = null;
                        node.value = null;
                    }
                    n--;
                    return;
                }
            }
        }
        public Iterable<Key> keys(){
            Queue<Key> queue = new Queue<>();
            for (Node node = first; node != null; node = node.next){
                queue.enqueue(node.key);
            }
            return queue;
        }
    }
    private int N;
    private int M;
    private int countOfCompares;
    private SequentialSearchST<Key, Value>[] st;
    public Ex38_SeparateChainingDistribution(){}
    public Ex38_SeparateChainingDistribution(int M){
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++){
            st[i] = new SequentialSearchST<>();
        }
    }
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public boolean isContains(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        return get(key) != null;
    }
    public int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (value == null){throw new IllegalArgumentException("Argument cannot be null");}
        int i = hash(key);
        if (!isContains(key)){
            N++;
        }
        st[i].put(key, value);
    }
    public void delete(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (isEmpty()){throw new RuntimeException("Symbol table is underflow");}
        st[hash(key)].delete(key);
        N--;
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < M; i++){
            if (st[i] != null){
                for (Key key : st[i].keys()){
                    queue.enqueue(key);
                }
            }
        }
        return queue;
    }
    public void plotComparesForInsertions(){
        Ex38_SeparateChainingDistribution<Integer, Integer> separateChainingDistribution = new Ex38_SeparateChainingDistribution<>(100000);
        int maxIntValue = 1000000;
        StdOut.printf("%23s %23s %25s\n", "Total of compares for 10^3 inserts |", "Average compares for each insert |", "Expected number of keys in list");
        String title = "Separate-Chaining hash table compares for inserts";
        String xAxisLabel = "inserts";
        String yAxisLabel = "compares";
        double maxNumberOfInserts = 100000;
        double maxCompares = 1500;
        int initialCompare = 0;
        VisualAccumulator visualAccumulator = new VisualAccumulator(initialCompare, maxNumberOfInserts, maxCompares, title, xAxisLabel, yAxisLabel);
        double totalOfCountOfCompares = 0;
        for (int insert = 1; insert <= 100000; insert++){
            int randomKey  = StdRandom.uniform(maxIntValue);
            separateChainingDistribution.put(randomKey, randomKey);
            totalOfCountOfCompares += separateChainingDistribution.countOfCompares;
            if (insert % 1000 == 0){
                double averageCompareOfInsert = totalOfCountOfCompares / 1000;
                double expectedNumberOfKeysInList = (double) separateChainingDistribution.N / separateChainingDistribution.M;
                StdOut.printf("%28s %31.3f %34.3f\n", totalOfCountOfCompares, averageCompareOfInsert, expectedNumberOfKeysInList);
                visualAccumulator.drawDataValue(insert, totalOfCountOfCompares, Color.BLACK);
                totalOfCountOfCompares = 0;
            }
        }
        //StdOut.println(separateChainingDistribution.N);
    }

    public static void main(String[] args) {
        Ex38_SeparateChainingDistribution separateChainingDistribution1 = new Ex38_SeparateChainingDistribution();
        separateChainingDistribution1.plotComparesForInsertions();
    }
}
/*
*       Total of compares for 10^3 inserts | Average compares for each insert | Expected number of keys in list
                         2.0                           0.002                              0.010
                        11.0                           0.011                              0.020
                        25.0                           0.025                              0.030
                        41.0                           0.041                              0.040
                        43.0                           0.043                              0.050
                        60.0                           0.060                              0.060
                        73.0                           0.073                              0.070
                        69.0                           0.069                              0.080
                        95.0                           0.095                              0.090
                       102.0                           0.102                              0.100
                        97.0                           0.097                              0.109
                       122.0                           0.122                              0.119
                       131.0                           0.131                              0.129
                       114.0                           0.114                              0.139
                       142.0                           0.142                              0.149
                       158.0                           0.158                              0.159
                       147.0                           0.147                              0.169
                       166.0                           0.166                              0.179
                       191.0                           0.191                              0.188
                       197.0                           0.197                              0.198
                       189.0                           0.189                              0.208
                       208.0                           0.208                              0.218
                       217.0                           0.217                              0.228
                       255.0                           0.255                              0.237
                       235.0                           0.235                              0.247
                       279.0                           0.279                              0.257
                       247.0                           0.247                              0.267
                       277.0                           0.277                              0.276
                       289.0                           0.289                              0.286
                       311.0                           0.311                              0.296
                       303.0                           0.303                              0.306
                       303.0                           0.303                              0.315
                       299.0                           0.299                              0.325
                       332.0                           0.332                              0.334
                       299.0                           0.299                              0.344
                       312.0                           0.312                              0.354
                       334.0                           0.334                              0.363
                       362.0                           0.362                              0.373
                       340.0                           0.340                              0.383
                       413.0                           0.413                              0.392
                       354.0                           0.354                              0.402
                       391.0                           0.391                              0.412
                       405.0                           0.405                              0.421
                       410.0                           0.410                              0.431
                       426.0                           0.426                              0.440
                       406.0                           0.406                              0.450
                       495.0                           0.495                              0.459
                       447.0                           0.447                              0.469
                       454.0                           0.454                              0.478
                       476.0                           0.476                              0.488
                       467.0                           0.467                              0.497
                       466.0                           0.466                              0.507
                       515.0                           0.515                              0.516
                       496.0                           0.496                              0.526
                       536.0                           0.536                              0.535
                       534.0                           0.534                              0.545
                       544.0                           0.544                              0.554
                       543.0                           0.543                              0.564
                       523.0                           0.523                              0.573
                       539.0                           0.539                              0.582
                       576.0                           0.576                              0.592
                       554.0                           0.554                              0.601
                       539.0                           0.539                              0.611
                       579.0                           0.579                              0.620
                       581.0                           0.581                              0.630
                       583.0                           0.583                              0.639
                       610.0                           0.610                              0.648
                       561.0                           0.561                              0.658
                       691.0                           0.691                              0.667
                       674.0                           0.674                              0.676
                       682.0                           0.682                              0.685
                       624.0                           0.624                              0.695
                       688.0                           0.688                              0.704
                       710.0                           0.710                              0.713
                       713.0                           0.713                              0.722
                       742.0                           0.742                              0.732
                       706.0                           0.706                              0.741
                       696.0                           0.696                              0.750
                       789.0                           0.789                              0.760
                       760.0                           0.760                              0.769
                       705.0                           0.705                              0.778
                       751.0                           0.751                              0.787
                       744.0                           0.744                              0.796
                       766.0                           0.766                              0.806
                       773.0                           0.773                              0.815
                       833.0                           0.833                              0.824
                       784.0                           0.784                              0.833
                       821.0                           0.821                              0.843
                       806.0                           0.806                              0.852
                       803.0                           0.803                              0.861
                       791.0                           0.791                              0.870
                       799.0                           0.799                              0.879
                       870.0                           0.870                              0.888
                       877.0                           0.877                              0.897
                       821.0                           0.821                              0.906
                       820.0                           0.820                              0.915
                       867.0                           0.867                              0.925
                       890.0                           0.890                              0.934
                       875.0                           0.875                              0.943
                       947.0                           0.947                              0.952

Process finished with exit code 0

*
* */
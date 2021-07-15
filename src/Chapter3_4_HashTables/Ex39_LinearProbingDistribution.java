package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import edu.princeton.cs.algs4.Queue;

/*
*   Linear-probing distribution. Write a program that inserts N/2 random int keys into a table of size N using linear probing,
*   then computes the average cost of a search miss in the resulting table from the cluster lengths, for N = 10^3, 10^4, 10^5
*   10^6. Discuss the extent to which your results validate Proposition M.
*
* */
@SuppressWarnings("unchecked")
public class Ex39_LinearProbingDistribution<Key, Value> {
    private int M;
    private int n;
    private Key[] keys;
    private Value[] values;
    private int countOfSearchMiss;
  /*  public Ex39_LinearProbingDistribution(){
        this(M);
    }*/
    public Ex39_LinearProbingDistribution(int M){
        this.M = M;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];

    }
    public boolean isEmpty(){return n == 0;}
    public boolean isContains(Key key){return get(key) != null;}
    public int size(){return n;}
    public Value get(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M){
            if (keys[i].equals(key)){
                return values[i];
            }
            //countOfSearchMiss++;
        }
        return null;
    }
    public int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }
    public void put(Key key, Value value){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (value == null){throw new IllegalArgumentException("Argument cannot be null");}
        int i = hash(key);
        while (keys[i] != null){
            if (keys[i].equals(key)){
                values[i] = value;
                return;
            }
            i = (i + 1) % M;
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }
    public void delete(Key key){
        if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
        if (!isContains(key)){return;}
        int i = hash(key);
        while (keys[i] != null){
            if (keys[i].equals(key)){
                return;
            }
            i = (i + 1) % M;
        }
        keys[i] = null;
        values[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null){
            Key keyToRedo = keys[i];
            Value valueToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            n--;
            put(keyToRedo, valueToRedo);
            i = (i + 1) % M;
        }
        n--;
    }
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < M; i++){
            if (keys[i] != null){
                queue.enqueue(keys[i]);
            }
        }
        return queue;
    }
    public double getAverageCostOfASearchMissFromClusterLengths(){
        int countOfSearchMiss = 0;
        for (Key key : keys()){
            for (int i = hash(key); keys[i] != null; i = (i + 1) % M){
                if (key.equals(keys[i])){
                    countOfSearchMiss++;
                    //break;
                }
                countOfSearchMiss++;
            }
        }
        return (double) countOfSearchMiss / M;
    }
    public void doExperiments(int N){
        Ex39_LinearProbingDistribution<Integer, Integer> linearProbingDistribution = new Ex39_LinearProbingDistribution<>(N);
        for (int i = 0; i < N/2; i++){
            int randomValue = StdRandom.uniform(Integer.MAX_VALUE);
            linearProbingDistribution.put(randomValue, randomValue);
        }
        double a = 0.5;
        double expectedCost = 0.5 * (1 + 1 / (Math.pow((1 - a), 2)));
        StdOut.printf("%10d %12.1f %13.1f\n", linearProbingDistribution.M, linearProbingDistribution.getAverageCostOfASearchMissFromClusterLengths(), expectedCost);
    }

    public static void main(String[] args) {
        int[] tableSize = {1000, 10000, 100000, 1000000};
        StdOut.printf("%10s %12s %13s\n", "tablesize", "averagecost", "expectedcost");
        for (int index = 0; index < tableSize.length; index++){
            new Ex39_LinearProbingDistribution<>(tableSize[index]).doExperiments(tableSize[index]);
        }
    }
}
/*
*           tablesize  averagecost  expectedcost
                1000          2.5           2.5
               10000          2.2           2.5
              100000          2.2           2.5
             1000000          2.3           2.5

*
* */
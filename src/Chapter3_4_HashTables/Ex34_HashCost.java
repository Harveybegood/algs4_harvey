package Chapter3_4_HashTables;

import edu.princeton.cs.algs4.*;

/*
*   Hash cost. Determine empirically the ratio of the time required for hash() to the time required for compareTo(),
*   for as many commonly-used types of keys for which you can get meaningful results.
*
* */
@SuppressWarnings("unchecked")
public class Ex34_HashCost {
    // 3 different type of keys
    public enum InputType{
        Integer, String, Double
    }

    // the ratio of time required in SeparateChainingHashTable for between hash() and compareTo()
    private class SeparateChainingHashTableCostRatio<Key, Value>{

        double[] timerCal1 = new double[2];

        private class SequentialSearchTable<Key, Value>{
            private class Node{
                Node next;
                int numberOfNodes;
                Key key;
                Value value;
                public Node(Key key, Value value, Node next, int numberOfNodes){
                    this.key = key;
                    this.value = value;
                    this.next = next;
                    this.numberOfNodes = numberOfNodes;
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
                for (Node x = first; x != null; x = x.next){
                    Stopwatch timer = new Stopwatch();
                    if (x.key.equals(key)){
                        timerCal1[0] += timer.elapsedTime();
                        return x.value;
                    }
                    timerCal1[0] += timer.elapsedTime();
                }
                return null;
            }
            public void put(Key key, Value value){
                if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
                if (value == null){throw new IllegalArgumentException("Argument cannot be null");}
                for (Node x = first; x != null; x = x.next){
                    Stopwatch timer = new Stopwatch();
                    if (x.key.equals(key)){
                        x.value = value;
                        timerCal1[0] += timer.elapsedTime();
                        return;
                    }
                    timerCal1[0] += timer.elapsedTime();
                }
                first = new Node(key, value, first, 0);
                first.numberOfNodes = first.numberOfNodes + 1;
                n++;
            }
            public void delete(Key key){
                if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
                if (isEmpty()){return;}
                for (Node x = first; x != null; x = x.next){
                    Stopwatch timer = new Stopwatch();
                    if (x.key.equals(key)){
                        if (x.next != null){
                            x.key = x.next.key;
                            x.value = x.next.value;
                            x = x.next;
                        }
                        else {
                            x.key = null;
                            x.value = null;
                        }
                        n--;
                    }
                    timerCal1[0] += timer.elapsedTime();
                }
            }
            public Iterable<Key> keys(){
                Queue<Key> queue = new Queue<>();
                for (Node x = first; x != null; x = x.next){
                    queue.enqueue(x.key);
                }
                return queue;
            }
        }
        private int M;
        private int N;
        private int lgN;  // the length of each link list
        //private final static int DEFAULT_NUMBER_OF_BUCKETS = 10;
        private final static int DEFAULT_AVERAGE_LIST_SIZE = 5;
        SequentialSearchTable<Key, Value>[] st;
        private int[] primes = {1, 1, 3, 7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381, 32749, 65521, 131071, 262139, 524287,
                1048573, 2097143, 4194301, 8388593, 16777213, 33554393, 67108859, 134217689, 268435399, 536870909, 1073741789, 2147483647};
        public SeparateChainingHashTableCostRatio(){
            this(10);
        }
        public SeparateChainingHashTableCostRatio(int DEFAULT_NUMBER_OF_BUCKETS){
            this.M = DEFAULT_NUMBER_OF_BUCKETS;
            this.N = 0;
            //timerCal1 = new Double[2];
            st = (SequentialSearchTable<Key, Value>[]) new SequentialSearchTable[M];
            for (int i = 0; i < M; i++){
                st[i] = new SequentialSearchTable<>();
            }
            lgN = (int) (Math.log(M) / Math.log(2));
        }
        private int hash(Key key){
            int t = key.hashCode() & 0x7fffffff;
            if (lgN < 26){
                t = t % primes[lgN + 5];
            }
            return t % M;
        }
        public boolean isEmpty(){return N == 0;}
        public int size(){return N;}
        public boolean isContains(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            return get(key) != null;
        }
        public Value get(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            Stopwatch timer = new Stopwatch();
            int i = hash(key);
            timerCal1[1] += timer.elapsedTime();
            return st[i].get(key);
        }
        public void put(Key key, Value value){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            if (value == null){throw new IllegalArgumentException("Argument cannot be null");}
            if (N / M >= DEFAULT_AVERAGE_LIST_SIZE){
                resize(2 * M);
                lgN++;
            }
            Stopwatch timer = new Stopwatch();
            int i = hash(key);
            timerCal1[1] += timer.elapsedTime();
            if (!st[i].isContains(key)){
                N++;
            }
            st[i].put(key, value);
        }
        public void resize(int newSize){
            SeparateChainingHashTableCostRatio<Key, Value> temp = new SeparateChainingHashTableCostRatio<>(newSize);
            for (int i = 0; i < M; i++){
                for (Key key : st[i].keys()){
                    if (key != null){
                        temp.put(key, st[i].get(key));
                    }
                }
            }
            this.M = temp.M;
            this.N = temp.N;
            this.st = temp.st;
        }
        public void delete(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            if (!isContains(key)){return;}
            Stopwatch timer = new Stopwatch();
            int i = hash(key);
            timerCal1[1] += timer.elapsedTime();
            st[i].delete(key);
            if (N / M < DEFAULT_AVERAGE_LIST_SIZE){
                resize(M / 2);
                lgN--;
            }
            N--;
        }
        public Iterable<Key> keys(){
            Queue<Key> queue = new Queue<>();
            for (int i = 0; i < M; i++){
                for (Key key : st[i].keys()){
                    if (isContains(key)){
                        queue.enqueue(key);
                    }
                }
            }
            return queue;
        }
    }

    // the ratio of time required in LinearProbingHashTable for between hash() and compareTo()
    private class LinearProbingHashTableCostRatio<Key, Value>{
        double[] timeCal = new double[2];
        private Key[] keys;
        private Value[] values;
        private int M;
        private int N;
        public LinearProbingHashTableCostRatio(int M){
            //timeCal = new Double[2];
            this.M = M;
            keys = (Key[]) new Object[M];
            values = (Value[]) new Object[M];
        }
        public boolean isEmpty(){return N == 0;}
        public int size(){return N;}
        public int hash(Key key){
            return (key.hashCode() & 0x7fffffff) % M;
        }
        public boolean isContains(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            return get(key) != null;
        }
        public Value get(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            Stopwatch timer = new Stopwatch();
            int i = hash(key);
            timeCal[1] += timer.elapsedTime();
            for ( ; keys[i] != null; i = (i + 1) % M){
                Stopwatch timer1 = new Stopwatch();
                if (keys[i].equals(key)){
                    timeCal[0] += timer1.elapsedTime();
                    return values[i];
                }
                timeCal[0] += timer1.elapsedTime();
            }
            return null;
        }
        public void put(Key key, Value value){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            if (value == null){throw new IllegalArgumentException("Argument cannot be null");}
            if (N >= M / 2){resize(M * 2);}
            Stopwatch timer = new Stopwatch();
            int i = hash(key);
            timeCal[1] += timer.elapsedTime();
            for ( ; keys[i] != null; i = (i + 1) % M){
                Stopwatch timer1 = new Stopwatch();
                if (keys[i].equals(key)){
                    timeCal[0] += timer1.elapsedTime();
                    values[i] = value;
                    return;
                }
                timeCal[0] += timer1.elapsedTime();
            }
            keys[i] = key;
            values[i] = value;
            N++;
        }
        public void resize(int newSize){
            LinearProbingHashTableCostRatio<Key, Value> temp = new LinearProbingHashTableCostRatio<>(newSize);
            for (int i = 0; i < M; i++){
                if (keys[i] != null){
                    temp.put(keys[i], values[i]);
                }
            }
            this.M = temp.M;
            this.keys = temp.keys;
            this.values = temp.values;
        }
        public void delete(Key key){
            if (key == null){throw new IllegalArgumentException("Argument cannot be null");}
            if (!isContains(key)){return;}
            Stopwatch timer = new Stopwatch();
            int i = hash(key);
            timeCal[1] += timer.elapsedTime();
            while (keys[i] != null){
                Stopwatch timer1 = new Stopwatch();
                if (!keys[i].equals(key)){
                    i = (i + 1) % M;
                }
                else {
                    timeCal[0] += timer1.elapsedTime();
                    break;
                }
                timeCal[0] += timer1.elapsedTime();
            }
            keys[i] = null;
            values[i] = null;
            i = (i + 1) % M;
            while (keys[i] != null){
                Key tempKey = keys[i];
                Value tempValue = values[i];
                put(tempKey, tempValue);
                i = (i + 1) % M;
            }
            N--;
            if (N <= M / 4){
                resize(M / 2);
            }
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
    }

    public static void main(String[] args) {
        Ex34_HashCost hashCost = new Ex34_HashCost();
        SeparateChainingHashTableCostRatio<Integer, Integer> separateChainingHashTableCostRatioInteger = hashCost.new SeparateChainingHashTableCostRatio<>();
        for (int i = 0; i < 20; i++){
            separateChainingHashTableCostRatioInteger.put(StdRandom.uniform(0, 100), StdRandom.uniform(1, 100));
        }
        for (int i = 0; i < 10; i++){
            separateChainingHashTableCostRatioInteger.get(i);
        }
        for (int i = 1; i < 10; i++){
            separateChainingHashTableCostRatioInteger.delete(i);
        }
        double timeCompareToCostForTypeInteger = separateChainingHashTableCostRatioInteger.timerCal1[0];
        double timeHashCostForTypeInteger = separateChainingHashTableCostRatioInteger.timerCal1[1];
        StdOut.print("The ratio of the time of Hash / CompareTo - ");
        StdOut.println(timeHashCostForTypeInteger / timeCompareToCostForTypeInteger);
        SeparateChainingHashTableCostRatio<Double, Integer> separateChainingHashTableCostRatioDouble = hashCost.new SeparateChainingHashTableCostRatio<>();
        for (int i = 0; i < 20; i++){
            separateChainingHashTableCostRatioDouble.put(StdRandom.uniform(0.0, 100.00), StdRandom.uniform(1, 100));
        }
        for (int i = 0; i < 20; i++){
            separateChainingHashTableCostRatioDouble.get((double)i);
        }
        for (int i = 0; i < 20; i++){
            separateChainingHashTableCostRatioDouble.delete((double)i);
        }
        double timeCompareToCostForTypeDouble = separateChainingHashTableCostRatioDouble.timerCal1[0];
        double timeHashCostForTypeDouble = separateChainingHashTableCostRatioDouble.timerCal1[1];
        StdOut.print("The ratio of the time of Hash / CompareTo - ");
        StdOut.println(timeHashCostForTypeDouble / timeCompareToCostForTypeDouble);
        SeparateChainingHashTableCostRatio<String, Integer> separateChainingHashTableCostRatioString = hashCost.new SeparateChainingHashTableCostRatio<>();
        for (int i = 0; i < 100; i++){
            separateChainingHashTableCostRatioString.put(StdRandom.uniform(0, 100) + "", StdRandom.uniform(1, 100));
        }
        for (int i = 0; i < 20; i++){
            separateChainingHashTableCostRatioString.get(i + "");
        }
        for (int i = 0; i < 20; i++){
            separateChainingHashTableCostRatioString.delete(i + "");
        }
        double timeCompareToCostForTypeString = separateChainingHashTableCostRatioString.timerCal1[0];
        double timeHashCostForTypeString = separateChainingHashTableCostRatioString.timerCal1[1];
        StdOut.print("The ratio of the time of Hash / CompareTo - ");
        StdOut.println(timeHashCostForTypeString / timeCompareToCostForTypeString);
        LinearProbingHashTableCostRatio<Integer, Integer> linearProbingHashTableCostRatioInteger = hashCost.new LinearProbingHashTableCostRatio<>(4);
        for (int i = 0; i < 100; i++){
            linearProbingHashTableCostRatioInteger.put(StdRandom.uniform(0, 100), StdRandom.uniform(1, 100));
        }
        for (int i = 0; i < 20; i++){
            linearProbingHashTableCostRatioInteger.get(i);
        }
        for (int i = 0; i < 20; i++){
            linearProbingHashTableCostRatioInteger.delete(i);
        }
        double timeCompareToCostIntegerLinearProbing = linearProbingHashTableCostRatioInteger.timeCal[0];
        double timeHashCostIntegerLinearProbing = linearProbingHashTableCostRatioInteger.timeCal[1];
        StdOut.print("The ratio of the time of Hash / CompareTo - ");
        StdOut.println(timeHashCostIntegerLinearProbing / timeCompareToCostIntegerLinearProbing);
        LinearProbingHashTableCostRatio<Double, Integer> linearProbingHashTableCostRatioDouble = hashCost.new LinearProbingHashTableCostRatio<>(4);
        for (int i = 0; i < 100; i++){
            linearProbingHashTableCostRatioDouble.put( StdRandom.uniform(0.0, 100.00), StdRandom.uniform(1, 100));
        }
        for (int i = 0; i < 20; i++){
            for (Double x : linearProbingHashTableCostRatioDouble.keys()){
                linearProbingHashTableCostRatioDouble.get(x);
            }
        }
        for (int i = 0; i < 20; i++){
            for (Double x : linearProbingHashTableCostRatioDouble.keys()){
                linearProbingHashTableCostRatioDouble.delete(x);
            }
        }
        double timeCompareToCostDoubleLinearProbing = linearProbingHashTableCostRatioDouble.timeCal[0];
        double timeHashCostDoubleLinearProbing = linearProbingHashTableCostRatioDouble.timeCal[1];
        StdOut.print("The ratio of the time of Hash / CompareTo - ");
        StdOut.println(timeHashCostDoubleLinearProbing / timeCompareToCostDoubleLinearProbing);
        LinearProbingHashTableCostRatio<String, Integer> linearProbingHashTableCostRatioString = hashCost.new LinearProbingHashTableCostRatio<>(4);
        for (int i = 0; i < 100; i++){
            linearProbingHashTableCostRatioString.put(StdRandom.uniform(0, 100) + "", StdRandom.uniform(1, 100));
        }
        for (int i = 0; i < 20; i++){
            linearProbingHashTableCostRatioString.get(i + "");
        }
        for (int i = 0; i < 20; i++){
            linearProbingHashTableCostRatioString.delete(i + "");
        }
        double timeCompareToCostStringLinearProbing = linearProbingHashTableCostRatioString.timeCal[0];
        double timeHashCostStringLinearProbing = linearProbingHashTableCostRatioString.timeCal[1];
        StdOut.print("The ratio of the time of Hash / CompareTo - ");
        StdOut.println(timeHashCostStringLinearProbing / timeCompareToCostStringLinearProbing);
    }
}

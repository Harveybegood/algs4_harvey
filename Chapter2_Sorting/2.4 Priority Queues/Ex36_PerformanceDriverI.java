package Chapter2_4_PriorityQueues;

import AnalysisOfAlgorithmsTest.Stopwatch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
*   Performance driver I. Write a performance driver client program that uses insert to fill a priority queue, then uses
*   remove the maximum to remove half the keys, then uses insert to fill it up again, then uses remove the maximum to
*   remove all the keys, doing so multiple times on random sequences of keys of various lengths ranging from small to
*   large; measures the time taken for each run; and prints out or plots the average running times.
*
* */
@SuppressWarnings("unchecked")
public class Ex36_PerformanceDriverI<Key extends Comparable<Key>> {
    private int N;
    private Key[] pq;
    public Ex36_PerformanceDriverI(int maxN){
        pq = (Key[]) new Comparable[maxN + 1];
    }
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public void resize(int newSize){
        Key[] array = (Key[]) new Comparable[newSize];
        for (int i = 1; i <= N; i++){
            array[i] = pq[i];
        }
        pq = array;
    }
    public void exchange(int i, int j){
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
    // insert()
    private void insert(Key key){
        if (N == pq.length / 2){resize(pq.length * 2);}
        pq[++N] = key;
        swim(N);
    }
    private Key delMax(){
        Key max = pq[1];
        exchange(1, N);
        N--;
        pq[N+1] = null;
        sink(1);
        return max;
    }
    private void swim(int k){
        while (k > 1){
            if (pq[k/2].compareTo(pq[k]) < 0){
                exchange(k, k/2);
            }
            k = k / 2;
        }
    }
    public double timeOfCost(int length){
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < length; i++){
            insert((Key) Double.valueOf(StdRandom.uniform()));
        }
        for (int i = length / 2; i < length; i++){
            delMax();
        }
        for (int i = length / 2; i < length; i++){
            insert((Key) Double.valueOf(StdRandom.uniform()));
        }
        for (int i = 0; i < length; i++){
            delMax();
        }
        return timer.elapsedTime();
    }
    private void sink(int k){
        while (k * 2 <= N){
            int j = k * 2;
            // compare times stay in between logN -- N-1
            if (j < N && pq[j+1].compareTo(pq[j]) < 0){
                j++;
            }
            // compare times stay in between 1 - logN
            if (!(pq[j].compareTo(pq[k]) < 0)){
                break;
            }
            exchange(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        Ex36_PerformanceDriverI performanceDriverI = new Ex36_PerformanceDriverI(1000);
        StdOut.println("Length      Time");
        for (int i = 1000000; i < 10000000; i += 1000000){
            StdOut.printf("%3d %10.1f\n", i, performanceDriverI.timeOfCost(i));
        }
        StdOut.printf("%3d %10.1f", 10000000, performanceDriverI.timeOfCost(10000000));
    }
}

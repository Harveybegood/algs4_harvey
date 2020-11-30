package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;

/*
*   Min/Max priority queue. Design a data type that supports the following operations: insert, delete the maximum, and delete
*   the minimum(all in logarithmic time); and find the maximum and find the minimum(both in constant time).
*   Hint: Use two heaps.
*
* */
@SuppressWarnings("unchecked")
public class Ex_2_4_29_MinMaxPQ<Key extends Comparable<Key>> {
    private int n;
    private Key[] pqMin;
    private Key[] pqMax;
    public enum MaxMinPQ{
        min, max
    }
    public Ex_2_4_29_MinMaxPQ(int MaxN, MaxMinPQ maxMinPQ){
        if (maxMinPQ == MaxMinPQ.min){
            pqMin = (Key[]) new Comparable[MaxN + 1];
        }else {
            pqMax = (Key[]) new Comparable[MaxN + 1];
        }
    }
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    public void resize(int doubleSize, MaxMinPQ maxMinPQ){
        Key[] array = (Key[]) new Comparable[doubleSize];
        if (maxMinPQ == MaxMinPQ.min){
            for (int i = 1; i <= n; i++){
                array[i] = pqMin[i];
            }
            pqMin = array;
        }
        else {
            for (int i = 1; i <= n; i++){
                array[i] = pqMax[i];
            }
            pqMax = array;
        }
    }
    public boolean less(int v, int w, MaxMinPQ maxMinPQ){
        if (maxMinPQ == MaxMinPQ.min){
            return pqMin[v].compareTo(pqMin[w]) < 0;
        }else {
            return pqMax[v].compareTo(pqMax[w]) < 0;
        }
    }
    public void exchange(int i, int j, MaxMinPQ maxMinPQ){
        if (maxMinPQ == MaxMinPQ.min){
            Key  temp = pqMin[i];
            pqMin[i] = pqMin[j];
            pqMin[j] = temp;
        }else {
            Key temp = pqMax[i];
            pqMax[i] = pqMax[j];
            pqMax[j] = temp;
        }
    }
    public void swim(int k, MaxMinPQ maxMinPQ){
        while (k > 1){
            if (maxMinPQ == MaxMinPQ.min){
                if (less(k, k / 2, maxMinPQ)){
                    exchange(k, k / 2, maxMinPQ);
                }
            }else {
                if (less(k / 2, k, maxMinPQ)){
                    exchange(k / 2, k, maxMinPQ);
                }
            }
            k = k / 2;
        }
    }
    public void sink(int k, MaxMinPQ maxMinPQ){
        if (maxMinPQ == MaxMinPQ.min){
            while (k * 2 <= n){
                int j = k * 2;
                if (j < n && less(j, j + 1, MaxMinPQ.min)){
                    j++;
                }
                if (!(less(k, j, MaxMinPQ.min))){
                    break;
                }
                exchange(k, j, maxMinPQ);
                k = j;
            }
        }else {
            while (k * 2 <= n){
                int j = k * 2;
                if (j < n && less(j + 1, j, MaxMinPQ.max)){
                    j++;
                }
                if (!(less(j, k, MaxMinPQ.max))){
                    break;
                }
                exchange(j, k, maxMinPQ);
                k = j;
            }
        }
    }
    // insert, delete maximum, delete minimum in logarithmic time.  find maximum, find minimum in constant time
    public void insert(Key key, MaxMinPQ maxMinPQ){
        if (maxMinPQ == MaxMinPQ.min){
            if (n == pqMin.length / 2){resize(2 * pqMin.length, MaxMinPQ.min);}
            pqMin[++n] = key;
            swim(n, MaxMinPQ.min);
        }else {
            if (n == pqMax.length / 2){resize(2 * pqMax.length, MaxMinPQ.max);}
            pqMax[++n] = key;
            swim(n, MaxMinPQ.max);
        }
    }
    public Key delMaxMin(MaxMinPQ maxMinPQ){
        if (maxMinPQ == MaxMinPQ.min){
            Key min = pqMin[1];
            exchange(1, n, maxMinPQ);
            pqMin[n] = null;
            n--;
            sink(1, MaxMinPQ.min);
            return min;
        }else {
            Key max = pqMax[1];
            exchange(1, n, maxMinPQ);
            pqMax[n] = null;
            n--;
            sink(1, MaxMinPQ.max);
            return max;
        }
    }
    public Key findMaxMin(MaxMinPQ maxMinPQ){
        if (maxMinPQ == MaxMinPQ.min){
            return pqMin[1];
        }else {
            return pqMax[1];
        }
    }

    public static void main(String[] args) {
        Ex_2_4_29_MinMaxPQ<Integer> minPQ = new Ex_2_4_29_MinMaxPQ(1, MaxMinPQ.min);
        Ex_2_4_29_MinMaxPQ<Integer> maxPQ = new Ex_2_4_29_MinMaxPQ(1, MaxMinPQ.max);
        minPQ.insert(6, MaxMinPQ.min);
        minPQ.insert(16, MaxMinPQ.min);
        minPQ.insert(60, MaxMinPQ.min);
        minPQ.insert(3, MaxMinPQ.min);
        minPQ.insert(9, MaxMinPQ.min);
        minPQ.insert(20, MaxMinPQ.min);
        maxPQ.insert(6, MaxMinPQ.max);
        maxPQ.insert(16, MaxMinPQ.max);
        maxPQ.insert(60, MaxMinPQ.max);
        maxPQ.insert(3, MaxMinPQ.max);
        maxPQ.insert(9, MaxMinPQ.max);
        maxPQ.insert(20, MaxMinPQ.max);
        StdOut.println("min PQ size: " + minPQ.size());
        StdOut.println("max PQ size: " + maxPQ.size());
        StdOut.println("The maximum in max PQ: " + maxPQ.findMaxMin(MaxMinPQ.max));
        StdOut.println("The minimum in Min PQ: " + minPQ.findMaxMin(MaxMinPQ.min));
        StdOut.println("Delete max in max PQ: " + maxPQ.delMaxMin(MaxMinPQ.max) + " Expected the value 60");
        StdOut.println("Delete min in min PQ: " + minPQ.delMaxMin(MaxMinPQ.min) + " Expected the value 3");
        StdOut.println("min PQ size: " + minPQ.size());
        StdOut.println("max PQ size: " + maxPQ.size());
    }
}

package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/*
*   Dynamic median-finding. Design a data type that supports insert in logarithmic time, find the median in constant time,
*   and delete the median in logarithmic time. Hint: Use a min-heap and a max-heap.
*
* */
@SuppressWarnings("unchecked")
public class Ex2_4_30_DynamicMedianFinding<Key extends Comparable<Key>> {
    private int nMinHeap;
    private int nMaxHeap;
    private Key[] minHeap;
    private Key[] maxHeap;
    private Key v;
    public enum MaxMin{
        max, min
    }
    public Ex2_4_30_DynamicMedianFinding(int MaxN){
        Comparable[] array = new Comparable[MaxN];
        for (int i = 0; i < MaxN; i++){
            array[i] = StdRandom.uniform(1, 50);
        }
        Arrays.sort(array);
        minHeap = (Key[]) new Comparable[MaxN / 2 + 1];
        for (int i = 0; i < MaxN / 2; i++){
            minHeap[i + 1] = (Key) array[i];
            nMinHeap++;
        }
        maxHeap = (Key[]) new Comparable[MaxN + 1];
        for (int i = MaxN / 2; i < MaxN; i++){
            maxHeap[i + 1] = (Key) array[i];
            //swim(i + 1, MaxMin.max);
            nMaxHeap++;
        }
        v = (Key) array[MaxN / 2];
    }
    //public boolean isEmpty(){return n == 0;}
    //public int size(){return n;}
    public void exchange(int v, int w, MaxMin maxMin){
        if (maxMin == MaxMin.max){
            Key temp = maxHeap[v];
            maxHeap[v] = maxHeap[w];
            maxHeap[w] = temp;
        }else {
            Key temp = minHeap[v];
            minHeap[v] = minHeap[w];
            minHeap[w] = temp;
        }
    }
    public boolean less(int i, int j, MaxMin maxMin){
        if (maxMin == MaxMin.max){
            return maxHeap[i].compareTo(maxHeap[j]) < 0;
        }else {
            return minHeap[i].compareTo(minHeap[j]) < 0;
        }
    }
    public void swim(int k, MaxMin maxMin){
        if (maxMin == MaxMin.max){
            while (k > 1 && less(k / 2, k, maxMin)){
                exchange(k / 2, k, maxMin);
                k = k / 2;
            }
        }
        else {
            while (k > 1 && less(k, k / 2, maxMin)){
                exchange(k, k / 2, maxMin);
                k = k / 2;
            }
        }
    }
    public void insert(Key key, MaxMin maxMin){
        if (key.compareTo(v) < 0){
            if (nMinHeap >= minHeap.length / 2){resize(2 * minHeap.length, maxMin);}
            v = minHeap[nMinHeap];
            minHeap[++nMinHeap] = key;
            swim(nMinHeap, maxMin);
        }
        else {
            if (nMaxHeap >= maxHeap.length / 2){resize(2 * maxHeap.length, maxMin);}
            v = maxHeap[nMaxHeap + 1];
            maxHeap[++nMaxHeap + 5] = key;
            swim(nMaxHeap + 5, maxMin);
        }
    }
    public Key findMedian(){
        return v;
    }
    public Key delMedian(){
        Key median = v;
        if (v.compareTo(minHeap[nMinHeap]) < 0){
            minHeap[nMinHeap] = null;
            nMinHeap--;
        }
        else if (v.compareTo(maxHeap[nMaxHeap]) > 0){
            maxHeap[nMaxHeap] = null;
            nMaxHeap--;
        }
        else {
            v = null;
            nMaxHeap--;
        }
        return median;
    }
    public void resize(int newSize, MaxMin maxMin){
        Key[] array = (Key[]) new Comparable[newSize];
        if (maxMin == MaxMin.max){
            for (int i = nMaxHeap + 1; i < maxHeap.length; i++){
                array[i] = maxHeap[i];
            }
            maxHeap = array;
        }
        else {
            for (int i = 1; i <= nMinHeap; i++){
                array[i] = minHeap[i];
            }
            minHeap = array;
        }
    }
    public static void main(String[] args) {
        Ex2_4_30_DynamicMedianFinding dynamicMedianFinding = new Ex2_4_30_DynamicMedianFinding(10);
        // the size minHeap or maxHeap
        StdOut.println("maxHeap size: " + dynamicMedianFinding.maxHeap.length);
        StdOut.println("minHeap size: " + dynamicMedianFinding.minHeap.length);
        // insert operation
        dynamicMedianFinding.insert(2, MaxMin.min);
        dynamicMedianFinding.insert(100, MaxMin.max);
        StdOut.println("maxHeap size: " + dynamicMedianFinding.maxHeap.length);
        StdOut.println("minHeap size: " + dynamicMedianFinding.minHeap.length);
        // find median
        StdOut.println("The median: " + dynamicMedianFinding.findMedian());
        // delMedian
        StdOut.println("delete median: " + dynamicMedianFinding.delMedian());
    }
}

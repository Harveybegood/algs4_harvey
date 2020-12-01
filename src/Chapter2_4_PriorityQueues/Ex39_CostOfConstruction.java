package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Cost of Construction. Determine empirically the percentage of time heapsort spends in the construction phase for
*   N = 10^3, 10^6 and 10^9
*
* */
@SuppressWarnings("unchecked")
public class Ex39_CostOfConstruction {
    private int N;
    private Comparable[] pq;
    public Ex39_CostOfConstruction(int maxN){
        pq = new Comparable[maxN + 1];
    }
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public void exchange(int i, int j){
        Comparable temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
    private void sink(int k){
        while (k * 2 <= N){
            int j = k * 2;
            if (j < N && pq[j+1].compareTo(pq[j]) < 0){
                j++;
            }
            if (!(pq[k].compareTo(pq[j]) < 0)){
                break;
            }
            exchange(k, j);
            k = j;
        }
    }
    public void heapSort(Comparable[] a){
        int n =  a.length;
        Stopwatch timer = new Stopwatch();
        for (int k = N / 2; k >= 1; k--){
            sink(k);
        }
        double T1 = timer.elapsedTime();
        while (n > 1){
            exchange(1, n--);
            sink(1);
        }
        double T2 =  timer.elapsedTime();
        StdOut.printf("%2d %8.1f\n", a.length, T1/T2);
    }

    public static void main(String[] args) {
        StdOut.printf("%2s %12s\n", "N", "Percentage");

        for (int N = 3; N <= 6; N += 3){
            int maxSize = (int) Math.pow(10, N);
            Comparable[] array = new Comparable[maxSize];
            for (int i = 0; i < maxSize; i++){
                array[i] = StdRandom.uniform(1, 100);
            }
            Ex39_CostOfConstruction costOfConstruction = new Ex39_CostOfConstruction(maxSize);
            costOfConstruction.heapSort(array);
        }
    }
}

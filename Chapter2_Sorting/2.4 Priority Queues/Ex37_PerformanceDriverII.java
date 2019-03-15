package Chapter2_4_PriorityQueues;


import Chapter1_4_AnalysisOfAlgorithms.Ex37;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Performance driver II. Write a performance driver client program that uses insert to fill a priority queue, then does
*   as many remove the maximum and insert operations as it can do in 1 second, doing so multiple times on random sequences
*   of keys of various lengths ranging from small to large; and print out or plots the average number of remove the maximum
*   operations it was able to do.
*
*
* */
@SuppressWarnings("unchecked")
public class Ex37_PerformanceDriverII<Key extends Comparable<Key>> {
    private int count;
    private int N;
    private Key[] pq;
    public Ex37_PerformanceDriverII(int maxN){
        count = 0;
        pq = (Key[]) new Comparable[maxN + 1];
    }
    public boolean isEmpty(){return N == 0;}
    public int size(){return N;}
    public void resize(int newSize){
        Key[] array = (Key[]) new Comparable[newSize];
        for (int i = 0; i < N; i++){
            array[i] = pq[i];
        }
        pq = array;
    }
    private void insert(Key key){
        pq[++N] = key;
        swim(N);
    }

    public void swim(int k){
        while (k > 1){
            if (pq[k / 2].compareTo(pq[k]) < 0){
                exchange(k / 2, k);
            }
            k = k / 2;
        }
    }
    public void exchange(int i, int j){
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
    public Key delMax(){
        Key max = pq[1];
        exchange(1, N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }
    public void sink(int k){
        while (k * 2 <= N){
            int j = 2 * k;
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
    // these snippets of codes above finish the basic operation for insert() and remove()
    // next, we have to satisfy two conditions that less than 1 second for insert and remove and various lengths
    // finally, count the number of remove maximum.
    public int experiments(int length){
        Stopwatch timer = new Stopwatch();
        while (timer.elapsedTime() <= 1.0){
            for (int i = 0; i < length; i++){
                if (N == 0){
                    for (int j = 0; j < length; j++){
                        insert((Key) Integer.valueOf(StdRandom.uniform(1, 100)));
                    }
                }
                delMax();
                count++;
            }
        }
        return count;
    }
    /*public double timeLess1(int length){
        Stopwatch timer = new Stopwatch();
        experiments(length);
        return timer.elapsedTime();
    }*/

    public static void main(String[] args) {
        StdOut.println("length      ext1    ext2    ext3    ext4    ext5    Avg");

        for (int i = 100000; i < 500001; i += 100000){
            Ex37_PerformanceDriverII performanceDriverII = new Ex37_PerformanceDriverII(i);
            StdOut.printf(i + "  ");
            int total = 0;
            for (int experiment = 1; experiment < 6; experiment++){
                int ext = performanceDriverII.experiments(i);
                StdOut.printf(ext + " ");
                total += ext;
            }
            StdOut.print(total);
            StdOut.println();
        }

    }
}

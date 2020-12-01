package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
*   Heap without exchanges. Because the exch() primitive is used in the sink() and swim() operations, the items are
*   loaded and stored twice as often as necessary. Give more efficient implementations that avoid this inefficiency,
*   a la insertion sort(see Exercise2.1.25).
*
* */
@SuppressWarnings("unchecked")
public class Ex2_4_26 {
    public class heapWithoutExchange<Key extends Comparable<Key>>{
        private int n;
        private Key[] pq;
        public heapWithoutExchange(){
            n = 0;
            pq = (Key[]) new Comparable[2];
        }
        public void insert(Key key){
            if (n == pq.length / 2){resize(pq.length * 2);}
            pq[n] = key;
            swim(n);
            n++;
        }
        public Key delMax(){
            if (isEmpty()){throw new RuntimeException("Queue is underflow. ");}
            Key max = pq[1];
            sink(1);
            pq[n+1] = null;
            n--;
            if (n == pq.length / 4){resize(pq.length / 2);}
            return max;
        }
        public boolean isEmpty(){return n == 0;}
        public int size(){return n;}
        public void resize(int newSize){
            Key[] newArray = (Key[]) new Comparable[newSize];
            for (int i = 0; i < n; i++){
                newArray[i] = pq[i];
            }
            pq = newArray;
        }
        public void swim(int k){
            while (k >= 1){
                Key aux = pq[k/2];
                if (pq[k].compareTo(pq[k/2]) > 0){
                    pq[k/2] = pq[k];
                }
                pq[k] = aux;
                k = k / 2;
            }
        }
        public void sink(int k){
            while (2 * k <= n){
                int j = 2 * k;
                Key aux = pq[k];
                if (j < n && pq[j].compareTo(pq[j+1]) > 0){
                    j++;
                }
                if (!(pq[k].compareTo(pq[j]) > 0)){
                    break;
                }
                pq[k] = pq[j];
                pq[j] = aux;
                k = j;
            }
        }
    }

    public class heapWithExchange<Key extends Comparable<Key>>{
        private int n;
        private Key[] pq;
        public heapWithExchange(){
            n = 0;
            pq = (Key[]) new Comparable[2];
        }
        public void insert(Key key){
            if (n == pq.length / 2){resize(pq.length * 2);}
            pq[++n] = key;
            swim(n);
            //n++;
        }
        public Key delMax(){
            if (isEmpty()){throw new RuntimeException("Queue is underflow. ");}
            Key max = pq[1];
            sink(1);
            pq[n] = null;
            n--;
            if (n == pq.length / 4){resize(pq.length / 2);}
            return max;
        }
        public boolean isEmpty(){return n == 0;}
        public int size(){return n;}
        public void resize(int newSize){
            Key[] newArray =(Key[]) new Comparable[newSize];
            for (int i = 1; i < n + 1; i++){
                newArray[i] = pq[i];
            }
            pq = newArray;
        }
        public void swim(int k){
            while (k > 1){
                if (pq[k].compareTo(pq[k/2]) > 0){
                    Key temp = pq[k/2];
                    pq[k/2] = pq[k];
                    pq[k] = temp;
                }
                k = k / 2;
            }
        }
        public void sink(int k){
            while (2 * k <= n){
                int j = 2 * k;
                if (j < n && pq[j].compareTo(pq[j+1]) > 0){
                    j++;
                }
                if (!(pq[k].compareTo(pq[j]) > 0)){
                    break;
                }
                Key temp = pq[k];
                pq[k] = pq[j];
                pq[j] = temp;
                k = j;
            }
        }
    }

    public static void main(String[] args) {
        heapWithoutExchange<Integer> withoutExchange = new Ex2_4_26().new heapWithoutExchange<>();
        heapWithExchange withExchange = new Ex2_4_26().new heapWithExchange();
        StdOut.printf("%14s %10s\n", "WithoutExchange", "withExchange");
        for (int experiment = 100000; experiment < 2000000; experiment += 100000){
            Stopwatch timer1 = new Stopwatch();
            for (int i = 0; i < experiment; i++){
                withoutExchange.insert(StdRandom.uniform(0, experiment));
            }
            for (int i = 0; i < experiment - 2; i++){
                withoutExchange.delMax();
            }
            double timeForWithoutExchange = timer1.elapsedTime();
            Stopwatch timer2 = new Stopwatch();
            for (int i = 0; i < experiment; i++){
                withExchange.insert(StdRandom.uniform(0, experiment));
            }
            for (int i = 0; i < experiment; i++){
                withExchange.delMax();
            }
            double timeForWithExchange = timer2.elapsedTime();
            StdOut.printf("%10.2f %15.2f\n", timeForWithoutExchange, timeForWithExchange);
        }
    }
}

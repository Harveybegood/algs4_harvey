package Chapter2_4_PriorityQueues;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Multiway {
    public static void merge(In[] streams){
        int N = streams.length;
        StdOut.println(N);
        IndexMinPQ<String> pq = new IndexMinPQ<>(N);
        for (int i = 0; i < N; i++){
            if (!streams[i].isEmpty()){
                pq.insert(i, streams[i].readString());
            }
        }
        while (!pq.isEmpty()){
            StdOut.print(pq.minKey() + " ");
            int i = pq.delMin();
            StdOut.print(i + " ");
            if (!streams[i].isEmpty()){
                StdOut.print(i + " ");
                pq.insert(i, streams[i].readString());
            }
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        // the number of args arguments
        int N = args.length;
        // create new object of In for receiving string, number, files, URLs and sockets...
        In[] streams = new In[N];
        for (int i = 0; i < N; i++){
            streams[i] = new In(args[i]);
        }
        merge(streams);
    }
}

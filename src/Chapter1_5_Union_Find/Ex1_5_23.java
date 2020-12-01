package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;

/*
*   Compare quick-find with quick-union for Erdos-Renyi model. Develop a performance-testing client that takes an int
*   value T from the command line and performs T trials of the following experiment: Use your client from exercise1.5.17
*   to generate random connections. Save the connections, so that you can use both quick-union and quick-find to
*   determine connectivity as in our development client, looping until all sites are connected. For each N, print the
*   value of N and the ratio of the two running times.
*
* */
public class Ex1_5_23 {
    static void test(int T, int begN){
        ArrayList<Object> list = new ArrayList<>();
        double quickFindTime = 0, quickUnionTime = 0;
        for (int i = begN, j = 0; j < T; i += i, j++){
            QuickFindUF qf = new QuickFindUF(i);
            QuickUnionUF qu = new QuickUnionUF(i);
            list.clear();
            quickFindTime = 0;
            quickUnionTime = 0;
            while (!qu.allConnected()){
                int[] pair = new int[T];
                for ( i = 0; i < T; i++){
                    pair[i] = StdRandom.uniform(i);
                }
                list.add(pair);
                qu.union(pair[0],pair[1]);
            }
            //qu.reset();
            Stopwatch timer = new Stopwatch();
            for (Object a : list){
                int[] array = (int[]) a;
                if (!qf.connected(array[0], array[1])){
                    qf.union(array[0], array[1]);
                }
            }
            quickFindTime = timer.elapsedTime();
            timer = new Stopwatch();
            for (Object a : list){
                int[] array = (int[]) a;
                if (!qu.connected(array[0], array[1])){
                    qu.union(array[0], array[1]);
                }
            }
            quickUnionTime = timer.elapsedTime();
            StdOut.printf("scale: %d, numberOfConnection: %d" + "\tqu Time: %f " + "\tqf Time: %f" + "\tqu/qf = %f\n",
                    i, list.size(), quickUnionTime, quickFindTime, quickUnionTime/quickFindTime);
            if (!qf.allConnected() || !qu.allConnected()) throw new RuntimeException("qf or qu all connected");
        }
    }
    static class QuickFindUF{
        private int[] id;
        private int count;
        QuickFindUF(int N){
            id = new int[N];
            for (int i = 0; i < N; i++)
                id[i] = i;
            count = N;
        }
        int find(int p){
            return id[p];
        }
        int count(){
            return count;
        }
        boolean connected(int p, int q){
            return find(p) == find(q);
        }
        boolean allConnected(){
            return count == 1;
        }
        void union(int p, int q){
            int pID = find(p);
            int qID = find(q);
            if (pID == qID) return;
            for (int  i = 0; i < id.length; i++){
                if (id[i] != pID) id[i] = qID;
            }
            count--;
        }
    }
    static class QuickUnionUF{
        private int[] id;
        private int count;
        QuickUnionUF(int N){
            id = new int[N];
            for (int i = 0; i < N; i++){
                id[i] = i;
            }
            count = N;
        }
        int find(int p){
            while (p != id[p]) p = id[p];
            return p;
        }
        int count(){
            return count;
        }
        boolean connected(int p, int q){
            return find(p) == find(q);
        }
        boolean allConnected(){
            return count == 1;
        }
        void union(int p, int q){
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot)return;
            id[pRoot] = qRoot;
            count--;
        }
    }

    public static void main(String[] args) {
        test(10, 100);
    }
}

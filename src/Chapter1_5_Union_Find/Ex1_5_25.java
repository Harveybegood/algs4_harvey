package Chapter1_5_Union_Find;

import AnalysisOfAlgorithmsTest.Stopwatch;
import edu.princeton.cs.algs4.StdOut;

import static Chapter1_5_Union_Find.Ex1_5_18.randomGridGenerator;

/*
*   Doubling test for random grids. Develop a performance-testing client that takes an int value T from the command line
*   and performs T trials of the following experiment: Use your client from exercise 1.5.18 to generate the connections
*   in an N-by-N square grid , randomly oriented and in random order, then use UnionFind to determine connectivity as in
*   our development client, looping until all sites are connected. For each N, print the value of N, the average number
*   of connections processed, and the ratio of the running time to the previous. Use your program to validate the
*   hypotheses in the text that the running times for quick-find and quick-union are quadratic and weighted quick-union
*   is near-linear. Note: As N doubles, the number of sites in the grid increases by a factor of 4, so expect a doubling
*   factor of 16 for quadratic and 4 for linear.
*
* */
public class Ex1_5_25 {
   interface UF {
       int find(int p);
       boolean connected(int p, int q);
       boolean allConnected();
       void union(int p, int q);
       void reset();
   }
   static void doubleRatioTest(int T, int begN, Class<?> type) throws Exception{
       double prev = 0, cur = 0;
       int pairCount = 0;
       for (int i = begN, j = 0; j < T; i += i, j++){
           int tmpI = (int)Math.sqrt(i);
           UF uf = (UF)type.getDeclaredConstructor(int.class).newInstance(tmpI*tmpI);
           Ex1_5_18.RandomBag<Ex1_5_18.Connection> randomBag = randomGridGenerator(tmpI);
           cur = 0;
           pairCount = 0;
           int testCount = 3;
           for (int k = 0; k < testCount; k++){
               Stopwatch timer = new Stopwatch();
               for (Ex1_5_18.Connection c : randomBag){
                   if (uf.allConnected())break;
                   pairCount++;
                   if (uf.connected(c.p, c.q)) continue;
                   uf.union(c.p, c.q);
               }
               cur += timer.elapsedTime();
           }
           cur /= (testCount * 1.0);
           pairCount /= (testCount * 1.0);
           StdOut.printf("Scale: %d " + "Number of Connected: %d " + "Time Cost: %f " + "Doubling: %f\n",
                   tmpI * tmpI, pairCount, cur, cur / prev);
           prev = cur;
       }

   }
   public static class QF implements UF{
       private int[] id;
       private int count;
       public QF(int N){
           count = N;
           id = new int[N];
           for (int i = 0; i < N; i++){
               id[i] = i;
           }
       }
       public boolean connected(int p, int q){return find(p) == find(q);}
       public void union(int p, int q){
           int pRoot = find(p);
           int qRoot = find(q);
           if (pRoot == qRoot) return;
           for (int i = 0; i < id.length; i++){
               if (id[i] == pRoot) id[i] = qRoot;
           }
           count--;
       }
       public void reset(){
           for (int i = 0; i < id.length; i++){
               id[i] = i;
           }
           count = id.length;
       }
       public boolean allConnected(){return count == 1;}
       public int find(int p){return id[p];}
   }
   public class QU implements UF{
       private int[] id;
       //private int[] size;
       private int count;
       public QU(int N){
           count = N;
           id = new int[N];
           //size = new int[N];
           for (int i = 0; i < N; i++){
               id[i] = i;
               //size[i] = 1;
           }
       }
       public int count(){return count;}
       public int find(int p){
           while (p != id[p]) p = id[p];
           return p;
       }
       public boolean connected(int p, int q){return find(p) == find(q);}
       public boolean allConnected(){return count == 1;}
       public void reset(){
           for (int i = 0; i < id.length; i++){
               id[i] = i;
           }
           count = id.length;
       }
       public void union(int p, int q){
           int pRoot = find(p);
           int qRoot = find(q);
           if (pRoot == qRoot)return;
           id[pRoot] = qRoot;
           count--;
       }
   }
   public class WQU implements UF{
       private int[] id;
       private int[] size;
       private int count;
       public WQU(int N){
           id = new int[N];
           size = new int[N];
           count = N;
           for (int i = 0; i < N; i++){
               id[i] = i;
               size[i] = 1;
           }
       }
       public void reset(){
           for (int i = 0; i < id.length; i++){
               id[i] = i;
           }
           count = id.length;
       }
       public int find(int p){
           while (p != id[p]) p = id[p];
           return p;
       }
       public int count(){return count;}
       public boolean connected(int p, int q){return find(p) ==  find(q);}
       public boolean allConnected(){return count == 1;}
       public void union(int p, int q){
           int pRoot = find(p);
           int qRoot = find(q);
           if (pRoot == qRoot) return;
           if (size[pRoot] < size[qRoot]) {
               id[pRoot] = qRoot;
               size[qRoot] += size[pRoot];
           }else {
               id[qRoot] = pRoot;
               size[pRoot] += size[qRoot];
           }
           count--;
       }
   }

    public static void main(String[] args) throws Exception{
        doubleRatioTest(14, 100, QF.class);
    }
}

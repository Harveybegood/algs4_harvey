package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/*
*   Animation. Write a RandomGrid client(see Exercise 1.5.18) that uses UnionFind as in our development client to check
*   connectivity and uses StdDraw to draw the connections as they are processed.
*
* */
public class Ex1_5_19 {
   static class depthWeightedQuickUnion{
       private int[] id;
       private int[] depth;
       depthWeightedQuickUnion(int N){
           id = new int[N];
           depth = new int[N];
           for (int i = 0; i < N; i++){
               id[i] = i;
           }
       }
       public int[] id(){return id;}
       public boolean connected(int p, int q){return find(p) == find(q);}
       public int find(int p){
           while (p != id[p]) p = id[p];
           return p;
       }
       public void update(int p){
           while (p != id[p]){
               depth[id[p]]++;
               p = id[p];
           }
       }
       public void union(int p, int q){
           int pRoot = find(p);
           int qRoot = find(q);
           if (depth[pRoot] < depth[qRoot]){
               id[pRoot] = qRoot;
           }else if (depth[pRoot] > depth[qRoot]){
               id[qRoot] = pRoot;
           }else {
               id[pRoot] = qRoot;
               update(pRoot);
           }
       }
   }
   public static void draw(int N) throws Exception{
       N = (int)Math.sqrt(N);
       StdDraw.setXscale(-2, N);
       StdDraw.setYscale(-2, N);
       StdDraw.setPenRadius(.005);
       StdDraw.setPenColor(Color.BLACK);
       for (int i = N-1; i >= 0; i--){
           for (int j = 0; j < N; j++){
               StdDraw.point(j, i);
           }
       }
       depthWeightedQuickUnion dwqu = new depthWeightedQuickUnion(N * N);
       Ex1_5_18.RandomBag<Ex1_5_18.Connection> randomBag = new Ex1_5_18.RandomBag<>();
       for (Ex1_5_18.Connection c : randomBag){
           if (dwqu.connected(c.p, c.q)){
               StdOut.printf("(%d, %d) connected\n", c.p, c.q);
               continue;
           }
           dwqu.union(c.p, c.q);
           StdDraw.setPenColor(Color.red);
           StdDraw.setPenRadius(.003);
           int x0 = c.p / N;
           int y0 = c.p % N;
           int x1 = c.q / N;
           int y1 = c.q % N;
           StdDraw.line(x0, y0, x1, y1);
           TimeUnit.MILLISECONDS.sleep(10);
       }
   }

    public static void main(String[] args)throws Exception {
        draw(1000);
    }
}

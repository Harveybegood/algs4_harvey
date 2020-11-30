package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

import java.awt.*;

/*
*   Amortized costs plots. Instrument your implementations from exercise1.5.7 to make amortized costs plots like
*   those in the text
*   Develop classes QuickUnionUF and QuickFindUF that implement quick-union and quick-find, respectively.
*
* */
public class Ex1_5_16 {
   static class QuickUnionUF{
       private int accessArray;
       private int TotalAccessArray;
       private int count;
       private int[] id;
       int i = 1;
       public QuickUnionUF(int N){
           StdDraw.setXscale(0, 1000);
           StdDraw.setYscale(0, 1);
           StdDraw.setPenRadius(.003);
           TotalAccessArray = 0;
           count = N;
           id = new int[N];
           for (int i = 0; i < id.length; i++) id[i] = i;
       }
       public int count(){return count;}
       public int find(int p){
           accessArray++;
           while (p != id[p]) p = id[p];
           accessArray++;
           return p;
       }
       public boolean connected(int p, int q){return find(p) == find(q);}
       public void union(int p, int q){
           accessArray = 0;
           int pRoot = find(p);
           int qRoot = find(q);
           accessArray += 2;
           if (pRoot == qRoot) return;
           id[pRoot] = qRoot;
           accessArray += 1;
           count--;
           TotalAccessArray += accessArray;
           StdDraw.setPenColor(Color.gray);
           StdDraw.point(i, accessArray);
           StdDraw.setPenColor(Color.red);
           StdDraw.point(i, TotalAccessArray/i);
           i++;
       }
   }
   static class QuickFindUF{
       private int count;
       private int[] id;
       public QuickFindUF(int N){
           count = N;
           id = new int[N];
           for (int i = 0; i < id.length; i++)id[i] = i;
       }
       public int count(){return count;}
       public int find(int p){return id[p];}
       public boolean connected(int p, int q){return find(p) == find(q);}
       public void union(int p, int q){
           int pID = find(p);
           int qID = find(q);
           if (pID == qID) return;
           for (int i = 0; i < id.length; i++){
               if (id[i] == pID) id[i] = qID;
           }
           count--;
       }
   }

    public static void main(String[] args) {

        int N = StdIn.readInt();
        QuickUnionUF quickUnionUF = new QuickUnionUF(N);
        QuickFindUF quickFindUF =new QuickFindUF(N);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (quickUnionUF.connected(p, q)) continue;
            quickUnionUF.union(p, q);
            //StdDraw.point();
            //StdOut.println(p + " " + q + "  Connected");
            //StdOut.println("---------------");
           /* if (quickFindUF.connected(p, q)) continue;
            quickFindUF.union(p, q);
            StdOut.println(p + "  " + q + " Connected");*/

        }
    }
}

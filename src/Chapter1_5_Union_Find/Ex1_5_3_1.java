package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdOut;

public class Ex1_5_3_1 {
    static class UF{
        private int[] id;
        private int[] sz;
        {
            id = new int[10];
            sz = new int[id.length];
            for (int i = 0; i < id.length; i++){
                id[i] = i;
            }
            for (int i = 0; i < sz.length; i++){
                sz[i] = 1;
            }
        }
        private int accessTimes;
        private int accessTotalTimes;
        public int find(int p){
            accessTimes++;
            if (p != id[p]){
                accessTimes++;
                p = id[p];
            }
            return p;
        }
        public void union(int p, int q){
            accessTimes = 0;
            int pRoot = find(p);
            int qRoot = find(q);
            //if (pRoot == qRoot) return;
            accessTimes += 2;
            if (sz[pRoot] < sz[qRoot]){
                id[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
                accessTimes += 4;
            }else {
                id[qRoot] = pRoot;
                sz[pRoot] += sz[qRoot];
                accessTimes += 4;
            }
            accessTotalTimes += accessTimes;
            StdOut.printf("Connecting: %d %d, array accessed: %d times, Total: %d times\n", p, q, accessTimes, accessTotalTimes);
            StdOut.println(this);
        }
        public String toString(){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("------------------------------------\n");
            stringBuilder.append("Index:");
            int i = 0;
            while (i < id.length)stringBuilder.append(" " + i++);
            stringBuilder.append("\n\t  ");
            i = 0;
            while (i < id.length)stringBuilder.append(" " + id[i++]);
            stringBuilder.append("\n------------------------------------\n");
            return stringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        UF uf = new UF();
        uf.union(9, 0);
        uf.union(3, 4);
        uf.union(5, 8);
        uf.union(7, 2);
        uf.union(2, 1);
        uf.union(5, 7);
        uf.union(0, 3);
        uf.union(4, 2);
    }
}

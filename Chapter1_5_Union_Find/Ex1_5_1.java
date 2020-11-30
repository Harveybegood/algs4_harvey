package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdOut;

/*
*   Do exercise 1.5.1, but use quick-union. In addition, draw the forest of trees represented by the id[] array after
*   each input pair is processed
*
*   Show the contents of the id[] array and the number of times the array is accessed for each input pair
*
* */
public class Ex1_5_1 {
    static class UF{
        private int accessTotalTime;
        private int[] id = new int[10];
        {
            for (int i = 0; i < 10; i++)
                id[i] = i;
        }
        public int find(int p){return id[p];}
        public void union(int p, int q){
            int accessTime = 0;
            int pID = find(p);
            int qID = find(q);
            accessTime += 2;
            if (pID != qID){
                for (int i = 0; i < id.length; i++){
                    accessTime++;
                    if (id[i] == pID){
                        id[i] = qID;
                        accessTime++;
                    }
                }
                accessTotalTime += accessTime;
                StdOut.printf("Connecting %d %d, access array: %d, times array accessed: %d\n", p, q, accessTime, accessTotalTime);
            }else {
                accessTotalTime += accessTime;
                StdOut.printf("%d %d connected, access array: %d, times array accessed: %d\n", p, q, accessTime, accessTotalTime);
                StdOut.println(this);
            }

        }
        public String toString(){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("-----------------------------------------\n");
            stringBuilder.append("Index: \t");
            int i = 0;
            while (i < id.length) {
                stringBuilder.append(" " + i++);
                stringBuilder.append("\n\t");
                i = 0;
            }
            while (i < id.length){
                stringBuilder.append(" " + id[i++]);
                stringBuilder.append("\n-------------------------------------------\n");
            }
            return stringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        UF uf = new UF();
        uf.union(9, 0 );
        uf.union(3, 4 );
        uf.union(5, 8 );
        uf.union(7, 2 );
        uf.union(2, 1 );
        uf.union(5, 7 );
        uf.union(0, 3 );
        uf.union(4, 2 );

    }
}

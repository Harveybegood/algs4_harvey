package Chapter1_5_Union_Find;
/*
*   Give a counterexample that shows why this intuitive implementation of union() for quick-find is not correct:
*
*
* */
public class Ex1_5_8 {
    //public int find(int p){return id[p]}
    private int id[];
    private int count;
    public Ex1_5_8(int N){
        count = N;
        id = new int[N];
        for (int i = 0; i < id.length; i++)id[i] = i;
    }

    public boolean connected(int p, int q){return id[p] == id[q];}
    public void union(int p, int q){
        if (connected(p,q)) return;
        for (int i = 0; i < id.length; i++){
            if (id[i] == id[p])
                id[i] = id[q];
                //id[i] = find(q);
        }
        count--;
    }
    /*
    *   counterexample:
    *   id[p] = 2;
    *   id[q] = 4;
    *         0 1 2 3 4 5 6
    *   Array 0 1 2 2 4 2 6
    *
    *   0 - > id[0] = 0 != 2, hence no updates.
    *   1 - > id[0] = 1 != 2, hence no updates.
    *   2 - > id[2] = 2 = 2, hence is updated to 4,
    *   Array converted as: 0 1 2 3 4 5 6
    *                       0 1 4 2 4 2 6
    *   2 - > id[2] != 4
    *   4 - > id[4], hence is updated to 4
    *   2 - > id[2] != 4
    *   6 - > id[6] != 4
    *
    *
    * */
}

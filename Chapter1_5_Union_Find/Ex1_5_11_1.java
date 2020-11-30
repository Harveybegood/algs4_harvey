package Chapter1_5_Union_Find;

public class Ex1_5_11_1 {
    private int[] id;
    private int[] size;
    private int count;
    private int accessTime;
    private int accessTotalTime;
    Ex1_5_11_1(int N){
        count = N;
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < id.length; i++){
            id[i] = i;
            size[i] = 1;
        }
    }
    int count(){return count;}
    int find(int p){return id[p];}
    boolean connected(int p, int q){return find(p) == find(q);}
    void union(int p, int q){
        accessTime = 0;
        int pID = find(p);
        int qID = find(q);
        accessTime += 2;
        if (pID == qID){
            accessTotalTime += accessTime;
            return;
        }
        int larger = size[pID] > size[qID] ? pID : qID;
        int smaller = larger == pID ? qID : pID;
        accessTime += 2;
        for (int i = 0; i < id.length; i++){
            accessTime++;
            if (id[i] == smaller){
                accessTime += 4;
                id[i] = larger;
                size[larger] += size[i];
            }
        }
        accessTotalTime += accessTime;
        count--;
    }
}

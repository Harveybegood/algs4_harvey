package Chapter1_5_Union_Find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
*   Estimate the minimum amount of time(in days) that would be required for quick-find to solve a dynamic connectivity
*   problem with 109 sites and 106 input pairs, on a computer capable of executing 109 instructions per second. Assume
*   that each iteration of the inner for loop requires 10 machine instructions.
*
* */
public class Ex1_5_5 {
    private int count;
    private int id[];
    private int N;
    public Ex1_5_5(){
        count = N;
        id = new int[N];
        for (int i = 0; i < id.length; i++) id[i] = i;
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

    public static void main(String[] args) {
        int N = StdIn.readInt();
        Ex1_5_5 UF = new Ex1_5_5();
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (UF.connected(p, q)) continue;
            UF.union(p, q);
            StdOut.println(p + " " + q);
        }
    }

    /*
    *   10^9 sites, 10^6 input pairs,
    *   10^9 instructions per second,
    *   the object initialization makes ~10^9 instructions (1 for initializing the count variable, 1 for creating the
    *   parent array and 10^9 for initializing each parent)
    *   Each find operation makes 1 instruction
    *   Each union operation makes ~10^9 instructions(1 for each find(), 1 for comparing parents of the components being
    *   united, 10^9 for comparing parents, 1 to 10^9 for updating parents and 1 for reducing the count of components)
    *   For 10^6 input pairs, the total number of instructions required is:
    *   I = M * IU * MI = 10^6 * 10^9 * 10 = 10^16
    *   Where M  is the number of input pairs, IU is the number of instructions in the union operation, MI is the number
    *   of machine instructions
    *   Seconds needed = 10^16 / 10^9 = 10^7
    *   = 115.7 days
    *
    *
    *
    *
    * */
}

package Chapter4_1_UndirectedGraphs;
/*
*   Develop an implementation for the Search API on page 528 that uses UF, as described in the text.
*
* */
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Ex08_Search {
    UF uf;
    WeightedQuickUnionUF wquf;
    Graph graph;
    int source;
    // build a UF object, do a union() operation for each of the graph's edges.
    public Ex08_Search(Graph G, int s){
        this.graph = G;
        this.source = s;
        uf = new UF(G.V());
        wquf = new WeightedQuickUnionUF(G.V());
        for (int v = 0; v < G.V(); v++){
            if (!uf.connected(v, s)){
                uf.union(v, s);
            }
            if (!wquf.connected(v, s)){
                wquf.union(v, s);
            }
        }
    }
    // implement this method by calling connected(s, v)
    public boolean marked(int s){
        for (int v = 0; v < graph.V(); v++){
             if (uf.connected(v, s)){
                 return true;
             }
        }
        return false;
    }
    // implementing this method requires using a weighted UF implementation and extending its API to use a count() method
    // that returns wt[find(v)]
    public int count(){
        return wquf.count();
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph graph = new Graph(in);
        int s = Integer.parseInt(args[1]);
        Ex08_Search search = new Ex08_Search(graph, s);
        for (int v = 0; v < graph.V(); v++){
            if (search.marked(v)){
                StdOut.print(v + " ");
            }
        }
        StdOut.println();
        if (search.count() != graph.V()){
            StdOut.println("Not connected");
        }
        else {
            StdOut.println("Connected");
        }
    }
}

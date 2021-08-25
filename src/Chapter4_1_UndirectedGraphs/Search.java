package Chapter4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Search {
    // build a UF object, do a union() operation for each of the graph's edges.
    public Search(Graph G, int s){

    }
    // implement this method by calling connected(s, v)
    public boolean marked(int s){
        for (){

        }
    }
    // implementing this method requires using a weighted UF ...
    public int count(){

    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph graph = new Graph(in);
        int s = Integer.parseInt(args[1]);
        Search search = new Search(graph, s);
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

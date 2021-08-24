package Chapter4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("unchecked")
public class Graph {
    private final int V;  // number of vertices
    private int E;        // number of edges
    private Bag<Integer>[] adj;
    // create a V-vertex graph with no edges
    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++){
            adj[v] = new Bag<>();
        }
    }
    // read a graph from input stream in
    public Graph(In in){
        this(in.readInt()); // Read V and construct this graph
        int E = in.readInt();  // Read E
        for (int i = 0; i < E; i++){
            int v = in.readInt();   // Read vertex
            int w = in.readInt();   // Read another vertex
            addEdge(v, w);          // add edge connecting them
        }
    }
    // number of vertices
    public int V(){
        return V;
    }
    // number of edges
    public int E(){
        return E;
    }
    // add edge v-w to this graph
    public void addEdge(int v, int w){
        adj[v].add(w); // add w to v's list
        adj[w].add(v); // add v to w's list
        E++;
    }
    // vertices adjacent to v, which iterates through the vertices adjacent to a given vertex
    Iterable<Integer> adj(int v){
        return adj[v];
    }
    // compute the degree of v
    public static int degree(Graph G, int v){
        int degree = 0;
        for (int w : G.adj(v)){
            degree++;
        }
        return degree;
    }
    // compute maximum degree
    public static int maxDegree(Graph G){
        int max = 0;
        for (int v = 0; v < G.V; v++){
            if (degree(G, v) > max){
                max = degree(G, v);
            }
        }
        return max;
    }
    // compute average degree
    public static int avgDegree(Graph G){
        //return 2 * G.E() / G.V(); // a method provided by text
        int totalDegree = 0;
        for (int v = 0; v < G.V; v++){
            totalDegree += degree(G, v);
        }
        return totalDegree / G.V();
    }
    // count self-loops
    public static int numberOfSelfLoops(Graph G){
        int count = 0;
        // a method provided by text
        /*for (int v = 0; v < G.V(); v++){
            for (int w : G.adj(v)){
                if (v == w){
                    count++;
                }
            }
        }
        // each edge counted twice
        return count / 2;*/

        for (int v = 0; v < G.V(); v++){
            if (degree(G, v) == 1){
                count++;
            }
        }
        return count;
    }
    // string representation, in a form of
    public String toString(){
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++){
            s += v + ": ";
            for (int w : this.adj(v)){
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        //Graph graph = new Graph(13);
        In in = new In(args[0]);
        Graph graph = new Graph(in);
        StdOut.println(graph);
    }
}

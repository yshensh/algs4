package graph;

import edu.princeton.cs.algs4.In;
import fundamentals.Bag;

/**
 * The Graph class represents an undirected graph of vertices named 0 through V-1
 * The implementation uses an adjacent-lists representation, which is a vertex-indexed array of Bag objects.
 *
 */
public class Graph {
    private final int V;            // number of vertices
    private int E;                  // number of edges
    private Bag<Integer>[] adj;     // adjacency lists

    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];  // create array of lists
        for (int v = 0; v < V; v++)         // initialize all lists to empty
            adj[v] = new Bag<Integer>();
    }

    public Graph(In in) {
        this.V = in.readInt();              // read V and construct this graph
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        adj = (Bag<Integer>[]) new Bag[V];  // create array of lists
        for (int v = 0; v < V; v++)         // initialize all lists to empty
            adj[v] = new Bag<Integer>();
        int E = in.readInt();               // read E
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            // add am edge
            int v = in.readInt();   // read a vertex
            int w = in.readInt();   // read another vertex
            addEdge(v, w);
        }
    }

    public int V() { return V; }

    public int E() { return E; }

    public void addEdge(int v, int w) {
        adj[v].add(w);  // add w to v's list
        adj[w].add(v);  // add v to w's list
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int degree(int v){
        return adj[v].size();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : this.adj(v))
                s.append(w + " ");
            s.append("\n");
        }
        return s.toString();
    }
}

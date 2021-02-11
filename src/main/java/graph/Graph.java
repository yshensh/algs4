package graph;

import edu.princeton.cs.algs4.In;
import fundamentals.Bag;

/**
 * The Graph class represents an undirected graph of vertices named 0 through V-1
 * The implementation uses an adjacent-lists representation, which is a vertex-indexed array of Bag objects.
 */
public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;            // number of vertices
    private int E;                  // number of edges
    private Bag<Integer>[] adj;     // adjacency lists

    // create a V-vertex graph with no edges
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];  // create array of lists
        for (int v = 0; v < V; v++)         // initialize all lists to empty
            adj[v] = new Bag<Integer>();
    }

    // read a graph from input stream in
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

    // number of vertices
    public int V() {
        return V;
    }

    // number of edges
    public int E() {
        return E;
    }

    // add edge v-w to this graph
    public void addEdge(int v, int w) {
        adj[v].add(w);  // add w to v's list
        adj[w].add(v);  // add v to w's list
        E++;
    }

    // vertices adjacent to v
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // string representation
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}

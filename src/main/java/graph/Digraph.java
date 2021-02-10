package graph;

import edu.princeton.cs.algs4.In;
import fundamentals.Bag;

/**
 * Digraph using the adjacency-lists representation, which is a vertex-index array of Bag objects.
 * <p>
 * Applications:
 * 1) directed cycle detection
 * 2) topological sort
 */
public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;            // number of vertices
    private int E;                  // number of edges
    private Bag<Integer>[] adj;     // adjacency lists


    // create a V-vertex digraph with no edges
    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    // read a diagraph from input stream in
    public Digraph(In in) {
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

    // add edge v->w to this digraph
    void addEdge(int v, int w) {
        adj[v].add(w);  // add w to v's list
        E++;
    }

    // vertices connected to v by edges pointing from v
    Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // reverse of this digraph
    Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    // string representation
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}

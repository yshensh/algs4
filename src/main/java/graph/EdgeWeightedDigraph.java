package graph;

import edu.princeton.cs.algs4.In;
import fundamentals.Bag;

public class EdgeWeightedDigraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;                 // number of vertices
    private int E;                      // number of edges
    private Bag<DirectedEdge>[] adj;    // adjacency lists

    // empty V-vertex digraph
    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<DirectedEdge>();
        }
    }

    // construct from in
    public EdgeWeightedDigraph(In in) {
        this.V = in.readInt();
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<DirectedEdge>();
        }

        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            addEdge(new DirectedEdge(v, w, weight));
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

    // add e to this digraph
    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    // edges pointing from v
    Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    // all edges in this digraph
    Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++) {
            for(DirectedEdge e : adj[v])
                bag.add(e);
        }
        return bag;
    }

    // string representation
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}

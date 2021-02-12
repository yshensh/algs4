package graph;

import edu.princeton.cs.algs4.In;
import fundamentals.Stack;
import sorting.IndexMinPQ;

public class DijkstraSP {
    // shortest-paths tree which gives a shortest path from s to every vertex reachable from s
    // edgeTo[v] = last edge on shortest s->v path
    private DirectedEdge[] edgeTo;
    // distance to the source: a vertex-indexed array
    // distTo[v] = distance of shortest s->v path
    private double[] distTo;
    // keep track of  vertices that are candidates for being the next to be relaxed
    // priority queue of vertices
    private IndexMinPQ<Double> pq;

    // constructor
    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<Double>(G.V());

        // initialize
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        pq.insert(s, 0.0);
        while (!pq.isEmpty())
            relax(G, pq.delMin());

    }

    // relax all the edges pointing from a given vertex v
    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                // edgeTo[w] is the edge that connects w to its parent in the tree (the last edge on a shortest path from s to w).
                edgeTo[w] = e;
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    // distance from s to v, infinity if no path
    double distTo(int v) {
        return distTo[v];
    }

    // path from s to v?
    boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    // path from s to v, null if none
    Iterable<DirectedEdge> pathTo(int v) {
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}

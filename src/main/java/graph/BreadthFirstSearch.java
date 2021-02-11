package graph;

import fundamentals.Queue;
import fundamentals.Stack;

/**
 * Breadth-first search can solve the problem: single-source shortest paths
 * <p>
 * Given a graph and a source vertex s, support queries of the form "Is there
 * a path from s to a given target vertex v?" If so, find a shortest such path
 * (one with a minimal number of edges)
 */
public class BreadthFirstSearch {
    private boolean[] marked;   // is a shortest path to this vertex known?
    private int[] edgeTo;       // last vertex on known path to this vertex (parent)
    private int[] distTo;       // distTo[v] = number of edges shortest s-v path

    public BreadthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Integer.MAX_VALUE;
        distTo[s] = 0;
        // mark it as having been visited
        marked[s] = true;
        q.enqueue(s);   // put it on the queue
        while (!q.isEmpty()) {
            int v = q.dequeue();    // remove next vertex from the queue
            for (int w : G.adj(v)) {
                if (!marked[w]) {       // for every unmarked adjacent vertex
                    edgeTo[w] = v;      // save last edge on a shortest path (parent of w is v)
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;   // mark it because path is known
                    q.enqueue(w);   // and add it to the queue
                }
            }
        }
    }

    // is there a path from s to v
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    // the number of edges in a shortest path between the source vertex s and vertex v
    public int distTo(int v) {
        return distTo[v];
    }

    // path from s to v; null if no such path
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(x);
        return path;
    }

}

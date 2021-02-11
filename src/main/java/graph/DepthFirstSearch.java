package graph;

import fundamentals.Stack;

/**
 * Depth-first search (DFS) - to search a graph, invoke a recursive method that visits
 * vertices. To visit a vertex:
 * - mark it as having been visited
 * - visit (recursively) all the vertices that are adjacent to it and that have not yet
 * been marked
 * <p>
 * Applications:
 * 1) connected components
 * 2) cycle detection
 * 3) two-colorability
 */
public class DepthFirstSearch {
    private boolean[] marked;   // has dfs() been called for this vertex
    private int[] edgeTo;       // last vertex on known path to this vertex (parent)
    private final int s;        // source

    // DFS
    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    // DFS visit of adjacent list of vertex v, w is each node on its adjacent list
    private void dfs(Graph G, int v) {
        // mark it as having been visited
        marked[v] = true;
        // visit (recursively) all the vertices w that are adjacent to it (on adjacent list) and have not yet been marked
        for (int w : G.adj(v))
            if (!marked[w]) {
                // parent of w is v
                edgeTo[w] = v;
                dfs(G, w);
            }
    }

    // is there a path from s to v
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    // path from s to v; null if no such path
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

}

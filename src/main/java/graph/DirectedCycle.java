package graph;

import fundamentals.Stack;

public class DirectedCycle {

    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;   // vertices on a cycle (if one exists)
    private boolean[] onStack;  // vertices on recursive call stack

    // cycle-finding constructor
    public DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v])
                dfs(G, v);
        }
    }

    /**
     * When executing dfs(G, v),we have followed a directed path from the source to v.
     * To keep track of this path, we maintain a vertex-indexed array onStack[] that marks the vertices on the recursive call stack (by setting onStack[v] to true on entry to dfs(G, v) and to false on exit).
     * We also maintain an edgeTo[] array so that it can return the cycle when it is detected.
     *
     */
    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        // find all w where v -> w
        for (int w : G.adj(v)) {
            // short circuit when a cycle is discovered
            if (this. hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G,w);
            }
            // When it finds an edge v->w to a vertex w that is on the stack, it has discovered a directed cycle, which it can recover by following edgeTo[] links.
            else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }


    // does G have a directed cycle?
    public boolean hasCycle() {
        return cycle != null;
    }

    // vertices on a cycle (if on exists)
    public Iterable<Integer> cycle() {
        return cycle;
    }

    // is v reachable
    public boolean marked(int v) {
        return marked[v];
    }
}

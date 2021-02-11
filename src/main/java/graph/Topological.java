package graph;

import fundamentals.Stack;

/**
 * Topological sort: given a digraph, put the vertices in order such that all its directed edges point from a vertex earlier in the order to a vertex later in the order (or report that doing so is not possible).
 * <p>
 * A reverse postorder in a DAG provides a topological order.
 */
public class Topological {
    private boolean[] marked;
    private Stack<Integer> reversePost;

    // topological-sorting constructor
    public Topological(Digraph G) {
        DirectedCycle cyclefinder = new DirectedCycle(G);
        if (!cyclefinder.hasCycle()) {
           reversePost = new Stack<Integer>();
           marked = new boolean[G.V()];

           for (int v = 0; v < G.V(); v++)
               if (!marked[v])
                   dfs(G,v);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }
        reversePost.push(v);
    }

    // vertices in topological order
    public Iterable<Integer> order() {
        return reversePost;
    }

}

package graph;

public class CC {
    private boolean[] marked;   // marked[v] = has vertex v been marked?
    // a vertex-indexed array id[] that associates the same int value to every vertex in each component
    private int[] id;   // id[v] = id of connected component containing v
    private int count;  // number of connected components

    // preprocessing constructor
    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    // are v and w connected?
    boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    // number of connected components
    public int id(int v) {
        return id[v];
    }

    // component identifier for v (between 0 and count()-1)
    public int count() {
        return count;
    }

}

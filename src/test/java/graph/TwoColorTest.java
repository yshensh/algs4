package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;

public class TwoColorTest {
    @Test
    void TwoColorTest() {
        In in = new In("src/test/resources/tinyG.txt");
        Graph G = new Graph(in);
        TwoColor c = new TwoColor(G);
        if (c.isBipartite()) {
            StdOut.println("Graph is bipartite");
        } else {
            StdOut.println("Graph is NOT bipartite");
        }
    }
}

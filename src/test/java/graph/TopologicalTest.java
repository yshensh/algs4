package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;


public class TopologicalTest {
    @Test
    void topologicalTest() {
        In in = new In("src/test/resources/tinyDAG.txt");
        Digraph G = new Digraph(in);
        Topological top = new Topological(G);
        for (int v : top.order()) {
            StdOut.println(v);
        }
    }
}

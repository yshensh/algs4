package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;


public class GraphTest {
    @Test
    void graphTest() {
        In in = new In("src/test/resources/tinyG.txt");
        Graph G = new Graph(in);
        StdOut.println(G.toString());
    }
}

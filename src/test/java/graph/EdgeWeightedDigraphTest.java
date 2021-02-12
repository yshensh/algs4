package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;


public class EdgeWeightedDigraphTest {
    @Test
    void edgeWeightedDigraphTest() {
        In in = new In("src/test/resources/tinyEWD.txt");
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        StdOut.println(G.toString());
    }
}

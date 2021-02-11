package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;


public class DigraphTest {
    @Test
    void digraphTest() {
        In in = new In("src/test/resources/tinyDG.txt");
        Digraph G = new Digraph(in);
        StdOut.println(G.toString());
    }
}

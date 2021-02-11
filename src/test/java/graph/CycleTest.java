package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import fundamentals.Queue;
import org.junit.jupiter.api.Test;

public class CycleTest {
    @Test
    void CycleTest() {
        In in = new In("src/test/resources/tinyG.txt");
        Graph G = new Graph(in);
        Cycle finder = new Cycle(G);
        if (finder.hasCycle()) {
            StdOut.println("Graph is cyclic");
        } else {
            StdOut.println("Graph is acyclic");
        }
    }
}
